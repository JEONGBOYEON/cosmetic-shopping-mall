<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="./layout/top.jsp"  %>

<div id="ap_container" class="ap_container">
	<!-- page title -->


	<div class="page_title_area">
		<div class="page_title">

			<h2 class="h_title page">신상품</h2>
			<p class="text font_lg"></p>
		</div>
	</div>

	<!-- // page title -->
	<!-- page contents -->
	<div class="ap_contents prd_list">

		<div class="item_list column2">
			<div class="panel notice etu_find_store_none no_result"
				style="display: none;">
				<i class="ico"></i>
				<p class="text font_lg">
					<span class="tit_1">상품이 존재하지 않습니다.</span>
				</p>
			</div>

			<ul class="before_list"></ul>



			<div class="banner" style="display: none"></div>


			<ul class="after_list"></ul>

			<div class="pagination"></div>
			<!-- 					
					<div class="loading" style="display: block; min-height: 300px">
						<span ><img alt=""
							src=""></span>
					</div>
					 -->

			<!-- 보연 시작 -->
			<table width="1200">
				<c:set var="i" value="0" />
				<c:forEach var="dto" items="${lists }">

					<c:if test="${i==0 }">
					
						<tr height="30px"></tr>
						<tr>
						<c:set var="j" value="0" />
					</c:if>

					<td align="center">
						<table width="620" height="270px" >
							<tr height="20">
								<td rowspan="6" width="270">
									<a href="<%=cp%>/pr/detail.do?productId=${dto.productId}"> 
									<img style="background-color: #f5f5f5; width: 270px; height: 270px; margin-left : 20px; margin-right: 20px" alt="" src="${imagePath }/${dto.saveFileName}" />
									</a>
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
							<tr height="40">
								<td>
								<c:if test="${!empty dto.productOption }">
										<select style="width: 280px; height: 40px;">
											<option>옵션선택</option>
											<c:forEach var="option" items="${dto.optionList}">
												<option>${option }</option>
											</c:forEach>
										</select>
								</c:if>
								</td>
							</tr>

							<tr height="1">
								<td style="border-top: 1px dashed #d6d6d6;"></td>
							</tr>

							<tr height="51">
								<td>
								<font style="font-size: 20px; color:#1C1C1C;">${dto.price }원</font>
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

				<c:if test="${j!=1 }">
					<c:forEach begin="${j }" end="1" step="1">
						<td width="220"></td>
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
			<!-- 보연 끝 -->

		</div>

	</div>
	<!-- // page contents -->
</div>

	
<%@include file="./layout/footer.jsp"  %>
