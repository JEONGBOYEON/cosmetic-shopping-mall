<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>마이 에뛰드</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, target-densitydpi=medium-dpi">
<link rel="shortcut icon" href="/kr/ko/favicon.ico">

<link rel="stylesheet" type="text/css" href="<%=cp %>/css/font.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/common.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/ui.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/board.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/layout.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/layer.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/member.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/cs.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/mypage.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/search.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/product.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/cart.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/brand.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/event.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/beautylife.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/policy.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/modify-my-info.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/coupon.css">

</head>
<body>

	<div id="ap_container" class="ap_container">
		<!-- mypage sitemap -->
		<div class="mypage_map">
			<ul style="height: 222px;">
				<li><span>나의 주문 관리</span> <!-- 메뉴 선택시 selected 클래스 -->
					<ul>
						<li><a href="/kr/ko/my/page/onlineOrderShipping">주문/배송조회</a></li>
						<!-- 메뉴 선택시 selected 클래스 -->
						<li><a href="/kr/ko/my/page/returnExchange">반품/교환</a></li>
						<li><a href="/kr/ko/my/page/cashReceipts">증빙서류발급</a></li>
					</ul></li>
				<li><span>나의 혜택 관리</span>
					<ul>
						<li><a href="/kr/ko/my/page/myCouponList">나의 쿠폰</a></li>
						<li><a href="/kr/ko/my/page/myLevelList">나의 회원 등급</a></li>
					</ul></li>
				<li><span>나의 포인트 관리</span>
					<ul>
						<li><a href="/kr/ko/my/page/info/beautyPoint">뷰티포인트</a></li>
						<li><a href="/kr/ko/my/page/pearl/present">진주알</a></li>
					</ul></li>
				<li><span>나의 지갑 관리</span>
					<ul>
						<li><a href="/kr/ko/my/page/myDepositList">예치금 관리</a></li>
						<li><a href="#non" onclick="wpayClickEvent();">원터치 간편결제
								카드관리</a></li>
						<li><a href="/kr/ko/my/page/myPinkMoney">PINK MONEY 관리</a></li>
					</ul></li>
				<li><span>나의 활동 관리</span>
					<ul>
						<li><a href="/kr/ko/my/page/myEventList">이벤트 참여 현황</a></li>
						<li><a href="/kr/ko/my/page/myReviewList">나의 구매 후기</a></li>
						<li><a href="/kr/ko/my/page/myInquiryList">나의 1:1 문의</a></li>
					</ul></li>
				<li><span>나의 정보 관리</span>
					<ul>
						<li><a href="/kr/ko/my/page/info/changeUserInfo">개인정보 수정</a></li>
						<li><a href="/kr/ko/my/page/info/snsLink">SNS 계정 연동 관리</a></li>
						<li><a href="/kr/ko/my/page/ship/address">배송지 관리</a></li>
						<li><a href="/kr/ko/my/page/store/list">단골매장 관리</a></li>
						<li><a href="/kr/ko/my/page/myShop">MY SHOP 관리</a></li>
						<li><a href="javascript:;" onclick="leaver()">회원 탈퇴</a></li>
					</ul></li>
			</ul>
		</div>
</body>

</html>