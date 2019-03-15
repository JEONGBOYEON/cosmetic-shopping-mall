<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../layout/top.jsp"  %>

<script src="http://code.jquery.com/jquery-2.1.0.min.js"></script>

<script>
	function showOrder(order) {

		var content = document.getElementById(order);

		if(content.style.display == "none"){
			content.style.display = "block";
		}else{
			content.style.display = "none";
		}
	}
	
	function sendOrderList() {
		    
		var f = document.orderForm;
		
		var str = f.purchaserEmailAddress.value;
		str = str.trim();
		if(!str) {
			alert("이메일을 입력해주세요.");
			f.purchaserEmailAddress.focus();
			return;
		}
		
		f.action = "<%=cp%>/order/complete.do";
		f.submit();
		
	}
	
</script>


<!-- page title -->
<div class="page_title">
	<h2 class="h_title page">결제하기</h2>
	<p class="text font_lg"></p>
</div>
<!-- // page title -->

<!-- page contents -->
<div class="ap_contents cart">

	
	<div class="ui_step">
		<ul>
			<!--<li th:class="current"><i class="ico"></i><span class="num">1</span>장바구니<span class="sr_only">선택됨</span></li>-->
			<li><i class="ico"></i><span class="num">1</span>장바구니</li>
			<li class="current"><i class="ico"></i><span class="num">2</span>결제하기</li>
			<li><i class="ico"></i><span class="num">3</span>주문완료</li>
		</ul>
	</div>
	
	<form class="order-validate" id="order-recept-info" name="orderForm" method="post">
	<div class="ui_accordion cart_list ＠accordion-apply">		
		<dl>
			<dt>
				<span>주문 상품 확인&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(<font color="#f54a7e" style="font-weight: bolder;">${totalAmount }개</font>)</span>			
				<button type="button" onclick="showOrder('1')" ><span class="sr_only">닫기</span>
					<img alt="" src="<%=cp%>/project/image/orderButton.PNG" height="20px;">
				</button>
			</dt>
			<dd class="on" id = "1" style="display: none">
				<div class="cont">
					<span style="font-size: 25px; font-weight: bolder; color: black;">쇼핑상품(<font color="#f54a7e" style="font-weight: bolder;">${totalOrderCount }개</font>)</span>
					<div style="border-bottom: 1px solid #cacaca; margin-top: 20px;"></div>
					
					<c:forEach var="orderListDto" items="${orderList }">
					<table style="width: 1118px; margin: 20px 0px 0px 0px;" class="panel_cont">
						<tr>
							<td style="padding: 20px 0px;">
								<img alt="" src="${imagePath }/${orderListDto.saveFileName}" height="150px;">
								<b style="color: black; font-size: 20px;">${orderListDto.productName}</b></td>
							<td style="padding: 20px 0px;" align="right">
								<span style="font-weight: bolder; color: black; font-size: 18px;">
									${orderListDto.amount }개 &nbsp;&nbsp; <fmt:formatNumber value="${orderListDto.amount * orderListDto.price }" type="number"/>원
								</span>
							</td>
						</tr>						
					</table>
					
					<table style="width: 1118px; margin: 20px 0px 50px 0px; background-color: #f5f5f5" class="panel_cont">
						<tr>
							<td style="padding: 20px 40px;">
								<img alt="" src="${imagePath }/${orderListDto.saveFileName}" height="50px;">
								<b style="color: black">#${orderListDto.productOption}</b></td>
							<td style="padding: 20px 40px;" align="right">
								<span>${orderListDto.amount }개</span>
							</td>
						</tr>						
					</table>
					<div style="border-bottom: 1px solid #cacaca; margin-top: 20px;"></div>
					</c:forEach>
				</div>
			</dd>			
		</dl>	
	
		<dl>
			<dt>
				<span>배송 정보 입력</span>
				<button type="button" onclick="showOrder('2')"><span class="sr_only">닫기</span>
					<img alt="" src="<%=cp%>/project/image/orderButton.PNG" height="20px;">
				</button>
			</dt>
			<dd class="on" id = "2" style="display: block">
				<div class="cont delivery_info">
					<span style="font-size: 25px; font-weight: bolder; color: black;">배송정보</span>
					<span style="font-size: 20px; font-weight: bolder; color: black; margin-left: 890px;">배송지 관리 ></span>
					<div style="border-bottom: 1px solid #cacaca; margin-top: 20px;"></div>
					
					<table style="border: 1px solid #cacaca; width: 1118px; margin: 20px 0px 50px 0px; background-color: #f5f5f5" class="panel_cont">
						<tr>
							<td style="padding: 20px 40px; width: 20%"><b style="color: black">이름</b></td>
							<td style="padding: 20px 40px;">
								<span>${destDto.destName}</span>
							</td>
						</tr>
						<tr>
							<td style="padding: 20px 40px;"><b style="color: black">주소</b></td>
							<td style="padding: 20px 40px;">
								<span>[${destDto.zip}] ${destDto.addr1 } ${destDto.addr2 }</span>
							</td>
						</tr>
						<tr>
							<td style="padding: 20px 40px;"><b style="color: black">휴대폰 번호</b></td>
							<td style="padding: 20px 40px;">
								<span>${destDto.destPhone}</span>
							</td>
						</tr>						
					</table>
					
					<span style="font-size: 25px; font-weight: bolder; color: black;">주문자 고객 정보</span>
					<div style="border-bottom: 1px solid #cacaca; margin-top: 20px;"></div>
					
					<table style="border: 1px solid #cacaca; width: 1118px; margin-top: 20px; background-color: #f5f5f5" class="panel_cont">
						<tr>
							<td style="padding: 20px 40px; width: 20%"><b style="color: black">이름</b></td>
							<td style="padding: 20px 40px;">
								<span>${destDto.destName}</span>
							</td>
						</tr>
						<tr>
							<td style="padding: 20px 40px;"><b style="color: black">휴대폰 번호</b></td>
							<td style="padding: 20px 40px;">
								<span>${destDto.destPhone}</span>
							</td>
						</tr>
						<tr>
							<td style="padding: 20px 40px;"><b style="color: black">이메일</b><b style="color: #f54a7e">필수</b></td>
							<td style="padding: 20px 40px;">
								<span class="input_wrap w70p">
									<input type="text" name="purchaserEmailAddress" placeholder="이메일을 입력하세요"> 			
								</span>
							</td>
						</tr>						
					</table>
				</div>
			</dd>
		</dl>
	
		<dl>
			<dt>
				<span>쿠폰/포인트 혜택 사용</span>
				<button type="button" onclick="showOrder('3')"><span class="sr_only">닫기</span>
					<img alt="" src="<%=cp%>/project/image/orderButton.PNG" height="20px;">	
				</button>
			</dt>
			<dd class="on" id = "3" style="display: none">
				<div class="cont">
					<div class="pqyment">
						<table style="border: 1px solid #cacaca; width: 1118px;" class="panel_cont">
							<tr>
								<td style="padding: 40px; border-bottom: 1px dotted #cacaca"><b>사용쿠폰</b></td>
								<td style="padding: 40px; border-bottom: 1px dotted #cacaca" align="right">
									<button type="button" class="btn_sm_neutral" id="b_mobileVoucher">쿠폰조회</button>
								</td>
							</tr>
							<tr>
								<td style="padding: 40px; border-bottom: 1px dotted #cacaca"><b>진주알</b></td>
								<td style="padding: 40px; border-bottom: 1px dotted #cacaca" class="point" align="right" >
									<span style="font-weight: bold;">0알</span>
									<span>/보유0알</span>
								</td>
							</tr>
							<tr>
								<td style="padding: 40px 0px 0px 40px;"><b>뷰티포인트</b></td>
								<td style="padding: 40px 40px 0px 40px;" class="point" align="right">
									<span class="input_wrap bpoint">
										<input type="number" id="beauty_point" name="beauty_point" size="1">
									</span>
									<b>P</b>
									<span>/보유 ${memberPoint }P</span>
									<button type="button" class="btn_sm_neutral" id="b_mobileVoucher" onclick="addPoind();">적용하기</button>
								</td>
							</tr>
							<tr>
								<td colspan="2" style="padding: 0px 0px 40px 40px;" class="text_notice light">
									<img alt="" src="<%=cp%>/project/image/ico.png">
									뷰티포인트 전용 제품구매시 자동으로 보유 포인트 중 사용액이 설정됩니다.
								</td>
							</tr>							
						</table>
					</div>
				</div>
			</dd>
		</dl>
	
		<dl>
			<dt> 
				<span>결제수단 선택</span>
				<button type="button" onclick="showOrder('4')"><span class="sr_only">닫기</span>
					<img alt="" src="<%=cp%>/project/image/orderButton.PNG" height="20px;">
				</button>
			</dt>
			<dd class="on" id = "4" style="display: none">
				<div class="cont">
					<div class="pqyment">
						<table style="border: 1px solid #cacaca; width: 1118px;" class="panel_cont">
							<tr>
								<td style="padding: 40px; border-bottom: 1px dotted #cacaca"><b>모바일 상품권</b></td>
								<td style="padding: 40px; border-bottom: 1px dotted #cacaca" align="right">
									<span class="price">0원</span>
									<button type="button" class="btn_sm_neutral" id="b_mobileVoucher">등록/사용</button>
								</td>
							</tr>
							<tr>
								<td style="padding: 40px;"><b>PINK MONEY</b></td>
								<td style="padding: 40px;" align="right">
									<span class="price">0원</span>
									<button type="button" class="btn_sm_neutral" id="b_mobileVoucher">조회</button>
								</td>
							</tr>
						</table>
						<img alt="" src="<%=cp%>/project/image/orderTable.PNG" height="200px;">
					</div>
				</div>
			</dd>	
		</dl>

	
		<!-- 총구매개수, 구매액 -->
		<dl>
			<dt>
				<span>최종 결제금액</span>
				<span style="text-align: right;">
					<font style="font-weight: bolder; font-size: 20px; color: #f54a7e;"><fmt:formatNumber value="${totalPrice }" type="number"/>원</font>
					<button type="button" onclick="showOrder('5')" style="margin-left: 15px;"><span class="sr_only">닫기</span>
						<img alt="" src="<%=cp%>/project/image/orderButton.PNG" height="20px;">
					</button>
				</span>
			</dt>
			<dd class="on" id = "5" style="display: none">
				<div class="cont">
					<table style="width: 1118px; margin: 0px 0px 30px 0px; background-color: #f5f5f5; border-top: 1px solid #cacaca; border-bottom: 1px solid #cacaca;" class="panel_cont">	
						<tr>
							<td style="padding: 20px 40px;">
								<font style="font-weight: bolder; font-size: 20px; color: black;">총 주문 금액</font></td>
							<td style="padding: 20px 40px;" align="right">
								<span><font style="font-weight: bolder; font-size: 20px; color: black;"><fmt:formatNumber value="${totalPrice }" type="number"/>원</font></span>
							</td>
						</tr>		
						<c:forEach var="orderListDto" items="${orderList }">
						<tr>
							<td style="padding: 10px 50px;">
								<font style="font-size: 17px; color: gray;">${orderListDto.productName}</font>
							</td>
							<td style="padding: 10px 40px;" align="right">
								<span style="font-size: 17px; color: gray; text-align: right;" >
									${orderListDto.amount }개 &nbsp;&nbsp; <fmt:formatNumber value="${orderListDto.amount * orderListDto.price }" type="number"/>원
								</span>					
							</td>
						</tr>	
						</c:forEach>		
					</table>
					
					<table style="width: 1118px; margin: 0px 0px 30px 0px; background-color: #f5f5f5; border-top: 1px solid #cacaca; border-bottom: 1px solid #cacaca;" class="panel_cont">
						<tr>
							<td style="padding: 20px 40px;">
								<font style="font-weight: bolder; font-size: 20px; color: black;">적립 예정 포인트</font></td>
							<td style="padding: 20px 40px;" align="right">
								<span><font style="font-weight: bolder; font-size: 20px; color: black;"><fmt:formatNumber value="${totalPrice*0.01 }" type="number"/>P</font></span>
							</td>
						</tr>
						<c:forEach var="orderListDto" items="${orderList }">
						<tr>
							<td style="padding: 10px 50px;">
								<font style="font-size: 17px; color: gray;">${orderListDto.productName}</font>
							</td>
							<td style="padding: 10px 40px;" align="right">
								<span style="font-size: 17px; color: gray; text-align: right;" >
									<fmt:formatNumber value="${orderListDto.amount * orderListDto.price * 0.01}" type="number"/>P
								</span>					
							</td>
						</tr>	
						</c:forEach>					
					</table>
				</div>
			</dd>	
		</dl>
	</div>
	
	<!-- 버튼 -->
	<div class="page_btns">
		<button type="button" class="btn_lg_primary" id="orderPayment" onclick="sendOrderList();" >결제하기</button>
	</div>
	
	</form>
	


</div>
	
<!-- // page contents -->



<%@include file="../layout/footer.jsp"  %>
