package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


public class TimerFilter implements Filter{
	
	//필터
	//필터는 웹 어플리케이션에서 사용하는 서블릿이나 JSP에
	//도착하기 전에 Request의 내용을 조작한 후 서블릿이나 JSP에 전송하는 기능
	//주로 보안을 위해 사용하고 요청정보의 인코딩작업 등에 사용
	
	//특정 페이지가 실행이 될 때, 페이지가 조회되는 시간을 필터를 통해 조회하고자 함 
	private FilterConfig config;

	@Override
	public void destroy() {
		//3. 필터에서 나갈때 실행
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//2. 필터작업
		
		long before = System.currentTimeMillis(); //시작시간
		
		chain.doFilter(request, response);
		
		long after = System.currentTimeMillis(); //종료시간
		
		String uri;
		if(request instanceof HttpServletRequest){
			//downcast
			HttpServletRequest req = (HttpServletRequest)request;
			
			uri = req.getRequestURI();
			//config.getServletContext().log(uri+":"+(after-before)+"ms");//로그 만드는 방법
			System.out.println(uri+":"+(after-before)+"ms");
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		//1. 필터에 들어올때 처음실행
		this.config = config;
	}
	
}
