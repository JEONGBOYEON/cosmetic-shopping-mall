<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../project/layout/top.jsp"%>
<%@ page import="java.net.URLEncoder" %>

<script type="text/javascript">
	function sendIt(){
		var f = document.searchForm;
		f.action = "<%=cp %>/event/adminlist.do";
		f.submit();
	}
</script>
	<div class="ap_contents product detail" style="padding-left: 70px;">

	<div style="width:1100px;margin:30px auto;text-align:left;">
		<div style="border:1px solid ; width: 1100px; padding-left:20px;height:40px;text-align:left;line-height:40px;margin-bottom:30px;">
		이벤트관리 (admin)
		</div>
		
		<div id="bbsList_header" style="height:30px;">
			<div style="float:right;width:600px;text-align:right;" >
			<form action="" name="searchForm" method="post">
				<button type="button" onclick="javascript:location.href='<%=cp %>/event/create.do';" style="padding:5px 5px 5px 5px; color:#f54a7e; border:1px solid #f54a7e;">
				이벤트등록
				</button>
			</form>
			</div>
		</div>
	
		<div style="width: 1100px;">
			<table border="1" >
				<tr align="center">
					<td width="100" height="30">이벤트키</td>
					<td width="200">이벤트명</td>
					<td width="200">저장파일이름</td>
					<td width="100">파일카테고리</td>
					<td width="150">시작일</td>
					<td width="150">종료일</td>	
					<td width="100">수정</td>
					<td width="100">삭제</td>	
				</tr>
				<c:forEach var="dto" items="${lists }">
				<tr align="center">
					<td width="100" height="30">${dto.eventKey }</td>
					<td >${dto.eventName }</td>
					<td >${dto.saveFileName }</td>
					<td >${dto.fileCategory }</td>
					<td >${dto.startDay }</td>
					<td >${dto.endDay }</td>	
					<td ><a href="${updateUrl}&eventKey=${dto.eventKey}&fileCategory=${dto.fileCategory}">수정</a></td>
					<td ><a href="${deleteUrl}&eventKey=${dto.eventKey}&fileCategory=${dto.fileCategory}">삭제</a></td>
				</tr>
			</c:forEach>
			</table>
			
			<div style="font-size:12pt; clear:both;	height:32px;line-height:32px;margin-top:5px;text-align:center;">
				<p>
					<c:if test="${dataCount!=0 }">
						${pageIndexList }
					</c:if>
					<c:if test="${dataCount==0 }">
						등록된 게시물이 없습니다
					</c:if>
				</p>
			</div>

		</div>
	</div>

</div>
	<%@include file="../project/layout/footer.jsp"%>