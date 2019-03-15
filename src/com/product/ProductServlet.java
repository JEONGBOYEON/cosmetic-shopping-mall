package com.product;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.DBCPConn;
import com.util.MyUtil;

public class ProductServlet extends HttpServlet{

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

		MyUtil myutil = new MyUtil();
		
		
		Connection conn = DBCPConn.getConnection();
		ProductDAO dao = new ProductDAO(conn);
		

		String cp = req.getContextPath();
		String uri = req.getRequestURI();
		
		String url;
		
		//파일 업로드 위치
		String root = getServletContext().getRealPath("/");
		String path = root + File.separator + "pds" + File.separator + "imageFile";
		//D:\java\work\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\study/pds/imageFile
		
		File f = new File(path);
		
		if(!f.exists())
			f.mkdirs();
		
		if(uri.indexOf("listNew.do")!=-1){
			

			String pageNum = req.getParameter("pageNum");
			
			int currentPage=1;
			
			if(pageNum!=null)
				currentPage = Integer.parseInt(pageNum);


			int dataCount = dao.getDataCount();
			
			int numPerPage = 6;
			int totalPage = myutil.getPageCount(numPerPage, dataCount);
			
			if(currentPage>totalPage)
				currentPage = totalPage;
			
			int start = (currentPage-1)*numPerPage + 1;
			int end = currentPage*numPerPage;
			

			//이미지저장 경로 보내주기
			String imagePath = cp + "/pds/imageFile";
			req.setAttribute("imagePath", imagePath);
			
			//전체 리스트 보내주기(filecategory컬럼이 list인 product만)
			List<ProductDTO> lists = dao.getListsNew(start, end);
			
			//옵션값 보내주기
			List<String> options = new ArrayList<String>();
			for (ProductDTO dto : lists){
				options = dao.getOption(dto.getProductName());
				dto.setOptionList(options);
			}

			req.setAttribute("lists", lists);
			
			//페이징을 위한 값들 보내주기
			String listUrl = cp + "/product/listNew.do";
			String pageIndexList = myutil.pageIndexList(currentPage, totalPage, listUrl);
			req.setAttribute("pageIndexList", pageIndexList);
			req.setAttribute("dataCount", dataCount);
			req.setAttribute("totalPage", totalPage);
			req.setAttribute("pageNum", pageNum);
			
			
			url = "/project/listNew.jsp";
			forward(req,resp,url);
			
		}else if(uri.indexOf("listBest.do")!=-1){
			

			String pageNum = req.getParameter("pageNum");
			
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
			

			//이미지저장 경로 보내주기
			String imagePath = cp + "/pds/imageFile";
			req.setAttribute("imagePath", imagePath);
			
			//전체 리스트 보내주기(filecategory컬럼이 list인 product만)
			List<ProductDTO> lists = dao.getListsBest(start, end);
			
			//옵션값 보내주기
			List<String> options = new ArrayList<String>();

			for (ProductDTO dto : lists){
				options = dao.getOption(dto.getProductName());
				dto.setOptionList(options);
			}

			req.setAttribute("lists", lists);
			
			//페이징을 위한 값들 보내주기
			String listUrl = cp + "/product/listBest.do";
			String pageIndexList = myutil.pageIndexList(currentPage, totalPage, listUrl);
			req.setAttribute("pageIndexList", pageIndexList);
			req.setAttribute("dataCount", dataCount);
			req.setAttribute("totalPage", totalPage);
			req.setAttribute("pageNum", pageNum);
			
			
			
			
			url = "/project/listBest.jsp";
			forward(req,resp,url);
			
		}else if(uri.indexOf("listCategory.do")!=-1){

			String pageNum = req.getParameter("pageNum");
			String productCategory = req.getParameter("productCategory");
			
			if (productCategory != null) {
				 productCategory = URLDecoder.decode(req.getParameter("productCategory"), "UTF-8");
			}
			
			int currentPage=1;
			
			if(pageNum!=null)
				currentPage = Integer.parseInt(pageNum);

			int dataCount = dao.getDataCountCategory(productCategory);
			
			int numPerPage = 6;
			int totalPage = myutil.getPageCount(numPerPage, dataCount);
			
			if(currentPage>totalPage)
				currentPage = totalPage;
			
			int start = (currentPage-1)*numPerPage + 1;
			int end = currentPage*numPerPage;
			

			//이미지저장 경로 보내주기
			String imagePath = cp + "/pds/imageFile";
			req.setAttribute("imagePath", imagePath);
			
			//전체 리스트 보내주기(filecategory컬럼이 list인 product만)
			List<ProductDTO> lists = dao.getListsCategory(start, end,productCategory);
			
			//옵션값 보내주기
			List<String> options = new ArrayList<String>();

			for (ProductDTO dto : lists){
				options = dao.getOption(dto.getProductName());
				dto.setOptionList(options);
			}

			req.setAttribute("lists", lists);
			
			if (productCategory != null) {
				 productCategory = URLEncoder.encode(productCategory, "UTF-8");
			}
			
			
			//페이징을 위한 값들 보내주기
			String listUrl = cp + "/product/listCategory.do?productCategory="+productCategory;
			String pageIndexList = myutil.pageIndexList(currentPage, totalPage, listUrl);
			req.setAttribute("pageIndexList", pageIndexList);
			req.setAttribute("dataCount", dataCount);
			req.setAttribute("totalPage", totalPage);
			req.setAttribute("pageNum", pageNum);
			
			url = "/project/listCategory.jsp";
			forward(req,resp,url);
			
		}else if(uri.indexOf("main.do")!=-1){
			
			List<ProductDTO> lists = dao.getList();
			req.setAttribute("lists", lists);

			String imagePath = cp + "/pds/imageFile";
			req.setAttribute("imagePath", imagePath);
			
			
			
			url = "/project/main.jsp";
			forward(req,resp,url);
			
		}
	}
}