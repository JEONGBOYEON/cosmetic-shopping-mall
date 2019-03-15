package com.member;

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

import com.coupon.CouponDAO;
import com.coupon.MyCouponDTO;
import com.util.DBCPConn;

public class MemberServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	
	protected void forward(HttpServletRequest req, HttpServletResponse resp,String url)
			throws ServletException, IOException {
		RequestDispatcher rd=req.getRequestDispatcher(url);
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		Connection conn=DBCPConn.getConnection();
		MemberDAO dao=new MemberDAO(conn);
		
		String cp=req.getContextPath();
		String uri=req.getRequestURI();
		
		String url;
		

		CouponDAO cdao = new CouponDAO(conn);
		
		
		if(uri.indexOf("created.do")!=-1){
			url="/project/member/created.jsp";
			forward(req,resp,url);
		}else if(uri.indexOf("created_ok.do")!=-1){
			
			MemberDTO dto=new MemberDTO();
			
			dto.setUserId(req.getParameter("userId"));
			dto.setUserPwd(req.getParameter("userPwd"));
			dto.setUserName(req.getParameter("userName"));
			dto.setBirth(req.getParameter("birth"));
			dto.setGender(req.getParameter("gender"));
			dto.setPhone(req.getParameter("phone"));
			
			dao.insertData(dto);
			
			url=cp+"/project/member/created_com.jsp";
			resp.sendRedirect(url);
		}else if(uri.indexOf("login.do")!=-1){
			url="/project/member/login.jsp";
			forward(req, resp, url);
		}else if(uri.indexOf("login_ok.do")!=-1){
			
			String userId=req.getParameter("userId");
			String userPwd=req.getParameter("userPwd");
			
			MemberDTO dto=dao.getReadData(userId);
			
			//dto가 null이면 아이디가 틀린것(id가없어서 오라클에서 return값이 null)
			//패스워드 비교해서 틀리면 패스워드가 틀린것
			if(dto==null||!dto.getUserPwd().equals(userPwd)){

				req.setAttribute("message", "false");
				
				url="/project/member/login.jsp";
				forward(req, resp, url);
				
				return;//로그인이 실패하면 return(뒤에 코드 실행하지 않는다)
			}
			
			//session도 out.print처럼 jsp에는 그냥 사용가능한데
			//java에서는 요청을 한뒤 써야한다.
			HttpSession session=req.getSession();
			
			//CustomInfo안쓰고 MemberDTO 사용!
			session.setAttribute("customInfo", dto);
			
			url=cp+"/product/main.do";
			resp.sendRedirect(url);				
		}else if(uri.indexOf("mypage.do")!=-1){

			HttpSession session = req.getSession();
			MemberDTO info = (MemberDTO) session.getAttribute("customInfo");
			
			int totalCouponCount = cdao.couponGetCount(info.getUserId());
			
			req.setAttribute("totalCouponCount", totalCouponCount);
			
			url="/project/member/mypage_content.jsp";
			forward(req,resp,url);
		}else if(uri.indexOf("logout.do")!=-1){
		
			HttpSession session=req.getSession();
			
			session.removeAttribute("customInfo");
			session.invalidate();//변수도 지움
			
			url=cp+"/product/main.do";
			resp.sendRedirect(url);
		}else if(uri.indexOf("searchid.do")!=-1){//아이디 찾기
			
			url="/project/member/searchid.jsp";
			forward(req, resp, url);
		}else if(uri.indexOf("searchid_ok.do")!=-1){//아이디 찾기_ok
			
			String userName=req.getParameter("userName");
			String phone=req.getParameter("phone");
			String gender=req.getParameter("gender");
			String birth=req.getParameter("birth");
			
			MemberDTO dto=dao.getReadName(userName);
			
			if(dto==null||!dto.getPhone().equals(phone)||!dto.getBirth().equals(birth)
					||!dto.getGender().equals(gender)){
				req.setAttribute("message", "회원정보가 존재하지 않습니다.");
				
				url="/project/member/searchid.jsp";
				forward(req, resp, url);
				
				return;
			}
			
			req.setAttribute("userId", dto.getUserId());
			
			url="/project/member/searchid_com.jsp";
			forward(req, resp, url);
			
		}else if(uri.indexOf("searchpw.do")!=-1){//비밀번호 찾기
			
			url="/project/member/searchpw.jsp";
			forward(req, resp, url);
		}else if(uri.indexOf("searchpw_ok.do")!=-1){//비밀번호 찾기_ok
			
			String userId=req.getParameter("userId");
			String userName=req.getParameter("userName");
			String phone=req.getParameter("phone");
			String gender=req.getParameter("gender");
			String birth=req.getParameter("birth");
			
			MemberDTO dto=dao.getReadData(userId);
			
			if(dto==null||!dto.getUserName().equals(userName)||!dto.getPhone().equals(phone)
					||!dto.getBirth().equals(birth)||!dto.getGender().equals(gender)){
				req.setAttribute("message", "회원정보가 존재하지 않습니다.");
				
				url="/project/member/searchpw.jsp";
				forward(req, resp, url);
				
				return;
			}

			req.setAttribute("userPwd", dto.getUserPwd());
			
			url="/project/member/searchpw_com.jsp";
			forward(req, resp, url);
			
		}else if(uri.indexOf("delete_ok.do")!=-1){

			//userPwd받아와서 회원탈퇴할때 비교
			String userPwd=req.getParameter("userPwd");
			
			HttpSession session=req.getSession();
			
			MemberDTO info=(MemberDTO)session.getAttribute("customInfo");
			
			MemberDTO dto=dao.getReadData(info.getUserId());
			
			if(!dto.getUserPwd().equals(userPwd)){
				req.setAttribute("message", "비밀번호가 다릅니다.");
				
				url="/member/mypage.do";
				forward(req, resp, url);
				return;
			}
			
			dao.deleteData(dto.getUserId());
			
			//세션도 지워줌
			session.removeAttribute("customInfo");
			session.invalidate();//변수도 지움
			
			//다시 인덱스 화면으로
			url=cp+"/project/member/delete_com.jsp";
			resp.sendRedirect(url);
			
		}else if(uri.indexOf("update.do")!=-1){//비밀번호 찾기
			
			url="/project/member/update.jsp";
			forward(req, resp, url);
			
		}else if(uri.indexOf("update_ok.do")!=-1){

			//userPwd받아와서 회원수정들어가는 페이지에서 비교
			String userPwd=req.getParameter("userPwd");
			
			HttpSession session=req.getSession();
			
			MemberDTO info=(MemberDTO)session.getAttribute("customInfo");
			
			MemberDTO dto=dao.getReadData(info.getUserId());
			
			if(!dto.getUserPwd().equals(userPwd)){
				req.setAttribute("message", "비밀번호가 다릅니다.");
				
				url="/member/update.do";
				forward(req, resp, url);
				return;
			}
			
			//다시 인덱스 화면으로
			url=cp+"/member/update_detail.do";
			resp.sendRedirect(url);
			
		}else if(uri.indexOf("update_detail.do")!=-1){
			
			HttpSession session=req.getSession();
			
			MemberDTO info=(MemberDTO)session.getAttribute("customInfo");
			
			MemberDTO dto=dao.getReadData(info.getUserId());
			
			req.setAttribute("dto", dto);
			
			url="/project/member/update_detail.jsp";
			forward(req, resp, url);
			
		}else if(uri.indexOf("update_detail_ok.do")!=-1){

			//비밀번호 수정
			
			//userPwd받아와서 회원수정할때 비교
			String oriPassword=req.getParameter("oriPassword");
			String pass1=req.getParameter("pass1");
			
			HttpSession session=req.getSession();
			
			MemberDTO info=(MemberDTO)session.getAttribute("customInfo");
			
			MemberDTO dto=dao.getReadData(info.getUserId());
			
			//!!session에 담은 메세지는 msg!!!!
			if(!dto.getUserPwd().equals(oriPassword)){
				session.setAttribute("msg", "기존 비밀번호가 다릅니다.");
				
				url=cp+"/member/update_detail.do";
				resp.sendRedirect(url);
				return;
			}
		
			//새로운 dto에 form으로 넘어온 userPwd등의 값들과
			//세션에 저장되있는 아이디값을 updateData sql을 통해 실행시켜준다.
			dto.setUserPwd(req.getParameter("pass1"));
			dto.setUserId(info.getUserId());
			
			dao.updateData(dto);
				
			req.setAttribute("msg", "회원 비밀번호정보가 수정되었습니다.");
			
			//다시 인덱스 화면으로
			url="/member/update_detail.do";
			forward(req, resp, url);
			
		}else if(uri.indexOf("update_detail_ok2.do")!=-1){
			
			//개인정보 수정
			
			//userPwd받아와서 회원수정할때 비교
			String birth=req.getParameter("birth");
			String phone=req.getParameter("phone");
			
			HttpSession session=req.getSession();
			
			MemberDTO info=(MemberDTO)session.getAttribute("customInfo");
			
			MemberDTO dto=dao.getReadData(info.getUserId());
		
			//새로운 dto에 form으로 넘어온 userPwd등의 값들과
			//세션에 저장되있는 아이디값을 updateData sql을 통해 실행시켜준다.
			dto.setBirth(req.getParameter("birth"));
			dto.setPhone(req.getParameter("phone"));
			dto.setUserId(info.getUserId());
			
			dao.updateData2(dto);
				
			req.setAttribute("msg", "회원개인정보가 수정되었습니다.");
			
			//다시 인덱스 화면으로
			url="/member/update_detail.do";
			forward(req, resp, url);
			
		}
	
		
	}

	
}
