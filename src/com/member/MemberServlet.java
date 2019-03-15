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
			
			//dto�� null�̸� ���̵� Ʋ����(id����� ����Ŭ���� return���� null)
			//�н����� ���ؼ� Ʋ���� �н����尡 Ʋ����
			if(dto==null||!dto.getUserPwd().equals(userPwd)){

				req.setAttribute("message", "false");
				
				url="/project/member/login.jsp";
				forward(req, resp, url);
				
				return;//�α����� �����ϸ� return(�ڿ� �ڵ� �������� �ʴ´�)
			}
			
			//session�� out.printó�� jsp���� �׳� ��밡���ѵ�
			//java������ ��û�� �ѵ� ����Ѵ�.
			HttpSession session=req.getSession();
			
			//CustomInfo�Ⱦ��� MemberDTO ���!
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
			session.invalidate();//������ ����
			
			url=cp+"/product/main.do";
			resp.sendRedirect(url);
		}else if(uri.indexOf("searchid.do")!=-1){//���̵� ã��
			
			url="/project/member/searchid.jsp";
			forward(req, resp, url);
		}else if(uri.indexOf("searchid_ok.do")!=-1){//���̵� ã��_ok
			
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
			
			req.setAttribute("userId", dto.getUserId());
			
			url="/project/member/searchid_com.jsp";
			forward(req, resp, url);
			
		}else if(uri.indexOf("searchpw.do")!=-1){//��й�ȣ ã��
			
			url="/project/member/searchpw.jsp";
			forward(req, resp, url);
		}else if(uri.indexOf("searchpw_ok.do")!=-1){//��й�ȣ ã��_ok
			
			String userId=req.getParameter("userId");
			String userName=req.getParameter("userName");
			String phone=req.getParameter("phone");
			String gender=req.getParameter("gender");
			String birth=req.getParameter("birth");
			
			MemberDTO dto=dao.getReadData(userId);
			
			if(dto==null||!dto.getUserName().equals(userName)||!dto.getPhone().equals(phone)
					||!dto.getBirth().equals(birth)||!dto.getGender().equals(gender)){
				req.setAttribute("message", "ȸ�������� �������� �ʽ��ϴ�.");
				
				url="/project/member/searchpw.jsp";
				forward(req, resp, url);
				
				return;
			}

			req.setAttribute("userPwd", dto.getUserPwd());
			
			url="/project/member/searchpw_com.jsp";
			forward(req, resp, url);
			
		}else if(uri.indexOf("delete_ok.do")!=-1){

			//userPwd�޾ƿͼ� ȸ��Ż���Ҷ� ��
			String userPwd=req.getParameter("userPwd");
			
			HttpSession session=req.getSession();
			
			MemberDTO info=(MemberDTO)session.getAttribute("customInfo");
			
			MemberDTO dto=dao.getReadData(info.getUserId());
			
			if(!dto.getUserPwd().equals(userPwd)){
				req.setAttribute("message", "��й�ȣ�� �ٸ��ϴ�.");
				
				url="/member/mypage.do";
				forward(req, resp, url);
				return;
			}
			
			dao.deleteData(dto.getUserId());
			
			//���ǵ� ������
			session.removeAttribute("customInfo");
			session.invalidate();//������ ����
			
			//�ٽ� �ε��� ȭ������
			url=cp+"/project/member/delete_com.jsp";
			resp.sendRedirect(url);
			
		}else if(uri.indexOf("update.do")!=-1){//��й�ȣ ã��
			
			url="/project/member/update.jsp";
			forward(req, resp, url);
			
		}else if(uri.indexOf("update_ok.do")!=-1){

			//userPwd�޾ƿͼ� ȸ���������� ���������� ��
			String userPwd=req.getParameter("userPwd");
			
			HttpSession session=req.getSession();
			
			MemberDTO info=(MemberDTO)session.getAttribute("customInfo");
			
			MemberDTO dto=dao.getReadData(info.getUserId());
			
			if(!dto.getUserPwd().equals(userPwd)){
				req.setAttribute("message", "��й�ȣ�� �ٸ��ϴ�.");
				
				url="/member/update.do";
				forward(req, resp, url);
				return;
			}
			
			//�ٽ� �ε��� ȭ������
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

			//��й�ȣ ����
			
			//userPwd�޾ƿͼ� ȸ�������Ҷ� ��
			String oriPassword=req.getParameter("oriPassword");
			String pass1=req.getParameter("pass1");
			
			HttpSession session=req.getSession();
			
			MemberDTO info=(MemberDTO)session.getAttribute("customInfo");
			
			MemberDTO dto=dao.getReadData(info.getUserId());
			
			//!!session�� ���� �޼����� msg!!!!
			if(!dto.getUserPwd().equals(oriPassword)){
				session.setAttribute("msg", "���� ��й�ȣ�� �ٸ��ϴ�.");
				
				url=cp+"/member/update_detail.do";
				resp.sendRedirect(url);
				return;
			}
		
			//���ο� dto�� form���� �Ѿ�� userPwd���� �����
			//���ǿ� ������ִ� ���̵��� updateData sql�� ���� ��������ش�.
			dto.setUserPwd(req.getParameter("pass1"));
			dto.setUserId(info.getUserId());
			
			dao.updateData(dto);
				
			req.setAttribute("msg", "ȸ�� ��й�ȣ������ �����Ǿ����ϴ�.");
			
			//�ٽ� �ε��� ȭ������
			url="/member/update_detail.do";
			forward(req, resp, url);
			
		}else if(uri.indexOf("update_detail_ok2.do")!=-1){
			
			//�������� ����
			
			//userPwd�޾ƿͼ� ȸ�������Ҷ� ��
			String birth=req.getParameter("birth");
			String phone=req.getParameter("phone");
			
			HttpSession session=req.getSession();
			
			MemberDTO info=(MemberDTO)session.getAttribute("customInfo");
			
			MemberDTO dto=dao.getReadData(info.getUserId());
		
			//���ο� dto�� form���� �Ѿ�� userPwd���� �����
			//���ǿ� ������ִ� ���̵��� updateData sql�� ���� ��������ش�.
			dto.setBirth(req.getParameter("birth"));
			dto.setPhone(req.getParameter("phone"));
			dto.setUserId(info.getUserId());
			
			dao.updateData2(dto);
				
			req.setAttribute("msg", "ȸ������������ �����Ǿ����ϴ�.");
			
			//�ٽ� �ε��� ȭ������
			url="/member/update_detail.do";
			forward(req, resp, url);
			
		}
	
		
	}

	
}
