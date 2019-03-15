<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../project/layout/top.jsp"  %>

<style>

ul.tabs{
  margin: 0px;
  padding: 0px;
  list-style: none;
}
ul.tabs li{
  display: inline-block;
  cursor: pointer;
}

.tab-content{
  display: none;
  padding: 15px;
}
 
.tab-content.current{
  display: inherit;
}

</style>

<script type="text/javascript">

	function thousandSeparatorCommas(number){ 		
		//문자변환
		var string = "" + number;
		//±기호와 소수점, 숫자들만 남기고 전부 지우기.
		string = string.replace( /[^-+\.\d]/g, "" )   
		//정규식 
		var regExp = /^([-+]?\d+)(\d{3})(\.\d+)?/;  
		//쉼표 삽입	
		while ( regExp.test( string ) ) string = string.replace( regExp, "$1" + "," + "$2" + "$3" );  
		return string; 
	} 

	function totSet(count){

		var f = document.detailForm;	
		var totalCount = count * ${dto.price};
		
		//천단위 콤마 삽입
		var totalCount1 = thousandSeparatorCommas(totalCount);
		document.getElementById("totalPrice").value = totalCount1;

	}

	function addCartItem(){
		
		f = document.detailForm;
		//장바구니 추가
		
		str = f.productOption.value;
		str = str.trim();
		//옵션이 없는 단일상품이 아닌 경우
		if(str=="single"){
			
			if(!str){
				alert("\n productOption을 선택하세요.");//공백제거후 내용이 없으면
				f.productOption.focus();
				return;
			}
			f.productOption.value = str;

		}
		
		str = f.amount.value;
		str = str.trim();
		if(str==0){
			alert("\n 수량을 선택하세요.");//공백제거후 내용이 없으면
			f.amount.focus();
			return;
		}
		f.amount.value = str;
		
		alert("고객님! 해당 상품이 장바구니에 담겼습니다!");
	
		f.action = "<%=cp %>/cart/cartAdd_ok.do";
		f.submit();
	}
	
	$(document).ready(function(){
		   
		  $('ul.tabs li').click(function(){
		    var tab_id = $(this).attr('data-tab');
		 
		    $('ul.tabs li').removeClass('on current');
		    $('.tab-content').removeClass('on current');
		 
		    $(this).addClass('on current');
		    $("#"+tab_id).addClass('on current');
		  })
		 
		})

	
