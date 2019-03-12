package com.destination;

import java.io.IOException;
import java.net.URLDecoder;
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
import com.util.MyUtil;

public class DestinationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	protected void forward(HttpServletRequest req, HttpServletResponse resp, String url)
			throws ServletException, IOException {
		
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		Connection conn = DBCPConn.getConnection();
		DestinationDAO dao = new DestinationDAO(conn);
		MyUtil myUtil = new MyUtil();
		
		String cp = req.getContextPath();
		String uri = req.getRequestURI();
		
		String url;
		
		if(uri.indexOf("writed.do")!=-1){
			
			String destNickname = "NoDestNickname";
			HttpSession session = req.getSession();
			MemberDTO info = (MemberDTO) session.getAttribute("customInfo");
			
			if(destNickname!=null){
				destNickname = URLDecoder.decode(destNickname,"UTF-8");
			}
			

			int getDataCount = dao.getDataCount(info.getUserId());
			if(getDataCount==10){
				List<DestinationDTO> lists = dao.getList(info.getUserId());

				int totalDataCount = dao.getDataCount(info.getUserId());
		
				req.setAttribute("lists", lists);
				req.setAttribute("totalDataCount", totalDataCount);
				req.setAttribute("message","배송지는 최대 10개까지만 등록 가능합니다.");
				
				url = "/project/destinationList.jsp";
				forward(req, resp, url);			
			}
			
			int totalDataCount = dao.getDataCount(info.getUserId());
			DestinationDTO dto = dao.getReadData(info.getUserId(), destNickname);
			String destNicknameList[] = dao.selectDestNickname(info.getUserId(),totalDataCount,destNickname);
			
			req.setAttribute("dto", dto);
			req.setAttribute("destNicknameList", destNicknameList);
			req.setAttribute("totalDataCount", totalDataCount);
			
			url = "/project/destinationWrited.jsp";
			forward(req, resp, url);
		}
		else if(uri.indexOf("writed_ok.do")!=-1){
			
			DestinationDTO dto = new DestinationDTO();
			
			HttpSession session = req.getSession();
			MemberDTO info = (MemberDTO) session.getAttribute("customInfo");
			
			dto.setUserId(info.getUserId());			
			dto.setDestNickname(req.getParameter("destNickname"));
			dto.setDestName(req.getParameter("destName"));
			dto.setZip(Integer.parseInt(req.getParameter("destZip")));
			dto.setAddr1(req.getParameter("addr1"));
			dto.setAddr2(req.getParameter("addr2"));
			dto.setDestPhone(req.getParameter("destPhone"));
			dto.setDestTel(req.getParameter("destTel"));
			//dto.setAddrKey(req.getParameter("addrKey"));
			dto.setAddrKey("no");
			
			dao.insertData(dto);
			
			url = cp + "/dest/list.do";
			resp.sendRedirect(url);
	
		}
		if(uri.indexOf("updated.do")!=-1){
			
			String destNickname = req.getParameter("destNickname");
			
			HttpSession session = req.getSession();
			MemberDTO info = (MemberDTO) session.getAttribute("customInfo");
			
			if(destNickname!=null){
				destNickname = URLDecoder.decode(destNickname,"UTF-8");
			}
			
			int totalDataCount = dao.getDataCount(info.getUserId());
			DestinationDTO dto = dao.getReadData(info.getUserId(), destNickname);
			String destNicknameList[] = dao.selectDestNickname(info.getUserId(),totalDataCount,destNickname);
			
			req.setAttribute("dto", dto);
			req.setAttribute("destNicknameList", destNicknameList);
			req.setAttribute("totalDataCount", totalDataCount);
			
			url = "/project/destinationUpdated.jsp";
			forward(req, resp, url);
		}
		else if(uri.indexOf("updated_ok.do")!=-1){
			
			DestinationDTO dto = new DestinationDTO();
			String ex_destNickname = req.getParameter("ex_destNickname");
			
			HttpSession session = req.getSession();
			MemberDTO info = (MemberDTO) session.getAttribute("customInfo");
			
			dto.setUserId(info.getUserId());			
			dto.setDestNickname(req.getParameter("destNickname"));
			dto.setDestName(req.getParameter("destName"));
			dto.setZip(Integer.parseInt(req.getParameter("destZip")));
			dto.setAddr1(req.getParameter("addr1"));
			dto.setAddr2(req.getParameter("addr2"));
			dto.setDestPhone(req.getParameter("destPhone"));
			dto.setDestTel(req.getParameter("destTel"));
			//dto.setAddrKey(req.getParameter("addrKey"));
			dto.setAddrKey("no");
			
			dao.updateData(dto,ex_destNickname);
			
			url = cp + "/dest/list.do";
			resp.sendRedirect(url);
	
		}
		else if(uri.indexOf("changeAddrkey_ok.do")!=-1){
			
			String destNickname = URLDecoder.decode(req.getParameter("destNickname"),"UTF-8");

			HttpSession session = req.getSession();
			MemberDTO info = (MemberDTO) session.getAttribute("customInfo");
			
			//addrKey yes → no
			dao.changeAddrkeyNo();
			
			//addrKey no → yes
			dao.changeAddrkeyYes(info.getUserId(), destNickname);
			

			url = cp + "/dest/list.do";
			resp.sendRedirect(url);
		}
		
		else if(uri.indexOf("list.do")!=-1){
			
			HttpSession session = req.getSession();
			MemberDTO info = (MemberDTO) session.getAttribute("customInfo");
			
			List<DestinationDTO> lists = dao.getList(info.getUserId());

			int totalDataCount = dao.getDataCount(info.getUserId());
	
			req.setAttribute("lists", lists);
			req.setAttribute("totalDataCount", totalDataCount);
			
			url = "/project/destinationList.jsp";
			forward(req, resp, url);
				
		}
		else if(uri.indexOf("deleted.do")!=-1){

			HttpSession session = req.getSession();
			MemberDTO info = (MemberDTO) session.getAttribute("customInfo");
			
			String destNickname = URLDecoder.decode(req.getParameter("destNickname"),"UTF-8");
			/*
			if(destNickname!=null){
				destNickname = URLDecoder.decode(destNickname,"UTF-8");
			}
			*/
			dao.deleteData(info.getUserId(), destNickname);
			
			url = cp + "/dest/list.do";
			resp.sendRedirect(url);
			
		}
		
	}
	
	
}
