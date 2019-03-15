<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="./layout/top.jsp"%>






<div class="ap_wrapper">

<div id="ap_container" class="ap_container">

	<div>
		<img src="<%=cp %>/project/image/mainTest.PNG" />
	</div>
	
	
	<div class="ap_contents prd_list">

		<div>
			<div class="title" style="font-size: 17pt; width: 1200px; margin-bottom: 35px; margin-top: 35px;">
					<img alt="" src="<%=cp %>/project/image/new.PNG">
					신상품
					<a style="margin-left: 1030px; font-size: 13pt" href="<%=cp %>/product/listNew.do">더보기&nbsp;&nbsp;></a>
			</div>
			
			
			<table>
				<tr>
					<c:set var="i" value="0" />
					<c:forEach var="dto" items="${lists }">
					<c:if test="${i<4 }">
						<td width="400">
							<a href="<%=cp%>/pr/detail.do?productId=${dto.productId}"> 
							<img style="background-color: #f5f5f5; width: 278x; height: 278px; margin-right: 30px" alt="" src="${imagePath }/${dto.saveFileName}" />
							</a>
							<br/><br/><br/>
							<font style="font-size: 15pt; color:#1C1C1C;">${dto.productName }</font>
							<br/><br/><br/>
							<font style="font-size: 20px; color:#1C1C1C; ">
								<fmt:formatNumber value="${dto.price}" groupingUsed="true"/>&nbsp;&nbsp;원
							</font>
							<br/><br/><br/>
							<c:set var="i" value="${i+1 }" />
						</td>
					</c:if>
					</c:forEach>
				</tr>
			</table>
			
		</div>

		<div class="item_list column2">
			<div class="section ch_etude " >
				<h2 class="title" style="font-size: 17pt; margin-bottom: 35px; margin-top: 35px; ">
					<img alt="" src="<%=cp %>/project/image/youtube.PNG">
					채널에뛰드
				</h2>
				<div class="clear">
					<div class="video_wrap">
						<iframe width="1200" height="680"
							src="https://www.youtube.com/embed/QZy5JNAsEa0" frameborder="0"
							allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture"
							allowfullscreen></iframe>
					</div>
					<dl>
						<dt class="font_xl"></dt>
						<dd>
							<ul></ul>
							<ul class="loading"></ul>
						</dd>
					</dl>
				</div>
			</div>
		</div>
		
		

	</div>
</div>

</div>

<%@include file="./layout/footer.jsp"%>
