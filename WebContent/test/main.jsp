<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
	String cp=request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title>마이 에뛰드</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />    
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, target-densitydpi=medium-dpi">
	<link rel="shortcut icon" href="/kr/ko/favicon.ico">
	
	<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" charset="utf-8" src="js/jquery.leanModal.min.js"></script>
    


	<script>
		csrfHeader = "X-CSRF-TOKEN";
		csrfParam = "_csrf";
		csrfToken = "81c4a46b-1244-4521-8014-fa0ea3ad50d2";
		contextPath = "\/kr\/ko";
	</script>
	
	<!-- Facebook Pixel Code -->

<script type="text/javascript">
!function(f,b,e,v,n,t,s){
	if(f.fbq) return;
	n=f.fbq=function(){
		n.callMethod?n.callMethod.apply(n,arguments):n.queue.push(arguments)
	};
	if(!f._fbq) f._fbq=n;
	n.push=n;
	n.loaded=!0;
	n.version='2.0';
	n.queue=[];
	t=b.createElement(e);
	t.async=!0;
	t.src=v;
	s=b.getElementsByTagName(e)[0];
	s.parentNode.insertBefore(t,s)
}(window,document,'script','https://connect.facebook.net/en_US/fbevents.js');
fbq('init', '304921183682508'); // Insert your pixel ID here.
fbq('track', 'PageView');
</script>
<noscript>
	<img height="1" width="1" style="display:none" src="https://www.facebook.com/tr?id=304921183682508&ev=PageView&noscript=1"/>
</noscript>
<!-- End Facebook Pixel Code -->

    
    <script type="text/javascript" src="/kr/ko/pc/ko/js/tagging/ap_satellite.js"></script>
<script>
<!-- Init Data -->
<!-- Basic Tagging Data -->
var gv_GA_products = [];
var gv_GA_payment = {"ap_price":0,"ap_prod_price":0,"ap_dc_total":0,"ap_dc_basic":0,"ap_dc_coupon":0,"ap_dc_membership":0,"ap_giftcard":0,"ap_point":0,"ap_online_gift":0,"ap_orderNo":"undefined","ap_beauty_acc":0,"ap_shipping":0,"checkoutOption":"undefined","orderNo":"undefined","amount":0,"tax":0,"shippingFee":0,"coupon":"undefined","detailPaymentMethod":"undefined"};
var gv_GA_dimension1 = ("7a8a3ddff121ea80be0d608b43a02e290dec8b2661531fff49fe6e8a5d7a5835ffae182a67685a01098c2b2168cb2b589ab94bcc8a48b84249e542bb35a56735" === "undefined" ) ? undefined : "7a8a3ddff121ea80be0d608b43a02e290dec8b2661531fff49fe6e8a5d7a5835ffae182a67685a01098c2b2168cb2b589ab94bcc8a48b84249e542bb35a56735";
var gv_GA_dimension2 = ("d37309decd6a9c945c4700dca6c6420fc515a35e66d2c0fea8a5daf3233703000b4f2415b5260e331fd03fa1ab5237d3b5d4387473637adb776a71d297453716" === "undefined" ) ? undefined : "d37309decd6a9c945c4700dca6c6420fc515a35e66d2c0fea8a5daf3233703000b4f2415b5260e331fd03fa1ab5237d3b5d4387473637adb776a71d297453716";
var gv_GA_dimension3 = ("O" === "undefined" ) ? undefined : "O";
var gv_GA_dimension4 = ("Y" === "undefined" ) ? undefined : "Y";
var gv_GA_dimension5 = ("NORMAL" === "undefined" ) ? undefined : "NORMAL";
var gv_GA_dimension6 = ("25" === "undefined" ) ? undefined : "25";
var gv_GA_dimension7 = ("1995" === "undefined" ) ? undefined : "1995";
var gv_GA_dimension8 = ("F" === "undefined" ) ? undefined : "F";
var gv_GA_dimension9 = ("Hello" === "undefined" ) ? undefined : "Hello";
//var gv_GA_dimension10 = ("ETUDEHOUSE" === "undefined" ) ? undefined : "ETUDEHOUSE";
var gv_GA_dimension10 = 'ETUDEHOUSE';
var gv_GA_dimension11 = ("PC" === "undefined" ) ? undefined : "PC";
var gv_GA_dimension12 = ("KOR" === "undefined" ) ? undefined : "KOR";
var gv_GA_dimension13 = ("KO" === "undefined" ) ? undefined : "KO";
var gv_GA_dimension14 = ("etude^\uB9C8\uC774\uC5D0\uB6F0\uB4DC^Main" === "undefined" ) ? undefined : "etude^\uB9C8\uC774\uC5D0\uB6F0\uB4DC^Main";
var gv_GA_dimension15 = ("etude" === "undefined" ) ? undefined : "etude";
var gv_GA_dimension20 = ("https:\/\/www.etudehouse.com\/kr\/ko\/my\/page\/info\/myEtude" === "undefined" ) ? undefined : "https:\/\/www.etudehouse.com\/kr\/ko\/my\/page\/info\/myEtude";
var gv_GA_dimension25 = ("CID" === "undefined" ) ? undefined : "CID";
var gv_GA_dimension21 = undefined;
var gv_GA_dimension22 = undefined;
var gv_GA_dimension23 = undefined;
var gv_GA_dimension24 = undefined;
<!-- End Init Data -->
</script>
<script type="text/javascript">
dataLayer = [ {
    'dimension1'  : gv_GA_dimension1,
    'dimension2'  : gv_GA_dimension2,
    'dimension3'  : gv_GA_dimension3,
    'dimension4'  : gv_GA_dimension4,
    'dimension5'  : gv_GA_dimension5,
    'dimension6'  : gv_GA_dimension6,
    'dimension7'  : gv_GA_dimension7,
    'dimension8'  : gv_GA_dimension8,
    'dimension9'  : gv_GA_dimension9,
    'dimension10' : gv_GA_dimension10,
    'dimension11' : gv_GA_dimension11,
    'dimension12' : gv_GA_dimension12,
    'dimension13' : gv_GA_dimension13,
    'dimension14' : gv_GA_dimension14,
    'dimension15' : gv_GA_dimension15,
    'dimension20' : (gv_GA_dimension11 === "APP") ?gv_GA_dimension20:undefined,
    'dimension21' : gv_GA_dimension21,
    'dimension22' : gv_GA_dimension22,
    'dimension23' : gv_GA_dimension23,
    'dimension24' : gv_GA_dimension24,
    'dimension25' : gv_GA_dimension25
	}];
<!-- End Init Data -->

