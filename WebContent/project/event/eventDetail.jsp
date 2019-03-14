
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../layout/top.jsp"%>

<div id="ap_container" class="ap_container">
	<!-- page contents -->
	<div class="ap_contents event detail">

		<div class="event_title_wrap">
			<h3 class="h_title">${eventName }</h3>
			<div class="align_center">
				<button type="button" class="btn_share">SNS 공유하기</button>
			</div>
		</div>


		<div class="event_image">
			<div data-component="contenteditor" class="contenteditor-root">
				<div data-itemtype="htmlcode" class="contenteditor-htmlcode">
					<!-- 시작 -->

					<div id="event">
					<c:forEach var="dto" items="${lists }">
						<div class="section01">
							<img src="${imagePath }/${dto.saveFileName}"
								alt="" class="section_img">
						</div>
					</c:forEach>
					</div>
					<!-- 끝 -->
				</div>
			</div>
		</div>
		

		<div class="page_btns">
			<a href="<%=cp%>/event/list.do"
				class="btn_md_neutral">목록</a>
		</div>


		<%@ include file="../layout/footer.jsp"%>