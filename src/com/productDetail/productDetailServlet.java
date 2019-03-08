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
      
      // ���� ���ε� ��ġ ����
      String root = getServletContext().getRealPath("/");
      String path = root + File.separator + "pds" + File.separator
            + "imageFile";
      
      File f = new File(path);
      
      if (!f.exists()) {
         f.mkdirs();
      }
      
      // �̹������ϰ��
      String imagePath = cp + "/pds/imageFile";
      req.setAttribute("imagePath", imagePath);
      
      if (uri.indexOf("detail.do")!=-1){ 
         
         //String productName = req.getParameter("productName");
         String productName = "�÷�Ǯ ���� ƾƮ";
         productDetailDTO dto = dao.getReadData(productName);
         
         if(dto==null){
            System.out.println("dto����!!");
         }
       
         List<productDetailImageDTO> detailImagelists = dao.getDetailImageList(productName);
         
         req.setAttribute("dto", dto);
         req.setAttribute("productName", productName);
         req.setAttribute("detailImagelists", detailImagelists);
         
         // detail.jsp �������� ������
         url = "/productDetail/detail.jsp";
         forward(req, resp, url);

      }else if(uri.indexOf("create.do")!=-1){
      
         url = "/productDetail/productCreate.jsp";
         forward(req, resp, url);
         
      }else if(uri.indexOf("create_ok.do")!=-1){
      
         String encType = "UTF-8";
         int maxSize = 10*1024*1024;
         
         //���� ���ε�
         MultipartRequest mr = new MultipartRequest(req, path, maxSize, encType, new DefaultFileRenamePolicy());
         productDetailDTO dto = null;
         
         //DB�� ���������� �Բ� �Է�
         //���ε��� ���Ϸκ��� ���� ����
         if(mr.getFile("productListImage")!=null){//null�� �ƴϸ� ������ ����� ���ε�Ȱ�
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
         
         //list.do �������� �����̷�Ʈ
         url = cp + "/product/create.do";
         resp.sendRedirect(url);
         
      }   
   
   }

}