<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../project/layout/top.jsp"  %>

<script type="text/javascript">
	String.prototype.trim = function() {
		var TRIM_PATTERN = /(^\s*)|(\s*$)/g;
		return this.replace(TRIM_PATTERN, "");
	};
	function sendIt(){
		f = document.myForm;
		
		str = f.productId.value;
		str = str.trim();
		if(!str){
			alert("\n상품id을 입력하세요.");//공백제거후 내용이 없으면
			f.productId.focus();
			return;
		}
		f.productId.value = str;
		
		str = f.productCategory.value;
		str = str.trim();
		if(!str){
			alert("\n상품Category을 입력하세요.");//공백제거후 내용이 없으면
			f.productCategory.focus();
			return;
		}
		f.productCategory.value = str;
		
		str = f.productName.value;
		str = str.trim();
		if(!str){
			alert("\n상품명을 입력하세요.");//공백제거후 내용이 없으면
			f.productName.focus();
			return;
		}
		f.productName.value = str;
		
/* 		str = f.productOption.value;
		str = str.trim();
		if(!str){
			alert("\n productOption을 입력하세요.");//공백제거후 내용이 없으면
			f.productOption.focus();
			return;
		}
		f.productOption.value = str;
 */		
		str = f.price.value;
		str = str.trim();
		if(!str){
			alert("\n price을 입력하세요.");//공백제거후 내용이 없으면
			f.price.focus();
			return;
		}
		f.price.value = str;
		
		str = f.color.value;
		str = str.trim();
		if(!str){
			alert("\n color을 입력하세요.");//공백제거후 내용이 없으면
			f.color.focus();
			return;
		}
		f.color.value = str;
		
		str = f.texture.value;
		str = str.trim();
		if(!str){
			alert("\n texture을 입력하세요.");//공백제거후 내용이 없으면
			f.texture.focus();
			return;
		}
		f.texture.value = str;
		
		str = f.season.value;
		str = str.trim();
		if(!str){
			alert("\n season을 입력하세요.");//공백제거후 내용이 없으면
			f.season.focus();
			return;
		}
		f.season.value = str;
		
		if(f.fileCategory.checked==true){
			f.fileCategory.value="list";
		}else{
			f.fileCategory.value='';
		}

		f.action = "<%=cp %>/pr/adminCreate_ok.do";
		f.submit();
		
	}