</script>
<div class="ap_contents product detail" >
	<form method="post" name="detailForm">
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
							<td><input type="hidden" name="productId" value="${dto.productId}"></td>
						</tr>
						<tr>
							<td>카테고리 ${dto.productCategory }</td>
						</tr>
						<tr>
							<td style="margin-top: 15px; padding-bottom: 10px; height: 80px;">
							<input type="hidden" value="${dto.productName}" name="productName"> 
							<h3 style="font-size: 34px; color:#333; font-weight: 300; margin-top: 4px; word-break: keep-all; ">${dto.productName} </h3>
							</td>
						</tr>
						<tr><td bgcolor="grey" height="1"></td></tr>
						<tr>
							<td style="font-size: 32px; height: 80px;">
							<span>
							<input type="hidden" value="${dto.price}" name="price" style="color:#333; ">
							<fmt:formatNumber value="${dto.price}" groupingUsed="true"/>원
							</span>
							</td>
						</tr>
						
						<!-- 상품옵션선택 -->
						<tr height="60">
						<c:choose>
							<c:when test="${!empty dto.productOption }">
								<td>
									<c:if test="${!empty optionList }">
										<select name="productOption" style="width: 580px; height: 50px; font-size: 15pt; padding: 2px 2px; border: 1px solid #d9d9d9;">
											<option>옵션선택</option>
											<c:forEach var="option" items="${optionList }">
												<option <c:if test="${option==dto.productOption}">selected='selected'</c:if>>${option }</option>
											</c:forEach>
										</select>
									</c:if>
								</td>
							</c:when>
							
							<c:when test="${empty dto.productOption }">
								<td>
									<input type="hidden" name="productOption" value="single">
								</td>
							</c:when>
						</c:choose>
						</tr>
						
						<!-- 수량선택 -->
						<tr height="60">
							<td style="padding: 5px 0px 0px 0px">
							수량
							<span style="float: right;">
							<select name="amount" style="width: 200px; height: 30px; font-size: 14pt; padding: 2px 2px; border: 1px solid #d9d9d9;">
								<option value="0">수량선택</option>
								<c:forEach var="cnt"  begin="1" end="30" step="1">
									<option onclick="totSet(${cnt});"value="${cnt}">${cnt}</option>
								</c:forEach>
							</select>
							</span>
							</td>
						</tr>
						
						<!-- 합계금액 -->
						<tr height="50">
							<td style="padding: 5px 10px;border: 1px solid #d9d9d9; margin-top: 2px;">
							<span style="float: left; padding: 5px 0;">총 합계금액</span>
							<div style="position:relative; padding-left:200px; float: right; padding: 5px 0;">
								<input type="text" readonly="readonly" id="totalPrice" size="10" value="" style="border: 0;">
							</div>
								
							</td>
						</tr>
						<tr height="50" >
							<td style="font-size: 12pt;">색상:${dto.color} 텍스쳐:${dto.texture} 추천계절 ${dto.season}</td>
						</tr>	
						<tr><td bgcolor="grey" height="2"></td></tr>
						<tr>
							<td>
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
							</td>
						</tr>
						<tr>
							<td>
							<!-- 구매버튼,장바구니버튼 -->
							<div class="purchase_button_set">
								<span><button class="btn_lg_bordered emp btn_buy_now" type="button" >바로구매</button></span>
								<span><button class="btn_lg_primary btn_basket" type="button" onclick="addCartItem();">장바구니 담기</button></span>
							</div>
							</td>
						</tr>						
						</table>

							
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
				<ul class="tabs">
					<li class="on current" data-tab="tab-1"><button type="button">상세정보</button></li>
					<li class="" data-tab="tab-2">
						<button type="button">리뷰/후기 (${dataCount_yes })</button>
					</li>
					<li class="" data-tab="tab-3"><button type="button">배송/교환/반품</button></li>
				</ul>

			</div>
		</div>

		<br /> <br /> <br />
		<div id="tab-1" class="tab-content current">
			<div class="prd_detail_wrap">
				<div class="contenteditor-root">
					<!-- 상세이미지 출력 -->
					<c:forEach var="dto" items="${detailImagelists}">
						<div class="contenteditor-image">
							<a href="${imagePath }/${dto.saveFileName}"><img
								src="${imagePath }/${dto.saveFileName}" /></a>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>



		<div id="tab-2" class="tab-content">
			<c:if test="${dataCount_yes==0 }">
				<div class="prd_detail_wrap">
					<div class="contenteditor-root">작성된 리뷰가 없습니다.</div>
				</div>
			</c:if>
			<c:if test="${dataCount_yes!=0 }">
				<div class="prd_detail_wrap">
					<div class="contenteditor-root">
						<div class="review_list">
							<table class="review_summary" style="margin-bottom: 50px;">
								<tr>
									<td style="width: 400px; text-align: center;">전체상품평<br />
										<span class="ui_rating"> <c:forEach var="i" begin="1"
												end="${avgReviewRate }" step="1">
												<img alt="" src="<%=cp%>/project/image/review_heart_on.PNG"
													height="25px;">
											</c:forEach> <c:forEach var="j" begin="${avgReviewRate+1 }" end="5"
												step="1">
												<img alt="" src="<%=cp%>/project/image/review_heart_off.PNG"
													height="25px;">
											</c:forEach>
									</span><br /> <small>(${dataCount_yes })</small>
									</td>
									<td>
										<ul class="rating_list">
											<c:forEach var="heart" begin="1" end="5" step="1">
												<li><span> <c:forEach var="on" begin="${heart}"
															end="5" step="1">
															<img alt=""
																src="<%=cp%>/project/image/review_heart_on.PNG"
																height="25px;">
														</c:forEach> <c:forEach var="on" begin="1" end="${heart-1}" step="1">
															<img alt=""
																src="<%=cp%>/project/image/review_heart_off.PNG"
																height="25px;">
														</c:forEach> <small>&nbsp;&nbsp;&nbsp;&nbsp;(${rate[heart-1] })</small>
												</span> <span class="graph" style="margin-left: 20px;"> <span
														style="width: ${rate[heart-1]/dataCount_yes * 100}%"></span>
												</span> <span class="num"><small>&nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatNumber
																value="${(rate[heart-1])/(dataCount_yes) }"
																type="percent" />
													</small></span></li>
											</c:forEach>
										</ul>

									</td>
								</tr>

							</table>

							<table
								class="ui_table_striped data_table thead_colored align_center @table-striped-apply"
								id="shpiTable">

								<colgroup>
									<col>
									<col>
								</colgroup>

								<tbody id="paging">
									<c:forEach var="dto" items="${lists }">
										<tr>
											<td
												style="text-align: left; width: 150px; vertical-align: top; padding-left: 50px;">
												<span class="ui_rating"> <c:forEach var="i" begin="1"
														end="${dto.rate }" step="1">
														<img alt=""
															src="<%=cp%>/project/image/review_heart_on.PNG"
															height="15px;">
													</c:forEach> <c:forEach var="j" begin="${dto.rate+1 }" end="5" step="1">
														<img alt=""
															src="<%=cp%>/project/image/review_heart_off.PNG"
															height="15px;">
													</c:forEach>
											</span> <span class="user_id">${dto.userId }</span> <small>${dto.reviewDate }</small>
											</td>

											<td style="text-align: left; width: 600px;"><small
												class="opt">옵션: #${dto.productOption }</small> <span
												class="flag">구매자 후기</span> <span class="title">${dto.subject }</span><br />
												<span class="text reduce">${dto.content }</span><br /> <c:if
													test="${!empty dto.savefileName }">
													<a href="${imagePath_review }/${dto.savefileName}"> <img
														alt="" src="${imagePath_review }/${dto.savefileName}"
														width="70" height="70">
													</a>
												</c:if></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div align="center">
					<font style="font-size: 20px">${pageIndexList}</font>
				</div>
			</c:if>
		</div>
		
						
		<br /> <br /> <br />
		<div id="tab-3" class="tab-content">
			<div class="prd_detail_wrap">
				<div class="contenteditor-root">
					<div class="contenteditor-image">
						<img src="<%=cp%>/project/image/deliveryImage1.PNG" /><br>
						<img src="<%=cp%>/project/image/deliveryImage2.PNG" />
					</div>
				</div>
			</div>
		</div>
		
		
	</div>
</form>
</div>


<!-- ap_contents product detail -->
<%@include file="../project/layout/footer.jsp"%>