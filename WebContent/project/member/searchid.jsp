<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>아이디 찾기</title>
<link rel="stylesheet" type="text/css"
	href="<%=cp%>/css/member-join.css">
<script type="text/javascript">

function sendIt(){
	
	var f= document.myForm;
	
	//생년월일 정규식
	var regExp3 = /^(19[0-9][0-9]|20\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$/;
	
	//핸드폰번호 정규식
	var regExp4 =/(01[016789])([1-9]{1}[0-9]{2,3})([0-9]{4})$/;
	

	//이름 검사
	str=f.userName.value;
	str=str.trim();
	if(!str){
		alert("이름을 입력하세요!");
		f.userName.focus();
		return;
	}
	f.userName.value=str;
	
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

  	f.action="<%=cp%>/member/searchid_ok.do";
	f.submit();

}
</script>
</head>
<body>
	<div class="ap_wrapper">

		<div id="ap_container" class="ap_container">
			<div class="page_title_area">
				<h1>아모레퍼시픽 통합 아이디 찾기</h1>
			</div>

			<div class="ap_contents find_id_pwd">
				<h2 class="h_title d1">아이디 찾기</h2>
				<p class="text">본인 인증으로 아이디를 찾으실 수 있습니다.</p>
				<form class="validate" name="myForm">
					<fieldset class="form">
						<legend class="sr_only">본인인증 정보입력</legend>

						<div class="input_wrap">
							<input type="text" title="이름 입력" name="userName"
								placeholder="이름(실명으로 입력해주세요)" required="required"
								>
						</div>

						<div class="input_group">

							<div class="input_wrap w60p">
								<input type="tel" pattern="[0-9]*"
									placeholder="생년월일 (ex.19980905)"
									name="birth" required="required"
									psnDtbr="psnDtbr" maxlength="8">
							</div>

							<div class="gap"></div>

							<div>
								<div class="check_btn_set">
									<div>
										<input type="radio" name="gender" value="F" id="female"
											checked><label for="female">여자</label>
									</div>
									<div>
										<input type="radio" name="gender" value="M" id="male"><label
											for="male">남자</label>
									</div>
								</div>
							</div>

						</div>



						<div class="input_group">
							<div class="select_wrap">
								<select title="통신사 선택" name="phoneCorp"
									required="required">
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
								<input type="tel" pattern="[0-9]*" placeholder="휴대폰 번호 ‘-’없이 입력"
									title="휴대폰 번호 입력" name="phone" required="required"
									data-msg-required="휴대폰번호를 입력해 주세요. 휴대폰번호 입력란으로 이동합니다."
									mobile-number="mobile-number"
									data-msg-mobile-number="휴대전화 번호를 확인해 주세요. 휴대전화 번호 입력란으로 이동합니다."
									maxlength="11" />
							</div>
						</div>

						<div class="form_btns">
								<button type="button" id="login" class="btn_lg_primary"
									onclick="sendIt();">아이디찾기</button>
						</div>

						<c:if test="${!empty message }">
							<script type="text/javascript">
							alert("회원정보가 조회되지 않습니다.");
							</script>
						</c:if>

					</fieldset>
				</form>
			</div>

		</div>
	</div>
</body>
</html>










































