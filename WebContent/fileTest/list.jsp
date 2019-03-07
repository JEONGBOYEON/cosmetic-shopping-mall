<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<br/><br/>

<table width="500" align="center" style="font-family: 돋움; font-size: 10pt;" cellspacing="0" cellpadding="1" >
<tr>
	<td align="right" colspan="4">
	<input type="button" value="글쓰기" onclick="javascript:location='<%=cp%>/file/write.do';"/>
	</td>
</tr>
<tr>
	<td align="center" width="50">번호</td>
	<td align="left" width="150">제목</td>
	<td align="left" width="250">파일</td>
	<td align="center" width="50">삭제</td>
</tr>
<c:forEach var="dto" items="${lists }">
<tr>
	<td align="center" width="50">${dto.num }</td>
	<td align="left" width="150">${dto.subject }</td>
	<td align="left" width="250">
<%-- 	<a href="${downloadPath }?num=${dto.num }">
	${dto.originalFileName }</a> --%>
	<a href="${imagePath }/${dto.saveFileName }">
	<img alt="" src="${imagePath }/${dto.saveFileName }" width="100" height="100">
	</a>
	</td>
	<td align="center" width="50">
	<a href="${deletePath}?num=${dto.num }&pageNum=${pageNum }">삭제</a></td>
</tr>
</c:forEach>

<tr>
	<td align="center" colspan="4">
	<c:if test="${dataCount!=0 }">
		${pageIndexList }
	</c:if>
	<c:if test="${dataCount==0 }">
		등록된 파일이 없습니다
	</c:if>
	</td>
</tr>
</table>

</body>
</html>