<%@ page contentType="text/html; charset=UTF-8"%>
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