<%@page import="java.net.URLEncoder"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../project/layout/top.jsp" %>
<%@ include file="../project/layout/mypage.jsp" %>

<table class="page_title_area" style="margin-top: 80px; margin-bottom: 50px;">
<tr class="page_title">
	<td colspan="2" align="center">
		<h2 class="h_title page">배송지 관리</h2>
		<p class="text font_lg"></p>
		<b style="color: red">${message }</b>
	</td>
</tr>
</table>

<div class="ap_contents mypage">
<div class="address_list">

<table class="clear" style="width: 1200px;">
<tr style="width: 1200px;">
	<td colspan="2">
		<p class="text font_lg"><font color="#A901DB" style="font-weight: bold;">${totalDataCount }개의 배송지</font>가 등록되어 있습니다.
	</td>
</tr>

<tr>
	<td width="1050px;">
		<p class="text bullet_dot">자주 사용하시는 배송지를 등록 및 관리하실 수 있습니다. 배송지는 최대 10개까지 추가하실 수 있습니다.</p>
	</td>
	<td>
		<button onclick="javascript:location.href='<%=cp%>/dest/writed.do';"
			class="btn_md_primary addr_add">배송지 추가하기</button>
	</td>
</tr>
</table>

<table class="ui_table_striped data_table thead_colored align_center @table-striped-apply" id="shpiTable">
	
	<colgroup>
		<col>
		<col>
		<col>
		<col>
		<col>
		<col>
		<col>
	</colgroup>
	
	<thread>
	<tr>
		<th scope="col" bgcolor="#F2F2F2">기본선택지</th>
		<th scope="col" bgcolor="#F2F2F2">배송지명</th>
		<th scope="col" bgcolor="#F2F2F2">받는이</th>
		<th scope="col" bgcolor="#F2F2F2">주소</th>
		<th scope="col" bgcolor="#F2F2F2">연락처</th>
		<th scope="col" bgcolor="#F2F2F2">수정</th>
		<th scope="col" bgcolor="#F2F2F2">삭제</th>
	</tr>
	</thread>
	
	<tbody id="paging">
	<c:forEach var="dto" items="${lists }">
	<tr>
		<td class="check_wrap check_only">
			<c:if test="${dto.addrKey=='no' }">
				<a href="<%=cp%>/dest/changeAddrkey_ok.do?destNickname=${dto.destNickname }">
					<img alt="" src="<%=cp%>/project/image/destination_addrkeyNo.JPG" height="25px;">
				</a>
			</c:if> 
			<c:if test="${dto.addrKey=='yes' }">
				<img alt="" src="<%=cp%>/project/image/destination_addrkeyYes.jpg" height="25px;">
			</c:if> 
		</td>
		<%int num = 0; %>
		<td>${dto.destNickname2 }</td>
		<%num++;%>
		<td>${dto.destName }</td>
		<td class="align_left">[${dto.zip }] ${dto.addr1 } ${dto.addr2 }</td>
		<td>${dto.destPhone }</td>
		<td>
			<button onclick="javascript:location.href='<%=cp%>/dest/updated.do?destNickname=${dto.destNickname }'" class="btn_sm_bordered" >
				수정하기
			</button>
		</td>
		<td>
			<button onclick="javascript:location.href='<%=cp%>/dest/deleted.do?destNickname=${dto.destNickname }'" class="btn_sm_bordered" >
				삭제하기
			</button>
		</td>
	</tr>
	</c:forEach>
	</tbody>
	
</table>

</div>
</div>

<%@ include file="../project/layout/footer.jsp" %>
</body>

</html>