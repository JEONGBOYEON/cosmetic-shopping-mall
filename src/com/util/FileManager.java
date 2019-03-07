package com.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;


public class FileManager {
	
	//파일다운로드
	//saveFileName : 서버에 저장된 파일명
	//originalFileName : 클라이언트가 업로드한 파일명
	//path : 서버에 저장된 실제 경로
	//서버에 있는 파일을 읽어서 클라이언트에게 내려보내는 것이기 때문에 req가 아니라 resp가 매개변수로 사용
	public static boolean doFileDownload(HttpServletResponse response, String saveFileName, String originalFileName, String path){
	
		try {
			//파일경로
			String filePath = path + File.separator + saveFileName ; 
			
			//클라이언트가 설정한 파일명이 없을경우 서버에 저장된 파일명으로 입력
			if(originalFileName==null || originalFileName.equals("")){
				originalFileName = saveFileName; 
			}
			
			//파일을 다운받아 클라이언트 컴에 파일이름 생성시 한글깨짐 방지
			originalFileName = new String(originalFileName.getBytes("euc-kr"),"ISO-8859-1");
			
			File f = new File(filePath);
			
			if(!f.exists()){
				return false; //파일이 존재하지 않으면 stop
			}
			
			//파일의 종류
			response.setContentType("application/octet-stream");// .txt 와 같이 파일확장자명 뒤에 붙는 .을 octet-stream라고 함
			//헤더정보
			response.setHeader("Content-disposition", "attachment;fileName="+originalFileName);
			
			//서버에서 클라이언트로 내려보낼 때, Fileinputstream으로 파일을 읽은 후 bis에 넣은 것
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));//f로 줘도 되고 filepath로 줘도 됨
			
			//읽은 파일을 내보낼 때 outputstream 사용
			OutputStream out = response.getOutputStream();
			
			int data;
			byte bytes[] = new byte[4096];
			
			while((data=bis.read(bytes,0,4096))!=-1){
				out.write(bytes,0,data);
			}
			out.flush();
			out.close();
			bis.close();	
			
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
		return true;
	}
	
	//파일삭제
	public static void doFileDelete(String fileName, String path){
		try {
			
			String filePath = path + File.separator + fileName;
			File f = new File(filePath);
			
			//파일의 경로를 찾아가서 존재할 경우 삭제
			if(f.exists()){
				f.delete();//물리적 파일삭제
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
