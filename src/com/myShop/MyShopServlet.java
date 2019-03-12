package com.myShop;

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
import com.shop.ShopDAO;
import com.shop.ShopDTO;
import com.util.DBCPConn;
import com.util.MyUtil;

public class MyShopServlet extends HttpServlet {

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
		MyShopDAO dao = new MyShopDAO(conn);
		ShopDAO shopDao = new ShopDAO(conn);
		MyUtil myUtil = new MyUtil();
		
		String cp = req.getContextPath();
		String uri = req.getRequestURI();
		
		String url;
		
		if(uri.indexOf("list.do")!=-1){
			
			HttpSession session = req.getSession();
			MemberDTO info = (MemberDTO) session.getAttribute("customInfo");
			
			List<ShopDTO> lists = dao.getLists(info.getUserId());
			//List<ShopDTO> lists = dao.getLists("rin0724");

			int totalDataCount = dao.getDataCount(info.getUserId());
			//int totalDataCount = dao.getDataCount("rin0724");
			
			req.setAttribute("lists", lists);
			req.setAttribute("totalDataCount", totalDataCount);
			
			url = "/project/myShopList.jsp";
			forward(req, resp, url);
				
		}
		else if(uri.indexOf("insert.do")!=-1){
			
			HttpSession session = req.getSession();
			MemberDTO info = (MemberDTO) session.getAttribute("customInfo");
			
			//아이디에 등록된 단골매장 수
			int dataCount = dao.getDataCount(info.getUserId());
			if(dataCount == 10){	
				List<ShopDTO> lists = dao.getLists(info.getUserId());
				
				int totalDataCount = dao.getDataCount(info.getUserId());
				
				req.setAttribute("lists", lists);
				req.setAttribute("totalDataCount", totalDataCount);
				req.setAttribute("message","단골매장은 최대 10개까지만 등록 가능합니다.");
				url = "/project/myShopList.jsp";
				forward(req, resp, url);
				
			}
			
			String searchValue = req.getParameter("searchValue");
			
			if(searchValue==null){
				searchValue="noSearchVlaue";
			}else{
				if(req.getMethod().equalsIgnoreCase("GET")){
					searchValue = URLDecoder.decode(searchValue,"UTF-8");
				}
			}
			
			List<ShopDTO> lists = shopDao.getLists(searchValue);
			//검색된 단골 매장 수
			int getDataCount = shopDao.getDataCount(searchValue);
			
			String param = "";
			if(!searchValue.equals("")){
				param+= "?searchValue=" + URLEncoder.encode(searchValue,"UTF-8");
			}
			
			req.setAttribute("lists", lists);
			req.setAttribute("getDataCount", getDataCount);
			req.setAttribute("param", param);
			
			url = "/project/myShopInsert.jsp";
			forward(req, resp, url);
		}
		else if(uri.indexOf("inserted_ok.do")!=-1){
			
			HttpSession session = req.getSession();
			MemberDTO info = (MemberDTO) session.getAttribute("customInfo");
					
			String shopName = URLDecoder.decode(req.getParameter("shopName"),"UTF-8");
			
			dao.insertData(info.getUserId(), shopName);
			//dao.insertData("rin0724", shopName);
			
			url = cp + "/shop/list.do";
			resp.sendRedirect(url);
			
		}
		else if(uri.indexOf("deleted.do")!=-1){

			HttpSession session = req.getSession();
			MemberDTO info = (MemberDTO) session.getAttribute("customInfo");
			
			String shopName = URLDecoder.decode(req.getParameter("shopName"),"UTF-8");
			/*
			if(destNickname!=null){
				destNickname = URLDecoder.decode(destNickname,"UTF-8");
			}
			*/
			dao.deleteData(info.getUserId(), shopName);
			//dao.deleteData("rin0724", shopName);
			
			url = cp + "/shop/list.do";
			resp.sendRedirect(url);
			
		}
		
	}

}
