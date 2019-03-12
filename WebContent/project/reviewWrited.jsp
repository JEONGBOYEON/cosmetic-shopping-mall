<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../project/layout/top.jsp" %>
<%@ include file="../project/layout/mypage.jsp" %>

<script type="text/javascript">

	function sendIt() {
		
		var f = document.myForm;
		
		var str = f.subject.value;
		if (!str) {
			alert("제목을 입력하세요");
			f.subject.focus();
			return;
		}
		f.subject.value = str;
		
		str = f.content.value;
		if (!str) {
			alert("내용을 입력해주세요");
			f.content.focus();
			return;
		}

		f.action = "<%=cp%>/review/writed_ok.do";
		f.submit();
	}
	
</script>

<form name="myForm" method="post" enctype="multipart/form-data">

<table class="page_title_area" style="margin-top: 80px; margin-bottom: 50px;">
<tr class="page_title">
	<td align="center">
		<h3 class="h_title page">${dto.productName }리뷰 작성</h3>
		<p class="text font_lg"></p>
	</td>
</tr>
</table>

<div class="ap_contents mypage">
<div class="address_list">

<hr class="div m20"/>
<table class="clear" style="width: 600px;" align="center">
<tr style="width: 600px;">
	<td>
		<p class="text bullet_dot">상품을 평가해주세요</p>
	</td>
</tr>

<tr>
	<td style="color: #6E6E6E;" width="500px;">
		<p class="text bullet_dot">리뷰 작성시 진주알 100알이 적립됩니다.</p>
	</td>
</tr>
</table>
<hr class="div m20"/>

<table width="600" align="center">
<tr>
	<td height="50">옵&nbsp;&nbsp;&nbsp;&nbsp;션</td>
	<td style="padding-left: 20px;" >${dto.productOption }</td>
</tr>
<tr>
	<td height="50">옵&nbsp;&nbsp;&nbsp;&nbsp;션</td>
	<td style="padding-left: 20px;" >${productId}</td>
</tr>

<tr>
	<td height="50">평&nbsp;&nbsp;&nbsp;&nbsp;점</td>
	<td class="input_wrap w80p" style="padding: 10px 0px 10px 20px;">
		<select name="rate">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
		</select>
	</td>
</tr>

<tr>
	<td height="50">제&nbsp;&nbsp;&nbsp;&nbsp;목</td>
	<td style="padding-left: 20px;">
		<input type="text" name="subject" maxlength="90" placeholder="최대 30자까지 입력 가능합니다" 
			class="input_wrap w80p" style="padding: 10px 0px 10px 20px;">
	</td>
</tr>

<tr>
	<td height="50">내&nbsp;&nbsp;&nbsp;&nbsp;용</td>
	<td style="padding-left: 20px;">
		<textarea rows="20" cols="80" name="content" style="padding: 10px 0px 10px 20px;"></textarea>
	</td>
</tr>

<tr>
	<td height="50">파&nbsp;&nbsp;&nbsp;&nbsp;일</td>
	<td class="input_wrap w80p" style="padding: 10px 0px 10px 20px;"><input type="file" name="upload" size="74"></td>
</tr>

<tr align="center">
	<td colspan="2">
		<input type="hidden" name="productId" value="${productId }">
		<input type="button" class="btn_blg_secondary" value="취소" onclick="javascript:location.href='<%=cp%>/review/possibleList.do';">
		<input type="button" class="btn_blg_primary" value="등록" onclick="sendIt();">
	</td>
</tr>

</table>

</div>
</div>

</form>


<%@include file="../project/layout/footer.jsp"  %>
</body>

</html>