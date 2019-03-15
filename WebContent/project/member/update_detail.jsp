<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../layout/top.jsp"%>
<%@ include file="../layout/mypage.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp3=request.getContextPath();
%>

<!-- 비밀번호 수정 -->
<script type="text/javascript">
function changePassword(){
	
	//아이디,비밀번호의 유효성검사
	
	var f= document.pwdUpdateForm;

	str=f.oriPassword.value;
	str=str.trim();
	if(!str){
		alert("기존 비밀번호를 입력하세요!");
		f.oriPassword.focus();
		return;
	}
	f.oriPassword.value=str; 
	
	str=f.pass1.value;
	str=str.trim();
	if(!str){
		alert("신규 비밀번호를 입력하세요!");
		f.pass1.focus();
		return;
	}
	f.pass1.value=str; 
	
	str=f.pass2.value;
	str=str.trim();
	if(!str){
		alert("신규 비밀번호확인을 입력하세요!");
		f.pass2.focus();
		return;
	}
	f.pass2.value=str; 
	
	str=f.pass1.value;
	str2=f.pass2.value;
	if(str!=str2){
		alert("신규 비밀번호와 확인이 같지 않습니다!");
		f.pass1.focus();
		return;
	}

  	f.action="<%=cp3%>/member/update_detail_ok.do"; 
	f.submit();  
}
</script>

