package com.coupon;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.member.MemberDTO;
import com.util.DBCPConn;

public class CouponServlet extends HttpServlet{


	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	protected void forward(HttpServletRequest req, HttpServletResponse resp,String url)
			throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		

		
		Connection conn = DBCPConn.getConnection();
		CouponDAO dao = new CouponDAO(conn);
		
		String cp = req.getContextPath();  // -> ~/study
		String uri = req.getRequestURI(); // -> /bbs/created.do
		
		String url;
		
		
		if(uri.indexOf("created.do")!=-1){
			
			url = "/project/adminCouponCreate.jsp";
			forward(req, resp, url);
			
			
		}else if(uri.indexOf("created_ok.do")!=-1){
			
			CouponDTO dto = new CouponDTO();
			
			int maxNum = dao.getMaxNum();
			
			dto.setCouponKey(maxNum+1);
			dto.setCouponName(req.getParameter("couponName"));
			dto.setDiscount(Integer.parseInt(req.getParameter("discount")));
			dto.setCouponGrade(req.getParameter("couponGrade"));
			dto.setCouponScore(Integer.parseInt(req.getParameter("couponScore")));
			
			//���絥���Ϳ��� �Է��� ����Ⱓ��ŭ ���ؼ� �÷��ֱ�
			int period = Integer.parseInt(req.getParameter("period"));
			String couponEndDate= dao.testGetDate(period);
			dto.setCouponEndDate(couponEndDate);
			
			dao.insertData(dto);
			
			url = cp + "/coupon/adminList.do";
			resp.sendRedirect(url);
			
		}else if(uri.indexOf("adminList.do")!=-1){

			List<CouponDTO> lists = dao.getLists();

			String deleteUrl = cp + "/coupon/adminDeleted_ok.do";
			
			String updateUrl = cp + "/coupon/adminUpdate.do";
			
			req.setAttribute("lists", lists);
			req.setAttribute("updateUrl", updateUrl);
			req.setAttribute("deleteUrl", deleteUrl);
			
			
			url = "/project/adminCouponList.jsp";
			forward(req, resp, url);
			
		}else if(uri.indexOf("adminDeleted_ok.do")!=-1){


			int couponKey = Integer.parseInt(req.getParameter("couponKey"));

			dao.deleteData(couponKey);

			url = cp + "/coupon/adminList.do";
			resp.sendRedirect(url);
			
			
		}else if(uri.indexOf("couponAllList.do")!=-1){

			List<CouponDTO> lists = dao.getLists();

			
			req.setAttribute("lists", lists);
			
			url = "/project/couponAllList.jsp";
			forward(req, resp, url);
			
			
		}else if(uri.indexOf("couponMyList.do")!=-1){

			List<CouponDTO> lists = dao.getLists();

			
			req.setAttribute("lists", lists);
			
			url = "/project/couponMyList.jsp";
			forward(req, resp, url);
			
			
		}else if(uri.indexOf("couponIssue_ok.do")!=-1){
			
			
			resp.setCharacterEncoding("EUC-KR");

			PrintWriter writer = resp.getWriter();
	

			int couponKey = Integer.parseInt(req.getParameter("couponKey"));
			String userId = req.getParameter("userId");
			String userGrade = req.getParameter("userGrade");
			String couponGrade = req.getParameter("couponGrade");
			
			

			//��ް˻�
			if(!couponGrade.equals(userGrade)){
				writer.println("<script type='text/javascript'>");
				writer.println("alert('����� �ٸ��ϴ�.');");
				writer.println("history.back();");
				writer.println("</script>");
				writer.flush();

				return;
			}

			
			
			//�̸����������� �˻�
			List<IssueDTO> lists = dao.couponGetLists();
			Iterator<IssueDTO> it = lists.iterator();
			
	        while (it.hasNext()){

				IssueDTO dto = it.next();
				

				if(dto.getUserId().equals(userId)&&dto.getCouponKey()==couponKey){
					writer.println("<script type='text/javascript'>");
					writer.println("alert('�̹� �߱޹��� �����Դϴ�');");
					writer.println("history.back();");
					writer.println("</script>");
					writer.flush();
					
					return;
				}
	        }
	        
	        
			IssueDTO dto = new IssueDTO();
			dto.setCouponKey(Integer.parseInt(req.getParameter("couponKey")));
			dto.setUserId(req.getParameter("userId"));

			
			int result;
			result = dao.couponInsertData(dto);
			
			if(result!=0){
				writer.println("<script type='text/javascript'>");
				writer.println("alert('�����߱��� �Ϸ�Ǿ����ϴ�.');");
				writer.println("history.back();");
				writer.println("</script>");
				writer.flush();
				
				return;
			}
			
			
			
		}else if(uri.indexOf("myCouponList.do")!=-1){
			
			HttpSession session = req.getSession();
			MemberDTO info = (MemberDTO) session.getAttribute("customInfo");
			
			List<MyCouponDTO> lists = dao.couponGetLists(info.getUserId());
			
			req.setAttribute("lists", lists);


			//��¥��
			SimpleDateFormat dateFormat = new  SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault());
			long now = System.currentTimeMillis();
			Date date = new Date(now);
			//���糯¥
		    String strDate = dateFormat.format(date);
		    Date date1 = null;
		    
		    //��¥Ȯ���ؼ� �������� �ƴ��� �־��ֱ�(�����̸� used�� 'M'�ֱ�)
		    //date1�� ���⳯¥
		    //date2�� ���糯¥
		    //���⳯¥�� ���糯¥���� �����̸� true = ���� ���Ⱑ �ȵ�
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
		        	dto.setUsed("M");
		        }
	        }
			
			
			url = "/project/myCouponList.jsp";	
			forward(req, resp, url);
	        
			
		}else if(uri.indexOf("myUsedCouponList.do")!=-1){


			HttpSession session = req.getSession();
			MemberDTO info = (MemberDTO) session.getAttribute("customInfo");
			
			List<MyCouponDTO> lists = dao.couponGetLists(info.getUserId());
			
			//��¥��
			SimpleDateFormat dateFormat = new  SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault());
			long now = System.currentTimeMillis();
			Date date = new Date(now);
			//���糯¥
		    String strDate = dateFormat.format(date);
		    Date date1 = null;
		    
		    //��¥Ȯ���ؼ� �������� �ƴ��� �־��ֱ�(�����̸� used�� 'M'�ֱ�)
		    //date1�� ���⳯¥
		    //date2�� ���糯¥
		    //���⳯¥�� ���糯¥���� �����̸� true = ���� ���Ⱑ �ȵ�
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
		        	dto.setUsed("M");
		        }
	        }
	        
	        
			req.setAttribute("lists", lists);
			
			url = "/project/myUsedCouponList.jsp";	
			forward(req, resp, url);
			
		}
		
		
	}
	
}
