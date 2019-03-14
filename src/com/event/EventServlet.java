package com.event;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.productDetail.ProductDetailDTO;
import com.productDetail.ProductDetailImageDTO;
import com.util.DBCPConn;
import com.util.FileManager;
import com.util.MyUtil;

public class EventServlet extends HttpServlet{

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

		MyUtil myutil = new MyUtil();
		
		Connection conn = DBCPConn.getConnection();
		EventDAO dao = new EventDAO(conn);

		//페이징
		MyUtil myUtil = new MyUtil();

		String cp = req.getContextPath();
		String uri = req.getRequestURI();
		String url;

		// 파일 업로드 위치 지정
		String root = getServletContext().getRealPath("/");
		String path = root + File.separator + "pds" + File.separator+ "eventImageFile";
		// D:\java\work\.metadata\.plugins\org.eclipse.wst.server.core\tmp1\wtpwebapps\cosmetic\pds\imageFile
		// D:\java\.metadata\.plugins\org.eclipse.wst.server.core\tmp2\wtpwebapps\cosmetic\pds\imageFile

		File f = new File(path);

		// 파일 없으면 디렉토리 생성
		if (!f.exists()) {
			f.mkdirs();
		}

		else if (uri.indexOf("create.do") != -1) {

			url = "/project/ad/adminEventCreate.jsp";
			forward(req, resp, url);

		} else if (uri.indexOf("create_ok.do") != -1) {

			String encType = "UTF-8";
			int maxSize = 10 * 1024 * 1024;

			// 파일 업로드
			MultipartRequest mr = new MultipartRequest(req, path, maxSize,
					encType, new DefaultFileRenamePolicy());

			EventDTO dto = new EventDTO();

			dto.setEventKey(Integer.parseInt(mr.getParameter("eventKey")));
			dto.setEventName(mr.getParameter("eventName"));
			dto.setStartDay(mr.getParameter("startDay"));
			dto.setEndDay(mr.getParameter("endDay"));
			
			//메인사진 올림
			if (mr.getFile("eventListImage") != null) {
				dto.setSaveFileName(mr.getFilesystemName("eventListImage"));
				dto.setOriginalName(mr.getOriginalFileName("eventListImage"));
				dto.setFileCategory(mr.getParameter("fileCategory"));
			}
			
			dao.insertData(dto);
			
			//상세사진 올림
			if (mr.getFile("eventDetailImage1") != null) {
				dto.setSaveFileName(mr.getFilesystemName("eventDetailImage1"));
				dto.setOriginalName(mr.getOriginalFileName("eventDetailImage1"));
				dto.setFileCategory(mr.getParameter("detail1"));
			}
			dao.insertData(dto);
					
			//list.do 페이지로 리다이렉트
			url = cp + "/event/adminlist.do.jsp";
			resp.sendRedirect(url);

		}else if (uri.indexOf("adminlist.do") != -1) {
			
			String pageNum = req.getParameter("pageNum");
			String productId = req.getParameter("eventkey");
			
			int currentPage=1;
			
			if(pageNum!=null)
				currentPage = Integer.parseInt(pageNum);


			int dataCount = dao.getDataCount();
			
			int numPerPage = 10;
			int totalPage = myutil.getPageCount(numPerPage, dataCount);
			
			if(currentPage>totalPage)
				currentPage = totalPage;
			
			int start = (currentPage-1)*numPerPage + 1;
			int end = currentPage*numPerPage;
			
			String deleteUrl = cp + "/pr/adminDeleted_ok.do?pageNum=" + currentPage;
			String updateUrl = cp + "/pr/adminUpdate.do?pageNum=" + currentPage ;


			//이미지저장 경로 보내주기
			String imagePath = cp + "/pds/eventImageFile";
			req.setAttribute("imagePath", imagePath);

			//진행중인 이벤트 배열
			List<EventDTO> lists = dao.getListAll(start, end);
			
			//페이징을 위한 값들 보내주기
			String listUrl = cp + "/event/adminlist.do";
			String pageIndexList = myutil.pageIndexList(currentPage, totalPage, listUrl);
			req.setAttribute("lists", lists);
			req.setAttribute("pageNum", pageNum);
			req.setAttribute("productId", productId);
			req.setAttribute("pageIndexList", pageIndexList);
			req.setAttribute("updateUrl", updateUrl);
			req.setAttribute("deleteUrl", deleteUrl);
			
			//adminList.jsp 페이지로 포워드
			url = "/project/ad/adminEventList.jsp?";
			forward(req, resp, url);
			
		}else if(uri.indexOf("adminDeleted_ok.do")!=-1){
			
			String eventKey = req.getParameter("eventKey");
			String fileCategory = req.getParameter("fileCategory");
			String pageNum = req.getParameter("pageNum");
	
			//삭제할 dto 읽어오기
			EventDTO dto = dao.getUpdateData(eventKey,fileCategory);	
			
			//물리적 파일 삭제 - 리스트사진
			if(dto.getSaveFileName()!=null){
				FileManager.doFileDelete(dto.getSaveFileName(), path);
			}
			//물리적 파일 삭제 - 리스트사진
			if(dto.getSaveFileName()!=null){
				FileManager.doFileDelete(dto.getSaveFileName(), path);
			}

			
			//테이블 정보 삭제(on delete cascade 조건으로 참조테이블의 데이터도 함께 삭제)
			dao.deleteData(eventKey,fileCategory);

			//삭제 진행 후 리스트 페이지로 리다이렉트
			url = cp + "/pr/adminList.do?";
			resp.sendRedirect(url);
			
		}else if(uri.indexOf("list.do")!=-1){
			

			String pageNum = req.getParameter("pageNum");
			
			int currentPage=1;
			
			if(pageNum!=null)
				currentPage = Integer.parseInt(pageNum);


			int dataCount = dao.getDataCount();
			int dataCount2 =dao.getDataCount2();
			
			int numPerPage = 4;
			int totalPage = myutil.getPageCount(numPerPage, dataCount);
			int totalPage2 = myutil.getPageCount(numPerPage, dataCount2);
			
			if(currentPage>totalPage)
				currentPage = totalPage;
			
			int start = (currentPage-1)*numPerPage + 1;
			int end = currentPage*numPerPage;

			//이미지저장 경로 보내주기
			String imagePath = cp + "/pds/eventImageFile";
			req.setAttribute("imagePath", imagePath);
			
			//진행중인 이벤트 배열
			List<EventDTO> lists = dao.getLists(start, end);
			
			//종료된 이벤트 배열
			List<EventDTO> lists2 = dao.getLists_end(start, end);

			req.setAttribute("lists", lists);
			req.setAttribute("lists2", lists2);
			
			//페이징을 위한 값들 보내주기
			String listUrl = cp + "/event/list.do";
			String pageIndexList = myutil.pageIndexList(currentPage, totalPage, listUrl);
			req.setAttribute("pageIndexList", pageIndexList);
			req.setAttribute("dataCount", dataCount);
			req.setAttribute("totalPage", totalPage);
			req.setAttribute("dataCount2", dataCount2);
			req.setAttribute("totalPage2", totalPage2);
			req.setAttribute("pageNum", pageNum);
			
			url = "/project/event/eventList.jsp";
			forward(req,resp,url);
			
		}else if (uri.indexOf("list_detail.do") != -1) {
			
			String eventKey=req.getParameter("eventKey");
			String imagePath = cp + "/pds/eventImageFile";
			req.setAttribute("imagePath", imagePath);
			
			//이미지가 여러개일경우 배열로 받기위해
			List<EventDTO> lists = dao.getList(eventKey);

			String name=lists.get(0).getEventName();
			req.setAttribute("lists", lists);
			req.setAttribute("eventName", name);

			url = "/project/event/eventDetail.jsp";
			forward(req, resp, url);

		}
	}



}
