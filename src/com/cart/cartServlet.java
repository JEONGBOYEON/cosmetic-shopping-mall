package com.cart;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.MemberDTO;
import com.util.DBCPConn;

public class cartServlet  extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void forward(HttpServletRequest req, HttpServletResponse resp,
			String url) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Connection conn = DBCPConn.getConnection();
		cartDAO dao = new cartDAO(conn);

		String cp = req.getContextPath();
		String uri = req.getRequestURI();
		String url;

		// ���� ���ε� ��ġ ����
		String root = getServletContext().getRealPath("/");
		String path = root + File.separator + "pds" + File.separator+ "imageFile";

		File f = new File(path);

		// ���� ������ ���丮 ����
		if (!f.exists()) {
			f.mkdirs();
		}

		if (uri.indexOf("cartList.do") != -1) {
			
			HttpSession session = req.getSession();
			MemberDTO info = (MemberDTO) session.getAttribute("customInfo");
			
			String userId= info.getUserId();
			
			List<cartDTO> lists = dao.getReadData(userId);
			int totalItemCount = dao.getTotalItemCount(userId);
			int totalItemPrice = dao.getTotalItemPrice(userId);
			int totalItemCountYes = dao.getTotalItemCountYes(userId);
			// �̹������ϰ��
			String imagePath = cp + "/pds/imageFile";
			
			//����
			String deleteUrl = cp + "/cart/cartDelete_ok.do?productId=";
			String updateUrl = cp + "/cart/cartUpdated_ok.do?productId=";
			
			req.setAttribute("imagePath", imagePath);
			req.setAttribute("lists", lists);
			req.setAttribute("deleteUrl", deleteUrl);
			req.setAttribute("updateUrl", updateUrl);
			req.setAttribute("totalItemCount", totalItemCount);
			req.setAttribute("totalItemPrice", totalItemPrice);
			req.setAttribute("totalItemCountYes", totalItemCountYes);
			//project/cart/cartList.jsp �������� ������
			url = "/project/order/cartList.jsp";
			forward(req, resp, url);
			
		}else if (uri.indexOf("cartAdd_ok.do") != -1){
			
			//��ٱ����߰�
			cartDTO dto = new cartDTO();
			HttpSession session = req.getSession();
			MemberDTO info = (MemberDTO) session.getAttribute("customInfo");
			String userId ;
			String productId = req.getParameter("productId");
			String productName = req.getParameter("productName");
			String productOption = req.getParameter("productOption");
			
			if(info.getUserId()!=null){
				userId = info.getUserId();
			}else{
				userId = req.getParameter("userId");
			}
			
			String param = "";
			
			//��ǰ�� ��ǰ�ɼ� �ѱ۵��ڵ�
			if(productName!=null && !productName.equals(null)){
				productName = URLDecoder.decode(productName, "UTF-8");				
			}
			if(productOption!=null && !productOption.equals(null)){
				productOption = URLDecoder.decode(productOption, "UTF-8");				
			}
			
			//��ٱ��Ͽ� ���� ��ǰid�� �ֹ�����
			int amount = Integer.parseInt(req.getParameter("amount"));
			
			//�����id
			dto.setUserId(userId);
			
			//��ǰ�ɼ��� ���ϰ�/���߰��� ���
			if(productOption.equals("single")){
				//���ϻ�ǰ�� ��� ��ǰ���̵� �о��
				dto.setProductId(productId);
				
			}else{
				//��ǰ�ɼ� ����� ����� ��ǰid���� �о�;� ��
				productId = dao.searchProductId(productName,productOption);
				dto.setProductId(productId);
			}
			dto.setAmount(amount);
			dto.setPrice(Integer.parseInt(req.getParameter("price")));
			
			//���� userId,productId�� ��ٱ��� ������ ������ ��������
			if(dao.searchBeforeProductId(productId,userId)==1){
				int addAmount = dao.getCartItemCount(userId, productId);
				dto.setAmount(amount+addAmount);
				dao.updateCartItem(dto);
			}else{
				dao.insertCartItem(dto);
			}
			
			if(productName!=null){
				param += "?productName=" + URLEncoder.encode(productName,"UTF-8");
				param += "&productOption=" + URLEncoder.encode(productOption,"UTF-8");
			}
			
			url = "/cart/cartList.do";
			forward(req, resp, url);
			
		}else if (uri.indexOf("cartAddFromList_ok.do") != -1){
			
			//����Ʈ���������� ��ٱ����߰�
			cartDTO dto = new cartDTO();
			HttpSession session = req.getSession();
			MemberDTO info = (MemberDTO) session.getAttribute("customInfo");
			String userId = info.getUserId();
			String productId = req.getParameter("productId");
			String productName = req.getParameter("productName");
			String productOption = req.getParameter("productOption");
			
			String param = "";
			
			//��ǰ�ɼ� �ѱ۵��ڵ�
			if(productOption!=null && !productOption.equals(null)){
				productOption = URLDecoder.decode(productOption, "UTF-8");				
			}

			//��ǰ�� �ѱ۵��ڵ�
			if(productName!=null && !productName.equals(null)){
				productName = URLDecoder.decode(productName, "UTF-8");				
			}
			
			
			//��ٱ��Ͽ� ���� ��ǰid�� �ֹ�����
			int amount = Integer.parseInt(req.getParameter("amount"));
			
			//�����id
			dto.setUserId(userId);
			
			//����Ʈȭ�鿡�� �̰� �ȸ���
			//��ǰ�ɼ��� ���ϰ�/���߰��� ���
			if(productOption.equals("single")){
				//���ϻ�ǰ�� ��� ��ǰ���̵� �о��--�Ϸ�
				dto.setProductId(productId);
			}else{
				
				//��ǰ�ɼ� ����� ����� ��ǰid���� �о�;� ��--�̰Ծȵ�(0313-3��)
				productId = dao.searchProductId(productName,productOption);
				dto.setProductId(productId);
			}
			
			if(productName!=null){
				param += "?productName=" + URLEncoder.encode(productName,"UTF-8");
				param += "&productOption=" + URLEncoder.encode(productOption,"UTF-8");
			}
			
			dto.setAmount(amount);
			dto.setPrice(Integer.parseInt(req.getParameter("price")));
			
			//���� userId,productId�� ��ٱ��� ������ ������ ��������
			if(dao.searchBeforeProductId(productId,userId)==1){
				int addAmount = dao.getCartItemCount(userId, productId);
				dto.setAmount(amount+addAmount);
				dao.updateCartItem(dto);
			}else{
				dao.insertCartItem(dto);
			}
			
			url = "/cart/cartList.do";
			forward(req, resp, url);
			
		}else if (uri.indexOf("cartDelete_ok.do") != -1){
			
			HttpSession session = req.getSession();
			MemberDTO info = (MemberDTO) session.getAttribute("customInfo");

			String productId = req.getParameter("productId");
			String userId = info.getUserId();
			
			//��ٱ��� ����
			dao.deleteCartItem(productId, userId);
			
			url = cp + "/cart/cartList.do";
			resp.sendRedirect(url);
			
			
		}else if (uri.indexOf("cartUpdated_ok.do") != -1){
			
			cartDTO dto = new cartDTO();
			HttpSession session = req.getSession();
			MemberDTO info = (MemberDTO) session.getAttribute("customInfo");
			String productId = req.getParameter("productId");
			String userId = info.getUserId();
			
			dto.setProductId(productId);
			dto.setUserId(userId);
			dto.setPrice(Integer.parseInt(req.getParameter("price")));
			dto.setAmount(Integer.parseInt(req.getParameter("amendAmount")));
			
			//��ٱ��� ����
			//���� userId,productId�� ��ٱ��� ������ ������ ��������, ������ �߰����
			if(dao.searchBeforeProductId(productId,userId)==1){
				dao.updateCartItem(dto);
			}else{
				dao.insertCartItem(dto);
			}
			
			url = cp + "/cart/cartList.do";
			resp.sendRedirect(url);
			
			
		}else if (uri.indexOf("orderSelectToYes_ok.do")!=-1){

			//�ֹ����κ��� 
			HttpSession session = req.getSession();
			MemberDTO info = (MemberDTO) session.getAttribute("customInfo");
			
			String productId = req.getParameter("productId");
			String userId = info.getUserId();
			
			//OrderSelect no �� yes
			dao.changeOrderSelectYes(productId,userId);		
			
			url = cp + "/cart/cartList.do";
			resp.sendRedirect(url);
			
		}else if (uri.indexOf("orderSelectToNo_ok.do")!=-1){
			
			//�ֹ����κ���
			HttpSession session = req.getSession();
			MemberDTO info = (MemberDTO) session.getAttribute("customInfo");
			
			String productId = req.getParameter("productId");
			String userId = info.getUserId();
			
			//OrderSelect yes �� no
			dao.changeOrderSelectNo(productId,userId);
			
			url = cp + "/cart/cartList.do";
			resp.sendRedirect(url);
		}
		
		
	}
	

}
