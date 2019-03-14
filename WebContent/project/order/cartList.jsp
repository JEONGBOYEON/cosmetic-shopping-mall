<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../layout/top.jsp"  %>



<script type="text/javascript">
	function setAmendAmount(count,productId,price){
		var amendCount = count ;
		document.getElementById("amendAmount").value = amendCount;
		
		document.getElementById("productId").value = productId;
		document.getElementById("price").value = price;
	}
	
	function sendUpdateData(){
		
		var f = document.amendForm;
		f.action = "<%=cp %>/cart/cartUpdated_ok.do";
		
		f.submit();
	}
</script>



<!-- page title -->
<div class="page_title">
	<h2 class="h_title page">장바구니</h2>
	<p class="text font_lg"></p>
</div>
<!-- // page title -->

<!-- page contents -->
<div class="ap_contents cart">


	<div class="ui_step">
		<ul>
			<!--<li th:class="current"><i class="ico"></i><span class="num">1</span>장바구니<span class="sr_only">선택됨</span></li>-->
			<li class="current"><i class="ico"></i><span class="num">1</span>장바구니</li>
			<li><i class="ico"></i><span class="num">2</span>결제하기</li>
			<li><i class="ico"></i><span class="num">3</span>주문완료</li>
		</ul>
	</div>
	<div class="ui_accordion cart_list">
		<dl>
			<dt class="on">
				<span class="check_wrap">
					총 장바구니 상품 ${totalItemCount }개
				</span>
				<button type="button"><span class="sr_only">닫기</span></button>
			</dt>
			<!-- 장바구니 상품 내역 -->
			<c:forEach var="dto" items="${lists }">
				<div align="center">
				<table border="0">
				<tr style="widows: 1200px; height: 70px;" align="center">
				
					<td class="check_wrap check_only" rowspan="2" width="50">
						<c:if test="${dto.orderSelect=='no' }">
 							<a href="<%=cp%>/cart/orderSelectToYes_ok.do?productId=${dto.productId }">
								<img alt="" src="<%=cp%>/project/image/destination_addrkeyNo.JPG" height="25px;">
 							</a>
						</c:if> 
						<c:if test="${dto.orderSelect=='yes' }">
							<a href="<%=cp%>/cart/orderSelectToNo_ok.do?productId=${dto.productId }">
								<img alt="" src="<%=cp%>/project/image/destination_addrkeyYes.jpg" height="25px;">
							</a>
						</c:if> 
					</td>
					
					<td width="150px" rowspan="2">
						<img src="${imagePath }/${dto.saveFileName}" width="110">
					</td>
					
					<td width="650px" style="padding-left: 30px;font-size: 20px;color: #000;" align="left" >
						${dto.productName}
					</td>
					
					<td width="100px">
					</td>
					
					<td width="100px">
						<span style="color: #000;font-size: 20px;font-weight: bold;">
						<fmt:formatNumber value="${dto.price * dto.amount}" groupingUsed="true"/> 원
						</span>
					</td>
					
					<td width="200px">
						<a href="${deleteUrl}${dto.productId}">
						<button class="btn_sm_bordered" >
						삭제
						</button>
						</a>
					</td>
				</tr>
				
					<tr align="center" bgcolor="#eeeeee" height="60px">
				
						<td align="left" style="padding-left: 30px; color: #000;">
							<c:if test="${!empty dto.productOption}">
							#옵션 ${dto.productOption}
							</c:if>
						</td>
						<td>
							<b>
							<select name="amount" style="background-color:#eee;  width: 60px; height: 20px; font-size: 10pt; padding: 2px 2px; border: 1px solid #d9d9d9;">
								<c:forEach var="cnt"  begin="1" end="30" step="1"> 
									<option onclick="setAmendAmount(${cnt},${dto.productId},${dto.price });"
									<c:if test="${cnt==dto.amount}">selected='selected'</c:if> value="${cnt}">
										${cnt}
									</option>
								</c:forEach>
							</select>개
							</b>
						</td>
						<td>
							<b style="color: #333;font-weight: 500;}">
							<fmt:formatNumber value="${dto.price}" groupingUsed="true"/> 원
							</b>
						</td>
						<td>
							<input type="button" class="btn_sm_neutral" value="수량변경"  onclick="sendUpdateData(${dto.productId},${dto.price});">
						</td>
					</tr>
					
				</table>
				</div>
			</c:forEach>
		</dl>
	</div>
	
	<!-- 총구매개수, 구매액 -->
	<dl class="total_money_list" id="calculationResult" >
		<dt class="on" >
			총 상품 구매금액(${totalItemCountYes }개) 
			<span style="float: right;font-style: normal;color: #f54a7e;font-size: 24px;font-weight: 700;">
				<em><fmt:formatNumber value="${totalItemPrice}" groupingUsed="true"/>원</em>
			</span>
		</dt>
	</dl>
	
	
	<!-- 버튼 -->
	<div class="page_btns">
		<input value="계속 쇼핑하기" type="button" class="btn_lg_bordered" id="btnMain" onclick="<%=cp %>/project/main.jsp">
		<button type="button" class="btn_lg_primary" id="btnCheckOrder" onclick="<%=cp %>/order/orderList.do">주문결제하기</button>
	</div>

</div>

<form name="amendForm">
	<input type="hidden" value="" readonly="readonly" id="productId" name="productId" ><br/>
	<input type="hidden" value="" readonly="readonly" id="price" name="price" ><br/>
	<input type="hidden" value="" readonly="readonly" id="amendAmount" name="amendAmount"><br/>
</form>

	
<!-- // page contents -->
<div class="loading_full_order" id="orderLoading" style="min-height: 100px; display: none">
	<span>
		<img alt="" src="/kr/ko/pc/ko/images/common/loading.gif">
	</span>
</div>



<%@include file="../layout/footer.jsp"  %>