<!-- 개인정보 수정 -->
<script type="text/javascript">
function changePrivateInfo(){
	
	//생년월일 정규식
	var regExp3 = /^(19[0-9][0-9]|20\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$/;
	
	//핸드폰번호 정규식
	var regExp4 =/(01[016789])([1-9]{1}[0-9]{2,3})([0-9]{4})$/;
	
	var f= document.infoUpdateForm;
	
	str=f.birth.value;
	str=str.trim();
	if(!regExp3.test(str)){
		alert("형식에 맞춰 생년월일을 입력하세요(8자리)");
		str="";
		f.birth.focus();
		return;
	}
	f.birth.value=str;
	
	str=f.phone.value;
	str=str.trim();
	if(!regExp4.test(str)){
		alert("형식에 맞춰 핸드폰번호를 입력하세요.");
		str="";
		f.phone.focus();
		return;
	}
	
	f.phone.value=str;

  	f.action="<%=cp3%>/member/update_detail_ok2.do"; 
	f.submit();  
}
</script>

<!-- session.sestAttribut() : 회원정보수정msg받아옴 -->
<c:if test="${!empty msg }">

	<script type="text/javascript">
				alert("${msg}");
							</script>
</c:if>

	<div class="page_title_area">
		<!-- breadcrumb 미노출 페이지는 감춤 -->

		<!-- // breadcrumb 미노출 페이지는 감춤 -->
		<div class="page_title">


			<h2 class="h_title page">개인정보 수정</h2>

			<p class="text font_lg"></p>
		</div>
	</div>

	<!-- // page title -->

	<div class="modify_wrap modify_my_info">
		<dl>
			<dd class="modify_cont">
				<h3 class="h_title sub mgt20">비밀번호 수정</h3>
				<form class="pass-check" name="pwdUpdateForm">
					<fieldset class="form">
						<legend class="sr_only">비밀번호 입력항목</legend>
						<div class="ui_table pw_check">
							<dl>
								<dt>
									<label for="prev_pw">비밀번호 수정</label>
								</dt>
								<dd>
									<div class="input_wrap w100p">
										<input type="password" maxlength="16" id="ori-pass"
											name="oriPassword" placeholder="기존 비밀번호" required="required"
											data-msg-required="기존 비밀번호를 입력해 주세요.">
									</div>
								</dd>
							</dl>
							<dl>
								<dt>
									<label for="new_pw">신규 비밀번호</label>
								</dt>
								<dd>
									<div class="input_wrap w100p">
										<input type="password" maxlength="16" id="pass1"
											name="pass1" required="required"
											placeholder="새 비밀번호 입력" title="새 비밀번호 입력"
											data-msg-required="새 비밀번호를 입력해 주세요.">
										<div class="rating_box">
											<span class="rating"></span>
										</div>
									</div>
								</dd>
							</dl>
							<dl>
								<dt>
									<label for="new_pw2">신규 비밀번호 확인</label>
								</dt>
								<dd>
									<div class="input_wrap w100p">
										<!-- 공통 : error 일 때 class="error" 추가 -->
										<input type="password" maxlength="16" id="pass2" name="pass2"
											placeholder="비밀번호 확인">
										<div class="help">
											<span class="completion"><span class="sr_only">완료</span></span>
										</div>
									</div>

								</dd>
							</dl>
							<p class="text_notice">
							비밀번호는 영문 대소문자 또는 숫자로 구성된 6-16자로 입력해주세요.
							</p>
						</div>
						<div class="form_btns">
							<button class="btn_md_primary" onclick="changePassword()"
								type="button">수정 완료</button>
						</div>
					</fieldset>
				</form>

				<hr class="div m60">

				<h3 class="h_title sub" id="private-title">개인정보 수정</h3>
				<form class="private-info" name="infoUpdateForm">
					<fieldset class="form">
						<legend class="sr_only">비밀번호 입력항목</legend>
						<div class="align_right">
							<span class="required">필수 입력 항목입니다.</span>
						</div>
						<div class="ui_table">
							<dl>
								<dt>
									<label for="user_name">이름</label>
								</dt>
								<dd>
									<div class="input_wrap w100p">
										<input type="text" value="${dto.userName}" title="이름" disabled="">
									</div>
								</dd>
							</dl>
							<dl>
								<dt>생년월일</dt>
								<dd class="ui_table">
										<div class="input_wrap w100p">
											<input type="text" value="${dto.birth}" title=생일" name="birth" maxlength="8">
										</div>
								</dd>
							</dl>
							<dl>
								<dt>
									<label for="user_phone">휴대전화<span class="required"><span
											class="sr_only">필수입력</span></span></label>
								</dt>
								<dd class="ui_table">
										<div class="input_wrap w100p">
											<input type="text" value="${dto.phone}" title=전화번호" name="phone" maxlength="11">
										</div>
								</dd>
							</dl>
							<dl>
								<dt>
									<label for="user_id">아이디</label>
								</dt>
								<dd>
									<div class="input_wrap w100p">
										<input type="text" value="${dto.userId}" title="아이디" disabled="" name="userId">
									</div>
								</dd>
							</dl>
							<!-- <dl>
								<dt>
									<label for="user_email">E-mail</label>
								</dt>
								<dd>
									<div class="input_wrap w100p">
										<input type="text" id="email" value="이메일주소"
											email2="email2" name="email" title="이메일 주소 입력">
									</div>
								</dd>
							</dl>
							<dl>
								<dt class="vat">
									<label for="new_pw2">주소</label>
								</dt>
								<dd class="ui_find_addresses">
									<div class="w100p input_btn_wrap">
										<div class="input_wrap">
											<input type="text" class="address_keyword"
												title="주소지 또는 건물명 입력" placeholder="주소지 또는 건물명">
										</div>
										<button type="button"
											class="btn_md_form btn_address_find btn_find_addresses">검색</button>
									</div>

									<div class="address_result"></div>

									<input value="18484" type="hidden" class="post_code"
										name="homeZip" title="우편번호 입력" readonly>
									<div class="input_wrap w100p">
										<input type="text" name="homeBscsAddr"
											value="기본주소" class="address_first"
											title="기본주소 입력" placeholder="기본주소" readonly="">
									</div>
									<div class="input_group w100p">
										<span class="input_wrap"><input type="text"
											value="상세주소" name="homeDtlAddr" class="address_last"
											title="상세주소 입력" placeholder="상세주소를 입력해주세요."></span>
									</div>
								</dd>
							</dl> -->
						</div>
						<div class="form_btns">
							<button class="btn_md_primary" onclick="changePrivateInfo()"
								type="button">수정 완료</button>
						</div>
					</fieldset>
				</form>
				
				</dd>
				</dl>
				</div>
			

				<%@ include file="../layout/footer.jsp"%>