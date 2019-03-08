<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, target-densitydpi=medium-dpi">

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



<style type="text/css">
.
#fonts-loaded body { /* 웹 폰트 다운로드 전에는 화면을 보여주지 않음 */
	display: none;
}

.wf-notosanskrregular-n4-active body {
	/* 웹 폰트 사용이 가능하면 화면을 보여주고 웹 폰트 속성 적용 */
	display: block;
	font-family: 'Noto Sans Korean', 'NotoSansKR-Regular', sans-serif;
}
</style>


<script type="text/javascript" src="https://www.youtube.com/iframe_api"></script>


</head>
<body>


	<div class="ap_wrapper">
	
		<header id="header" class="ap_header">

		<div class="inner_wrap">
			<ul class="header_menu">
				<li><a href="javascript:;" onclick="login()" class="log">로그인</a></li>
				<li><a href="/kr/ko/customer/signup" class="join">회원가입</a></li>
				<li><a href="/kr/ko/my/page/onlineOrderShipping" class="order">주문/배송조회
					<span class="num" style="display: none;"></span>
					</a>
				</li>
				<li><a href="/kr/ko/cart/cartList" class="cart">장바구니
					<span class="num" style="display: none;"></span>
					</a></li>
				<li><a href="#none" class="history">히스토리</a></li>
			</ul>
			<h1 class="logo">
				<a href="../project/main.jsp">
					<img alt="ETUDEHOUSE" src="../project/image/img_logo.png"/>
				</a>
			</h1>
		</div>


		<div class="gnb_area">
			<div class="inner_wrap">
				<button type="button" class="btn_category">카테고리</button>
				<ul class="gnb">
					<li><a href="<%=cp %>/product/topTest.do">신상품</a></li>
					<li><a href="">베스트</a></li>
					<li><a href="">이벤트</a></li>
					<li><a href="">쿠폰/혜택</a></li>
					<li><a href="">채널에뛰드</a></li>
					<li><a href="">LOOKS</a></li>
					<li><a href="">브랜드</a></li>
				</ul>
			</div>
		</div>

		</header>
