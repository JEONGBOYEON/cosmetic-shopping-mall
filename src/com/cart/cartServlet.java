package com.cart;

import java.io.File;
import java.io.IOException;
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

			// �̹������ϰ��
			String imagePath = cp + "/pds/imageFile";
			
			//����
			String deleteUrl = cp + "/cart/cartDelete_ok.do?productId=";
			
			req.setAttribute("imagePath", imagePath);
			req.setAttribute("lists", lists);
			req.setAttribute("deleteUrl", deleteUrl);
			req.setAttribute("totalItemCount", totalItemCount);
			req.setAttribute("totalItemPrice", totalItemPrice);
			
			//project/cart/cartList.jsp �������� ������
			url = "/project/order/cartList.jsp";
			forward(req, resp, url);
			
		}else if (uri.indexOf("cartAdd_ok.do") != -1){
			
			cartDTO dto = new cartDTO();
			HttpSession session = req.getSession();
			MemberDTO info = (MemberDTO) session.getAttribute("customInfo");

			String productId = req.getParameter("productId");
			String productName = req.getParameter("productName");
			String productOption = req.getParameter("productOption");
			String userId = info.getUserId();
			int amount = Integer.parseInt(req.getParameter("amount"));
			dto.setUserId(userId);
			
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
		}
		
	}
	

}
