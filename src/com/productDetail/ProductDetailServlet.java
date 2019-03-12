package com.productDetail;

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

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.util.DBCPConn;
import com.util.FileManager;
import com.util.MyUtil;

public class ProductDetailServlet extends HttpServlet {

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

		Connection conn = DBCPConn.getConnection();
		ProductDetailDAO dao = new ProductDetailDAO(conn);
		
		//페이징
		MyUtil myUtil = new MyUtil();

		String cp = req.getContextPath();
		String uri = req.getRequestURI();
		String url;

		// 파일 업로드 위치 지정
		String root = getServletContext().getRealPath("/");
		String path = root + File.separator + "pds" + File.separator+ "imageFile";
		// D:\java\work\.metadata\.plugins\org.eclipse.wst.server.core\tmp1\wtpwebapps\cosmetic\pds\imageFile
		// D:\java\.metadata\.plugins\org.eclipse.wst.server.core\tmp2\wtpwebapps\cosmetic\pds\imageFile

		File f = new File(path);

		// 파일 없으면 디렉토리 생성
		if (!f.exists()) {
			f.mkdirs();
		}

		if (uri.indexOf("detail.do") != -1) {

			String productId = req.getParameter("productId");
			ProductDetailDTO dto = dao.getReadData(productId);
			
			if (dto == null) {
				String productName = req.getParameter("productName");
				dto = dao.getReadData("productName",productName);
			}
			String productName = dto.getProductName();
			
			// 이미지파일경로
			String imagePath = cp + "/pds/imageFile";
			req.setAttribute("imagePath", imagePath);
			List<ProductDetailImageDTO> detailImagelists = dao.getDetailImageList("productName",productName);
			List<String> optionList = dao.getOptionList(productName);

			req.setAttribute("dto", dto);
			req.setAttribute("productName", productName);
			req.setAttribute("detailImagelists", detailImagelists);
			req.setAttribute("optionList", optionList);

			//detail.jsp 페이지로 포워드
			url = "/project/detail.jsp?productName="+productName;
			forward(req, resp, url);

		} else if (uri.indexOf("adminCreate.do") != -1) {
			
			url = "/project/adminCreate.jsp";
			forward(req, resp, url);

		} else if (uri.indexOf("adminCreate_ok.do") != -1) {

			String encType = "UTF-8";
			int maxSize = 10 * 1024 * 1024;

			// 파일 업로드
			MultipartRequest mr = new MultipartRequest(req, path, maxSize,
					encType, new DefaultFileRenamePolicy());

			ProductDetailDTO dto = new ProductDetailDTO();
			ProductDetailImageDTO detailDTO = new ProductDetailImageDTO();

			dto.setProductId(mr.getParameter("productId"));
			dto.setProductCategory(mr.getParameter("productCategory"));
			dto.setProductName(mr.getParameter("productName"));
			dto.setProductOption(mr.getParameter("productOption"));
			dto.setState(mr.getParameter("state"));
			dto.setPrice(Integer.parseInt(mr.getParameter("price")));
			dto.setColor(mr.getParameter("color"));
			dto.setTexture(mr.getParameter("texture"));
			dto.setSeason(mr.getParameter("season"));
			if (mr.getFile("productListImage") != null) {
				dto.setSaveFileName(mr.getFilesystemName("productListImage"));
				dto.setOriginalName(mr.getOriginalFileName("productListImage"));
				dto.setFileCategory(mr.getParameter("fileCategory"));
			}
			
			dao.insertData(dto);

			// DB에 파일정보와 함께 입력
			// 업로드한 상세이미지 파일 정보 추출
			for (int i=1; i<=3; i++) {
				//상세이미지는 첨부파일이 3개까지 가능
				String productDetailImage = "productDetailImage"+i;
				
				//상품수를 세기 위해 getMaxNum 메소드 호출
				//int maxNum = dao.getMaxNum(mr.getParameter("productName"));
				if (mr.getFile(productDetailImage)!=null) {//null이 아니면 파일이 제대로 업로드된것
					String detailNum = mr.getParameter("productId")+"-"+i;
					detailDTO.setDetailNum(detailNum);
					detailDTO.setProductId(mr.getParameter("productId"));
					detailDTO.setProductName(mr.getParameter("productName"));
					detailDTO.setSaveFileName(mr.getFilesystemName(productDetailImage));
					detailDTO.setOriginalName(mr.getOriginalFileName(productDetailImage));
					dao.insertDetailImage(detailDTO);
				}
			}
	
			//list.do 페이지로 리다이렉트
			url = cp + "/pr/adminList.do";
			resp.sendRedirect(url);

		} else if (uri.indexOf("adminList.do") != -1) {
			
			String pageNum = req.getParameter("pageNum");
			String productId = req.getParameter("productId");
			int currentPage = 1; //처음 띄우는 리스트 페이지
			
			if(pageNum!=null){
				currentPage = Integer.parseInt(pageNum);
			}
			
			String searchKey = req.getParameter("searchKey");
			String searchValue = req.getParameter("searchValue");
			
			if(searchValue==null || searchValue.equals(null)){
				searchKey ="productId";
				searchValue = "";
			}else{
				if(req.getMethod().equalsIgnoreCase("GET")){
					searchValue = URLDecoder.decode(searchValue, "UTF-8");
				}
			}
			
			int dataCount = dao.getDataCount(searchKey, searchValue);
			int numPerPage = 10;
			int totalPage = myUtil.getPageCount(numPerPage, dataCount);
			
			if(currentPage>totalPage)
				currentPage = totalPage;
			
			int start = (currentPage-1)*numPerPage+1;
			int end = currentPage*numPerPage;
			
			List<ProductDetailDTO> lists = dao.getReadData(start, end, searchKey, searchValue);
			
			String param = "";
			if(!searchValue.equals("")){
				param = "searchKey=" + URLEncoder.encode(searchKey,"UTF-8");
				param += "&searchValue=" + URLEncoder.encode(searchValue,"UTF-8");
			}
			
			String listUrl = cp+"/pr/adminList.do";
			if(!param.equals("")){
				listUrl +="?" + param;
			}
			
			String pageIndexList = myUtil.pageIndexList(currentPage, totalPage, listUrl);
			
			//검색 내용이 있으면 url에 추가
			String deleteUrl = cp + "/pr/adminDeleted_ok.do?pageNum=" + currentPage;
			if(!param.equals("")){
				deleteUrl += "&" + param;
			}
			
			//검색 내용이 있으면 url에 추가
			String updateUrl = cp + "/pr/adminUpdate.do?pageNum=" + currentPage ;
			if(!param.equals("")){
				updateUrl += "&" + param;
			}
			
			// 이미지파일경로
			String imagePath = cp + "/pds/imageFile";
			req.setAttribute("imagePath", imagePath);
			
			req.setAttribute("lists", lists);
			req.setAttribute("pageNum", pageNum);
			req.setAttribute("productId", productId);
			req.setAttribute("pageIndexList", pageIndexList);
			req.setAttribute("updateUrl", updateUrl);
			req.setAttribute("deleteUrl", deleteUrl);
			
			//adminList.jsp 페이지로 포워드
			url = "/project/adminList.jsp?"+param;
			forward(req, resp, url);
			
		}else if(uri.indexOf("adminDeleted_ok.do")!=-1){
			
			String productId = req.getParameter("productId");
			String pageNum = req.getParameter("pageNum");
			String searchKey = req.getParameter("searchKey");
			String searchValue = req.getParameter("searchValue");
			
			//삭제할 dto 읽어오기
			ProductDetailDTO dto = dao.getUpdateData(productId);
			
			if(searchValue!=null){
				searchKey = URLDecoder.decode(searchKey, "UTF-8");
				searchValue = URLDecoder.decode(searchValue, "UTF-8");
			}

			String param = "pageNum="+pageNum;
			
			if(searchValue!=null){
				param += "&searchKey=" + URLEncoder.encode(searchKey,"UTF-8");
				param += "&searchValue=" + URLEncoder.encode(searchValue,"UTF-8");
			}
			
			//물리적 파일 삭제 - 리스트사진
			if(dto.getSaveFileName()!=null){
				FileManager.doFileDelete(dto.getSaveFileName(), path);
			}
			
			//물리적 파일 삭제 - 상세사진
			List<ProductDetailImageDTO> DeleteDetailImageList = dao.getDetailImageList("productId", productId);
			Iterator<ProductDetailImageDTO> it = DeleteDetailImageList.iterator(); 
			while(it.hasNext()){
				ProductDetailImageDTO detaildto = it.next();
				if(detaildto.getSaveFileName()!=null){
				FileManager.doFileDelete(detaildto.getSaveFileName(), path);
				}
			}
			
			//테이블 정보 삭제(on delete cascade 조건으로 참조테이블의 데이터도 함께 삭제)
			dao.deleteData(productId);

			//삭제 진행 후 리스트 페이지로 리다이렉트
			url = cp + "/pr/adminList.do?"+param;
			resp.sendRedirect(url);
			
		}else if(uri.indexOf("adminUpdate.do")!=-1){
			
			String productId = req.getParameter("productId");
			String pageNum = req.getParameter("pageNum");
			String searchKey = req.getParameter("searchKey");
			String searchValue = req.getParameter("searchValue");
			
			//수정할 dto 읽어오기
			ProductDetailDTO dto = dao.getUpdateData(productId);
			if(dto==null){
				url = cp+"/pr/adminList.do";
				resp.sendRedirect(url);
			}

			//등록된 상세사진 dto 읽어오기
			List<ProductDetailImageDTO> detailImagelists = dao.getDetailImageList("productId",dto.getProductId());
			
			String param = "pageNum="+pageNum;
			
			if(searchValue!=null){
				param += "&searchKey=" + URLEncoder.encode(searchKey,"UTF-8");
				param += "&searchValue=" + URLEncoder.encode(searchValue,"UTF-8");
			}
			req.setAttribute("dto", dto);
			req.setAttribute("pageNum", pageNum);
			req.setAttribute("params", param);
			req.setAttribute("detailImagelists", detailImagelists);
		
			//project폴더변경
			url = "/project/adminUpdate.jsp";
			forward(req, resp, url);
			
			
		}else if(uri.indexOf("adminUpdate_ok.do")!=-1){
			
			String params = req.getParameter("params");
			
			String encType = "UTF-8";
			int maxSize = 10 * 1024 * 1024;
			
			MultipartRequest mr = new MultipartRequest(req, path, maxSize, encType, new DefaultFileRenamePolicy());
			ProductDetailDTO dto = new ProductDetailDTO();
			ProductDetailImageDTO detailDTO = new ProductDetailImageDTO();

			dto.setProductId(mr.getParameter("productId"));
			dto.setProductCategory(mr.getParameter("productCategory"));
			dto.setProductName(mr.getParameter("productName"));
			dto.setProductOption(mr.getParameter("productOption"));
			dto.setState(mr.getParameter("state"));
			dto.setPrice(Integer.parseInt(mr.getParameter("price")));
			dto.setColor(mr.getParameter("color"));
			dto.setTexture(mr.getParameter("texture"));
			dto.setSeason(mr.getParameter("season"));
			dto.setFileCategory(mr.getParameter("fileCategory"));
			dao.updateData(dto);
			
/*			
 			//첨부파일수정은 다시 작성
 			for (int i=1; i<=3; i++) {
				String productDetailImage = "productDetailImage"+i;
				if (mr.getFile(productDetailImage)!=null) {//null이 아니면 파일이 제대로 업로드된것
					String detailNum = mr.getParameter("productId")+"-"+i;
					detailDTO.setDetailNum(detailNum);
					detailDTO.setProductId(mr.getParameter("productId"));
					detailDTO.setProductName(mr.getParameter("productName"));
					detailDTO.setSaveFileName(mr.getFilesystemName(productDetailImage));
					detailDTO.setOriginalName(mr.getOriginalFileName(productDetailImage));
					dao.insertDetailImage(detailDTO);
				}
			}
*/
			url = cp + "/pr/adminList.do?"+params;
			resp.sendRedirect(url);
			
		}
	}
}