</script>
	<div class="ap_contents product detail" style="padding-left: 150px;">
		<table width="600" border="1" bordercolor="#eeeeee" align="center" >
		<tr height="40">
			<td style="padding-left: 20px;">
			<b>상품등록(ADMIN)</b>
			</td>
		</tr>
		</table>
		<br/><br/>
		
		<form name="myForm" method="post" enctype="multipart/form-data">
		<table border="0" align="center">
		<tr><td colspan="2" height="1" bgcolor="#dbdbdb" align="center"></td></tr>
		<tr>
			<td width="140" height="30" style="padding-left: 20px;">
			상품id
			</td>
			<td width="460" style="padding-left: 10px;">
			<input type="text" name="productId" maxlength="20" class="boxTF">
			</td>
		</tr>
		<tr>
			<td width="140" height="30" style="padding-left: 20px;">
			상품카테고리
			</td>
			<td width="460" style="padding-left: 10px;">
			<select name="productCategory" style="width: 300px; height: 20px;" >
	  			<option value="">카테고리 선택</option>
	  			<c:forTokens var='item' items="아이,립,페이스,네일,스킨케어,팩/마스크,클렌징,바디/헤어,향수,화장소품" delims="," >
					<option value="${item}" >${item}</option>
				</c:forTokens>
			</select>
			</td>
		</tr>
		<tr>
			<td width="140" height="30" style="padding-left: 20px;">
			상품명
			</td>
			<td width="460" style="padding-left: 10px;">
			<input type="text" name="productName" maxlength="100" class="boxTF">
			</td>
		</tr>
		<tr>
			<td width="140" height="30" style="padding-left: 20px;">
			상품옵션
			</td>
			<td width="460" style="padding-left: 10px;">
			<input type="text" name="productOption" maxlength="100" class="boxTF" >
			</td>
		</tr>
		<tr>
			<td width="140" height="30" style="padding-left: 20px;">
			상품상태
			</td>
			<td width="460" style="padding-left: 10px;">
				<select name="state" style="width: 350px; height: 20px;" class="boxTF">
					<option value="">제품상태 선택</option>
					<c:forTokens var='item' items="판매중,일시품절,판매중지" delims="," >
					<option value="${item}">${item}</option>
					</c:forTokens>
				</select>
			</td>
		</tr>
		<tr>
			<td width="140" height="30" style="padding-left: 20px;">
			상품가격
			</td>
			<td width="460" style="padding-left: 10px;">
			<input type="text" name="price" maxlength="100" class="boxTF">
			</td>
		</tr>
		<tr>
			<td width="140" height="30" style="padding-left: 20px;">
			텍스쳐
			</td>
			<td width="460" style="padding-left: 10px;">
				<select name="texture" style="width: 350px; height: 20px;" class="boxTF">
					<option value="">텍스처 선택</option>
					<c:forTokens var='item' items="매트,크림,내추럴,촉촉,펄,무펄" delims="," >
					<option value="${item}">${item}</option>
					</c:forTokens>
				</select>
			</td>
		</tr>
		<tr>
			<td width="140" height="30" style="padding-left: 20px;">
			색상
			</td>
			<td width="460" style="padding-left: 10px;">
				<select name="color" style="width: 350px; height: 20px;" class="boxTF">
					<option value="">색상 선택</option>
					<c:forTokens var='item' items="핑크,레드,오렌지,보라,블랙/그레이,브라운,그린,블루,옐로우/베이지,화이트/실버" delims="," >
					<option value="${item}">${item}</option>
					</c:forTokens>
				</select>
			</td>
		</tr>
		<tr>
			<td width="140" height="30" style="padding-left: 20px;">
			추천계절
			</td>
			<td width="460" style="padding-left: 10px;">
			<select name="season" style="width: 350px; height: 20px;" class="boxTF">
				<option value="">계절 선택</option>
				<c:forTokens var='item' items="봄,여름,가을,겨울,봄/가을,여름/겨울" delims="," >
				<option value="${item}">${item}</option>
				</c:forTokens>
			</select>
			</td>
		</tr>
		
		<tr><td colspan="2" height="1" bgcolor="#dbdbdb" align="center"></td></tr>
		<tr>
			<td width="140" height="30" style="padding-left: 20px;">
			리스트사진
			</td>
			<td width="460" style="padding-left: 10px;">
			<input type="file" name="productListImage" maxlength="100" class="boxTF"/>
			메인 이미지
			<input type="checkbox" name="fileCategory" value="" /> 
			</td>
		</tr>
		<tr><td colspan="2" height="1" bgcolor="#dbdbdb" align="center"></td></tr>
		<tr>
			<td width="140" height="30" style="padding-left: 20px;">
			상세사진1
			</td>
			<td width="460" style="padding-left: 10px;">
			<input type="file" name="productDetailImage1" maxlength="100" class="boxTF"/>
			</td>
		</tr>
		<tr>
			<td width="140" height="30" style="padding-left: 20px;">
			상세사진2
			</td>
			<td width="460" style="padding-left: 10px;">
			<input type="file" name="productDetailImage2" maxlength="100" class="boxTF"/>
			</td>
		</tr>
		<tr>
			<td width="140" height="30" style="padding-left: 20px;">
			상세사진3
			</td>
			<td width="460" style="padding-left: 10px;">
			<input type="file" name="productDetailImage3" maxlength="100" class="boxTF"/>
			</td>
		</tr>
		<tr><td colspan="2" height="1" bgcolor="#dbdbdb" align="center"></td></tr>
		<tr>
			<td colspan="2" align="center">
			<div class="purchase_button_set" style="padding-left: 200px;">
			<span><button class="btn_lg_bordered emp btn_buy_now" type="button" onclick="sendIt();">상품등록</button></span>
			<span><button class="btn_lg_bordered emp btn_buy_now" type="button" onclick="javascript:location.href='<%=cp %>/pr/adminList.do';">제품리스트</button></span>
			<span><button class="btn_lg_primary btn_basket" type="reset" onclick="document.myForm.productId.focus();">다시입력</button></span>
		</div>
			</td>
		</tr>
		</table>
		</form>
	</div>
	
<%@include file="../project/layout/footer.jsp"  %>