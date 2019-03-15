<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="java.net.URLEncoder"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="./layout/top.jsp"  %>

<script type="text/javascript">
	
	function setCartItem(productName,productOption,price){
		
		var cartProductName = productName;
		var cartProductOption = productOption;
		var cartPrice = price;
		
		document.getElementById("productName").value = cartProductName;
		document.getElementById("productOption").value = cartProductOption;
		document.getElementById("price").value = cartPrice;
	}
	
	function sendCartSingleItem(productId,productName,price){
		
		var cartProductId = productId;
		var cartProductName = productName;
		var cartPrice = price;
		
		document.getElementById("productId").value = cartProductId;		
		document.getElementById("productName").value = cartProductName;
		document.getElementById("productOption").value = "single";
		document.getElementById("price").value = cartPrice;
		
		sendCartItemFromList();
	}
	
	function sendCartItemFromList(){
		
		var f = document.cartForm;
		
		str = f.productOption.value;
		str = str.trim();
		if(!str){
			alert("\n productOption을 선택하세요.");//공백제거후 내용이 없으면
			return;
		}
		
		f.action = "<%=cp %>/cart/cartAddFromList_ok.do";
		
		f.submit();
	}

</script>

<div id="ap_container" class="ap_container">
	<!-- page title -->
<form name="CategoryForm">

</form>

	<div class="page_title_area">
		<!-- breadcrumb 미노출 페이지는 감춤 -->

		<!-- // breadcrumb 미노출 페이지는 감춤 -->
		<div class="page_title">

			<h2 class="h_title page">베스트</h2>


			<p class="text font_lg"></p>
		</div>
	</div>

	<!-- // page title -->
	<!-- page contents -->
	<div class="ap_contents prd_list">

		<div class="prd_category">
			<ul>
				<li><a href="" class="on">전체</a></li>
					<% 
						String arrCategory3[] = {"아이","립","페이스","네일","스킨케어","팩/마스크","클렌징","바디/헤어","향수","화장소품"};
					
						for(String s:arrCategory3 ){
							
							String category = URLEncoder.encode(s, "UTF-8");
							out.println("<li><a href="+cp+"/product/listCategory.do?productCategory="+category+">");
							out.println(s+"</a></li>");
						}
					%>
			</ul>
		</div>


		<div class="item_list column2">
			<div class="panel notice etu_find_store_none no_result"
				style="display: none;">
				<i class="ico"></i>
				<p class="text font_lg">
					<span class="tit_1">상품이 존재하지 않습니다.</span>
				</p>
			</div>

			<div class="pagination"></div>

			<!-- 보연 시작 -->
			<form name="listForm">
			<table width="1200">
				<c:set var="i" value="0" />
				<c:set var="count" value="1" />
				<c:forEach var="dto" items="${lists }">

					<c:if test="${i==0 }">
						<tr height="30px"></tr>
						<tr>
						<c:set var="j" value="0" />
					</c:if>

					<td align="center">
						<table width="620" height="270px" >
							<tr height="20">
								<td rowspan="6" width="270" style="position: relative;">
									<a href="<%=cp%>/pr/detail.do?productId=${dto.productId}"> 
									<img style="background-color: #f5f5f5; width: 270px; height: 270px; margin-left : 20px; margin-right: 20px" alt="" src="${imagePath }/${dto.saveFileName}" />
									</a>
									<div style="position: absolute; top: 15px; left:33px; z-index: 2; font-size: 20pt; color: #000000; font-family: 'Arial';">
										<span>
										${count}<br/>
										</span>
										<span style="color: #FE2E9A">
										--
										</span>
										
										<c:set var="count" value="${count+1 }" />
									</div>
								</td>
								<td width="290">
									<font style="background-color: #f7a2ba; border-color: #f7a2ba; color: #fff;">베스트</font>
									<font style="background-color: #9adada; border-color: #9adada; color: #fff;">신상품</font>
								</td>
							</tr>
							<tr height="56">
								<td>
								<font style="font-size: 20px; color:#1C1C1C;">${dto.productName }</font>
								</td>
							</tr>
							<tr height="20">
								<td>&nbsp;</td>
							</tr>
							<!-- 상품옵션선택 -->
							<tr height="40">
							<c:choose>
								<c:when test="${!empty dto.productOption }">
								<td>
									<select style="width: 280px; height: 40px;" id="selectOption" >
										<option >옵션선택</option>
										<c:forEach var="option" items="${dto.optionList}">
											<option value="${option }" onclick="setCartItem('${dto.productName}','${option}',${dto.price});">${option }</option>
										</c:forEach>
									</select>
								</td>
								</c:when>							
							</c:choose>								
							</tr>

							<tr height="1">
								<td style="border-top: 1px dashed #d6d6d6;"></td>
							</tr>

							<tr height="51">
								<td>
									<table>
										<tr>
											<td width="250">
												<font style="font-size: 20px; color:#1C1C1C; ">
												<fmt:formatNumber value="${dto.price}" groupingUsed="true"/>원</font>
											</td>
											<td>
												<!-- 장바구니 -->
												<c:choose>
													<c:when test="${empty dto.productOption }">
														<img alt="basket" src="../project/image/basket.PNG" onclick="sendCartSingleItem('${dto.productId}','${dto.productName}',${dto.price});"/>
													</c:when>
													<c:when test="${!empty dto.productOption }">
														<img alt="basket" src="../project/image/basket.PNG" onclick="sendCartItemFromList();"/>
													</c:when>
												</c:choose>
											</td>										
										</tr>
									</table>
								</td>
							</tr>

						</table> 
						
						<c:set var="j" value="${j+1 }" /> 
						<c:set var="i" value="1" />

					</td>

					<c:if test="${j==2 }">
						</tr>
						<c:set var="j" value="0" />
						<c:set var="i" value="0" />
					</c:if>

				</c:forEach>

				<c:if test="${j!=2 }">
					<c:forEach begin="${j }" end="1" step="1">
						<td width="620"></td>
						<c:set var="j" value="${j+1 }" />
					</c:forEach>
					</tr>
				</c:if>



				<tr height="100">
					<td align="center" colspan="2">
						<c:if test="${dataCount!=0 }">
							<font style="font-size: 20px">${pageIndexList}</font>
						</c:if> 
						<c:if test="${dataCount==0 }">
							등록된 게시물이 없습니다.
						</c:if>
					</td>
				</tr>

			</table>
			</form>
			<!-- 보연 끝 -->

		</div>

	</div>
	<!-- // page contents -->
</div>

<form name="cartForm">
	<input type="hidden" value="" readonly="readonly" id="productId" name="productId" ><br/>
	<input type="hidden" value="" readonly="readonly" id="productName" name="productName" ><br/>
	<input type="hidden" value="" readonly="readonly" id="productOption" name="productOption" ><br/>	
	<input type="hidden" value="" readonly="readonly" id="price" name="price"><br/>
	<input type="hidden" value="1" readonly="readonly" id="amount" name="amount"><br/>
</form>


	
<%@include file="./layout/footer.jsp"  %>