<!-- Google Analytics -->
(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
})(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

//window.ga_debug = {trace: true};
ga('create', 'UA-110770460-2', 'auto');
<!-- End Google Analytics -->

<!-- Google Tag Manager -->

(function(w,d,s,l,i){w[l]=w[l]||[];w[l].push({'gtm.start':
new Date().getTime(),event:'gtm.js'});var f=d.getElementsByTagName(s)[0],
j=d.createElement(s),dl=l!='dataLayer'?'&l='+l:'';j.async=true;j.src=
'https://www.googletagmanager.com/gtm.js?id='+i+dl;f.parentNode.insertBefore(j,f);
})(window,document,'script','dataLayer','GTM-N2HHXNV');


window.onload = function () {
	var str = "<noscript><iframe src='https://www.googletagmanager.com/ns.html?id=GTM-N2HHXNV' height='0' width='0' style='display: none; visibility: hidden'></iframe></noscript>"; 
	$("body").prepend(str);
};
<!-- End Google Tag Manager -->


ga('set', 'dimension1' , gv_GA_dimension1);
ga('set', 'dimension2' , gv_GA_dimension2);
ga('set', 'dimension3' , gv_GA_dimension3);
ga('set', 'dimension4' , gv_GA_dimension4);
ga('set', 'dimension5' , gv_GA_dimension5);
ga('set', 'dimension6' , gv_GA_dimension6);
ga('set', 'dimension7' , gv_GA_dimension7);
ga('set', 'dimension8' , gv_GA_dimension8);
ga('set', 'dimension9' , gv_GA_dimension9);
ga('set', 'dimension10' , gv_GA_dimension10);
ga('set', 'dimension11' , gv_GA_dimension11);
ga('set', 'dimension12' , gv_GA_dimension12);
ga('set', 'dimension13' , gv_GA_dimension13);
ga('set', 'dimension14' , gv_GA_dimension14);
ga('set', 'dimension15' , gv_GA_dimension15);
ga('set', 'dimension20' , (gv_GA_dimension11 === "APP") ?gv_GA_dimension20:undefined);
ga('set', 'dimension21' , gv_GA_dimension21);
ga('set', 'dimension22' , gv_GA_dimension22);
ga('set', 'dimension23' , gv_GA_dimension23);
ga('set', 'dimension24' , gv_GA_dimension24);
ga('set', 'dimension25' , gv_GA_dimension25);

var dtmDataLayer= {
		'page_name':gv_GA_dimension14,
		'login_status':gv_GA_dimension4,
		'app_visitor':(gv_GA_dimension11 === "APP") ? "Y":"N",
		'member_code':gv_GA_dimension2,
		'member_gender':gv_GA_dimension8,
		'member_age':gv_GA_dimension6,
		'member_level':gv_GA_dimension9,
		'social_login':gv_GA_dimension5
	};
</script>

    
        <link rel="stylesheet" type="text/css" href="/kr/ko/pc/ko/css/fonts.css?20190307111504">
    <link rel="stylesheet" type="text/css" href="/kr/ko/pc/ko/css/common.css?20190307111504">
    <link rel="stylesheet" type="text/css" href="/kr/ko/pc/ko/css/ui.css?20190307111504">
    <link rel="stylesheet" type="text/css" href="/kr/ko/pc/ko/css/board.css?20190307111504">
    <link rel="stylesheet" type="text/css" href="/kr/ko/pc/ko/css/layout.css?20190307111504">
    <link rel="stylesheet" type="text/css" href="/kr/ko/pc/ko/css/layer.css?20190307111504">
    <link rel="stylesheet" type="text/css" href="/kr/ko/pc/ko/css/member.css?20190307111504">
    <link rel="stylesheet" type="text/css" href="/kr/ko/pc/ko/css/cs.css?20190307111504">
    <link rel="stylesheet" type="text/css" href="/kr/ko/pc/ko/css/mypage.css?20190307111504">
    <link rel="stylesheet" type="text/css" href="/kr/ko/pc/ko/css/search.css?20190307111504">
    <link rel="stylesheet" type="text/css" href="/kr/ko/pc/ko/css/product.css?20190307111504">
    <link rel="stylesheet" type="text/css" href="/kr/ko/pc/ko/css/cart.css?20190307111504">
    <link rel="stylesheet" type="text/css" href="/kr/ko/pc/ko/css/brand.css?20190307111504">
    <link rel="stylesheet" type="text/css" href="/kr/ko/pc/ko/css/event.css?20190307111504">
    <link rel="stylesheet" type="text/css" href="/kr/ko/pc/ko/css/beautylife.css?20190307111504">
    <link rel="stylesheet" type="text/css" href="/kr/ko/pc/ko/css/policy.css?20190307111504">
    <link rel="stylesheet" type="text/css" href="/kr/ko/pc/ko/css/modify-my-info.css?20190307111504">
    <link rel="stylesheet" type="text/css" href="/kr/ko/pc/ko/css/coupon.css?20190307111504">

    
    


    <script src="/kr/ko/pc/ko/js/libs-all-min.js?20190307111504"></script>


    
    

    
    <script type="text/javascript">
        window.AP = {
			DEVICE: 'pc',
            REGION: 'kr',
            LANGUAGE: 'ko',
            CURRENCY_SYMBOL: '원',
			CURRENCY_CODE: 'KRW',
            DATE_FORMAT: 'YYYY.MM.DD',
			CURRENT_DATE: 1552272153871, //server date (milliseconds since 1970/01/01)
			LOGIN_USER: true,
			DISPLAY_MENU_ID: null,
			BROWSER_INCOMPATIBLE_PAGE: contextPath + "/browserIncompatible"
        };
    </script>

	
	
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/webfont/1.6.16/webfont.js"></script>
    <script>
        (function () {
            WebFont.load({
                custom: {
                    families: ['NotoSansKR-Regular'], // @font-face에 선언한 폰트 패밀리명
                    urls: [contextPath + '/pc/ko/css/fonts.css'], // @font-face가 선언된 css 경로
                }
            });

        })();
    </script>

    <style type="text/css">
        .#fonts-loaded body { /* 웹 폰트 다운로드 전에는 화면을 보여주지 않음 */
            display: none;
        }

        .wf-notosanskrregular-n4-active body { /* 웹 폰트 사용이 가능하면 화면을 보여주고 웹 폰트 속성 적용 */
            display: block;
            font-family: 'Noto Sans Korean', 'NotoSansKR-Regular', sans-serif;
        }
    </style>
		
    
    <script type="text/javascript" src="https://www.youtube.com/iframe_api"></script>

    
	
		
		
		
			<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?libraries=services&autoload=false&appkey=0367034911d0d4e6d270c13bd07ea3f8"></script>
		
	
	<script>
		(function () {
			window.onKakaoAPIReady = new $.Deferred();

			//kakao api load
			window.kakaoAsyncInit = function () {
				Kakao.init("daf78194797745c52f56602b1fd75e67");
				window.onKakaoAPIReady.resolve();
			}
		})();
	</script>
	<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>

	
	
		<script type="text/javascript" src="//webchat.amorepacific.com/prichat/etude/web/pub/prichat.common.js"></script>
	
	
	
	
	<script>
		(function () {
			window.onPixleeAsyncReady = new $.Deferred();
			//PixleeAsync api load
			window.PixleeAsyncInit = function () {
				Pixlee.init({apiKey:"Qb96XS0UZhAN7jhR23"});
				window.onPixleeAsyncReady.resolve();
			}
		})();
	</script>
	<script type="text/javascript" src="//assets.pixlee.com/assets/pixlee_widget_1_0_0.js" report-sample></script>



    
    <script type="text/javascript" charset="UTF-8" src="//t1.daumcdn.net/adfit/static/kp.js"></script>

