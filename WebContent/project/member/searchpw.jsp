<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp=request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>비밀번호 찾기</title>
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/common2.css">
<link rel="stylesheet" href="<%=cp %>/css/EDK.css">
<script type="text/javascript">

function sendIt(){
	
	var f= document.myForm;
	
	//생년월일 정규식
	var regExp3 = /^(19[0-9][0-9]|20\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$/;
	
	//핸드폰번호 정규식
	var regExp4 =/(01[016789])([1-9]{1}[0-9]{2,3})([0-9]{4})$/;
	
	//아이디 검사
	str=f.userId.value;
	str=str.trim();
	if(!str){
		alert("아이디을 입력하세요!");
		f.userId.focus();
		return;
	}
	f.userId.value=str;

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

  	f.action="<%=cp%>/member/searchpw_ok.do";
	f.submit();

}
</script>
</head>
<body>
<body class="web">
	<div class="wrap">
		<form id="frm" name="myForm">
			<div class="header">
				<h1>
					<span>아모레퍼시픽 </span>비밀번호 찾기
				</h1>
			</div>

			<div class="content find-content">
				<h2 id="titleH1">
					<strong>비밀번호 찾기</strong>본인 인증으로 비밀번호를 찾으실 수 있습니다.
				</h2>
				<div class="join-table">
					<div class="form-table">
						<div class="ipt">
							<input type="text" name="userId" class="place-h"
								placeholder="아이디를 입력해주세요" />
						</div>
					</div>
					<div class="form-table">
						<div class="ipt">
							<input type="text" id="userName" name="userName" class="place-h"
								placeholder="이름(실명으로 입력해주세요)" />
						</div>
					</div>
					<div class="form-table">
						<div class="radio-wrap multi-column">
							<div class="ipt flt-l">
								<input type="number" name="birth" class="place-h"
									pattern="[0-9]*"  maxlength="8"
									placeholder="생년월일(ex.19980905)" />
							</div>
							<div class="radio-area flt-r">
								<span class="form-tit hide">성별</span>
								<ul class="length2">
									<li><input type="radio" name="gender" value="M" id="male"
										class="hide"><label for="male">남자</label></li>
									<li><input type="radio" name="gender" value="F"
										id="female" class="hide" checked="checked"><label
										for="female">여자</label></li>
								</ul>
							</div>
						</div>
					</div>

					<div class="form-table">
						<div class="multi-column tbl-btn">
							<div class="select flt-l">
								<select id="telecom" name="phoneCorp" title="통신사">
									<option value="">통신사 선택</option>
									<option value="SKT">SKT</option>
									<option value="KTF">KT</option>
									<option value="LGT">LG U+</option>
									<option value="SKM">SKT 알뜰폰</option>
									<option value="KTM">KT 알뜰폰</option>
									<option value="LGM">LG U+ 알뜰폰</option>
								</select>
							</div>
							<div class="input-wrap flt-r">
								<input type="tel" id="user-tel" name="phone"
									maxlength="11" value="" class="place-h" placeholder="핸드폰번호" />

							</div>

						</div>
					</div>

				</div>

				<div class="btn-area">
					<button type="button" id="login" class="btn-type1 confirm on"onclick="sendIt();">비밀번호 찾기</button>

				</div>

				<c:if test="${!empty message }">
					<script type="text/javascript">
							alert("회원정보가 조회되지 않습니다.");
							</script>
				</c:if>
		</form>


	</div>




</body>
</html>


































