package com.review;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.MemberDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.util.DBCPConn;
import com.util.FileManager;
import com.util.MyUtil;

public class ReviewServlet extends HttpServlet{

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
		ReviewDAO dao = new ReviewDAO(conn);
		MyUtil myUtil = new MyUtil();
		
		String cp = req.getContextPath();
		String uri = req.getRequestURI();
		
		String url;
		
		String root = getServletContext().getRealPath("/");
		String path = root + File.separator + "pds" + File.separator+ "reviewImageFile";

		File f = new File(path);

		// 파일 없으면 디렉토리 생성
		if (!f.exists()) {
			f.mkdirs();
		}
	
		if(uri.indexOf("list.do")!=-1){
			
			HttpSession session = req.getSession();
			MemberDTO info = (MemberDTO) session.getAttribute("customInfo");
			
			String pageNum = req.getParameter("pageNum");
			
			int currentPage = 1;
			
			if(pageNum!=null)
				currentPage = Integer.parseInt(pageNum);
			
			int dataCount_yes = dao.getDataCount(info.getUserId(),"yes");
			int dataCount_no = dao.getDataCount(info.getUserId(), "no");
			
			int numPerPage = 5;
			int totalPage = myUtil.getPageCount(numPerPage, dataCount_yes);
			
			if(currentPage>totalPage)
				currentPage = totalPage;
			
			int start = (currentPage-1)*numPerPage+1;
			int end = currentPage*numPerPage;
			
			List<ReviewDTO> lists = dao.getLists(start, end, info.getUserId(),"yes");
			Iterator<ReviewDTO> it = lists.iterator();
			while(it.hasNext()){
				ReviewDTO dto = it.next();
				dto.setProductName2(URLDecoder.decode(dto.getProductName(),"UTF-8"));
			}
			
			Iterator<ReviewDTO> it2 = lists.iterator();	
			while(it2.hasNext()){
				ReviewDTO dto = it2.next();
				dto.setProductName(URLEncoder.encode(dto.getProductName(),"UTF-8")); 
			}
			
			String listUrl = cp + "/review/list.do";
			
			String pageIndexList = myUtil.pageIndexList(currentPage, totalPage, listUrl);
			
			//포워딩 데이터
			req.setAttribute("lists", lists);
			req.setAttribute("pageIndexList", pageIndexList);
			req.setAttribute("dataCount_yes", dataCount_yes);
			req.setAttribute("dataCount_no", dataCount_no);
			
			url = "/project/myReviewList.jsp";
			forward(req, resp, url);
				
		}
		else if(uri.indexOf("possibleList.do")!=-1){
			
			HttpSession session = req.getSession();
			MemberDTO info = (MemberDTO) session.getAttribute("customInfo");
			
			String pageNum = req.getParameter("pageNum");
			
			int currentPage = 1;
			
			if(pageNum!=null)
				currentPage = Integer.parseInt(pageNum);
			
			//int dataCount = dao.getDataCount(info.getUserId());
			int dataCount_yes = dao.getDataCount(info.getUserId(),"yes");
			int dataCount_no = dao.getDataCount(info.getUserId(), "no");
			
			int numPerPage = 5;
			int totalPage = myUtil.getPageCount(numPerPage, dataCount_no);
			
			if(currentPage>totalPage)
				currentPage = totalPage;
			
			int start = (currentPage-1)*numPerPage+1;
			int end = currentPage*numPerPage;
			
			List<ReviewDTO> lists = dao.getLists(start, end, info.getUserId(),"no");
			Iterator<ReviewDTO> it = lists.iterator();
			while(it.hasNext()){
				ReviewDTO dto = it.next();
				dto.setProductName2(URLDecoder.decode(dto.getProductName(),"UTF-8"));
			}
			
			Iterator<ReviewDTO> it2 = lists.iterator();	
			while(it2.hasNext()){
				ReviewDTO dto = it2.next();
				dto.setProductName(URLEncoder.encode(dto.getProductName(),"UTF-8")); 
			}
			
			String listUrl = cp + "/review/posibleList.do";
			
			String pageIndexList = myUtil.pageIndexList(currentPage, totalPage, listUrl);
			
			//포워딩 데이터
			req.setAttribute("lists", lists);
			req.setAttribute("pageIndexList", pageIndexList);
			req.setAttribute("dataCount_yes", dataCount_yes);
			req.setAttribute("dataCount_no", dataCount_no);
			
			url = "/project/myPossibleReviewList.jsp";
			forward(req, resp, url);
				
		}
		else if(uri.indexOf("writed.do")!=-1){
			
			String productId = req.getParameter("productId");
			HttpSession session = req.getSession();
			MemberDTO info = (MemberDTO) session.getAttribute("customInfo");
			
			ReviewDTO dto = dao.getProductList(info.getUserId(), productId);
			
			req.setAttribute("dto", dto);
			req.setAttribute("productId", productId);
			
			url = "/project/reviewWrited.jsp";
			forward(req, resp, url);
		}
		else if(uri.indexOf("writed_ok.do")!=-1){
			
			HttpSession session = req.getSession();
			MemberDTO info = (MemberDTO) session.getAttribute("customInfo");
			
			//이미지업로드
			//이미지업로드가 문제 없이 진행된 다음에 DB업로드 실행
			String encType = "UTF-8";
			int maxSize = 10*1024*1024;
			MultipartRequest mr = new MultipartRequest(req, path, maxSize, encType, new DefaultFileRenamePolicy());

			//DB에 파일정보 입력
			//업로드한 파일정보 추출
				
			ReviewDTO dto = new ReviewDTO();

			dto.setUserId(info.getUserId());
			dto.setProductId(mr.getParameter("productId"));
			dto.setSubject(mr.getParameter("subject"));
			dto.setContent(mr.getParameter("content"));
			dto.setRate(Integer.parseInt(mr.getParameter("rate")));
			if (mr.getFile("upload") != null) {
				dto.setOriginalName(mr.getFilesystemName("upload"));
				dto.setSavefileName(mr.getOriginalFileName("upload"));
			}
	
			dao.insertData(dto);
		
			
			url = cp +"/review/list.do";
			resp.sendRedirect(url);
			
		}
		else if(uri.indexOf("deleted.do")!=-1){

			HttpSession session = req.getSession();
			MemberDTO info = (MemberDTO) session.getAttribute("customInfo");
			
			String productId = req.getParameter("productId");
			
			String fileName = dao.getSaveFile(info.getUserId(), productId);
			
			FileManager.doFileDelete(fileName, path);
			
			dao.deleteData(info.getUserId(), productId);
			
			url = cp + "/review/list.do";
			resp.sendRedirect(url);
			
		}
	}
}
