<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../project/layout/top.jsp" %>
<%@ include file="../project/layout/mypage.jsp" %>

<script type="text/javascript">
	function writeDest() {
		
		var f = document.dest;
	    
	    str = f.destNickname.value;
	    str = str.trim();
	    if(!str) {
	        alert("배송지명을 입력하세요");
	        f.destNickname.focus();
	        return;
	    }
	    
	    var destNick = new Array();
	    <c:forEach items="${destNicknameList}" var="dest">
	    	destNick.push("${dest}");
	    </c:forEach>
	    for ( var i = 0; i < ${totalDataCount-1}; i++) {
	    	if(str==destNick[i]){
		        alert("같은 배송지명이 존재합니다.");
		        f.destNickname.focus();
		        return;
		    }
	       
	    }
	    
	    str = f.destName.value;
	    str = str.trim();
	    if(!str) {
	        alert("받는사람을 입력하세요");
	        f.destName.focus();
	        return;
	    }
	    
	    str = f.destPhone.value;
	    str = str.trim();
	    if(!str) {
	        alert("휴대폰 번호를 입력하세요");
	        f.destPhone.focus();
	        return;
	    }
	    
	    str = f.destTel.value;
	    str = str.trim();
	    if(!str) {
	        alert("일반 전화번호를 입력하세요");
	        f.destTel.focus();
	        return;
	    }
	    
	    str = f.destZip.value;
	    str = str.trim();
	    if(!str) {
	        alert("우편번호를 입력하세요");
	        f.destZip.focus();
	        return;
	    }
	    
	    str = f.addr1.value;
	    str = str.trim();
	    if(!str) {
	        alert("기본주소를 입력하세요");
	        f.addr1.focus();
	        return;
	    }
	    
	    str = f.addr2.value;
	    str = str.trim();
	    if(!str) {
	        alert("상세주소를 입력하세요");
	        f.addr2.focus();
	        return;
	    }
	    
	    f.action = "<%=cp%>/dest/writed_ok.do";
	    f.submit();
		
	}

</script>

<form name="dest" method="post">

<table class="page_title_area" style="margin-top: 80px; margin-bottom: 50px;">
<tr class="page_title">
	<td colspan="2" align="center">
		<h2 class="h_title page">배송지 추가</h2>
		<p class="text font_lg"></p>
	</td>
</tr>
</table>

<div class="ap_contents mypage">
<div class="address_list">

<hr class="div m20"/>
<table class="clear" style="width: 600px;" align="center">

<tr style="width: 700px;">
	<td colspan="2">
		<p class="text bullet_dot">자주 사용하시는 배송지를 등록 및 관리하실 수 있습니다./</p>
	</td>
</tr>

<tr>
	<td style="color: #6E6E6E;" width="500px;">
		<p class="text bullet_dot">배송지는 최대 10개까지 추가하실 수 있습니다.</p>
	</td>
	<td style="color: #A901DB;">
		*필수 입력항목
	</td>
</tr>

</table>
<hr class="div m20"/>


<table width="600" align="center">

<tr>
	<td width="100" height="50">배송지명</td>
	<td style="padding-left: 20px;">
		<input type="text" name="destNickname" maxlength="50" placeholder="배송지명을 입력해 주세요" 
			class="input_wrap w100p" style="padding: 10px 0px;">
	</td>
</tr>

<tr>
	<td width="100" height="50">받는사람</td>
	<td style="padding-left: 20px;">
		<input type="text" name="destName" maxlength="50" placeholder="받는 분 성함을 입력해 주세요" 
			class="input_wrap w100p" style="padding: 10px 0px;">
	</td>
</tr>

<tr>
	<td width="100" height="50">휴대전화</td>
	<td style="padding-left: 20px;">
		<input type="text" name="destPhone" maxlength="50" placeholder="010-1234-5678" 
			class="input_wrap w100p" style="padding: 10px 0px;">
	</td>
</tr>

<tr>
	<td width="100" height="50">일반 전화번호</td>
	<td style="padding-left: 20px;">
		<input type="text" name="destTel" maxlength="50" placeholder="02-123-4567" 
			class="input_wrap w100p" style="padding: 10px 0px;">
	</td>
</tr>

<tr>
	<td width="100" rowspan="3" height="50">주소</td>
	<td style="padding-left: 20px;">
		<input type="text" name="area" maxlength="50" placeholder="지역명" class="input_wrap w70p" style="padding: 10px 0px;">
		<button size="20" class="btn_md_form btn_address_fins">찾기</button>
	</td>
</tr>
<tr>
	<td style="padding-left: 20px;" height="50">
		<input type="text" name="destZip"   placeholder="우편번호" class="input_wrap w25p" style="padding: 10px 0px;">
		<input type="text" name="addr1"  placeholder="기본주소" class="input_wrap w74p" style="padding: 10px 0px;">
	</td>
</tr>
<tr>
	<td style="padding-left: 20px;" height="50">
		<input type="text" name="addr2" placeholder="상세주소" class="input_wrap w100p" style="padding: 10px 0px;">
	</td>
</tr>


<tr align="center">
	<td colspan="2">
		<input type="button" class="btn_blg_secondary" value="취소" onclick="javascript:location.href='<%=cp%>/dest/list.do';">
		<input type="button" class="btn_blg_primary" value="등록" onclick="writeDest();">
	</td>
</tr>

</table>

</div>
</div>

</form>

<%@ include file="../project/layout/footer.jsp" %>
</body>

</html>