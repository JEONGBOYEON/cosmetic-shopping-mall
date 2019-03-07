package com.fileTest;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.util.DBCPConn;
import com.util.FileManager;
import com.util.MyUtil;

public class FileTestServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	protected void forward(HttpServletRequest req, HttpServletResponse resp, String url)
			throws ServletException, IOException {
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
		
		req.setCharacterEncoding("UTF-8");
		Connection conn = DBCPConn.getConnection();
		FileTestDAO dao = new FileTestDAO(conn);
		//페이징
		MyUtil myUtil = new MyUtil();
		String cp = req.getContextPath();
		String uri = req.getRequestURI();
		String url;
		
		//파일 업로드 위치 지정
		String root = getServletContext().getRealPath("/") ;
		String path = root + File.separator + "pds" + File.separator + "saveFile";
		//D:\java\work\.metadata\.plugins\org.eclipse.wst.server.core\tmp1\wtpwebapps\cosmetic\pds\saveFile
		
		File f = new File(path);
		
		//파일 존재하지 않으면 디렉토리 생성
		if(!f.exists()){
			f.mkdirs();
		}
		
		if(uri.indexOf("write.do")!=-1){
			
			url = "/fileTest/write.jsp";
			forward(req, resp, url);
			
		}else if(uri.indexOf("write_ok.do")!=-1){
			
			String encType = "UTF-8";
			int maxSize = 10*1024*1024;
			//파일 업로드
			MultipartRequest mr = new MultipartRequest(req, path, maxSize, encType, new DefaultFileRenamePolicy());
			
			//DB에 파일정보 입력
			//업로드한 파일로부터 정보 추출
			if(mr.getFile("uploadFile")!=null){//null이 아니면 파일이 제대로 업로드된것
				FileTestDTO dto = new FileTestDTO();
				int maxNum = dao.getMaxNum();
				dto.setNum(maxNum+1);
				dto.setSubject(mr.getParameter("subject"));
				dto.setSaveFileName(mr.getFilesystemName("uploadFile"));
				dto.setOriginalFileName(mr.getOriginalFileName("uploadFile"));
				dao.insertData(dto);
			}
			
			//list.do 페이지로 리다이렉트
			url = cp + "/file/list.do";
			resp.sendRedirect(url);
			
		}else if(uri.indexOf("list.do")!=-1){
			
			String pageNum = req.getParameter("pageNum");
			int currentPage = 1; //처음 띄우는 리스트 페이지
			
			if(pageNum!=null){
				currentPage = Integer.parseInt(pageNum);
			}
			
			int dataCount = dao.getDataCount();
			int numPerPage = 5;
			int totalPage = myUtil.getPageCount(numPerPage, dataCount);
			
			if(currentPage>totalPage)
				currentPage = totalPage;
			
			int start = (currentPage-1)*numPerPage+1;
			int end = currentPage*numPerPage;
			
			String listUrl = cp+"/file/list.do";
			List<FileTestDTO> lists = dao.getList(start,end);
			String pageIndexList = myUtil.pageIndexList(currentPage, totalPage, listUrl);
			
			//다운로드경로
			String downloadPath = cp + "/file/download.do";
			//삭제경로
			String deletePath = cp + "/file/delete.do";
			//이미지파일경로
			String imagePath = cp + "/pds/saveFile";
			req.setAttribute("imagePath", imagePath);
			
			//파일정보 테이블을 리스트로 전달
			req.setAttribute("lists", lists);
			req.setAttribute("pageNum", pageNum);
			req.setAttribute("downloadPath", downloadPath);
			req.setAttribute("deletePath", deletePath);
			req.setAttribute("pageIndexList", pageIndexList);
			
			//list.jsp 페이지로 포워드
			url = "/fileTest/list.jsp";
			forward(req, resp, url);
		
		}else if(uri.indexOf("download.do")!=-1){
			 
			int num = Integer.parseInt(req.getParameter("num"));
			FileTestDTO dto = dao.getReadData(num);
			
			if(dto==null)
				return;//다운로드할 파일이 없으면 종료
			
			//파일을 정상적으로 받으면 true, 아닐경우 false
			boolean flag = FileManager.doFileDownload(resp, dto.getSaveFileName(), dto.getOriginalFileName(), path);
			
			//물리적 파일은 지웠는데 DB상의 정보에서는 삭제되지 않은 경우와 같이 
			//두 데이터가 자동으로 동기화가 되지 않는 문제가 있음. 그래서 boolean 이용
			
			//다운로드 오류 발생시 flag에 false 반환 이 때, 오류 메세지 출력
			if(flag==false){
				resp.setContentType("text/html;charset=utf-8");
				PrintWriter out = resp.getWriter();
				out.print("<script type='text/javascript'>");
				out.print("alert('download error');");
				out.print("history.back()");//확인버튼을 누르면 이전화면으로 돌려줌
				out.print("</script>");
			}
			
		}else if(uri.indexOf("delete.do")!=-1){
			
			int num = Integer.parseInt(req.getParameter("num"));
			int pageNum = Integer.parseInt(req.getParameter("pageNum"));
			FileTestDTO dto = dao.getReadData(num);
			
			//물리적 파일 삭제
			FileManager.doFileDelete(dto.getSaveFileName(), path);
			
			//테이블 정보 삭제
			dao.deleteData(num);
	
			//삭제 진행 후 리스트 페이지로 리다이렉트
			url = cp + "/file/list.do?pageNum="+pageNum;
			resp.sendRedirect(url);
		}
	}
}