<script type="text/javascript">
    kakaoPixel('4828325319449855892').pageView();

</script>

    <!--[if lt IE 9]>
    <script>
        var incompatibleAgree = $B.utils.cookie('incompatible_browser_agree');
        var url = location.pathname,
                checkReg = new RegExp(AP.BROWSER_INCOMPATIBLE_PAGE + '(.html)*$');
        if ( !incompatibleAgree && !checkReg.test(url) ) {
            location.replace(AP.BROWSER_INCOMPATIBLE_PAGE);
        }
    </script>
    <![endif]-->
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/font.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/common.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/ui.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/board.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/layout.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/layer.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/member.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/cs.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/mypage.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/search.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/product.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/cart.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/brand.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/event.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/beautylife.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/policy.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/modify-my-info.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/coupon.css">

</head>
<body>
    
    
   
    
    <div id="accessibility"><a href="#ap_container">본문 바로가기</a></div>
    
    
    <div class="ap_wrapper">
        <div class="banner_wrap">
            <span class="banner_cont btn_banner_2">
                <em class="txt">MY SHOP 등록하고 <b>옴니클럽 혜택</b> 받으세요</em>
                <a class="btn_banner" href="/kr/ko/display/currentMyShopEvent" target="_self" ap-click-area="MAIN" ap-click-name="TOPBAN" ap-click-data="MYSHOP등록혜택_190101" onclick="ga('send', 'event', $(this).attr('ap-click-area'), $(this).attr('ap-click-name'), $(this).attr('ap-click-data'))">자세히 보기</a>
            </span>
        </div>
        
        <header id="header" class="ap_header">
        	
            <div class="inner_wrap">
	<ul class="header_menu">

		
		
		
		
		<li><a href="javascript:;" onclick="logout()" class="logout">로그아웃</a></li>
		<li><a href="/kr/ko/my/page/info/myEtude" class="mypage">마이 에뛰드</a></li>

		<li><a href="/kr/ko/my/page/onlineOrderShipping" class="order">주문/배송조회<span class="num" style="display:none;"></span></a></li>
		<li><a href="/kr/ko/cart/cartList" class="cart">장바구니<span class="num" style="display:none;"></span></a></li>
		<li><a href="#none" class="history">히스토리</a></li>
	</ul>
	<h1 class="logo"><a href="/kr/ko/main"><img alt="ETUDEHOUSE" src="/kr/ko/pc/ko/images/common/img_logo.png"></a></h1>
</div>

<div class="shopping_history_wrap">
	<div class="inner_wrap">
		<dl class="shopping_history">
			<dt>쇼핑 히스토리</dt>
			<dd>
				
				
				<div class="loading" style="height:120px;">
					<span>
						<img alt="" src="<%=cp %>/images/common/loading.gif">
					</span>
				</div>
				
				
			</dd>
		</dl>
		<div class="clear mgt10">
			<button type="button" class="btn_delete" style="display:none;">전체삭제</button>
			<button type="button" class="btn_close">닫기</button>
		</div>
	</div>
</div>

