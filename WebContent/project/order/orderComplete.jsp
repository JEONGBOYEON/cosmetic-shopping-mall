<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../layout/top.jsp"  %>

<script src="http://code.jquery.com/jquery-2.1.0.min.js"></script>

<!-- page title -->
<div class="page_title">
	<h2 class="h_title page">주문완료</h2>
	<p class="text font_lg"></p>
</div>
<!-- // page title -->

<!-- page contents -->
<div class="ap_contents cart">

	
	<div class="ui_step">
		<ul>
			<!--<li th:class="current"><i class="ico"></i><span class="num">1</span>장바구니<span class="sr_only">선택됨</span></li>-->
			<li><i class="ico"></i><span class="num">1</span>장바구니</li>
			<li><i class="ico"></i><span class="num">2</span>결제하기</li>
			<li class="current"><i class="ico"></i><span class="num">3</span>주문완료</li>
		</ul>
	</div>
	
	<form class="order-validate" id="order-recept-info" name="orderCompleteFoem" method="post">
	<div class="ui_accordion cart_list ＠accordion-apply">		
		<div class="cont">
			<span style="font-size: 30px; font-weight: bolder; color: black; padding-left: 500px;">주문 확인</span>
			<div style="border-bottom: 1px solid #cacaca; margin-top: 50px; margin-bottom: 50px;"></div>
					
			<div style="padding: 0px 20px;">
				<table style="width: 1000px;">
					<tr style="height: 50px;">
						<td style="width: 200px;">주문 일자</td>
						<td style="text-align: left;">${orderDate }</td>
					</tr>	
					<tr style="height: 50px;">
						<td style="text-align: left;">받는 사람 정보</td>
						<td>${userName }</td>
					</tr>
					<tr style="height: 50px;">
						<td style="text-align: left;">배송지</td>
						<td>[${orderDto.zip }] ${orderDto.addr1 } ${orderDto.addr2 }</td>
					</tr>
					<tr style="height: 50px;">
						<td colspan="2">상품 목록</td>
					</tr>
					<c:forEach var="orderCompleteDto" items="${orderCompleteList }">
					<tr style="height: 50px;">
						<td>${orderCompleteDto.productName }</td>
						<td style="text-align: right;">${orderCompleteDto.amount }개/<fmt:formatNumber value="${orderCompleteDto.price }" type="number"/>원 
							<fmt:formatNumber value="${orderCompleteDto.amount * orderCompleteDto.price }" type="number"/>
						</td>
					</tr>
					
					</c:forEach>
					<tr style="height: 50px;">
						<td></td>
						<td style="text-align: right;">${totalAmount }개 (총 ${totalPrice }원 ) </td>
					</tr>
				</table>
			</div>
			
			<div style="border-bottom: 1px solid #cacaca; margin-top: 50px;"></div>
		</div>
	</div>
	</form>
	<!-- 버튼 -->
	<div class="page_btns">
		<button type="button" class="btn_lg_primary" id="orderPayment" onclick="<%=cp%>/project/main.jsp">쇼핑계속하기</button>
	</div>

</div>
	
<!-- // page contents -->



<%@include file="../layout/footer.jsp"  %>
