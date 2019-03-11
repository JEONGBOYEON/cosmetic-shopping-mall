<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../project/layout/top.jsp" %>
<%@ include file="../project/layout/mypage.jsp" %>

<table class="page_title_area" style="margin-top: 80px; margin-bottom: 50px;">
<tr class="page_title">
	<td colspan="2" align="center">
		<h2 class="h_title page">단골매장 관리</h2>
		<p class="text font_lg"></p>
	</td>
</tr>
</table>

<div class="ap_contents mypage">
<div class="address_list">

<table class="clear" style="width: 1200px;">
<tr style="width: 1200px;">
	<td colspan="2">
		<p class="text font_lg"><font color="#A901DB" style="font-weight: bold;">${totalDataCount }개의 단골매장</font>이 등록되어 있습니다.
	</td>
</tr>

<tr>
	<td width="1000px;">
		<p class="text bullet_dot">단골매장은 최대 10개까지 추가하실 수 있습니다.</p>
	</td>
	<td>
		<button onclick="javascript:location.href='<%=cp%>/shop/insert.do';"
			class="btn_md_primary addr_add">단골 매장 추가하기</button>
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
	</colgroup>
	
	<thread>
	<tr>
		<th scope="col" bgcolor="#F2F2F2">매장</th>
		<th scope="col" bgcolor="#F2F2F2">전화번호</th>
		<th scope="col" bgcolor="#F2F2F2">주소</th>
		<th scope="col" bgcolor="#F2F2F2">매장위치</th>
		<th scope="col" bgcolor="#F2F2F2">삭제</th>
	</tr>
	</thread>
	
	<tbody id="paging">
	<c:if test="${totalDataCount==0 }">
		<p class="text bullet_dot">등록된 단골 매장 데이터가 없습니다.</p>
	</c:if>
	
	<c:if test="${totalDataCount!=0}">
		<c:forEach var="dto" items="${lists }">
		<tr>
			<td>${dto.shopName }</td>
			<td>${dto.shopTel }</td>
			<td class="align_left">${dto.shopAddr1 } ${dto.shopAddr2 }</td>
			<td>
				<button onclick="" class="btn_sm_bordered" >
					위치보기
				</button>
			</td>
			<td>
				<button onclick="javascript:location.href='<%=cp%>/shop/deleted.do?shopName=${dto.shopName }';" class="btn_sm_bordered" >
					삭제하기
				</button>
			</td>
		</tr>
		</c:forEach>
	</c:if>
	</tbody>
	
</table>

</div>
</div>

<%@ include file="../project/layout/footer.jsp" %>
</body>

</html>