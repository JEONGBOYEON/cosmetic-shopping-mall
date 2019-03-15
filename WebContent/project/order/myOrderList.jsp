<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../layout/top.jsp" %>
<%@ include file="../layout/mypage.jsp" %>
<script src="http://code.jquery.com/jquery-2.1.0.min.js"></script>

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

ul.tabs li button{
	background-color: #F2F2F2;
	width: 150px; 
	height: 40px;
	border-right: gray solid 1px;
}


</style>

<script>

	$(document).ready(function() {

		$('ul.tabs li').click(function() {
			var tab_id = $(this).attr('data-tab');

			$('ul.tabs li').removeClass('on2 current');
			$('.tab-content').removeClass('on2 current');

			$(this).addClass('on2 current');
			$("#" + tab_id).addClass('on2 current');
		})

	})
	
</script>

<div class="page_title_area">

	<div class="page_title">
		<h2 class="h_title page">주문/배송조회</h2>
		<p class="text font_lg"></p>
	</div>
</div>

<div class="ap_contents mypage coupon">

	<!-- tab menu wrap -->
	<div class="ui_tab prd_detail_tap">
		<!-- tab menu -->
		<div class="tab_menu">
			<ul class="tabs" style="border: gray solid 1px; width: 753px;">
				<li class="" data-tab="tab-1"><button type="button">1주일</button></li>
				<li class="" data-tab="tab-2"><button type="button">1개월</button></li>
				<li class="on2 current" data-tab="tab-3"><button type="button">3개월</button></li>
				<li class="" data-tab="tab-4"><button type="button">6개월</button></li>
				<li class="" data-tab="tab-5"><button type="button">12개월</button></li>
			</ul>
		</div>
	</div>

	<div class="ui_tab">
	
		<div id="tab-1" class="tab-content">		
		<div class="tab_cont">
			<div class="panel benefit_panel_list">
				<c:if test="${empty userOrder7day }">
					<div style="text-align: center; font-size: 30px; margin-top: 20px;">조회된 내용이 없습니다.</div>
				</c:if>	
				
				<c:if test="${!empty userOrder7day }">	
				<table class="ui_table_striped data_table thead_colored align_center @table-striped-apply" id="shpiTable" style="margin-left: -33px;">

					<colgroup>
						<col width="15%">
						<col width="5%">
						<col width="30%">
						<col width="50%">
					</colgroup>

					<thread>
					<tr>
						<th scope="col" bgcolor="#F2F2F2">구매일</th>
						<th scope="col" bgcolor="#F2F2F2" colspan="2">상품</th>
						<th scope="col" bgcolor="#F2F2F2">배송지</th>
					</tr>
					</thread>

					<tbody id="paging">
						<c:forEach var="dto" items="${userOrder7day }">
							<tr>
								<td class="check_wrap check_only">${dto.orderDate }</td>
								<td style="vertical-align: top; text-align: left;">
									<a href="<%=cp%>/pr/detail.do?productName=${dto.productName}">
										<img alt="없어요" src="${imagePath }/${dto.saveFileName }" height="50px;">
									</a>
								</td>
								<td style="vertical-align: top; text-align: left;">
									<a href="<%=cp%>/pr/detail.do?productName=${dto.productName}">${dto.productName2 }</a>
									${dto.amount }&nbsp;개 &nbsp;&nbsp;${dto.price }&nbsp;원 
									<br/>(총합&nbsp;&nbsp;:&nbsp;&nbsp;${dto.amount * dto.price }) &nbsp;&nbsp;&nbsp;&nbsp;
								</td>
								<td style="text-align: left;">
									[${dto.zip }] ${dto.addr1 } ${dto.addr2 }
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</c:if>	
			</div>
		</div>
		</div>
		
		<div id="tab-2" class="tab-content">
		<div class="tab_cont">
			<div class="panel benefit_panel_list">
				<c:if test="${empty userOrder1month }">
					<div style="text-align: center; font-size: 30px; margin-top: 20px;">조회된 내용이 없습니다.</div>
				</c:if>	
				
				<c:if test="${!empty userOrder1month }">	
				<table class="ui_table_striped data_table thead_colored align_center @table-striped-apply" id="shpiTable" style="margin-left: -33px;">

					<colgroup>
						<col width="15%">
						<col width="5%">
						<col width="30%">
						<col width="50%">
					</colgroup>

					<thread>
					<tr>
						<th scope="col" bgcolor="#F2F2F2">구매일</th>
						<th scope="col" bgcolor="#F2F2F2" colspan="2">상품</th>
						<th scope="col" bgcolor="#F2F2F2">배송지</th>
					</tr>
					</thread>

					<tbody id="paging">
						<c:forEach var="dto" items="${userOrder1month }">
							<tr>
								<td class="check_wrap check_only">${dto.orderDate }</td>
								<td style="vertical-align: top; text-align: left;">
									<a href="<%=cp%>/pr/detail.do?productName=${dto.productName}">
										<img alt="없어요" src="${imagePath }/${dto.saveFileName }" height="50px;">
									</a>
								</td>
								<td style="vertical-align: top; text-align: left;">
									<a href="<%=cp%>/pr/detail.do?productName=${dto.productName}">${dto.productName2 }</a>
									${dto.amount }&nbsp;개 &nbsp;&nbsp;${dto.price }&nbsp;원 
									<br/>(총합&nbsp;&nbsp;:&nbsp;&nbsp;${dto.amount * dto.price }) &nbsp;&nbsp;&nbsp;&nbsp;
								</td>
								<td style="text-align: left;">
									[${dto.zip }] ${dto.addr1 } ${dto.addr2 }
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</c:if>	
			</div>
		</div>
		</div>
		
		<div id="tab-3" class="tab-content current">
		<div class="tab_cont">
			<div class="panel benefit_panel_list">
				<c:if test="${empty userOrder3month }">
					<div style="text-align: center; font-size: 30px; margin-top: 20px;">조회된 내용이 없습니다.</div>
				</c:if>	
				
				<c:if test="${!empty userOrder3month }">	
				<table class="ui_table_striped data_table thead_colored align_center @table-striped-apply" id="shpiTable" style="margin-left: -33px;">

					<colgroup>
						<col width="15%">
						<col width="5%">
						<col width="30%">
						<col width="50%">
					</colgroup>

					<thread>
					<tr>
						<th scope="col" bgcolor="#F2F2F2">구매일</th>
						<th scope="col" bgcolor="#F2F2F2" colspan="2">상품</th>
						<th scope="col" bgcolor="#F2F2F2">배송지</th>
					</tr>
					</thread>

					<tbody id="paging">
						<c:forEach var="dto" items="${userOrder3month }">
							<tr>
								<td class="check_wrap check_only">${dto.orderDate }</td>
								<td style="vertical-align: top; text-align: left;">
									<a href="<%=cp%>/pr/detail.do?productName=${dto.productName}">
										<img alt="없어요" src="${imagePath }/${dto.saveFileName }" height="50px;">
									</a>
								</td>
								<td style="vertical-align: top; text-align: left;">
									<a href="<%=cp%>/pr/detail.do?productName=${dto.productName}">${dto.productName2 }</a>
									${dto.amount }&nbsp;개 &nbsp;&nbsp;${dto.price }&nbsp;원 
									<br/>(총합&nbsp;&nbsp;:&nbsp;&nbsp;${dto.amount * dto.price }) &nbsp;&nbsp;&nbsp;&nbsp;
								</td>
								<td style="text-align: left;">
									[${dto.zip }] ${dto.addr1 } ${dto.addr2 }
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</c:if>	
			</div>
		</div>
		</div>
		
		<div id="tab-4" class="tab-content">
		<div class="tab_cont">
			<div class="panel benefit_panel_list">
				<c:if test="${empty userOrder6month }">
					<div style="text-align: center; font-size: 30px; margin-top: 20px;">조회된 내용이 없습니다.</div>
				</c:if>	
				
				<c:if test="${!empty userOrder6month }">	
				<table class="ui_table_striped data_table thead_colored align_center @table-striped-apply" id="shpiTable" style="margin-left: -33px;">

					<colgroup>
						<col width="15%">
						<col width="5%">
						<col width="30%">
						<col width="50%">
					</colgroup>

					<thread>
					<tr>
						<th scope="col" bgcolor="#F2F2F2">구매일</th>
						<th scope="col" bgcolor="#F2F2F2" colspan="2">상품</th>
						<th scope="col" bgcolor="#F2F2F2">배송지</th>
					</tr>
					</thread>

					<tbody id="paging">
						<c:forEach var="dto" items="${userOrder6month }">
							<tr>
								<td class="check_wrap check_only">${dto.orderDate }</td>
								<td style="vertical-align: top; text-align: left;">
									<a href="<%=cp%>/pr/detail.do?productName=${dto.productName}">
										<img alt="없어요" src="${imagePath }/${dto.saveFileName }" height="50px;">
									</a>
								</td>
								<td style="vertical-align: top; text-align: left;">
									<a href="<%=cp%>/pr/detail.do?productName=${dto.productName}">${dto.productName2 }</a>
									${dto.amount }&nbsp;개 &nbsp;&nbsp;${dto.price }&nbsp;원 
									<br/>(총합&nbsp;&nbsp;:&nbsp;&nbsp;${dto.amount * dto.price }) &nbsp;&nbsp;&nbsp;&nbsp;
								</td>
								<td style="text-align: left;">
									[${dto.zip }] ${dto.addr1 } ${dto.addr2 }
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</c:if>	
			</div>
		</div>
		</div>
		
		<div id="tab-5" class="tab-content">
		<div class="tab_cont">
			<div class="panel benefit_panel_list">
				<c:if test="${empty userOrder1year }">
					<div style="text-align: center; font-size: 30px; margin-top: 20px;">조회된 내용이 없습니다.</div>
				</c:if>	
				
				<c:if test="${!empty userOrder1year }">	
				<table class="ui_table_striped data_table thead_colored align_center @table-striped-apply" id="shpiTable" style="margin-left: -33px;">

					<colgroup>
						<col width="15%">
						<col width="5%">
						<col width="30%">
						<col width="50%">
					</colgroup>

					<thread>
					<tr>
						<th scope="col" bgcolor="#F2F2F2">구매일</th>
						<th scope="col" bgcolor="#F2F2F2" colspan="2">상품</th>
						<th scope="col" bgcolor="#F2F2F2">배송지</th>
					</tr>
					</thread>

					<tbody id="paging">
						<c:forEach var="dto" items="${userOrder1year }">
							<tr>
								<td class="check_wrap check_only">${dto.orderDate }</td>
								<td style="vertical-align: top; text-align: left;">
									<a href="<%=cp%>/pr/detail.do?productName=${dto.productName}">
										<img alt="없어요" src="${imagePath }/${dto.saveFileName }" height="50px;">
									</a>
								</td>
								<td style="vertical-align: top; text-align: left;">
									<a href="<%=cp%>/pr/detail.do?productName=${dto.productName}">${dto.productName2 }</a>
									${dto.amount }&nbsp;개 &nbsp;&nbsp;${dto.price }&nbsp;원 
									<br/>(총합&nbsp;&nbsp;:&nbsp;&nbsp;${dto.amount * dto.price }) &nbsp;&nbsp;&nbsp;&nbsp;
								</td>
								<td style="text-align: left;">
									[${dto.zip }] ${dto.addr1 } ${dto.addr2 }
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</c:if>	
			</div>
		</div>
		</div>
	</div>
	
</div>

<%@include file="../layout/footer.jsp"  %>
</body>

</html>