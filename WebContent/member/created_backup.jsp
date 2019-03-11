<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/lNamese.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>
<style type="text/css">
/* 페이지 타이틀 영역 */
.page_title_area {
	position: relative;
	background: #f3f3f3;
	border-bottom: 1px solid #d1d1d1;
}

.page_title_area:after {
	content: '';
	display: block;
	clear: both;
}

.page_title_area h1 {
	max-width: 450px;
	height: 50px;
	margin: 0 auto;
	padding: 13px 0 0 12px;
	font-size: 18px;
}

/* 레이아웃 */
table {
	width: 450px;
	margin: 0 auto;
}

td {
	height: 50px;
}

input, button, select, textarea {
	color: inherit;
	vertical-align: middle;
	-webkit-appearance: none;
	letter-spacing: -1px;
}

input:not([type=radio]){
width:100%;
height:50px;
	
padding-left: 5px;
border:1px solid #dadada;

}
input[type=radio] {
	display: none;
}

label {
	display: block;
	border: 1px solid #ccc;
	width: 100%;
	height: 100%;
	background-color: #ffffff;
	text-align: center;
}

select {
	width: 100%;
	height: 50px;
	padding-left: 5px;
	border: 1px solid #dadada;
}

</style>
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/member-join.css">

<script type="text/javascript" src="<%=cp%>/member/data/util.js"></script>
<script type="text/javascript">

	function sendIt(){
		
		var f= document.myForm;
		
		//아이디의 유효성 검사
		var regExp1= /^[a-zA-Z0-9]{4,12}$/;
		
		//비밀번호의 유효성 검사
		var regExp2= /^[a-zA-Z0-9]{6,16}$/;
		
		//생년월일 정규식
		var regExp3 = /^(19[0-9][0-9]|20\d{2})-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/;
		
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

		f.action="<%=cp%>/join/created_ok.do";
		f.submit();

	}
</script>

</head>
<body>
	<div class="page_title_area">
		<h1>아모레퍼시픽 통합 회원가입</h1>
	</div>
	<form name="myForm" >
		
	<table style="font-size: 10pt;" cellpadding="10" cellspacing="0">
		<tr>
			<td colspan="3" style="padding-bottom: 0px;"><font size="6">환영합니다.</font>
			</td>
		</tr>
		<tr>
			<td colspan="3" style="padding-top: 0px; height: 30px;">
			<font size="2">간단한 회원가입으로 포인트를 확인하세요.</font>
			</td>
		</tr>
		
			<tr>
				<td colspan="3"><input type="text" name="userId"
					placeholder="아이디(4-12자 영문 대소문자)" maxlength="20" /></td>
			</tr>

			<tr>
				<td colspan="3"><input type="password" name="userPwd"
					placeholder="비밀번호(6-16자 영문 대소문자, 숫자)" maxlength="20" /></td>
			</tr>
			<tr>
				<td colspan="3"><input type="password" name="userPwd1"
					placeholder="비밀번호 확인" maxlength="20" /></td>
			</tr>

			<tr>
				<td colspan="3"><input type="text" name="userName"
					placeholder="이름(실명으로 입력해주세요)" maxlength="50" /></td>
			</tr>


			<tr>
				<td><input type="text" name="birth"
					placeholder="생년월일 (ex.19980905)" maxlength="50" /></td>
				<td >
				<input type="radio" name="gender" value="F" checked />
				<label for="Female">여자</label>
				</td>
				<td>
				<input type="radio" name="gender" value="M">
				<label for="Male">남자</label>
				</td>
			</tr>


			<tr>
				<td><select title="통신사 선택" name="phoneCorp">
						<option value="">통신사 선택</option>
						<option value="SKT">SKT</option>
						<option value="KTF">KT</option>
						<option value="LGT">LG U+</option>
						<option value="SKM">SKT 알뜰폰</option>
						<option value="KTM">KT 알뜰폰</option>
						<option value="LGM">LG U+ 알뜰폰</option>
				</select></td>
				<td colspan="2"><input type="text" name="phone" maxlength="50"
					class="boxTF" /></td>
			</tr>
			<tr>
				<td colspan="3"><input type="button" value="가입완료"
					onclick="sendIt();" /></td>
			</tr>
	</table>
	</form>


</body>
</html>
























