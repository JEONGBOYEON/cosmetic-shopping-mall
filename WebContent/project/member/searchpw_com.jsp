<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
	String cp=request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"href="<%=cp%>/css/member-join.css">
</head>
<body>
 <div class="ap_wrapper">
		
        <div id="ap_container" class="ap_container">
	<div class="page_title_area">
		<h1>아모레퍼시픽 뷰티포인트 통합 회원 비밀번호 찾기</h1>
	</div>

	<div class="ap_contents find_id_pwd">
		<h2 class="h_title d2"><b>비밀번호 찾기가 완료 되었습니다.</b></h2>
		<p class="text">조회된 고객님의 비밀번호입니다.</p>
		<div class="btn_lg_bordered w100p">${userPwd}</div>
		<div class="page_btns mgt15">
			<button type="button" onclick="location.replace('<%=cp %>/member/login.do')" class="btn_lg_primary w100p"
			style="background-color: #f54a7e;"><span>로그인 하러가기</span><i class="ico_arr_w"></i></button>
		</div>
		<dl class="dl_cont mgt30">
			<dt class="h_title d4"><b>비밀번호 발송</b></dt>
			<dd>
				<p class="text color_light_gray font_sm">고객님이 가입하실 때 입력하신 휴대번호로 비밀번호를 발송해
					드립니다.<br>
					아래 발송 요청 버튼을 선택해 주세요.</p>
			</dd>
		</dl>
		<div class="page_btns mgt30">
			<button type="button" onclick="send()" class="btn_lg_primary w100p"
			style="background-color: #f54a7e;" ><span>비밀번호 발송 요청</span><i class="ico_arr_w"></i></button>
		</div>
	</div>

</div>
    </div>

</body>
</html>


































