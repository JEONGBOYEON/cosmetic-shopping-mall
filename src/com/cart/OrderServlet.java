package com.cart;

import java.io.IOException;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.destination.DestinationDTO;
import com.member.MemberDTO;
import com.util.DBCPConn;

public class OrderServlet extends HttpServlet {
	
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
		OrderDAO dao = new OrderDAO(conn);

		String cp = req.getContextPath();
		String uri = req.getRequestURI();
		String url;
		
		if(uri.indexOf("orderList.do") != -1){
			
			HttpSession session = req.getSession();
			MemberDTO info = (MemberDTO) session.getAttribute("customInfo");
			
			String imagePath = cp + "/pds/imageFile";
			
			//배송지 정보
			DestinationDTO destDto = dao.getOrderDest(info.getUserId());
			//주문 리스트
			List<OrderListDTO> orderList = dao.getOrderList(info.getUserId());
			//주문 개수
			int totalOrderCount = dao.getOrderCount(info.getUserId());
			//
			
			//총 주문 합계 / 총 주문 개수
			int totalPrice=0;
			int totalAmount=0;
			Iterator<OrderListDTO> orderLists = orderList.iterator();
			while(orderLists.hasNext()){
				OrderListDTO dto = orderLists.next();
				totalPrice += dto.getPrice()*dto.getAmount();
				totalAmount += dto.getAmount();
			}
			
			req.setAttribute("destDto", destDto);
			req.setAttribute("orderList", orderList);
			req.setAttribute("totalOrderCount", totalOrderCount);
			req.setAttribute("imagePath", imagePath);
			req.setAttribute("totalPrice", totalPrice);
			req.setAttribute("totalAmount", totalAmount);
			
			url = "/project/order/reception.jsp";
			forward(req, resp, url);

		}
		else if(uri.indexOf("complete.do") != -1){
			
			HttpSession session = req.getSession();
			MemberDTO info = (MemberDTO) session.getAttribute("customInfo");
			
			String imagePath = cp + "/pds/imageFile";
			String purchaserEmailAddress = req.getParameter("purchaserEmailAddress");
			
			//배송지 정보
			DestinationDTO destDto = dao.getOrderDest(info.getUserId());
			//주문 리스트
			List<OrderListDTO> orderList = dao.getOrderList(info.getUserId());
			//주문 개수
			int totalOrderCount = dao.getOrderCount(info.getUserId());
			int maxNum = dao.getMaxNum()+1;
			
			//총 주문 합계 / 총 주문 개수
			int totalPrice=0;
			int totalAmount=0;
			Iterator<OrderListDTO> orderLists = orderList.iterator();
			while(orderLists.hasNext()){
				OrderListDTO dto = orderLists.next();
				OrderDTO orderDto = new OrderDTO();
				
				orderDto.setOrderNum(maxNum);
				orderDto.setUserId(info.getUserId());
				orderDto.setProductId(dto.getProductId());
				orderDto.setAmount(dto.getAmount());
				orderDto.setPrice(dto.getPrice());
				orderDto.seteMail(purchaserEmailAddress);
				
				dao.insertOrderDataProduct(maxNum, orderDto);
				dao.deleteCartProduct(info.getUserId(), dto.getProductId());
				
				totalPrice += dto.getPrice()*dto.getAmount();
				totalAmount += dto.getAmount();
			}
			
			OrderDTO orderDto = new OrderDTO();
			orderDto.setZip(destDto.getZip());
			orderDto.setAddr1(destDto.getAddr1());
			orderDto.setAddr2(destDto.getAddr2());
			orderDto.setAddrKey(destDto.getAddrKey());
			
			dao.insertOrderDataAddr(maxNum, orderDto);
			dao.updateMemberPoint(info.getUserId(), (int)(totalPrice*0.01));
			
			
			//주문정보 가져오기
			//List<OrderDTO> orderCompleteLists = dao.getCompleteOrder(maxNum, info.getUserId());
			List<OrderDTO> orderCompleteList = dao.getCompleteOrder(maxNum, info.getUserId());
			
			//주문 날짜 가져오기 / 리뷰 등록 추가하기
			String orderDate ="";
			Iterator<OrderDTO> orderCompleteLists = orderCompleteList.iterator();
			if(orderCompleteLists.hasNext()){
				OrderDTO dto = orderCompleteLists.next();
				dao.insertReview(dto);
				orderDate = dto.getOrderDate();
			}
			
			req.setAttribute("userName", info.getUserName());
			req.setAttribute("orderDto", orderDto);
			req.setAttribute("totalPrice", totalPrice);
			req.setAttribute("totalAmount", totalAmount);
			req.setAttribute("orderCompleteList", orderCompleteList);
			req.setAttribute("orderDate", orderDate);
			
			url = "/project/order/orderComplete.jsp";
			forward(req, resp, url);
			
		}
		else if(uri.indexOf("myOrderLists.do") != -1){
			
		}
	
	
	}

}
