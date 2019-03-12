<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../layout/top.jsp"  %>
<%@ include file="../layout/mypage.jsp"  %>

<div class="page_title_area">
		<!-- breadcrumb 미노출 페이지는 감춤 -->
	    
	    <!-- // breadcrumb 미노출 페이지는 감춤 -->
	    <div class="page_title">
	    	
	        
	        	<h2 class="h_title page">회원 탈퇴</h2>
	        
	        <p class="text font_lg"></p>
	    </div>
    </div>

            <!-- // page title -->
            
            <!-- page contents -->
            <div class="ap_contents">
                <div class="panel membership_withdrawal">
                    <div class="panel_cont complete">
                    	<div class="mgb30">
                    		<img alt="" src="<%=cp %>/images/my/img_member_disband.png">
                    	</div>
                        <h3 class="h_title">회원 탈퇴가 완료되었습니다.</h3>
                        <br>
                        <p class="text">그 동안 이용해 주셔서 감사합니다.<br>더욱더 좋은 서비스와 혜택으로 보답하겠습니다</p>
                        <div class="page_btns">
                            <button type="button" onclick="location.href='<%=cp%>/project/main.jsp'" class="btn_lg_primary">홈으로</button>
                        </div>
                    </div>
                </div>
            </div>
        	<!-- // page contents -->
        </div>
        
        
		
<!--  아티스트특 상담 
	<aside>
		
		<button type="button" class="btn_artist_talk"><img alt="아티스트특" src="/kr/ko/pc/ko/images/common/btn_artist_talk.png"></button>
		<div class="layer_popup_artist_talk">
			<div class="layer_wrap_artist_talk">
				<dl class="layer_artist_talk">
					<dt class="sr_only">아티스트톡</dt>
					<dd class="layer_cont_artist_talk">
						<div class="align_center"><img alt="artist talk" src="/kr/ko/pc/ko/images/cs/text_artist_talk.png"></div>
						<p class="text_quotes align_center"><b>에뛰드하우스 전문 아티스트가<br>메이크업에 대한 고민을 해결해드립니다.</b></p>
						<p class="text_center"><b>상담시간 : 평일 오전 9시 ~ 오후 6시</b> <br><span>(점심시간 제외 : 오후 12시 ~ 1시)</span></p>
						<hr class="div dotted m20">
						<div class="page_btns"><button type="button" class="btn_lg_primary btn_consultation">상담하기</button></div>
						<p class="text_notice"><i class="ico_notice"></i> 톡 상담이 많을 경우 Artist와의 연결이 지연될 수 있어요.</p>
						
						<div class="banner">
							<a href="#none"><img alt="12.15 ~ 12.27 애니쿠션 올데이 퍼팩트 50% SALE" ap:src="@{/images/dummy/img_banner_talk.jpg}"></a>
						</div>
						
					</dd>
				</dl>
				<button class="layer_close_artist_talk" type="button">레이어 닫기</button>
			</div>
		</div>
		
		<button type="button" class="btn_totop"><img alt="상단으로 이동" src="/kr/ko/pc/ko/images/common/btn_totop.png"></button>
	</aside>

 -->
<%@ include file="../layout/footer.jsp"  %>

































