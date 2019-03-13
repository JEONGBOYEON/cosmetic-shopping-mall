<%@ page contentType="text/html; charset=UTF-8"%>
<%
	String cp2 = request.getContextPath();
%>
<!-- 회원탈퇴 -->
<div id="loginmodal" style="display: none;">
	<dl class="layer">
		<!-- handlebars@include:common/modal-info-contents.hbs -->

		<dt class="sr_only"></dt>

		<dd style="padding: 1px;">
			<div style="text-align: center;">
				<dl>
					<dt>
						<p style="font-size: 20px; font-family: '맑은 고딕' "><strong>회원탈퇴</strong></p><br>
						<p style="font-size: 14px; font-family: '맑은 고딕' height: 60px; ">
							아모레 뷰티포인트 서비스 및 에뛰드하우스 사이트를 이용하시며 불편한 사항이 있으셨나요?<br> 뷰티포인트 및
							에뛰드하우스를 떠나시는 사유를 남겨주시면 보다 나은 뷰티포인트,<br> 에뛰드하우스가 되는 데
							참고하겠습니다.
						</p>
					</dt>
					<form name="myForm">
						<dd>
							<div class="password_box">
								<strong>비밀번호 확인</strong> 
								
								<span class="input_wrap"> 
								<input name="userPwd" type="password" />
								</span>
							</div>
			</div>
			<div class="mgb10" style="text-align: center; margin-top: 20px;">
				<button class="btn_md_primary" id="closeMember" type="button"
					onclick="sendIt();">회원탈퇴</button>
			</div>

		</dd>
		</form>
	</dl>
<!-- <button class="layer_close" type="button" data-dismiss="modal">레이어 닫기</button> -->
</div>

<c:if test="${!empty message }">

	<script type="text/javascript">
				alert("${message}");
							</script>
</c:if>

<script type="text/javascript">
function sendIt(){
	
	//아이디,비밀번호의 유효성검사
	
	var f= document.myForm;

	str=f.userPwd.value;
	str=str.trim();
	if(!str){
		alert("패스워드를 입력하세요!");
		f.userPwd.focus();
		return;
	}
	f.userPwd.value=str; 

  	f.action="<%=cp2%>/member/delete_ok.do"; 
	f.submit();  

}
</script>


<!--모달윈도우부분-->
<script type="text/javascript">
$(function(){
  $('#loginform').submit(function(e){
    return false;
  });
  
  $('#modaltrigger').leanModal({ top: 110, overlay: 0.8, closeButton: ".hidemodal" });
  
});
</script>
<!--//모달윈도우부분-->

