<div class="gnb_area"> 
	<div class="inner_wrap">
		<button type="button" class="btn_category">카테고리</button>
		<ul class="gnb">
			<li>
            
            
            
                <a href="/kr/ko/display/new?displayMenuId=new">신상품</a>
            
            </li>
			<li>
            
            
            
                <a href="/kr/ko/display/best?displayMenuId=best">베스트</a>
            
            </li>
			<li>
            
            
            
                <a href="/kr/ko/display/hotdeal?displayMenuId=hotdeal">핫딜</a>
            
            </li>
			<li>
            
            
            
                <a href="/kr/ko/display/event?displayMenuId=event">이벤트</a>
            
            </li>
			<li>
            
            
            
                <a href="/kr/ko/display/couponBenefit?displayMenuId=couponBenefit">쿠폰/혜택</a>
            
            </li>
			<li>
            
            
            
                <a href="/kr/ko/display/etude_ch?displayMenuId=etude_ch">채널에뛰드</a>
            
            </li>
			<li>
            
            
            
                <a href="/kr/ko/display/etude_looks?displayMenuId=etude_looks">LOOKS</a>
            
            </li>
			<li>
            
            
            
                <a href="/kr/ko/display/brand?displayMenuId=brand">브랜드</a>
            
            </li>
			
		</ul>
		<dl class="weekly_best">
			<dt>주간베스트</dt>
			<dd>
				<div class="slide" data-ix-options="view-length:1; auto-play:true; axis:vertical;">
					
				</div>
			</dd>
		</dl>
		
		<fieldset class="search_form">
			<legend class="sr_only">통합검색</legend>
			<div class="wrap">
				<input type="text" title="검색어 입력" value="">
				<button class="btn_search"><span class="sr_only">검색</span></button>
				<button class="input_del" style="display:none;"><i class="ico_close_w"></i><span class="sr_only">검색어 삭제</span></button>
			</div>
			
			<div class="auto_complete">
				<ul class="list">
				</ul>
			</div>
			
			<div class="unified_search">
				<dl class="popular">
					<dt>인기검색어</dt>
					<dd>
						<ul class="list col_02">
							
								<li><a href="/kr/ko/common/search?searchWord=%EC%84%80%EB%8F%84%EC%9A%B0">#섀도우</a></li>
							
								<li><a href="/kr/ko/common/search?searchWord=%ED%8B%B4%ED%8A%B8">#틴트</a></li>
							
								<li><a href="/kr/ko/common/search?searchWord=%EB%A3%A9%EC%95%B3%EB%A7%88%EC%9D%B4%EC%95%84%EC%9D%B4%EC%A6%88">#룩앳마이아이즈</a></li>
							
								<li><a href="/kr/ko/common/search?searchWord=%EB%B8%94%EB%9F%AC%EC%85%94">#블러셔</a></li>
							
								<li><a href="/kr/ko/common/search?searchWord=%ED%8C%94%EB%A0%88%ED%8A%B8">#팔레트</a></li>
							
								<li><a href="/kr/ko/common/search?searchWord=%EC%88%9C%EC%A0%95">#순정</a></li>
							
								<li><a href="/kr/ko/common/search?searchWord=%EB%B8%8C%EB%9F%AC%EC%89%AC">#브러쉬</a></li>
							
								<li><a href="/kr/ko/common/search?searchWord=%EB%A7%88%EC%8A%A4%EC%B9%B4%EB%9D%BC">#마스카라</a></li>
							
								<li><a href="/kr/ko/common/search?searchWord=%ED%8C%8C%EC%9A%B4%EB%8D%B0%EC%9D%B4%EC%85%98">#파운데이션</a></li>
							
								<li><a href="/kr/ko/common/search?searchWord=%ED%94%BC%EA%B8%80%EB%A0%9B">#피글렛</a></li>
							
						</ul>
					</dd>
				</dl>
				<dl>
					<dt>컬러검색</dt>
					<dd>
						<div class="color_chip rounded">
						
							<span><input type="checkbox" id="header_pink" name="color"><label for="header_pink" style="background-color:#f06da1"><span class="sr_only">핑크계열</span></label></span>
							<span><input type="checkbox" id="header_red" name="color"><label for="header_red" style="background-color:#e9224e"><span class="sr_only">레드계열</span></label></span>
							<span><input type="checkbox" id="header_orange" name="color"><label for="header_orange" style="background-color:#ff7a2d"><span class="sr_only">오렌지계열</span></label></span>
							<span><input type="checkbox" id="header_purple" name="color"><label for="header_purple" style="background-color:#b261e4"><span class="sr_only">보라계열</span></label></span>
							<span><input type="checkbox" id="header_black" name="color"><label for="header_black" style="background-color:#1f1d20"><span class="sr_only">블렉/그레이계열</span></label></span>
							<span><input type="checkbox" id="header_brown" name="color"><label for="header_brown" style="background-color:#8e502f"><span class="sr_only">브라운계열</span></label></span>
							<span><input type="checkbox" id="header_green" name="color"><label for="header_green" style="background-color:#8fe48a"><span class="sr_only">그린계열</span></label></span>
							<span><input type="checkbox" id="header_blue" name="color"><label for="header_blue" style="background-color:#3a6abf"><span class="sr_only">블루계열</span></label></span>
							<span><input type="checkbox" id="header_yellow" name="color"><label for="header_yellow" style="background-color:#f9c361"><span class="sr_only">옐로우/베이지계열</span></label></span>
							<span><input type="checkbox" id="header_white" name="color"><label for="header_white" style="background-color:#fff"><span class="sr_only">화이트/실버계열</span></label></span>
						</div>
					</dd>
				</dl>
				<div class="banner">
					<a href="#none"><img alt="배너 내용" src="/kr/ko/pc/ko/images/dummy/img_search_banner.jpg"></a>
				</div>
			</div>
		</fieldset>
	</div>
	
	<div class="category_wrap">
		<div class="inner_wrap">
			<h2 class="sr_only">전체메뉴</h2>
			<dl class="focus_target" tabindex="0">
				<dt class="on"><button type="button">유형별</button></dt>
				<dd>
					<ul class="sub">
						
							<li>
								<a href="/kr/ko/display/category/eye?upperMenuId=eye&amp;categoryType=prod_types" class="on">아이</a>
								
						
							<li>
								<a href="/kr/ko/display/category/lip?upperMenuId=lip&amp;categoryType=prod_types">립</a>
								
						
							<li>
								<a href="/kr/ko/display/category/face?upperMenuId=face&amp;categoryType=prod_types">페이스</a>
								
						
							<li>
								<a href="/kr/ko/display/category/nail?upperMenuId=nail&amp;categoryType=prod_types">네일</a>
								
						
							<li>
								<a href="/kr/ko/display/category/skin_care?upperMenuId=skin_care&amp;categoryType=prod_types">스킨케어</a>
								
						
							<li>
								<a href="/kr/ko/display/category/pack?upperMenuId=pack&amp;categoryType=prod_types">팩/마스크</a>
								
						
							<li>
								<a href="/kr/ko/display/category/cleansing?upperMenuId=cleansing&amp;categoryType=prod_types">클렌징</a>
								
						
							<li>
								<a href="/kr/ko/display/category/body?upperMenuId=body&amp;categoryType=prod_types">바디/헤어</a>
								
						
							<li>
								<a href="/kr/ko/display/category/perfume?upperMenuId=perfume&amp;categoryType=prod_types">향수</a>
								
						
							<li>
								<a href="/kr/ko/display/category/etc?upperMenuId=etc&amp;categoryType=prod_types">화장소품</a>
								
						
						
					</ul>
					
					<div class="banner" style="display:none;">
						
					</div>
					
					<div class="banner corner_slide" style="width:425px; display:block;">
						<div class="slide" data-ix-options="view-length:1; auto-play:true; delay:3000;">
							<div class="ix-list-viewport">
								<ul class="ix-list-items"></ul>
							</div>
							<div class="ix-controller">
								<ul class="ix-thumbs">
									<li class="ix-thumb"><button type="button" class="ix-btn"><!--ix-index-->번째 배너</button></li>
								</ul>
							</div>
						</div>
					</div>
				</dd>
				<dt><button type="button">라인별</button></dt>
				<dd>
					<ul class="sub">
						
							<li>
								<a href="/kr/ko/display/category/Blossom_Picnic?upperMenuId=Blossom_Picnic&amp;categoryType=prod_lines">블라썸 피크닉</a></li>
						
							<li>
								<a href="/kr/ko/display/category/the_zam?upperMenuId=the_zam&amp;categoryType=prod_lines">더 잠</a></li>
						
							<li>
								<a href="/kr/ko/display/category/PlayColor_Eyes?upperMenuId=PlayColor_Eyes&amp;categoryType=prod_lines">플레이 컬러 아이즈</a></li>
						
							<li>
								<a href="/kr/ko/display/category/fountation?upperMenuId=fountation&amp;categoryType=prod_lines">순정</a></li>
						
							<li>
								<a href="/kr/ko/display/category/moistfull_collagen?upperMenuId=moistfull_collagen&amp;categoryType=prod_lines">수분가득 콜라겐</a></li>
						
							<li>
								<a href="/kr/ko/display/category/wonder_pore?upperMenuId=wonder_pore&amp;categoryType=prod_lines">원더포어</a></li>
						
							<li>
								<a href="/kr/ko/display/category/Play_101?upperMenuId=Play_101&amp;categoryType=prod_lines">플레이 101</a></li>
						
							<li>
								<a href="/kr/ko/display/category/ac_cleanup?upperMenuId=ac_cleanup&amp;categoryType=prod_lines">AC클린업</a></li>
						
						
					</ul>
					
					<div class="banner" style="display:none;">
						
					</div>
					
					<div class="banner corner_slide" style="width:425px; display:block;"> 
						<div class="slide" data-ix-options="view-length:1; auto-play:true; delay:3000;">
							<div class="ix-list-viewport">
								<ul class="ix-list-items"></ul>
							</div>
							<div class="ix-controller">
								<ul class="ix-thumbs">
									<li class="ix-thumb"><button type="button" class="ix-btn"><!--ix-index-->번째 배너</button></li>
								</ul>
							</div>
						</div>
					</div>
				</dd>
				<dt><button type="button">테마샵</button></dt>
				<dd>
					<ul class="sub">
						
							<li><a href="/kr/ko/display/category/online_only?upperMenuId=online_only&amp;categoryType=prod_thema">온라인전용</a></li>
						
							<li><a href="/kr/ko/display/category/beauty_select_shop?upperMenuId=beauty_select_shop&amp;categoryType=prod_thema">달달템샵</a></li>
						
							<li><a href="/kr/ko/display/category/pearl_shop?upperMenuId=pearl_shop&amp;categoryType=prod_thema">진주알스토어</a></li>
						

						
					</ul>
					
					<div class="banner" style="display:none;">
						
					</div>
					
					<div class="banner corner_slide" style="width:425px; display:block;"> 
						<div class="slide" data-ix-options="view-length:1; auto-play:true; delay:3000;">
							<div class="ix-list-viewport">
								<ul class="ix-list-items"></ul>
							</div>
							<div class="ix-controller">
								<ul class="ix-thumbs">
									<li class="ix-thumb"><button type="button" class="ix-btn"><!--ix-index-->번째 배너</button></li>
								</ul>
							</div>
						</div>
					</div>
				</dd>
			</dl>
			<button type="button" class="btn_close">닫기</button>
		</div>
	</div>
