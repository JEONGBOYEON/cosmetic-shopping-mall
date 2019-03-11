<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../project/layout/top.jsp"  %>

<div class="ap_contents product detail" >

	<!-- product 정보 -->
	<div class="prd_detail_default">	
		<div class="prd_detail_info">
			<div class="prd_detail_default">
			
				<div class="prd_img_wrap">
					<div class="slide goods_slide ix-slide-max-apply">
						<img style="width: 540px; height: 540px;" alt="${dto.originalName}" src="${imagePath }/${dto.saveFileName }">
					</div>
				</div>
			
				<div class="prd_info_wrap">
					<table style="width: 500px; font-size: 15pt;">
					<tr>
						<td>카테고리 ${dto.productCategory }</td>
					</tr>
					<tr>
						<td style="margin-top: 15px; padding-bottom: 10px; height: 80px;">
						<h3 style="font-size: 34px; font-weight: 300; margin-top: 4px; word-break: keep-all; ">${dto.productName} </h3>
						</td>
					</tr>
					<tr><td bgcolor="grey" height="1"></td></tr>
					<tr>
						<td style="font-size: 32px; height: 80px;"><span>${dto.price}원</span></td>
					</tr>
					<tr height="60">
						<td>
							<c:if test="${!empty optionList }">
								<select name="productCategory" style="width: 580px; height: 50px; font-size: 15pt; padding: 5px 5px; border: 1px solid #d9d9d9;">
									<option>옵션선택</option>
									<c:forEach var="option" items="${optionList }">
										<option <c:if test="${option==dto.productCategory}">selected='selected'</c:if>>${option }</option>
									</c:forEach>
								</select>
							</c:if>
						</td>
					</tr>
					<tr height="60">
						<td style="padding: 5px 0px 0px 0px">수량
						<span style="float: right;"><input type="number" style="padding: 5px 5px;border: 1px solid #d9d9d9; margin-top: 2px;"></span>
						</td>
					</tr>
					<tr height="50">
						<td style="padding: 5px 10px;border: 1px solid #d9d9d9; margin-top: 2px;">
						<span style="float: left; padding: 5px 0;">총 합계금액</span>
						<span style="float: right; padding: 5px 0;">${dto.price}원</span>
						</td>
					</tr>
					<tr height="50" >
						<td style="font-size: 12pt;">색상:${dto.color} 텍스쳐:${dto.texture} 추천계절 ${dto.season}</td>
					</tr>	
					<tr><td bgcolor="grey" height="2"></td></tr>
					</table>
					
						<div class="prd_etc_info">
							<div class="prd_etc_info_left">
								<dl>
									<dt>상태</dt>
									<dd>${dto.state}</dd>
								</dl>
								<dl>
									<dt>배송비</dt>
									<dd>최종 결제금액 15,000원 이상 주문시 무료배송</dd>
								</dl>
							</div>
							<div class="prd_etc_info_right"> 
								<dl> 
									<dt>뷰티포인트</dt>
									<dd>1% 적립</dd>
								</dl>
							</div>
						</div>
						<!-- 구매버튼,장바구니버튼 -->
						<div class="purchase_button_set">
							<span><button class="btn_lg_bordered emp btn_buy_now" type="button">바로구매</button></span>
							<span><button class="btn_lg_primary btn_basket" type="button">장바구니 담기</button></span>
						</div>
						
					</div> 

			</div>
		</div>
	</div>
<hr class="div m30">

<!-- product 상세정보 -->
	<div class="prd_detail_bottom">
		<!-- tab menu wrap -->
		<div class="ui_tab prd_detail_tap">
			<!-- tab menu -->
			<div class="tab_menu">
				<ul>
				<li class="on"><button type="button">상세정보</button></li>
				<li>
				<button type="button">리뷰/후기 ()</button>			
				</li>
				<li><button type="button">배송/교환/반품</button></li>
				</ul>
				
				<br/><br/><br/>
				
				<div class="prd_detail_wrap">
					<div class="contenteditor-root">
						<!-- 상세이미지 출력 -->
						<c:forEach var="dto" items="${detailImagelists}">
						<div class="contenteditor-image">
					      <a href="${imagePath }/${dto.saveFileName}"><img src="${imagePath }/${dto.saveFileName}" /></a>
					 	</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
</div><!-- ap_contents product detail -->	
<%@include file="../project/layout/footer.jsp"  %>

