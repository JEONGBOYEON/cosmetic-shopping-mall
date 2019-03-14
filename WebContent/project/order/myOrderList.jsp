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

.on2>button {
	background-color: #FFFFFF;
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
				<li class="on2 current" data-tab="tab-1"><button type="button">1주일</button></li>
				<li class="" data-tab="tab-2"><button type="button">1개월</button></li>
				<li class="" data-tab="tab-3"><button type="button">3개월</button></li>
				<li class="" data-tab="tab-4"><button type="button">6개월</button></li>
				<li class="" data-tab="tab-5"><button type="button">12개월</button></li>
			</ul>
		</div>
	</div>

	<div class="ui_tab">
		<div id="tab-1" class="tab-content current">
		1
		</div>
		
		<div id="tab-2" class="tab-content">
		2
		</div>
		
		<div id="tab-3" class="tab-content">
		3
		</div>
		
		<div id="tab-4" class="tab-content">
		4
		</div>
		
		<div id="tab-5" class="tab-content">
		5
		</div>
	</div>
	
</div>

<%@include file="../layout/footer.jsp"  %>
</body>

</html>