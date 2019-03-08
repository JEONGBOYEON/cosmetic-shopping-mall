<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="./top.jsp"  %>

	<div class="ap_contents product detail">
		
		<!-- product 정보 -->
		<div class="prd_detail_default">	
			<div class="prd_detail_info">
			product 테이블 정보
				<div class="prd_img_wrap">
					<div class="slide goods_slide ix-slide-max-apply">
						<img alt="${dto.originalName}" src="<%=cp %>/productDetail/img/${dto.saveFileName}">
					</div>
				</div>
			
				<div class="prd_info_wrap">
					<table>
					<tr>
						<td>카테고리 ${dto.productCategory }</td>
					</tr>
					<tr>
						<td><h3 class="h_title">${dto.productName} </h3></td>
					</tr>
					<tr>
						<td class="ui_select product_option_select ＠design-selectbox-apply">옵션
							<select>
								<option value="${dto.productOption}">${dto.productOption}</option>
							</select>
						</td>
					</tr>
					<tr>
						<td class="price_area">가격 <span cl>${dto.price}원</span></td>
					</tr>
					<tr>
						<td>수량 <select name=""><option>0</option></select></td>
					</tr>
					<tr>
						<td>색상 ${dto.color}</td>
					</tr>
					<tr>
						<td>텍스쳐 ${dto.texture}</td>
					</tr>	
					<tr>
						<td>추천계절 ${dto.season}</td>
					</tr>		
					</table>
					
						<div class="prd_etc_info">
							<div class="prd_etc_info_left">
								<dl>
									<dt>상태</dt>
									<dd>판매중</dd>
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
				</div>
	
			</div>
		</div>
	</div>
	<hr class="div m30">
	<!-- product 상세내용 -->
	<div class="prd_detail_bottom">
		<!-- tab menu wrap -->
		<div class="ui_tab prd_detail_tap">
		<!-- tab menu -->
			<div class="tab_menu">
			</div>
		</div>
	</div>
	
	
<%@include file="./footer.jsp"  %>