</div>
        </header>

        
        <div id="ap_container" class="ap_container">
        	<!-- mypage sitemap -->
            <div class="mypage_map">
	<ul style="height: 222px;">
		<li><span>나의 주문 관리</span> <!-- 메뉴 선택시 selected 클래스 -->
			<ul>
				<li><a href="/kr/ko/my/page/onlineOrderShipping">주문/배송조회</a></li> <!-- 메뉴 선택시 selected 클래스 -->
				<li><a href="/kr/ko/my/page/returnExchange">반품/교환</a></li>
				<li><a href="/kr/ko/my/page/cashReceipts">증빙서류발급</a></li>
			</ul>
		</li>
		<li><span>나의 혜택 관리</span>
			<ul>
				<li><a href="/kr/ko/my/page/myCouponList">나의 쿠폰</a></li>
				<li><a href="/kr/ko/my/page/myLevelList">나의 회원 등급</a></li>
			</ul>
		</li>
		<li><span>나의 포인트 관리</span>
			<ul>
				<li><a href="/kr/ko/my/page/info/beautyPoint">뷰티포인트</a></li>
				<li><a href="/kr/ko/my/page/pearl/present">진주알</a></li>
			</ul>
		</li>
		<li><span>나의 지갑 관리</span>
			<ul>
				<li><a href="/kr/ko/my/page/myDepositList">예치금 관리</a></li>
				<li><a href="#non" onclick="wpayClickEvent();">원터치 간편결제 카드관리</a></li>
				<li><a href="/kr/ko/my/page/myPinkMoney">PINK MONEY 관리</a></li>
			</ul>
		</li>
		<li><span>나의 활동 관리</span>
			<ul>
				<li><a href="/kr/ko/my/page/myEventList">이벤트 참여 현황</a></li>
				<li><a href="/kr/ko/my/page/myReviewList">나의 구매 후기</a></li>
				<li><a href="/kr/ko/my/page/myInquiryList">나의 1:1 문의</a></li>
			</ul>
		</li>
		<li><span>나의 정보 관리</span>
			<ul>
				<li><a href="/kr/ko/my/page/info/changeUserInfo">개인정보 수정</a></li>
				<li><a href="/kr/ko/my/page/info/snsLink">SNS 계정 연동 관리</a></li>
				<li><a href="/kr/ko/my/page/ship/address">배송지 관리</a></li>
				<li><a href="/kr/ko/my/page/store/list">단골매장 관리</a></li>
				<li><a href="/kr/ko/my/page/myShop">MY SHOP 관리</a></li>
				<li><a href="javascript:;" onclick="leaver()">회원 탈퇴</a></li>
			</ul>
		</li>
	</ul>
</div>

<script>
	function wpayClickEvent(){
		
		//원터치 간편결제 카드 회원여부 체크
		AP.api.getMemberWPayInfo({}, {
						
		}).done(function (data) {
			if (data.result != "success"){
				//간편결제 가입 안내 문구
				openWpayNonMemberNoti();
				
			} else {
				//간편결제 연동페이지 호출
				callWpayMgmtPopup();
			}
		}).fail(function (e) {
			AP.modal.alert("[ajax.fail]실패");
		}).always(function () {
		});
		
		
	}
	
	function openWpayNonMemberNoti(){
		
		var contents = new Object();
		contents.title = '원터치 간편결제 안내';
		contents.contents = '원터치 간편결제 서비스는 회원전용입니다.<br>주문결제 시 [<strong>원터치 간편결제</strong>] 를 가입 후<br>카드를 등록해 주세요.';
		AP.modal.info(contents);
		return false;
	}
	
	
	function callWpayMgmtPopup() {
		
		var w = 850;
		var h = 750;
		
		var dualScreenLeft = window.screenLeft != undefined ? window.screenLeft : window.screenX;
	    var dualScreenTop = window.screenTop != undefined ? window.screenTop : window.screenY;
		
		var width = window.innerWidth ? window.innerWidth : document.documentElement.clientWidth ? document.documentElement.clientWidth : screen.width;
	    var height = window.innerHeight ? window.innerHeight : document.documentElement.clientHeight ? document.documentElement.clientHeight : screen.height;
	
	    var left = ((width / 2) - (w / 2)) + dualScreenLeft;
	    var top = ((height / 2) - (h / 2)) + dualScreenTop;
	    
	    var wpayMgmtPopup = window.open(contextPath + '/my/page/wpayMgmt','wpayMgmtPopup', 'width=' + w + ', height=' + h + ', top=' + top + ', left=' + left);
		
	}
	
	function wpayResultMsg(msg){
		AP.modal.alert(msg);
	}
