<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
	String cp=request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>

<body><noscript><iframe src='https://www.googletagmanager.com/ns.html?id=GTM-N2HHXNV' height='0' width='0' style='display: none; visibility: hidden'></iframe></noscript>
    
    
   
    
    <div id="accessibility"><a href="#ap_container">본문 바로가기</a></div>
    
    


<!-- Mobon Tracker v3.1 [공용] start -->
<script type="text/javascript">
<!--
function mobRf(){
var rf = new EN;
rf.setSSL(true);
rf.sendRf();
}
//-->
</script>
<script src="https://cdn.megadata.co.kr/js/enliple_min2.js" defer="defer" onload="mobRf()"></script>
<!-- Mobon Tracker v3.1 [공용] end -->


		
		<script type="text/javascript" src="https://wcs.naver.net/wcslog.js"></script>
<script type="text/javascript">
	if(!wcs_add) var wcs_add = {};
    wcs_add["wa"] = "s_cdef8784d02";

	if (!_nasa) var _nasa={};
	wcs.inflow("");

	wcs_do(_nasa);
	// wcs_do();
</script>
    </div>

<div class="modal_dimmed mdkrvcvid60b8krm"></div><div class="modal_popup layer_popup js_open modal_info mdkrvcvid60b8krm" style="display:block">
    <div class="layer_wrap layer_md modify_my_info layer_type2 transition_top" tabindex="0" style="display: block; top: 1428px;">
        <dl class="layer">
            <!-- handlebars@include:common/modal-info-contents.hbs -->

