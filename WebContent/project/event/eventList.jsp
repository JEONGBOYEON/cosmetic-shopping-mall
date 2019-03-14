<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../layout/top.jsp"%>

<style>

ul.tab{
  margin: 0px;
  padding: 0px;
  list-style: none;
}
ul.tab li{
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
$(document).ready(function(){
	   
	  $('ul.tab li').click(function(){
	    var tab_id = $(this).attr('data-tab');
	 
	    $('ul.tab li').removeClass('on current');
	    $('.tab-content').removeClass('on current');
	 
	    $(this).addClass('on current');
	    $("#"+tab_id).addClass('on current');
	  })
	 
	})
</script>

<div class="page_title_area">
	<!-- breadcrumb 미노출 페이지는 감춤 -->

	<!-- // breadcrumb 미노출 페이지는 감춤 -->
	<div class="page_title">
		<h2 class="h_title page">이벤트</h2>

		<p class="text font_lg"></p>
	</div>
</div>

<!-- // page title -->
<!-- page contents -->
<div class="ap_contents event_main event_progress">
	<!-- tab menu wrap -->
	<div class="ui_tab">
		<!-- tab menu -->
		<div class="tab_menu equally">
			<ul class="tab">
				<li class="on current" data-tab="tab-1"><a href="#none">진행중인 이벤트</a></li>
				<li data-tab="tab-2"><a href="#">종료된 이벤트</a></li>
				<li data-tab="tab-3"><a href="#">당첨자 발표</a></li>
			</ul>
		</div>
		<!-- tab 1 -->
		<div class="tab-content current tab_cont space" id="tab-1">
			<h3 class="sr_only">진행중인 이벤트</h3>

			<ul class="event_list">
				<!-- handlebars:display/events/event-progress.hbs -->
				<c:forEach var="dto" items="${lists }">
					<li><a href="<%=cp%>/event/list_detail.do?eventKey=${dto.eventKey}"><img alt=""
							src="${imagePath }/${dto.saveFileName}"></a>
						<dl class="sr_only">
							<dt>${dto.eventName }</dt>
							<dd>${dto.endDay}</dd>
						</dl>	
					</li>
				</c:forEach>

				<!-- //handlebars:display/events/event-progress.hbs -->
			</ul>

			<div class="pagination">
				<c:if test="${dataCount!=0 }">
					<font style="font-size: 20px">${pageIndexList}</font>
				</c:if>
				<c:if test="${dataCount==0 }">
							등록된 게시물이 없습니다.
				</c:if>
	   		</div>
		</div>
		
		<!-- tab 2 //종료된 이벤트 -->
		<div class="tab-content tab_cont space" id="tab-2">
			<h3 class="sr_only">종료된 이벤트</h3>

			<ul class="event_list">
				<!-- handlebars:display/events/event-progress.hbs -->
				<c:forEach var="dto" items="${lists2}">
					<li><a href="<%=cp%>/event/list_detail.do?eventKey=${dto.eventKey}"><img alt=""
							src="${imagePath }/${dto.saveFileName}"></a>
						<dl class="sr_only">
							<dt>${dto.eventName }</dt>
							<dd>${dto.endDay}</dd>
						</dl>	
					</li>
				</c:forEach>

				<!-- //handlebars:display/events/event-progress.hbs -->
			</ul>

			<div class="pagination">
				<c:if test="${dataCount2!=0 }">
					<font style="font-size: 20px">${pageIndexList}</font>
				</c:if>
				<c:if test="${dataCount2==0 }">
							등록된 게시물이 없습니다.
				</c:if>
	   		</div>
		</div>
		
				<!-- tab 2 //종료된 이벤트 -->
		<div class="tab-content tab_cont space" id="tab-3">
			<h3 class="sr_only">종료된 이벤트</h3>

			
<div id="ap_container" class="ap_container">
	<!-- page title -->


	<div class="page_title_area">
		<!-- breadcrumb 미노출 페이지는 감춤 -->

		<!-- // breadcrumb 미노출 페이지는 감춤 -->
		<div class="page_title">


			<h2 class="h_title sub">당첨자 발표</h2>

			<p class="text font_lg"></p>
		</div>
	</div>

	<!-- // page title -->
	<!-- page contents -->
	<div class="ap_contents event event_winner">
		<div class="board_list2">
			<table class="align_center" >
				<tr style="padding:30px;">
					<td class="subj align_center">당첨자는 개별 연락드립니다.</td>
				</tr>
			</table>
		</div>

	</div>
	<!-- // page contents -->
</div>
		</div>
<!-- // page contents -->
</div>

<%@ include file="../layout/footer.jsp"%>
