</script>
            <!-- //  mypage sitemap -->
            
        	<!-- page title -->
            

	<div class="page_title_area">
		<!-- breadcrumb 미노출 페이지는 감춤 -->
	    
	    <!-- // breadcrumb 미노출 페이지는 감춤 -->
	    <div class="page_title">
	    	
	        
	        	<h2 class="h_title page">마이 에뛰드</h2>
	        
	        <p class="text font_lg"></p>
	    </div>
    </div>

            <!-- // page title -->
            
            <!-- page contents -->
			<div class="ap_contents my_etude" id="ap_container">
				<div class="clear welcome_wrap">
					<div>
						
						<dl class="welcome hello"> <!-- 등급별 클래스 pink_love, friends, hello, guest  -->
							<dt>
								
								<i class="ico_grade"></i>안녕하세요. <span>전가영</span> 님</dt>
							<dd>
								<p class="text font_xl">고객님의 회원등급은 <em>Hello</em> 입니다.</p>
								<a href="/kr/ko/my/page/info/changeUserInfo" class="link">개인정보 수정</a>
								<a href="/kr/ko/my/page/myLevelList" class="link">내 예상등급 확인</a>
							</dd>
						</dl>
						
						<div class="my_wallet_info">
							<div class="clear">
								<dl class="coupon" style="cursor: pointer;" onclick="location.href=contextPath +'/my/page/myCouponList'">
									<dt>쿠폰</dt>
									<dd><b class="num">1</b> 장</dd>
								</dl>
								<dl class="pearl" style="cursor: pointer;" onclick="location.href=contextPath + '/my/page/pearl/present'">
									<dt>진주알</dt>
									<dd><b class="num">0</b> 알</dd>
								</dl>
							</div>
							<div class="clear">
								<dl class="beauty" style="cursor: pointer;" onclick="location.href=contextPath + '/my/page/info/beautyPoint'">
									<dt>뷰티포인트</dt>
									<dd><b class="num">0</b> 원</dd>
								</dl>
								<dl class="deposit" style="cursor: pointer;" onclick="location.href=contextPath + '/my/page/myDepositList'">
									<dt>예치금</dt>
									<dd><b class="num">0</b> 원</dd>
								</dl>
							</div>
						</div>
					</div>
					<div>
						
						
						<dl class="pinkmoney">
							<dt><span>전가영</span> 님의 핑크머니입니다.</dt>
							<dd>
								<a href="/kr/ko/my/page/myPinkMoney" class="link_pinkmoney">내역보기</a>
							</dd>
						</dl>		
						
						<dl class="order_process">
							<dt><b>주문/배송조회</b> <small>(최근 3개월 기준)</small></dt>
							<dd>
								<ul class="process">
									<li><a href="/kr/ko/my/page/onlineOrderShipping" class="null"><em class="num">0</em><span>주문접수</span></a></li>
									<li><a href="/kr/ko/my/page/onlineOrderShipping" class="null"><em class="num">0</em><span>결제완료</span></a></li>
									<li><a href="/kr/ko/my/page/onlineOrderShipping" class="null"><em class="num">0</em><span>배송준비중</span></a></li>
									<li><a href="/kr/ko/my/page/onlineOrderShipping" class="null"><em class="num">0</em><span>배송중</span></a></li>
									<li><a href="/kr/ko/my/page/onlineOrderShipping" class="null"><em class="num">0</em><span>배송완료</span></a></li>
								</ul>
								<div class="_btns clear">
									<a href="/kr/ko/my/page/returnExchange" class="btn_md_bordered">반품/교환 (<strong class="num">0</strong>)</a>
									<a href="/kr/ko/my/page/myReviewList" class="btn_md_bordered">미작성 구매후기 (<strong class="num">0</strong>)</a>
								</div>
								<a href="/kr/ko/my/page/onlineOrderShipping" class="more" title="주문/배송조회">더보기</a>
							</dd>
						</dl>
					</div>
				</div>

				
				<div class="my_color_finder">
					<h3 class="h_title sub"><strong>마이 컬러파인더</strong></h3>
					<!-- 측정값이 있는 경우 -->
                      
					<!-- end:  측정값이 있는 경우 -->
					<!-- 측정값이 없는 경우 -->
					
						<div class="panel notice">
							<i class="ico"></i>
							<p class="text font_lg">
								<span>피부 측정 데이터가 없습니다.<br>가까운 에뛰드 하우스 매장에서 무료로 피부톤 측정을 받으세요~</span>
							</p>
							<div class="page_btns">
		                        <a href="/kr/ko/display/store_location?displayMenuId=store_location" class="btn_lg_big_neutral">가까운 매장 찾기</a>
		                   </div>							
						</div>
					
					
					
				
				</div>
				<div class="my_banner"> <!-- /* 20180803:App설치배너 수정*/ -->
					<a href="https://play.google.com/store/apps/details?id=com.etudehouse" target="_blank" class="android"><img alt="에뛰드 하우스 앱으로 더 많은 정보를 확인해 보세요~. 앱 설치하기" src="/kr/ko/pc/ko/images/my/ico_android.png" /></a>					
					<a href="https://itunes.apple.com/kr/app/id538275329" target="_blank" class="ios"><img alt="에뛰드 하우스 앱으로 더 많은 정보를 확인해 보세요~. 앱 설치하기" src="/kr/ko/pc/ko/images/my/ico_ios.png" /></a>						
				</div>				
			</div>
        	<!-- // page contents -->
        </div>
        
        
		

	<aside>
		
		<button type="button" class="btn_artist_talk"><img alt="아티스트특" src="/kr/ko/pc/ko/images/common/btn_artist_talk.png"></button>
		<div class="layer_popup_artist_talk">
			<div class="layer_wrap_artist_talk">
				<dl class="layer_artist_talk">
					<dt class="sr_only">아티스트톡</dt>
					<dd class="layer_cont_artist_talk">
						<div class="align_center"><img alt="artist talk" src="/kr/ko/pc/ko/images/cs/text_artist_talk.png"></div>
						<p class="text_quotes align_center"><b>에뛰드하우스 전문 아티스트가<br>메이크업에 대한 고민을 해결해드립니다.</b></p>
						<p class="text_center"><b>상담시간 : 평일 오전 9시 ~ 오후 6시</b> <br><span>(점심시간 제외 : 오후 12시 ~ 1시)</span></p>
						<hr class="div dotted m20">
						<div class="page_btns"><button type="button" class="btn_lg_primary btn_consultation">상담하기</button></div>
						<p class="text_notice"><i class="ico_notice"></i> 톡 상담이 많을 경우 Artist와의 연결이 지연될 수 있어요.</p>
						<!--
						<div class="banner">
							<a href="#none"><img alt="12.15 ~ 12.27 애니쿠션 올데이 퍼팩트 50% SALE" ap:src="@{/images/dummy/img_banner_talk.jpg}"></a>
						</div>
						-->
					</dd>
				</dl>
				<button class="layer_close_artist_talk" type="button">레이어 닫기</button>
			</div>
		</div>
		
		<button type="button" class="btn_totop"><img alt="상단으로 이동" src="/kr/ko/pc/ko/images/common/btn_totop.png"></button>
	</aside>

	<script>
		/*<![CDATA[*/
		//Artist talk
		$(function () {
			var parms = '';

			
			
				AP.api.getLoginMemberInfo({}, {}).done( function (data) {
					parms += ( 'CNTR_CD' + '=' + '1004' );
					parms += ( '&CSTMID' + '=' + data.cstmid );
					parms += ( '&CHCSTMID' + '=' + data.id );
					parms += ( '&CSTMNM' + '=' + data.name ); 
					parms += ( '&RESIDNO1' + '=' + data.residno1 );
					parms += ( '&GEND_DV_CD' + '=' + data.gendDvCd );
					AP.footer.setArtistTalk( parms );
					parms = null;
				});
			

		});
		/*]]>*/
	</script>


        
        <footer id="footer" class="ap_footer">
        	
            <div>
	<div class="clear inner_wrap">
		<ul class="footer_menu">
			
				<li><a href="/kr/ko/common/servicePolicy">이용약관</a></li>
				<li><a href="/kr/ko/common/personalInfoPolicy"><b>개인정보처리방침</b></a></li>
				<li><a href="/kr/ko/common/imageryIntelliPolicy">영상정보처리기기 운영&middot;관리 방침</a></li>
				<li><a href="/kr/ko/common/emailCollecting">이메일 무단수집 거부</a></li>
				<li><a href="/kr/ko/common/enotice">전자공고</a></li>
				<li><a href="/kr/ko/display/store_opening">매장개설문의</a></li>
				<li><a href="/kr/ko/cs/customerCenter">고객센터</a></li>
			
		</ul>
		<div class="select_wrap family_site">
			<button type="button">FAMILY SITE</button>
			<ul class="select_options">
				<li class="select_option"><a href="http://www.etudehouse.com/int/en/index.php" target="_blank" title="새창">글로벌 통합몰</a></li>
				<li class="select_option"><a href="http://www.etude.cn" target="_blank" title="새창">중문사이트</a></li>
				<li class="select_option"><a href="http://www.etude.jp" target="_blank" title="새창">일문사이트</a></li>
			</ul>
		</div>
	</div>
