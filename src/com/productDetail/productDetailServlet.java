package com.productDetail;

import java.io.File;
import java.io.IOException;
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


public class productDetailServlet extends HttpServlet{

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
      productDetailDAO dao = new productDetailDAO(conn);

      String cp = req.getContextPath();
      String uri = req.getRequestURI();
      String url;
      
      // 파일 업로드 위치 지정
      String root = getServletContext().getRealPath("/");
      String path = root + File.separator + "pds" + File.separator
            + "imageFile";
      
      File f = new File(path);
      
      if (!f.exists()) {
         f.mkdirs();
      }
      
      // 이미지파일경로
      String imagePath = cp + "/pds/imageFile";
      req.setAttribute("imagePath", imagePath);
      
      if (uri.indexOf("detail.do")!=-1){ 
         
         //String productName = req.getParameter("productName");
         String productName = "컬러풀 비비드 틴트";
         productDetailDTO dto = dao.getReadData(productName);
         
         if(dto==null){
            System.out.println("dto없음!!");
         }
       
         List<productDetailImageDTO> detailImagelists = dao.getDetailImageList(productName);
         
         req.setAttribute("dto", dto);
         req.setAttribute("productName", productName);
         req.setAttribute("detailImagelists", detailImagelists);
         
         // detail.jsp 페이지로 포워드
         url = "/productDetail/detail.jsp";
         forward(req, resp, url);

      }else if(uri.indexOf("create.do")!=-1){
      
         url = "/productDetail/productCreate.jsp";
         forward(req, resp, url);
         
      }else if(uri.indexOf("create_ok.do")!=-1){
      
         String encType = "UTF-8";
         int maxSize = 10*1024*1024;
         
         //파일 업로드
         MultipartRequest mr = new MultipartRequest(req, path, maxSize, encType, new DefaultFileRenamePolicy());
         productDetailDTO dto = null;
         
         //DB에 파일정보와 함께 입력
         //업로드한 파일로부터 정보 추출
         if(mr.getFile("productListImage")!=null){//null이 아니면 파일이 제대로 업로드된것
            dto = new productDetailDTO();
            
            dto.setProductId(req.getParameter("productid"));
            dto.setProductCategory(req.getParameter("productCategory"));
            dto.setProductName(req.getParameter("productName"));
            dto.setProductOption(req.getParameter("productOption"));
            dto.setPrice(Integer.parseInt(req.getParameter("price")));
            dto.setColor(req.getParameter("color"));
            dto.setTexture(req.getParameter("texture"));
            dto.setSeason(req.getParameter("season"));
            dto.setSaveFileName(mr.getFilesystemName("productListImage"));
            dto.setOriginalName(mr.getOriginalFileName("productListImage"));
            dto.setFileCategory(req.getParameter("fileCategory"));
         }
         
         if(dto!=null){
            dao.insertData(dto);
         }
         
         //list.do 페이지로 리다이렉트
         url = cp + "/product/create.do";
         resp.sendRedirect(url);
         
      }   
   
   }

}