<dt class="sr_only"></dt>

    <dd class="layer_cont">
                <p class="text_center">        	</p><div class="layer_popup" style="display: block;">
                <div>
                    <dl class="layer">
                        <dt class="layer_title">
							<p class="title">회원탈퇴</p>
							<p class="s_title">
								아모레 뷰티포인트 서비스 및 에뛰드하우스 사이트를 이용하시며 불편한 사항이 있으셨나요?<br>
								뷰티포인트 및 에뛰드하우스를 떠나시는 사유를 남겨주시면 보다 나은 뷰티포인트,<br>
								에뛰드하우스가 되는 데 참고하겠습니다.
							</p>
						</dt>
                        <dd class="layer_cont">
							<fieldset class="form" name="deleteForm">
							
								<div class="pointbox_out member_out">
									<div>
										<p class="title"><strong>포인트 현황</strong></p>
										<dl>
											<dt>잔여 뷰티포인트</dt>
											<dd>0P</dd>
										</dl>
										<dl>
											<dt>잔여 진주알</dt>
											<dd>0알</dd>
										</dl>
										<dl>
											<dt>잔여 예치금 <button type="button" onclick="location.href=contextPath + '/my/page/myDepositManagementList'" class="btn_sm_form">출금하기</button></dt>
											<dd>0원</dd>
										</dl>
									</div>
									<div class="mgt20">
										<ul class="list_bullet_dot">
											<li>모든 뷰티포인트 멤버십 서비스 탈퇴 시 잔여 뷰티포인트 마일리지가 모두 소멸되며, 유료회원 (마트 VIP, 아리따움 유료 VIP, 에뛰드 핑크패스 멤버십, 헬로우 캠퍼스 멤버십)
												으로서의 혜택도 모두 사라집니다.</li>
											<li>회원탈퇴 후 30일간 재가입이 불가하며 동일 아이디는 사용할 수 없습니다.</li>
											<li>회원님의 잔여 포인트는 탈퇴 후 사용이 불가능하오니 신중하게 결정하시기 바랍니다.</li>
											<li>탈퇴 전, 현재 배송중인 상품 또는 뷰티포인트 교환신청 내역이 없는지 다시 한번 확인 부탁드립니다. 탈퇴 후에는 정보 복구가 불가합니다.</li>
										</ul>
									</div>
								</div>

								
								<!-- //에뛰드 회원 탈퇴 선택시 -->
								<div class="pointbox_out member_out" style="display:none;">
									<div>
										<p class="title"><strong>포인트 현황</strong></p>
										<dl>
											<dt>잔여 진주알</dt>
											<dd>0알</dd>
										</dl>
										<dl>
											<dt>잔여 예치금 <button type="button" onclick="location.href=contextPath + '/my/page/myDepositManagementList'" class="btn_sm_form">출금하기</button></dt>
											<dd>0원</dd>
										</dl>
									</div>
									<div class="member_out">
										<p class="title mgb10"><strong>사이트 탈퇴 시 잔여 진주알 모두 <em>삭제</em>됩니다.</strong></p>
										<ul class="list_bullet_dot">
											<li>에뛰드하우스 사이트를 탈퇴하시면, 에뛰드하우스 사이트 회원으로서의 모든 권리가 취소됨은 물론,<br>
												진주알 및 쿠폰 혜택 등도 삭제됩니다.<br>
												단, 아모레퍼시픽 뷰티포인트 통합회원으로서의 회원자격은 유지되며, 보유하신 뷰티포인트는<br>
												에뛰드하우스를 제외한 다른 브랜드에서 이용하실 수 있습니다.</li>
											<li>탈퇴 사유를 남겨 주시면, 보다 나은 에뛰드하우스를 만드는 데 참고하겠습니다.</li>
										</ul>
									</div>
								</div>
								
								<div class="member_out">
									<p class="title"><strong>탈퇴 사유를 선택해 주세요</strong></p>
									<div class="check_set mgt20">
										<span class="check_wrap">
											<input type="radio" name="reasonCode" value="C01" id="cause01" checked="">
											<label for="cause01">
												<span>개인정보 유출이 우려되서</span>
											</label>
										</span>
										<span class="check_wrap">
											<input type="radio" name="reasonCode" value="C02" id="cause02">
											<label for="cause02">
												<span>아모레퍼시픽 제품을 더 이상 사용하지 않아서</span>
											</label>
										</span>		
										<span class="check_wrap">
											<input type="radio" name="reasonCode" value="C03" id="cause03">
											<label for="cause03">
												<span>광고 메일 및 문자가 귀찮아서</span>
											</label>
										</span>	
										<span class="check_wrap">
											<input type="radio" name="reasonCode" value="C04" id="cause04">
											<label for="cause04">
												<span>방문했던 매장의 서비스에 불만족해서</span>
											</label>
											
											<div class="cause_box" id="cause_box04" style="display:none;">
												<strong>이유를 선택해주세요. (복수 응답 가능)</strong>
												<div class="check_wrap">
													<input type="checkbox" id="cause04_01"><label for="cause04_01">매장의 청결상태</label>
												</div>
												<div class="check_wrap">
													<input type="checkbox" id="cause04_02"><label for="cause04_02">재고가 없어서</label>
												</div>	
												<div class="check_wrap">
													<input type="checkbox" id="cause04_03"><label for="cause04_03">판매한 제품의 상태</label>
												</div>	
												<div class="check_wrap">
													<input type="checkbox" id="cause04_04"><label for="cause04_04">판매사원의 태도</label>
												</div>	
											</div>
										</span>
											
										<span class="check_wrap">
											<input type="radio" name="reasonCode" value="C05" id="cause05">
											<label for="cause05">
												<span>뷰티포인트 서비스를 이용하지 않아서</span>
											</label>
											<div class="cause_box" style="display:none;">
												<strong>이유를 선택해주세요. (복수 응답 가능)</strong>
												<div class="check_wrap">
													<input type="checkbox" id="cause05_01"><label for="cause05_01">기타</label>
												</div>
												<div class="check_wrap">
													<input type="checkbox" id="cause05_02"><label for="cause05_02">적립률이 낮아서</label>
												</div>	
												<div class="check_wrap">
													<input type="checkbox" id="cause05_03"><label for="cause05_03">혜택이 많지 않아서</label>
												</div>	
											</div>
										</span>

										<span class="check_wrap">
											<input type="radio" name="reasonCode" value="C07" id="cause06">
											<label for="cause06">
												<span>기타 
													<span class="input_wrap"><input name="closedAcDetailReason" type="text"></span>
												</span>
											</label>
										</span>
									</div>
									
									<div class="mgt30">
										<small class="mgb10">그 외 남기고 싶은 말씀</small>
										<div class="input_wrap">
											<input type="text">
										</div>
									</div>
									
									<div class="out_agree_box">
										<span class="check_wrap">
											<input type="checkbox" name="closeAgree" id="agree">
											<label for="agree"><strong>위 유의사항을 모두 확인하였으며, 에뛰드하우스 사이트 탈퇴에 동의합니다.</strong></label>
										</span>
									</div>
									
									<div class="password_box">
										<strong>비밀번호 확인</strong>
										<span class="input_wrap">
											<input name="closePassword" type="password">
										</span>
									</div>
								</div>
								<div class="form_btns mgb10">
									<button class="btn_md_primary" id="closeMember" type="button">회원탈퇴</button>
								</div>

							</fieldset>

						</dd>
					</dl>
					<button class="layer_close" type="button">레이어 닫기</button>
				</div>
			</div>
<p></p>

    </dd>

<!-- //handlebars@include:common/modal-info-contents.hbs -->
        </dl>
        <button class="layer_close" type="button">레이어 닫기</button>
    </div>
</div></body>
</body>
</html>


































