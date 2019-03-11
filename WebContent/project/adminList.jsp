<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../project/layout/top.jsp"  %>
<%@ page import="java.net.URLEncoder" %>

<script type="text/javascript">
	function sendIt(){
		var f = document.searchForm;
		f.action = "<%=cp %>/pr/adminList.do";
		f.submit();
	}
</script>
	<div class="ap_contents product detail" style="padding-left: 70px;">

	<div style="width:1100px;margin:30px auto;text-align:left;">
		<div style="border:1px solid ; width: 1100px; padding-left:20px;height:40px;text-align:left;line-height:40px;margin-bottom:30px;">
		상품관리 (admin)
		</div>
	
		<div id="bbsList_header" style="height:30px;">
			<div style="float:right;width:600px;text-align:right;" >
			<form action="" name="searchForm" method="post">
				<select name="searchKey" class="selectField" style="width: 100px; height: 26px;">
					<option value="productId">상품 Id</option>
					<option value="productCategory">상품 카테고리</option>
					<option value="productName">상품명</option>
					<option value="state">판매상태</option>
					<option value="color">색상</option>
					<option value="texture">텍스쳐</option>
					<option value="texture">추천계절</option>
				</select>
				<input type="text" name="searchValue" style="width: 250px; height: 26px;" />
	 			<input type="button" value="상품검색" onclick="sendIt();" style="padding:5px 5px 5px 5px; color: #fff; background: #f54a7e; border:1px solid #f54a7e;"/>
				<button type="button" onclick="javascript:location.href='<%=cp %>/pr/adminCreate.do';" style="padding:5px 5px 5px 5px; color:#f54a7e; border:1px solid #f54a7e;">
				상품등록
				</button>
			</form>
			</div>
		</div>
		<div style="width: 1100px;">
			<table border="1" >
				<tr align="center">
					<td width="100" height="30">상품ID</td>
					<td width="150">상품카테고리</td>
					<td width="150">상품명</td>
					<td width="150">상품옵션</td>
					<td width="100">판매상태</td>
					<td width="100">가격</td>
					<td width="100">등록일자</td>
					<td width="150">색상</td>
					<td width="60">텍스쳐</td>
					<td width="100">추천계절</td>
					<td width="60">수정</td>
					<td width="60">삭제</td>		
				</tr>
				<c:forEach var="dto" items="${lists }">
				<tr align="center">
					<td width="100" height="30">${dto.productId }</td>
					<td >${dto.productCategory }</td>
					<td >${dto.productName }</td>
					<td >${dto.productOption }</td>
					<td >${dto.state }</td>
					<td >${dto.price }</td>	
					<td >${dto.productDate }</td>				
					<td >${dto.color }</td>
					<td >${dto.texture }</td>
					<td >${dto.season }</td>
					<td ><a href="${updateUrl}&productId=${dto.productId}">수정</a></td>
					<td ><a href="${deleteUrl}&productId=${dto.productId}">삭제</a></td>
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
	
<%@include file="../project/layout/footer.jsp"  %>