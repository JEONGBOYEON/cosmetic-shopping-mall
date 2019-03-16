<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="./layout/top.jsp"  %>



<div id="ap_container" class="ap_container">

	<!-- 제목 -->
	<div class="page_title_area">
		<div class="page_title">
			<h2 class="h_title page">쿠폰/혜택</h2>
			<p class="text font_lg"></p>
		</div>
	</div>


	<!-- 내용 -->
	<div class="ap_contents">
	
		<div class="ui_tab">
			<div class="tab_menu equally">
				<ul>
					<li><a href="<%=cp %>/coupon/couponAllList.do">등급별 고객 혜택 쿠폰</a></li>
					<li class="on"><a href="">나만의 혜택</a></li>
				</ul>
			</div>
		</div>
		

		<div class="tab-cont" >
		
			<c:choose>
				<c:when test="${empty sessionScope.customInfo.userId }">
					<div class="coupon-section-join">
						<p class="coupon-section-join__desc">로그인 하여 고객 등급과 혜택을 확인하세요.</p>
						<a href="<%=cp %>/member/login.do" class="coupon-section-join__btn">로그인</a>
					</div>
				</c:when>
				<c:otherwise>
					<h3 class="coupon-section__title">나만의 쿠폰</h3>
					<div align="center">
					<ul class="coupon-section_list clear">
					<!-- 쿠폰 하나씩 출력 -->
						<c:forEach var="dto" items="${lists }">
							<c:if test="${sessionScope.customInfo.userGrade eq dto.couponGrade}">
							<li class="coupon-section_list__item" style="width: 550px;">
								<div class="coupon-section-coupon clear">
									<div class="coupon-section-coupon__desc-area">
										<span class="coupon-section-coupon__flag">온라인 전용</span>
										
										<div class="coupon-section-coupon__unit-block">
											<span class="coupon-section-coupon__unit coupon-section-coupon__unit--v2 coupon-section-coupon__unit--point"  style="font-size: 30pt;">${dto.discount }</span>
											<span class="coupon-section-coupon__unit coupon-section-coupon__unit--v3"  style="font-size: 30pt;">원 할인 </span>
										</div>
										
										<div class="coupon-section-coupon__guide-block" style="width: 180px;" >
											<span class="coupon-section-coupon__guide" style="font-size: 11pt;">[${dto.couponGrade }] ${dto.couponScore }원 이상 구매시 ${dto.discount }원 할인쿠폰</span> 
											<span class="coupon-section-coupon__subtext" style="font-size: 11pt;">${dto.couponScore }원 이상 구매 시</span>
										</div>
										
									</div>
									
									<form action="<%=cp %>/coupon/couponIssue_ok.do" class="coupon-section-coupon__btn-area" method="post">
										<input type="hidden" name="userId" value="${sessionScope.customInfo.userId}"/>
										<input type="hidden" name="couponKey" value="${dto.couponKey }"/>
										<input type="hidden" name="userGrade" value="${sessionScope.customInfo.userGrade}"/>
										<input type="hidden" name="couponGrade" value="${dto.couponGrade }"/>
										<input type="submit" value="" style="width: 90px; height: 220px; border: none; background-color: rgba( 255, 255, 255, 0 );"></input>
									</form>
									
								</div>
							</li>
							</c:if>
						</c:forEach>
					</ul>
					</div>
				</c:otherwise>
			</c:choose>



			<!-- 유의사항 -->
			<dl class="coupon-section-dot-list">
				<dt class="coupon-section-dot-list__title">유의사항</dt>
				<dd class="coupon-section-dot-list__item">해당 쿠폰은 온라인몰 전용쿠폰입니다.</dd>
				<dd class="coupon-section-dot-list__item">로그인 후 쿠폰 다운로드 및 사용 가능합니다.</dd>
				<dd class="coupon-section-dot-list__item">쿠폰 유효기간은 마이페이지 쿠폰함에서 확인해 주세요.</dd>
				<dd class="coupon-section-dot-list__item">쿠폰은 타 프로모션 및 쿠폰과 중복사용이 불가합니다.(1주문당 1쿠폰 사용 가능)</dd>
				<dd class="coupon-section-dot-list__item">각 쿠폰별로 1회만 다운로드 가능합니다.</dd>
				<dd class="coupon-section-dot-list__item">다운로드 쿠폰은 당사 사정에 따라 사전 고지 없이 변경/종료 될 수 있습니다.</dd>
				<dd class="coupon-section-dot-list__item">부정한 방법으로 쿠폰을 사용 및 취득한 경우, 해당 주문 건은 사전 고지 없이 취소됩니다.</dd>
			</dl>
			
			
		</div>
	</div>
</div>

<%@include file="./layout/footer.jsp"  %>
