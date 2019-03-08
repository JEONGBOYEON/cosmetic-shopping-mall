package com.member;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
			
			url=cp+"/project/main.jsp";
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
			
			//로그인이 성공했을 경우
			//세션에 현재 아이디,이름을 담는다.
/*			CustomInfo info=new CustomInfo();
			
			info.setUserId(dto.getUserId());
			info.setUserPwd(dto.getUserPwd());
			info.setUserName(dto.getUserName());
			info.setBirth(dto.getBirth());
			info.setPhone(dto.getPhone());
			info.setPoint(dto.getPoint());
			info.setUserGrade(dto.getUserGrade());
			info.setGender(dto.getGender());*/
			
			//session도 out.print처럼 jsp에는 그냥 사용가능한데
			//java에서는 요청을 한뒤 써야한다.
			HttpSession session=req.getSession();
			
			//CustomInfo안쓰고 MemberDTO 사용!
			session.setAttribute("customInfo", dto);
			
			url=cp+"/project/main.jsp";
			resp.sendRedirect(url);				
		}else if(uri.indexOf("mypage.do")!=-1){
			url="/project/member/mypage_content.jsp";
			forward(req,resp,url);
		}else if(uri.indexOf("logout.do")!=-1){
		
			HttpSession session=req.getSession();
			
			session.removeAttribute("customInfo");
			session.invalidate();//변수도 지움
			
			url=cp+"/project/main.jsp";
			resp.sendRedirect(url);
		}
		/*else if(uri.indexOf("searchpw.do")!=-1){//비밀번호 찾기
			
			url="/member/searchpw.jsp";
			forward(req, resp, url);
		}else if(uri.indexOf("searchpw_ok.do")!=-1){//비밀번호 찾기_jsp
			
			String userId=req.getParameter("userId");
			String userTel=req.getParameter("userTel");
			
			MemberDTO dto=dao.getReadData(userId);
			
			if(dto==null||!dto.getUserTel().equals(userTel)){
				req.setAttribute("message", "회원정보가 존재하지 않습니다.");
				
				url="/member/login.jsp";
				forward(req, resp, url);
				
				return;
			}
			
			CustomInfo info=new CustomInfo();
			
			info.setUserId(dto.getUserId());
			info.setUserName(dto.getUserName());
			
			HttpSession session=req.getSession();
			
			session.setAttribute("customInfo", info);
			
			req.setAttribute("message", "비밀번호는 ["+dto.getUserPwd()+"]입니다.");
			
			req.setAttribute("find", "비밀번호 찾기");
			url="/member/login.jsp";
			forward(req, resp, url);
					
		}else if(uri.indexOf("updated.do")!=-1){
			
			HttpSession session=req.getSession();
			
			CustomInfo info=(CustomInfo)session.getAttribute("customInfo");
			
			MemberDTO dto=dao.getReadData(info.getUserId());
			
			req.setAttribute("dto", dto);//updated.jsp에서 정보 띄워주기위해 받아준다.
			
			//회원정보가 없을때
			if(dto==null){
				url=cp;
				resp.sendRedirect(url);
			}
			
			url="/member/updated.jsp";
			forward(req, resp, url);
			
		}else if(uri.indexOf("updated_ok.do")!=-1){
			
			MemberDTO dto=new MemberDTO();
			
			//다시 세션값을 받아와서
			HttpSession session=req.getSession();
			
			CustomInfo info=new CustomInfo();
			
			info=(CustomInfo)session.getAttribute("customInfo");
		
			//새로운 dto에 form으로 넘어온 userPwd등의 값들과
			//세션에 저장되있는 아이디값을 updateData sql을 통해 실행시켜준다.
			dto.setUserPwd(req.getParameter("userPwd"));
			dto.setUserBirth(req.getParameter("userBirth"));
			dto.setUserTel(req.getParameter("userTel"));
			dto.setUserId(info.getUserId());
			
			dao.updateData(dto);
			
			//다시 인덱스 화면으로
			url=cp;
			resp.sendRedirect(url);
			
		}*/
		
		
	}

	
}