</div>
<div class="inner_wrap">
	<ul class="sns_list">
		<li><a target="_blank" href="https://www.facebook.com/etudehouseofficial/" class="facebook"><span class="sr_only">페이스북</span></a></li>
		<li><a target="_blank" href="https://twitter.com/etudeplay" class="twitter"><span class="sr_only">트위터</span></a></li>
		<li><a target="_blank" href="https://www.youtube.com/user/etudeblog" class="youtube"><span class="sr_only">유투브</span></a></li>
		<li><a target="_blank" href="https://www.instagram.com/etudehouseofficial/" class="instagram"><span class="sr_only">인스타그램</span></a></li>
		<li><a target="_blank" href="https://blog.naver.com/etudeblog" class="naver"><span class="sr_only">네이버</span></a></li>
	</ul>
	<div class="clear company_info_wrap">
		<div>
			<p class="text">(주)에뛰드 │ 대표이사: 심재완, 서울특별시 용산구 한강대로 100(한강로2가)<br>
				사업자등록번호: 135-81-05033  │ 통신판매업신고번호: 제2017-서울용산-1292<br>
				개인정보보호책임자: 양민주 │ 호스팅제공자: (주)에뛰드  │ etudemall@etude.co.kr<br>
				고객상담: 1544-5418(쇼핑몰) │ 080-022-2285(제품 및 매장 외)<br>
				상담시간: 평일9시~18시(토/일/공휴일 휴무)</p>
			<div>
				<a href="http://www.ftc.go.kr/bizCommPop.do?wrkr_no=1358105033&apv_perm_no=" class="link" target="_blank" title="새창열림">사업자 정보확인</a> <a href="#none" class="link btn_find_map">찾아오시는 길</a>
			</div>
		</div>
		<div>
			<div class="clear">
				<div>
					<p class="text">고객님은 안전거래를 위해 현금 등으로 결제 시 금액에 관계없이 저희 쇼핑몰에서 가입한 구매 안전 서비스, 소비자피해보상보험 서비스를 이용하실 수 있습니다. (보상대상:미배송, 반품/환불거부, 쇼핑몰부도)</p>
					<a href="https://mall.sgic.co.kr/csh/iutf/sh/shop/CSHINFO004VM0.mvc?tm=3" target="_blank" title="새창" class="link">소비자피해보상보험 서비스 가입 사실 확인</a>
				</div>
				<p class="text">가맹점 바로드림서비스에 대해 (주)에뛰드는 통신판매중개 시스템의 제공자로서 통신판매의 당사자가 아닙니다.<br>따라서 바로드림 서비스 거래에 대한 주문, 환불 등과 관련한 책임은 각 매장에 있습니다.</p>
			</div>
		</div>
	</div>
	<div class="copy">Copyright©2018 ETUDE HOUSE. All Rights Reserved.</div>
