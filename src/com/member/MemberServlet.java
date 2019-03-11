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
			
			//dto�� null�̸� ���̵� Ʋ����(id����� ����Ŭ���� return���� null)
			//�н����� ���ؼ� Ʋ���� �н����尡 Ʋ����
			if(dto==null||!dto.getUserPwd().equals(userPwd)){

				req.setAttribute("message", "false");
				
				url="/project/member/login.jsp";
				forward(req, resp, url);
				
				return;//�α����� �����ϸ� return(�ڿ� �ڵ� �������� �ʴ´�)
			}
			
			//�α����� �������� ���
			//���ǿ� ���� ���̵�,�̸��� ��´�.
/*			CustomInfo info=new CustomInfo();
			
			info.setUserId(dto.getUserId());
			info.setUserPwd(dto.getUserPwd());
			info.setUserName(dto.getUserName());
			info.setBirth(dto.getBirth());
			info.setPhone(dto.getPhone());
			info.setPoint(dto.getPoint());
			info.setUserGrade(dto.getUserGrade());
			info.setGender(dto.getGender());*/
			
			//session�� out.printó�� jsp���� �׳� ��밡���ѵ�
			//java������ ��û�� �ѵ� ����Ѵ�.
			HttpSession session=req.getSession();
			
			//CustomInfo�Ⱦ��� MemberDTO ���!
			session.setAttribute("customInfo", dto);
			
			url=cp+"/project/main.jsp";
			resp.sendRedirect(url);				
		}else if(uri.indexOf("mypage.do")!=-1){
			url="/project/member/mypage_content.jsp";
			forward(req,resp,url);
		}else if(uri.indexOf("logout.do")!=-1){
		
			HttpSession session=req.getSession();
			
			session.removeAttribute("customInfo");
			session.invalidate();//������ ����
			
			url=cp+"/project/main.jsp";
			resp.sendRedirect(url);
		}else if(uri.indexOf("searchid.do")!=-1){//���̵� ã��
			
			url="/project/member/searchid.jsp";
			forward(req, resp, url);
		}else if(uri.indexOf("searchid_ok.do")!=-1){//���̵� ã��_jsp
			
			String userName=req.getParameter("userName");
			String phone=req.getParameter("phone");
			String gender=req.getParameter("gender");
			String birth=req.getParameter("birth");
			
			MemberDTO dto=dao.getReadName(userName);
			
			if(dto==null||!dto.getPhone().equals(phone)||!dto.getBirth().equals(birth)
					||!dto.getGender().equals(gender)){
				req.setAttribute("message", "ȸ�������� �������� �ʽ��ϴ�.");
				
				url="/project/member/searchid.jsp";
				forward(req, resp, url);
				
				return;
			}
			
			MemberDTO info=new MemberDTO();
			
			info.setUserId(dto.getUserId());
			info.setUserName(dto.getUserName());
			
			HttpSession session=req.getSession();
			session.setAttribute("customInfo", info);
			
			req.setAttribute("userId", dto.getUserId());
			
			url="/project/member/searchid_com.jsp";
			forward(req, resp, url);
			
		}else if(uri.indexOf("searchid.do")!=-1){//���̵� ã��
			
			url="/project/member/searchid.jsp";
			forward(req, resp, url);
		}else if(uri.indexOf("searchid_ok.do")!=-1){//���̵� ã��_jsp
			
			String userName=req.getParameter("userName");
			String phone=req.getParameter("phone");
			String gender=req.getParameter("gender");
			String birth=req.getParameter("birth");
			
			MemberDTO dto=dao.getReadName(userName);
			
			if(dto==null||!dto.getPhone().equals(phone)||!dto.getBirth().equals(birth)
					||!dto.getGender().equals(gender)){
				req.setAttribute("message", "ȸ�������� �������� �ʽ��ϴ�.");
				
				url="/project/member/searchid.jsp";
				forward(req, resp, url);
				
				return;
			}
			
			MemberDTO info=new MemberDTO();
			
			info.setUserId(dto.getUserId());
			info.setUserName(dto.getUserName());
			
			HttpSession session=req.getSession();
			session.setAttribute("customInfo", info);
			
			req.setAttribute("userId", dto.getUserId());
			
			url="/project/member/searchid_com.jsp";
			forward(req, resp, url);
			
		}
		/*else if(uri.indexOf("updated.do")!=-1){
			
			HttpSession session=req.getSession();
			
			CustomInfo info=(CustomInfo)session.getAttribute("customInfo");
			
			MemberDTO dto=dao.getReadData(info.getUserId());
			
			req.setAttribute("dto", dto);//updated.jsp���� ���� ����ֱ����� �޾��ش�.
			
			//ȸ�������� ������
			if(dto==null){
				url=cp;
				resp.sendRedirect(url);
			}
			
			url="/member/updated.jsp";
			forward(req, resp, url);
			
		}else if(uri.indexOf("updated_ok.do")!=-1){
			
			MemberDTO dto=new MemberDTO();
			
			//�ٽ� ���ǰ��� �޾ƿͼ�
			HttpSession session=req.getSession();
			
			CustomInfo info=new CustomInfo();
			
			info=(CustomInfo)session.getAttribute("customInfo");
		
			//���ο� dto�� form���� �Ѿ�� userPwd���� �����
			//���ǿ� ������ִ� ���̵��� updateData sql�� ���� ��������ش�.
			dto.setUserPwd(req.getParameter("userPwd"));
			dto.setUserBirth(req.getParameter("userBirth"));
			dto.setUserTel(req.getParameter("userTel"));
			dto.setUserId(info.getUserId());
			
			dao.updateData(dto);
			
			//�ٽ� �ε��� ȭ������
			url=cp;
			resp.sendRedirect(url);
			
		}*/
		
		
	}

	
}
