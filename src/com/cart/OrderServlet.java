package com.cart;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.coupon.CouponDAO;
import com.coupon.CouponDTO;
import com.coupon.MyCouponDTO;
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
		CouponDAO dao2 = new CouponDAO(conn);

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
			//멤버 포인트 정보 가져오기
			int memberPoint = dao.getMemberPoint(info.getUserId());
			
			//총 주문 합계 / 총 주문 개수
			int totalPrice=0;
			int totalAmount=0;
			Iterator<OrderListDTO> orderLists = orderList.iterator();
			while(orderLists.hasNext()){
				OrderListDTO dto = orderLists.next();
				totalPrice += dto.getPrice()*dto.getAmount();
				totalAmount += dto.getAmount();
			}
			
			//만표쿠폰 변경하기---------------------------------------------------------------------------
			List<MyCouponDTO> lists = dao2.couponGetLists(info.getUserId());
			


			//날짜비교
			SimpleDateFormat dateFormat = new  SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault());
			long now = System.currentTimeMillis();
			Date date = new Date(now);
			//현재날짜
		    String strDate = dateFormat.format(date);
		    Date date1 = null;
		    
		    //날짜확인해서 만기인지 아닌지 넣어주기(만기이면 used에 'M'넣기)
		    //date1이 만기날짜
		    //date2가 현재날짜
		    //만기날짜가 현재날짜보다 이후이면 true = 아직 만기가 안됨
			Iterator<MyCouponDTO> it = lists.iterator();
			
	        while (it.hasNext()){

	        	MyCouponDTO dto = it.next();

		        try {
					date1 = dateFormat.parse(dto.getCouponEndDate());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
		        Date date2 = null;
				try {
					date2 = dateFormat.parse(strDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        boolean re = date1.after(date2);
		        
		        if(re!=true){
		        	dao2.couponInsertM(dto.getCouponKey(),info.getUserId());
		        }
	        }

			//사용 가능한 쿠폰 정보 가져오기
			List<CouponDTO> couponList = dao.getUserCoupon(info.getUserId(), totalPrice);
			
			req.setAttribute("destDto", destDto);
			req.setAttribute("orderList", orderList);
			req.setAttribute("totalOrderCount", totalOrderCount);
			req.setAttribute("imagePath", imagePath);
			req.setAttribute("totalPrice", totalPrice);
			req.setAttribute("totalAmount", totalAmount);
			req.setAttribute("memberPoint", memberPoint);
			req.setAttribute("couponList", couponList);
			
			url = "/project/order/reception.jsp";
			forward(req, resp, url);

		}
		else if(uri.indexOf("complete.do") != -1){
			
			HttpSession session = req.getSession();
			MemberDTO info = (MemberDTO) session.getAttribute("customInfo");
			
			String imagePath = cp + "/pds/imageFile";
			String purchaserEmailAddress = req.getParameter("purchaserEmailAddress");
			String totalOrderPrice = req.getParameter("totalOrderPrice");
			String totalOrderPoint = req.getParameter("totalOrderPoint");
			
			int useCouponKey = Integer.parseInt(req.getParameter("useCouponKey"));

			
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
				orderDate = dto.getOrderDate();
			}
			
			Iterator<OrderDTO> orderCompleteLists2 = orderCompleteList.iterator();
			while(orderCompleteLists2.hasNext()){
				OrderDTO dto = orderCompleteLists2.next();
				dao.insertReview(dto);
			}
			//사용한 쿠폰 변경 : N->Y
			dao.useCouponUpdate(useCouponKey, info.getUserId());

			req.setAttribute("userName", info.getUserName());
			req.setAttribute("orderDto", orderDto);
			req.setAttribute("totalOrderPrice", totalOrderPrice);
			req.setAttribute("totalAmount", totalAmount);
			req.setAttribute("orderCompleteList", orderCompleteList);
			req.setAttribute("orderDate", orderDate);
			
			url = "/project/order/orderComplete.jsp";
			forward(req, resp, url);
			
		}
		else if(uri.indexOf("myOrderLists.do") != -1){
			
			HttpSession session = req.getSession();
			MemberDTO info = (MemberDTO) session.getAttribute("customInfo");
			
			String imagePath = cp + "/pds/imageFile";
				
			//사용자 주문리스트 기간별 가져오기
			List<OrderDTO> userOrder7day = dao.getUserOrder7dayLists(info.getUserId());
			Iterator<OrderDTO> userOrder7dayit = userOrder7day.iterator();
			while(userOrder7dayit.hasNext()){
				OrderDTO dto = userOrder7dayit.next();
				dto.setProductName2(URLDecoder.decode(dto.getProductName(),"UTF-8"));
			}
			
			Iterator<OrderDTO> userOrder7dayit2 = userOrder7day.iterator();	
			while(userOrder7dayit2.hasNext()){
				OrderDTO dto = userOrder7dayit2.next();
				dto.setProductName(URLEncoder.encode(dto.getProductName(),"UTF-8")); 
			}
			
			List<OrderDTO> userOrder1month = dao.getUserOrder1monthLists(info.getUserId());
			Iterator<OrderDTO> userOrder1monthit = userOrder1month.iterator();
			while(userOrder1monthit.hasNext()){
				OrderDTO dto = userOrder1monthit.next();
				dto.setProductName2(URLDecoder.decode(dto.getProductName(),"UTF-8"));
			}
			
			Iterator<OrderDTO> userOrder1monthit2 = userOrder1month.iterator();	
			while(userOrder1monthit2.hasNext()){
				OrderDTO dto = userOrder1monthit2.next();
				dto.setProductName(URLEncoder.encode(dto.getProductName(),"UTF-8")); 
			}
			
			List<OrderDTO> userOrder3month = dao.getUserOrder3monthLists(info.getUserId());
			Iterator<OrderDTO> userOrder3monthit = userOrder3month.iterator();
			while(userOrder3monthit.hasNext()){
				OrderDTO dto = userOrder3monthit.next();
				dto.setProductName2(URLDecoder.decode(dto.getProductName(),"UTF-8"));
			}
			
			Iterator<OrderDTO> userOrder3monthit2 = userOrder3month.iterator();	
			while(userOrder3monthit2.hasNext()){
				OrderDTO dto = userOrder3monthit2.next();
				dto.setProductName(URLEncoder.encode(dto.getProductName(),"UTF-8")); 
			}
			
			List<OrderDTO> userOrder6month = dao.getUserOrder6monthLists(info.getUserId());
			Iterator<OrderDTO> userOrder6monthit = userOrder6month.iterator();
			while(userOrder6monthit.hasNext()){
				OrderDTO dto = userOrder6monthit.next();
				dto.setProductName2(URLDecoder.decode(dto.getProductName(),"UTF-8"));
			}
			
			Iterator<OrderDTO> userOrder6monthit2 = userOrder6month.iterator();	
			while(userOrder6monthit2.hasNext()){
				OrderDTO dto = userOrder6monthit2.next();
				dto.setProductName(URLEncoder.encode(dto.getProductName(),"UTF-8")); 
			}
			
			List<OrderDTO> userOrder1year = dao.getUserOrder1yearLists(info.getUserId());
			Iterator<OrderDTO> userOrder1yearit = userOrder1year.iterator();
			while(userOrder1yearit.hasNext()){
				OrderDTO dto = userOrder1yearit.next();
				dto.setProductName2(URLDecoder.decode(dto.getProductName(),"UTF-8"));
			}
			
			Iterator<OrderDTO> userOrder1yearit2 = userOrder1year.iterator();	
			while(userOrder1yearit2.hasNext()){
				OrderDTO dto = userOrder1yearit2.next();
				dto.setProductName(URLEncoder.encode(dto.getProductName(),"UTF-8")); 
			}
			
			req.setAttribute("userOrder7day", userOrder7day);
			req.setAttribute("userOrder1month", userOrder1month);
			req.setAttribute("userOrder3month", userOrder3month);
			req.setAttribute("userOrder6month", userOrder6month);
			req.setAttribute("userOrder1year", userOrder1year);
			req.setAttribute("imagePath", imagePath);
			
			url = "/project/order/myOrderList.jsp";
			forward(req, resp, url);
			
		}
	
	
	}

}
