<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
	String cp=request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>회원가입</title>
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/member-join.css">
<script type="text/javascript">
function sendIt(){
	
	var f= document.myForm;
	
	//아이디의 유효성 검사
	var regExp1= /^[A-Za-z0-9+]{4,12}$/;
	
	//비밀번호의 유효성 검사
	var regExp2= /^[a-zA-Z0-9]{6,16}$/;
	
	//생년월일 정규식
	var regExp3 = /^(19[0-9][0-9]|20\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$/;
	
	//핸드폰번호 정규식
	var regExp4 =/(01[016789])([1-9]{1}[0-9]{2,3})([0-9]{4})$/;

	//아이디 검사
	str=f.userId.value;
	str=str.trim();
	if(!str){
		alert("아이디를 입력하세요!");
		f.userId.focus();
		return;
	}
	
	if(!regExp1.test(str)){
		alert("형식에 맞춰 ID를 입력하세요.");
		str="";
		f.userId.focus();
		return;
	}
	
	f.userId.value=str;
	
	//패스워드 검사
	str=f.userPwd.value;
	str=str.trim();
	if(!str){
		alert("패스워드를 입력하세요!");
		f.userPwd.focus();
		return;
	}
	
	if(!regExp2.test(str)){
		alert("형식에 맞춰 PASSWORD를 입력하세요.");
		str="";
		f.userPwd.focus();
		return;
	}
	
	if(str!=f.userPwd1.value){

		alert("비밀번호가 동일하지 않습니다.");
		str="";
		f.userPwd1.focus();
		return;
	}
	
	f.userPwd.value=str;
	
	//이름 검사
	str=f.userName.value;
	str=str.trim();
	if(!str){
		alert("이름을 입력하세요!");
		f.userName.focus();
		return;
	}
	f.userName.value=str;
	
	//생일 검사
	str=f.birth.value;
	str=str.trim();
	if(!str){
		alert("생일을 입력하세요!");
		f.birth.focus();
		return;
	}
	if(!regExp3.test(str)){
		alert("형식에 맞춰 생년월일을 입력하세요(8자리)");
		str="";
		f.birth.focus();
		return;
	}
	f.birth.value=str;
	
	//핸드폰검사
	str=f.phoneCorp.value;
	str=str.trim();
	if(!str){
		alert("통신사를 선택해주세요.");
		f.phoneCorp.focus();
		return;
	}
	
	str=f.phone.value;
	str=str.trim();
	if(!str){
		alert("핸드폰번호를 입력하세요!");
		f.phone.focus();
		return;
	}
	if(!regExp4.test(str)){
		alert("형식에 맞춰 핸드폰번호를 입력하세요.");
		str="";
		f.phone.focus();
		return;
	}
	
	f.phone.value=str;

	f.action="<%=cp%>/member/created_ok.do";
	f.submit();

}
</script>
</head>
<body>

<div class="ap_wrapper">
		
        <div id="ap_container" class="ap_container">
	<div class="page_title_area">
		<h1>아모레퍼시픽 통합 회원가입</h1>
	</div>

	<div class="ap_contents member_join">
		<h2 class="h_title d1">환영합니다.</h2>
		<p class="text">간단한 회원가입으로 포인트를 확인하세요.</p>
		<form class="validate" name="myForm">
			<fieldset class="form">
				<legend class="sr_only">회원가입 정보입력</legend>
				<div class="input_wrap">
					<input type="text" title="아이디 입력" id="chcsNo" name="userId" value="" required="required" id_l="id_l" id_n="id_n" placeholder="아이디(4-12자 영문 대소문자, 숫자)" data-msg-required="아이디를 입력해 주세요. 아이디 입력란으로 이동 합니다.">
				</div>
				<div class="input_wrap">
						<input type="password" title="비밀번호 입력" id="userPwd" name="userPwd" placeholder="비밀번호(6-16자 영문 대소문자, 숫자)" required="required" data-msg-required="비밀번호를 입력해 주세요. 비밀번호 입력란으로 이동 합니다."
							   pass-word="pass-word">
				</div>
				<div class="input_wrap">
						<input type="password" title="비밀번호 확인 입력" placeholder="비밀번호 확인" id="userPwdEcChk" name="userPwd1"
							   required="required" data-msg-required="비밀번호를 다시 입력해 주세요.">
				</div>
				
	<div class="input_wrap">
		<input type="text" placeholder="이름(실명으로 입력해주세요)" title="이름 입력" id="custNm" name="userName" required="required"
			 data-msg-required="이름을 입력해 주세요. 이름 입력란으로 이동합니다." user-name="user-name"/>
  </div>

				<div class="input_group">
					
	<div class="input_wrap w60p">
		<input type="tel" pattern="[0-9]*" placeholder="생년월일 (ex.19980905)" title="생년월일 입력" id="athtDtbr" name="birth"
			   required="required" data-msg-required="생년월일을 입력해 주세요. 생년월일 입력란으로 이동합니다." psnDtbr="psnDtbr" maxlength="8">
	</div>

					<div class="gap"></div>
					
	<div>
		<div class="check_btn_set">
			<div><input type="radio" name="gender" value="F" id="female" checked><label for="female">여자</label></div>
			<div><input type="radio" name="gender" value="M" id="male"><label for="male">남자</label></div>
		</div>
	</div>

				</div>
				
				

	<div class="input_group">
		<div class="select_wrap">
              	<select title="통신사 선택" id="phoneCorp" name="phoneCorp" required="required" data-msg-required="통신사를 선택해 주세요.">
				<option value="">통신사 선택</option>
				<option value="SKT">SKT</option>
				<option value="KTF">KT</option>
				<option value="LGT">LG U+</option>
				<option value="SKM">SKT 알뜰폰</option>
				<option value="KTM">KT 알뜰폰</option>
				<option value="LGM">LG U+ 알뜰폰</option>
			</select>
		</div>
		<span class="gap"></span>
		<div class="input_wrap w60p no-check">
			<input type="tel" pattern="[0-9]*" placeholder="휴대폰 번호 ‘-’없이 입력" title="휴대폰 번호 입력" name="phone" required="required" data-msg-required="휴대폰번호를 입력해 주세요. 휴대폰번호 입력란으로 이동합니다."
				   mobile-number="mobile-number" data-msg-mobile-number="휴대전화 번호를 확인해 주세요. 휴대전화 번호 입력란으로 이동합니다." maxlength="11"/>
				<button type="button" id="certBtn" class="btn_sm_certification" style="z-index: 2;background: white;display: none">인증</button>
       			<button class="btn_sm_certification" type="button" id="reCertBtn" style="z-index: 2;background: white;display: none">재전송</button>
		</div>
    </div>
          
	<div class="input_wrap no-check" id="sms-form" style="display: none">
		<input type="number" pattern="[0-9]*" placeholder="인증번호" title="인증번호 6자리 입력" name="smsNum" required="required" data-msg-required="인증번호를 입력해 주세요. 인증번호 입력란으로 이동합니다." maxlength="6">
		<span class="count" name="timer">00:00</span>
		<button type="button" id="complete" style="z-index: 2;background: white;" class="btn_sm_certification">확인</button>
	</div>

				<input type="hidden" name="frclCd" value="K" required="required">
				<div class="form_btns">
					<a href="javascript:sendIt();" id="send" class="btn_lg_primary"><i class="ico_check_w"></i><span>가입완료</span></a>
				</div>
			</fieldset>
		</form>
		
		
	</div>

</div>
    </div>

</body>
</html>


































