<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../project/layout/top.jsp"%>

<script type="text/javascript">
	String.prototype.trim = function() {
		var TRIM_PATTERN = /(^\s*)|(\s*$)/g;
		return this.replace(TRIM_PATTERN, "");
	};
	
	function insertFile(){
		f = document.eventForm;
		
		str = f.eventKey.value;
		str = str.trim();
		if(!str){
			alert("\n이벤트키를 입력하세요.");//공백제거후 내용이 없으면
			f.eventKey.focus();
			return;
		}
		f.eventKey.value = str;

		f.action = "<%=cp %>/event/create_ok.do";
		f.submit();
		
	}
</script>

	<div class="ap_contents product detail" style="padding-left: 150px;">
		<table width="600" border="1" bordercolor="#eeeeee" align="center" >
		<tr height="40">
			<td style="padding-left: 20px;">
			<b>이벤트등록(ADMIN)</b>
			</td>
		</tr>
		</table>
		<br/><br/>
		
		<form name="eventForm" method="post" enctype="multipart/form-data">
		<table border="0" align="center">
		<tr><td colspan="2" height="1" bgcolor="#dbdbdb" align="center"></td></tr>
		<tr>
			<td width="140" height="30" style="padding-left: 20px;">
			이벤트키
			</td>
			<td width="460" style="padding-left: 10px;">
			<input type="text" name="eventKey" maxlength="20" class="boxTF">
			</td>
		</tr>
			<tr>
			<td width="140" height="30" style="padding-left: 20px;">
			이벤트명
			</td>
			<td width="460" style="padding-left: 10px;">
			<input type="text" name="eventName" maxlength="20" class="boxTF">
			</td>
		</tr>
		<tr>
			<td width="140" height="30" style="padding-left: 20px;">
			시작일
			</td>
			<td width="460" style="padding-left: 10px;">
				<input name="startDay" type="tel">
			</td>
		</tr>
		<tr>
			<td width="140" height="30" style="padding-left: 20px;">
			종료일
			</td>
			<td width="460" style="padding-left: 10px;">
				<input name="endDay" type="tel">
			</td>
		</tr>
		
		<tr><td colspan="2" height="1" bgcolor="#dbdbdb" align="center"></td></tr>
		<tr>
			<td width="140" height="30" style="padding-left: 20px;">
			이벤트리스트사진
			</td>
			<td width="460" style="padding-left: 10px;">
			<input type="file" name="eventListImage" maxlength="50" size="10" class="boxTF"/>
			메인이미지
			<input type="checkbox" name="fileCategory" value="list" /> 
			</td>
		</tr>

		<tr><td colspan="2" height="1" bgcolor="#dbdbdb" align="center"></td></tr>
		
		<tr><td colspan="2" height="1" bgcolor="#dbdbdb" align="center"></td></tr>
		<tr>
			<td width="140" height="30" style="padding-left: 20px;">
			상세사진1
			</td>
			<td width="460" style="padding-left: 10px;">
			<input type="file" name="eventDetailImage1" maxlength="50" size="10" class="boxTF"/>
			상세이미지
			<input type="checkbox" name="detail1" value="detail" /> 
			</td>
		</tr>
		
		<tr><td colspan="2" height="1" bgcolor="#dbdbdb" align="center"></td></tr>
		
		<tr>
			<td colspan="2" align="center">
			<div class="purchase_button_set" style="padding-left: 200px;">
			<span><button class="btn_lg_bordered emp btn_buy_now" type="button" onclick="insertFile();">이벤트등록</button></span>
			<span><button class="btn_lg_bordered emp btn_buy_now" type="button" onclick="javascript:location.href='<%=cp %>/event/adminlist.do';">이벤트리스트</button></span>
			<span><button class="btn_lg_primary btn_basket" type="reset" onclick="document.myForm.productId.focus();">다시입력</button></span>
			</div>
			</td>
		</tr>
		</table>
		</form>
	</div>
	

<%@include file="../project/layout/footer.jsp"  %>