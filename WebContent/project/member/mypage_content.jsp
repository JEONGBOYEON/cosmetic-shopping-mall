<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../layout/top.jsp"  %>
<%@ include file="../layout/mypage.jsp"  %>

	<div class="page_title_area">
		<!-- breadcrumb 미노출 페이지는 감춤 -->

		<!-- // breadcrumb 미노출 페이지는 감춤 -->
		<div class="page_title">


			<h2 class="h_title page">마이 에뛰드</h2>

			<p class="text font_lg"></p>
		</div>
	</div>



	<!-- page contents -->
	<div class="ap_contents my_etude" id="ap_container">
		<div class="clear welcome_wrap">
			<div>

				<dl class="welcome hello">
					<!-- 등급별 클래스 pink_love, friends, hello, guest  -->
					<dt>

						<i class="ico_grade"></i>안녕하세요. <span>${sessionScope.customInfo.userName }</span> 님
					</dt>
					<dd>
						<p class="text font_xl">
							고객님의 회원등급은 <em>${sessionScope.customInfo.userGrade }</em> 입니다.
						</p>
						<a href="/kr/ko/my/page/info/changeUserInfo" class="link">개인정보
							수정</a> <a href="/kr/ko/my/page/myLevelList" class="link">내 예상등급
							확인</a>
					</dd>
				</dl>

				<div class="my_wallet_info">
					<div class="clear">
						<dl class="coupon" style="cursor: pointer;"
							onclick="location.href=contextPath +'/my/page/myCouponList'">
							<dt>쿠폰</dt>
							<dd>
								<b class="num">1</b> 장
							</dd>
						</dl>
						<dl class="pearl" style="cursor: pointer;"
							onclick="location.href=contextPath + '/my/page/pearl/present'">
							<dt>진주알</dt>
							<dd>
								<b class="num">0</b> 알
							</dd>
						</dl>
					</div>
					<div class="clear">
						<dl class="beauty" style="cursor: pointer;"
							onclick="location.href=contextPath + '/my/page/info/beautyPoint'">
							<dt>뷰티포인트</dt>
							<dd>
								<b class="num">${sessionScope.customInfo.point }</b> 원
							</dd>
						</dl>
						<dl class="deposit" style="cursor: pointer;"
							onclick="location.href=contextPath + '/my/page/myDepositList'">
							<dt>예치금</dt>
							<dd>
								<b class="num">0</b> 원
							</dd>
						</dl>
					</div>
				</div>
			</div>
			<div>


				<dl class="pinkmoney">
					<dt>
						<span>전가영</span> 님의 핑크머니입니다.
					</dt>
					<dd>
						<a href="/kr/ko/my/page/myPinkMoney" class="link_pinkmoney">내역보기</a>
					</dd>
				</dl>

				<dl class="order_process">
					<dt>
						<b>주문/배송조회</b> <small>(최근 3개월 기준)</small>
					</dt>
					<dd>
						<ul class="process">
							<li><a href="/kr/ko/my/page/onlineOrderShipping"
								class="null"><em class="num">0</em><span>주문접수</span></a></li>
							<li><a href="/kr/ko/my/page/onlineOrderShipping"
								class="null"><em class="num">0</em><span>결제완료</span></a></li>
							<li><a href="/kr/ko/my/page/onlineOrderShipping"
								class="null"><em class="num">0</em><span>배송준비중</span></a></li>
							<li><a href="/kr/ko/my/page/onlineOrderShipping"
								class="null"><em class="num">0</em><span>배송중</span></a></li>
							<li><a href="/kr/ko/my/page/onlineOrderShipping"
								class="null"><em class="num">0</em><span>배송완료</span></a></li>
						</ul>
						<div class="_btns clear">
							<a href="/kr/ko/my/page/returnExchange" class="btn_md_bordered">반품/교환
								(<strong class="num">0</strong>)
							</a> <a href="/kr/ko/my/page/myReviewList" class="btn_md_bordered">미작성
								구매후기 (<strong class="num">0</strong>)
							</a>
						</div>
						<a href="/kr/ko/my/page/onlineOrderShipping" class="more"
							title="주문/배송조회">더보기</a>
					</dd>
				</dl>
			</div>
		</div>


		<div class="my_color_finder">
			<h3 class="h_title sub">
				<strong>마이 컬러파인더</strong>
			</h3>
			<!-- 측정값이 있는 경우 -->

			<!-- end:  측정값이 있는 경우 -->
			<!-- 측정값이 없는 경우 -->

			<div class="panel notice">
				<i class="ico"></i>
				<p class="text font_lg">
					<span>피부 측정 데이터가 없습니다.<br>가까운 에뛰드 하우스 매장에서 무료로 피부톤 측정을
						받으세요~
					</span>
				</p>
				<div class="page_btns">
					<a
						href="/kr/ko/display/store_location?displayMenuId=store_location"
						class="btn_lg_big_neutral">가까운 매장 찾기</a>
				</div>
			</div>




		</div>
		<div class="my_banner">
			<!-- /* 20180803:App설치배너 수정*/ -->
			<a
				href="https://play.google.com/store/apps/details?id=com.etudehouse"
				target="_blank" class="android"><img
				alt="에뛰드 하우스 앱으로 더 많은 정보를 확인해 보세요~. 앱 설치하기"
				src="/kr/ko/pc/ko/images/my/ico_android.png" /></a> <a
				href="https://itunes.apple.com/kr/app/id538275329" target="_blank"
				class="ios"><img alt="에뛰드 하우스 앱으로 더 많은 정보를 확인해 보세요~. 앱 설치하기"
				src="/kr/ko/pc/ko/images/my/ico_ios.png" /></a>
		</div>
	</div>
	
	
	
<%@ include file="../layout/footer.jsp"  %>


































