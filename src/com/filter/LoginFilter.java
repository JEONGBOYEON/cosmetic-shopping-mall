package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.join.CustomInfo;

public class LoginFilter implements Filter{

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		boolean flag = false;
		
		if(request instanceof HttpServletRequest){
			
			HttpServletRequest req = (HttpServletRequest)request;
			
			//세션 받아옴
			HttpSession session = req.getSession();
			
			if(session!=null){
				//CustomInfo info = (CustomInfo)session.getAttribute("customInfo");
				//세션에 여러 데이터를 담을 수 있지만 공간이 좁기 때문에 클래스로 만들어 하나만 보냄.
				if(session.getAttribute("customInfo")!=null){
					flag = true;
				}
			}
			
			if(flag){
				//로그인을 했을경우 true
				chain.doFilter(request, response);
			}else{
				//로그인 하지 않았으므로 로그인페이지로 포워드
				RequestDispatcher rd = request.getRequestDispatcher("/member/login.jsp");
				rd.forward(request, response);
			}
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

}