</div>
        </footer>

        
        


    <script src="/kr/ko/pc/ko/js/common-all-min.js?20190307111504"></script>


        
        
        

	<script type="text/javascript">
		if (pageInfo === undefined) {
			var pageInfo = {};
		    pageInfo.WARNING_TIME = 60000 * 60;
		}
		$(function() {

			
		    $(document).ready(function() {
				pageInfo.warning = setTimeout(warningEndOfSession, pageInfo.WARNING_TIME);
		    })
			
		
		    function warningEndOfSession() {
		        var warningTime = new Date();
		
		
		        var r = confirm("- 개인정보 보호를 위하여, 로그인 후 60분 동안 서비스 이용이 없을경우 자동으로 로그아웃 처리됩니다.\n" + "- 로그인 시간을 연장하시려면, '확인' 버튼을 클릭하세요.\n" + "- '취소' 버튼을 클릭하면, 로그아웃됩니다.");
		        if (r == true) {
		            var clickTime = new Date();
		            if (clickTime.getTime() - warningTime.getTime() > 60000) {
		                endOfSession();
		                return false;
		            }
		            if (pageInfo.warning !== undefined) {
		                clearTimeout(pageInfo.warning);
		            }
		            pageInfo.warning = setTimeout(warningEndOfSession, pageInfo.WARNING_TIME);
		        } else {
		            logout(true);
		        }
		    }
		
		    function endOfSession() {
		        alert("- 로그인 세션이 이미 종료되어, 자동으로 로그아웃 되었습니다.\n" +
		            "- 서비스를 다시 이용하시려면, '로그인' 해주시길 바랍니다.");
		        logout(true);
		    }
		
		    $(document).keyup(function(evt) {
		        if (pageInfo.warning !== undefined) {
		            clearTimeout(pageInfo.warning);
		        }
		        pageInfo.warning = setTimeout(warningEndOfSession, pageInfo.WARNING_TIME);
		    });
		});

		var progressHtml = '<div id="progress2" style="top: 0;margin-top: 1050px;left: 0;margin-left: calc(50% - 5px);position: absolute;z-index: 10;opacity: 1;/* display: none; */">' +
		'   <div class="loading_full_order">' +
		'		<span>' +
		'			<img alt="" src="' + contextPath + '"/images/common/loading.gif"">' +
		'		</span>' +
		'   </div>' +
	 	'</div>';
	 	
		function leaver() {
			$('body').append(progressHtml);
			$('#progress2').fadeIn(500);
		    $.get(contextPath + '/my/page/leaveId', function(data) {
				$('#progress2').remove();
		        /**
		         * 동적으로 생성된 modal의 Node Element 접근하기
		         * alert, confirm, info 모두 동일
		         */

		        var contents = {};
		        contents.contents = data;
		        var modal = AP.modal.info(contents);
		        var $modalEl = modal.getElement(); //jquery object
		        
		        $modalEl.find('.layer_wrap.layer_md').addClass('modify_my_info');
		        $modalEl.find('.layer_wrap.layer_md').addClass('layer_type2');

				var outContent = $(".pointbox_out");
				$("input[type='radio'][name=integrationCloseAcYn]").click(function(){
					outContent.hide();
					outContent.eq($("input[type='radio'][name=integrationCloseAcYn]").index(this)).show();
				});
				
				$("input[name='reasonCode']").click(function () {
				    $('#cause04 ~ .cause_box').css('display', ($(this).val() === 'C04') ? 'block':'none');
				    $('#cause05 ~ .cause_box').css('display', ($(this).val() === 'C05') ? 'block':'none');
				});		
		        

				$("#closeMember").click(function () {
					
					if(!$('[name=closeAgree]').prop('checked')) {
						AP.modal.alert( "탈퇴여부에 동의해 주세요." );
						return;
					}

	        		if($("[name=reasonCode]:checked").length == 0) {
		        		AP.modal.alert( "탈퇴사유를 체크 해주세요." );
	        			return;
	        		}
	        		if($("#cause06:checked").length != 0 && $("#desc").val() == '') {
		        		AP.modal.alert( "탈퇴사유를 입력 해주세요." );
	        			return;
	        		}
	        		if(!$("[name=closePassword]").val()) {
		        		AP.modal.alert( "비밀번호를 입력해주세요" );
	        			return;
	        		}
	        		var closedAcDetailReason = $('.check_set input[name=reasonCode]:checked + label input[type=text]').val();
	        		if(!closedAcDetailReason)
	        			closedAcDetailReason = $.map($('.check_set input[name=reasonCode]:checked + label + div input:checkbox:checked + label'), function(n, i){return n.textContent;}).join(', ');
	        		$("#closeMember").attr("disabled", "disabled");
	        		AP.api.closeMember({}, 
					{
					    reasonCode : $("[name=reasonCode]:checked").val(),
					    closedAcDetailReason : closedAcDetailReason,
					    integrationCloseAcYn : $("[name=integrationCloseAcYn]:checked").val(),
					    closePassword : $("[name=closePassword]").val()
					    	
					}).done(function(data) {
			            location.href = contextPath + "/customer/leaveComplete";
					}).fail(function(xhr, textStatus, errorThrown) {
						$("#closeMember").removeAttr("disabled");
						if(xhr.errorCode == 'EAPI001') {
		                    AP.modal.alert("비밀번호가 일치하지 않습니다.");
						} else {
		                    AP.modal.alert(xhr.errorMessage);
						}
					}).always(function() {
		                //성공, 실패
		            });
				});
		        
		    });
		}
		var prevOrderUrl = 'https://oldord.etudehouse.com/kr/ko/order9.do?method=';
		var prevOrderUrlStore = 'https://oldord.etudehouse.com/kr/ko/storeOrder9.do?method=';
		var encryptedId = "fe5fa1b67579bdeeac6af2776ba2e6c685b1180e77d9d942ad9d8309e4931439b426761d21e6fc595a7d9c2dd043197150fa91f47d4e01c88b452fb65f7883c6";
		/*
		myOrderList :  주문/배송
		myOrderClaimList : 취소/반품/교환
		myOrderList : 매장 주문조회
		myCashReceiptList : 증명서 발급신청
		*/
		function prevOrderView(type, isStore) {
			if(!encryptedId) {
                AP.modal.alert("이전 주문 목록을 찾을 수 없습니다.");
                return;
			}

            var win;

			if (isStore) {
				win = window.open(contextPath + '/my/page/emtOrderShipping?type=store', '_blank');
			} else if (type == 'myOrderClaimList') {
				win = window.open(contextPath + '/my/page/emtOrderShipping?type=claim', '_blank');
			} else {
				win = window.open(contextPath + '/my/page/emtOrderShipping?type=online', '_blank');
			}

            win.focus();
		}
		
		function logout(flag) {
		
		    $.get(contextPath + "/api/logout", function(data) {
		        if (flag)
		            location.reload();
		    });
		}

	</script>
	<script>

		function post_goto(url, parm, target) {
			var f = document.createElement('form');

			var objs, value;
			for (var key in parm) {
				value = parm[key];
				objs = document.createElement('input');
				objs.setAttribute('type', 'hidden');
				objs.setAttribute('name', key);
				objs.setAttribute('value', value);
				f.appendChild(objs);
			}

			// csrf token
			objs = document.createElement('input');
			objs.setAttribute('type', 'hidden');
			objs.setAttribute('name', csrfParam);
			objs.setAttribute('value', csrfToken);
			f.appendChild(objs);

			if (target)
				f.setAttribute('target', target);

			f.setAttribute('method', 'post');
			if( url.indexOf(contextPath) == -1 ){
				url = contextPath + url;
			}
			f.setAttribute('action', url);
			document.body.appendChild(f);
			f.submit();
		}

		function login() {
        	location.href = contextPath + '/loginRedirect?returl=' + location.href.replace(location.origin, '');
		}

		function logout() {
			location.href = contextPath + "/doLogout";
		}
	</script>



        
        
        


    <script src="/kr/ko/pc/ko/js/contents-all-min.js?20190307111504"></script>


        
        
        


    <script src="/kr/ko/pc/ko/js/my-etude-all-min.js?20190307111504"></script>


        
        
        
			
			<script>
				// slide
				//$( '.my_tone_recommend .slide' ).ixSlideMax();
				var recommend = null;
				AP.myRecommendationList.setRecommendData(recommend);
			</script>
        
        
        
        <script type="text/javascript">
if(AP.prodTag !== undefined){
	AP.prodTag._setGaData()
}

if(AP.ordCompleteTag !== undefined){
	AP.ordCompleteTag._setGaData();
}

_satellite.pageBottom();
</script>

<!-- Mobon Tracker v3.1 [공용] start -->
<script type="text/javascript">
<!--
function mobRf(){
var rf = new EN;
rf.setSSL(true);
rf.sendRf();
}
//-->
</script>
<script src="https://cdn.megadata.co.kr/js/enliple_min2.js" defer="defer" onload="mobRf()"></script>
<!-- Mobon Tracker v3.1 [공용] end -->






		
		<script type="text/javascript" src="https://wcs.naver.net/wcslog.js"></script>
<script type="text/javascript">
	if(!wcs_add) var wcs_add = {};
    wcs_add["wa"] = "s_cdef8784d02";

	if (!_nasa) var _nasa={};
	wcs.inflow("");

	wcs_do(_nasa);
	// wcs_do();
</script>
    </div>

</body>
</html>


































