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

		// 파일 업로드 위치 지정
		String root = getServletContext().getRealPath("/");
		String path = root + File.separator + "pds" + File.separator+ "imageFile";

		File f = new File(path);

		// 파일 없으면 디렉토리 생성
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
			// 이미지파일경로
			String imagePath = cp + "/pds/imageFile";
			
			//삭제
			String deleteUrl = cp + "/cart/cartDelete_ok.do?productId=";
			String updateUrl = cp + "/cart/cartUpdated_ok.do?productId=";
			
			req.setAttribute("imagePath", imagePath);
			req.setAttribute("lists", lists);
			req.setAttribute("deleteUrl", deleteUrl);
			req.setAttribute("updateUrl", updateUrl);
			req.setAttribute("totalItemCount", totalItemCount);
			req.setAttribute("totalItemPrice", totalItemPrice);
			req.setAttribute("totalItemCountYes", totalItemCountYes);
			//project/cart/cartList.jsp 페이지로 포워드
			url = "/project/order/cartList.jsp";
			forward(req, resp, url);
			
		}else if (uri.indexOf("cartAdd_ok.do") != -1){
			
			//장바구니추가
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
			
			//상품명 상품옵션 한글디코딩
			if(productName!=null && !productName.equals(null)){
				productName = URLDecoder.decode(productName, "UTF-8");				
			}
			if(productOption!=null && !productOption.equals(null)){
				productOption = URLDecoder.decode(productOption, "UTF-8");				
			}
			
			//장바구니에 담을 상품id의 주문갯수
			int amount = Integer.parseInt(req.getParameter("amount"));
			
			//사용자id
			dto.setUserId(userId);
			
			//상품옵션이 단일값/다중값인 경우
			if(productOption.equals("single")){
				//단일상품인 경우 상품아이디 읽어옴
				dto.setProductId(productId);
				
			}else{
				//상품옵션 변경시 변경된 상품id값을 읽어와야 함
				productId = dao.searchProductId(productName,productOption);
				dto.setProductId(productId);
			}
			dto.setAmount(amount);
			dto.setPrice(Integer.parseInt(req.getParameter("price")));
			
			//동일 userId,productId로 장바구니 내용이 있으면 수량증가
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
			
			//리스트페이지에서 장바구니추가
			cartDTO dto = new cartDTO();
			HttpSession session = req.getSession();
			MemberDTO info = (MemberDTO) session.getAttribute("customInfo");
			String userId = info.getUserId();
			String productId = req.getParameter("productId");
			String productName = req.getParameter("productName");
			String productOption = req.getParameter("productOption");
			
			String param = "";
			
			//상품옵션 한글디코딩
			if(productOption!=null && !productOption.equals(null)){
				productOption = URLDecoder.decode(productOption, "UTF-8");				
			}

			//상품명 한글디코딩
			if(productName!=null && !productName.equals(null)){
				productName = URLDecoder.decode(productName, "UTF-8");				
			}
			
			
			//장바구니에 담을 상품id의 주문갯수
			int amount = Integer.parseInt(req.getParameter("amount"));
			
			//사용자id
			dto.setUserId(userId);
			
			//리스트화면에서 이게 안먹음
			//상품옵션이 단일값/다중값인 경우
			if(productOption.equals("single")){
				//단일상품인 경우 상품아이디 읽어옴--완료
				dto.setProductId(productId);
			}else{
				
				//상품옵션 변경시 변경된 상품id값을 읽어와야 함--이게안됨(0313-3시)
				productId = dao.searchProductId(productName,productOption);
				dto.setProductId(productId);
			}
			
			if(productName!=null){
				param += "?productName=" + URLEncoder.encode(productName,"UTF-8");
				param += "&productOption=" + URLEncoder.encode(productOption,"UTF-8");
			}
			
			dto.setAmount(amount);
			dto.setPrice(Integer.parseInt(req.getParameter("price")));
			
			//동일 userId,productId로 장바구니 내용이 있으면 수량증가
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
			
			//장바구니 삭제
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
			
			//장바구니 수정
			//동일 userId,productId로 장바구니 내용이 있으면 수량수정, 없으면 추가등록
			if(dao.searchBeforeProductId(productId,userId)==1){
				dao.updateCartItem(dto);
			}else{
				dao.insertCartItem(dto);
			}
			
			url = cp + "/cart/cartList.do";
			resp.sendRedirect(url);
			
			
		}else if (uri.indexOf("orderSelectToYes_ok.do")!=-1){

			//주문여부변경 
			HttpSession session = req.getSession();
			MemberDTO info = (MemberDTO) session.getAttribute("customInfo");
			
			String productId = req.getParameter("productId");
			String userId = info.getUserId();
			
			//OrderSelect no → yes
			dao.changeOrderSelectYes(productId,userId);		
			
			url = cp + "/cart/cartList.do";
			resp.sendRedirect(url);
			
		}else if (uri.indexOf("orderSelectToNo_ok.do")!=-1){
			
			//주문여부변경
			HttpSession session = req.getSession();
			MemberDTO info = (MemberDTO) session.getAttribute("customInfo");
			
			String productId = req.getParameter("productId");
			String userId = info.getUserId();
			
			//OrderSelect yes → no
			dao.changeOrderSelectNo(productId,userId);
			
			url = cp + "/cart/cartList.do";
			resp.sendRedirect(url);
		}
		
		
	}
	

}
