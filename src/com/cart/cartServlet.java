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

		// 파일 업로드 위치 지정
		String root = getServletContext().getRealPath("/");
		String path = root + File.separator + "pds" + File.separator+ "imageFile";

		File f = new File(path);

		// 파일 없으면 디렉토리 생성
		if (!f.exists()) {
			f.mkdirs();
		}

		if (uri.indexOf("cartList.do") != -1) {
			
			//HttpSession session = req.getSession();
			//MemberDTO info = (MemberDTO) session.getAttribute("customInfo");
			//String userId= info.getUserId();
			//List<cartDTO> lists = dao.getReadData(userId);
			
			List<cartDTO> lists = dao.getReadData("asd123");

			// 이미지파일경로
			String imagePath = cp + "/pds/imageFile";
			
			//삭제
			String deleteUrl = cp + "/cart/cartDelete_ok.do?productId=";
			
			req.setAttribute("imagePath", imagePath);
			req.setAttribute("lists", lists);
			req.setAttribute("deleteUrl", deleteUrl);
			
			//project/cart/cartList.jsp 페이지로 포워드
			url = "/project/order/cartList.jsp";
			forward(req, resp, url);
			
		}else if (uri.indexOf("cartAdd_ok.do") != -1){
			
			cartDTO dto = new cartDTO();
			HttpSession session = req.getSession();
			MemberDTO info = (MemberDTO) session.getAttribute("customInfo");
			
			dto.setUserId("asd123");
			//dto.setUserId(info.getUserId());
			dto.setProductId(req.getParameter("productId"));
			dto.setAmount(Integer.parseInt(req.getParameter("amount")));
			dto.setPrice(Integer.parseInt(req.getParameter("price")));
			dao.insertCartItem(dto);
			
			url = "/cart/cartList.do";
			forward(req, resp, url);
			
			
		}else if (uri.indexOf("cartDelete_ok.do") != -1){
			
			HttpSession session = req.getSession();
			MemberDTO info = (MemberDTO) session.getAttribute("customInfo");
			
			String productId = req.getParameter("productId");
			String userId = "asd123" ;
			//String userId = info.getUserId();

			dao.deleteCartItem(productId, userId);
			
			url = cp + "/cart/cartList.do";
			resp.sendRedirect(url);
		}
		
	}
	

}
