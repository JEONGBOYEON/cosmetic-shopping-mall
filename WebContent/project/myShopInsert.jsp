<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../project/layout/top.jsp" %>
<%@ include file="../project/layout/mypage.jsp" %>

<script>
	function sendIt() {
		
		var f = document.myForm;
		
		f.action = "<%=cp%>/shop/insert.do"
		f.submit();
		
	}
</script>	

<table class="page_title_area" style="margin-top: 80px; margin-bottom: 50px;">
<tr class="page_title">
	<td colspan="2" align="center">
		<h2 class="h_title page">단골매장 추가</h2>
		<p class="text font_lg"></p>
	</td>
</tr>
</table>

<div class="ap_contents mypage">
<div class="address_list">

<hr class="div m20"/>
<form name="myForm" method="post">
<table class="clear" style="width: 600px;" align="center">

<tr style="width: 700px; height: 50px;">
	<td colspan="2">
		<p class="text bullet_dot">찾고자 하는 매장명 또는 지역명을 입력하신 후 검색을 누르세요. (예:명동1호점, 강남)</p>
	</td>
</tr>

<tr>
	<td style="color: #6E6E6E;" width="500px;">
		<input type="text" name="searchValue" maxlength="50" placeholder="매장명 또는 지역명" 
			class="input_wrap w100p" style="padding: 10px 0px;">
	</td>
	<td>
		<img alt="검색" src="<%=cp%>/project/image/search.PNG" onclick="sendIt();">
	</td>
</tr>

</table>
</form>
<hr class="div m20"/>

<table width="1000" align="center" class="ui_table_striped data_table thead_colored align_center @table-striped-apply">

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
		<th scope="col" bgcolor="#F2F2F2">선택</th>
	</tr>
	</thread>
	
	<tbody id="paging">
	<c:if test="${getDataCount==0 }">
		<p class="text bullet_dot">검색된 정보가 없습니다.</p>
	</c:if>
	
	<c:if test="${getDataCount!=0 }">
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
				<button onclick="javascript:location.href='<%=cp%>/shop/inserted_ok.do?shopName=${dto.shopName }';" class="btn_sm_bordered" >
					선택
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