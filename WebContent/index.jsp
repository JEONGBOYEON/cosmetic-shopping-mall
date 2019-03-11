<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:choose>
	<c:when test="${empty sessionScope.customInfo.userId }">
	<b>로그인 하면 새로운 세상이 보입니다.</b><br><br>
	</c:when>
	<c:otherwise>
		${sessionScope.customInfo.userName }님 방가방가...<br><br>
	</c:otherwise>
	</c:choose>
	
	<a href="<%=cp%>/join/created.do">회원가입</a>
	<br> 
	<a href="<%=cp%>/join/login.do">로그인</a>
	<br>
	<a href="<%=cp%>/join/mypage.do">마이페이지</a>
	<br>
	<a href="<%=cp%>/join/logout.do">로그아웃</a>
	<br>

</body>
</html>


































