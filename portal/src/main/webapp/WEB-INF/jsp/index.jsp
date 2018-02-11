<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	pageContext.setAttribute("basePath", request.getContextPath()+"/") ;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>网上商城-综合网购首选（JD.COM）-正品低价、品质保障、货到付款、配送及时、放心服务、轻松购物！</title>
<meta name="description"
	content="JD.COM-专业的综合网上购物商城，在线销售家电、数码通讯、电脑、家居百货、服装服饰、母婴、图书、食品、在线旅游等数万个品牌千万种优质商品。便捷、诚信的服务，为您提供愉悦的网上商城购物体验! ">
	<meta name="Keywords"
		content="网上购物,网上商城,手机,笔记本,电脑,MP3,CD,VCD,DV,相机,数码,配件,手表,存储卡,商城">
	<link href="css/mycss/index.css" rel="stylesheet" type="text/css">
	<link href="css/mycss/common.css" rel="stylesheet" type="text/css">
	<link href="css/mycss/product_list.css" rel="stylesheet" type="text/css">

	<script type="text/javascript" src="js/myjs/jquery-1.7.2.min.js"></script>

	<script type="text/javascript" src="/js/myjs/unslider.min.js"></script>
	<script type="text/javascript" src="/js/myjs/jquery.cookie.js"></script>
	<script type="text/javascript" src="/js/myjs/delListCookie.js"></script>
	<script type="text/javascript" src="/js/myjs/index.js"></script>
</head>

            <script type="text/javascript">

            var basePath = '';
            var basePathImg = '';
		</script>
<body>

<!--引用头部导航栏 当不需要商品导航栏出现时，请 根据 commodityMenu 这个class找到相关标签。设置为display  -->
<jsp:include page="commons/header2.jsp" />
<!--引用头部导航栏 当不需要商品导航栏出现时，请 根据 commodityMenu 这个class找到相关标签。设置为display  -->


<div class="banner">
	<div id="focus">
		<ul class="clearfix" id="clearfixindexid">
			<li style="display: none;"><a href="http://www.zzokk.com/eshop/commodity/proInfo.do?ID=1379" target="_blank" style="background-image:url(http://www.zzokk.com:80/sdFile/uploadFiles/bannerimg/20161202/a8cc493473324dd2a56e94c30bdeffc1.jpg)"></a></li>
			<li style="display: none;"><a href="http://www.zzokk.com/eshop/commodity/proInfo.do?ID=1379" target="_blank" style="background-image:url(http://www.zzokk.com:80/sdFile/uploadFiles/bannerimg/20161202/a8cc493473324dd2a56e94c30bdeffc1.jpg)"></a></li>
			<li style="display: none;"><a href="http://www.zzokk.com/eshop/commodity/proInfo.do?ID=1379" target="_blank" style="background-image:url(http://www.zzokk.com:80/sdFile/uploadFiles/bannerimg/20161202/a8cc493473324dd2a56e94c30bdeffc1.jpg)"></a></li>
		</ul>
	</div>
</div>

<div class="content">
	<div class="grid">
		<!--公告-->
		<section class="notice mt20 clearfix">
			<h2>公告：</h2>
			<ul class="clearfix" id="notice">
				<li><a href="javascript:void(0)"></a></li>
				<li><a href="javascript:void(0)"></a></li>
				<li><a href="javascript:void(0)"></a></li>
				<li style="border-right:0"><a href="javascript:void(0)"></a></li>
			</ul>
		</section>
		<div class="modelArea" style="display:none;">
			<h3></h3>
			<div id="modelAreaData">

			</div>
		</div>
		<!--今日特卖-->
		<section class="mt20 hot">
			<h2>今日特卖</h2>
			<div class="hotLists clearfix mt5" id="hotListsId">

			</div>
		</section>
		<section class="mt20 hot">
			<h2>时尚服饰 <a href="javascript:void(0)"></a></h2>
			<div class="modelList clearfix mt5" id="loadRecommendation_ID">
			</div>
		</section>

		<section class="mt20 hot">
			<h2>新品上架<a href="javascript:void(0)"> <span style="float:right;padding-right: 23px;">更多</span></a></h2>
			<div class="hotLists clearfix mt5" id="loadSaleToday_ID">
			</div>
		</section>

		<!--服装馆 开始-->
		<section class="mt20 house">
			<h2>

				<a href="javascript:void(0)"><span class="mall-icon mr5 c-r2">J</span>服装馆</a></h2>
			<p id="cmTypeList_01">
			</p>
			<div class="houseContent mt5">
				<div class="min-banner clearfix">
					<ul id="clothingBannerId">

					</ul>
				</div>
				<div class="dressLists clearfix houseContentLists" id="houseContentLists">
				</div>
			</div>
		</section>


		<!--美妆馆-->
		<section class="mt20 house">
			<h2><a href="javascript:void(0)"><span class="mall-icon mr5 c-r2">J</span>美妆馆</a></h2>
			<p>
				<a href="javascript:void(0)">上装</a>&nbsp;&nbsp;·&nbsp;<a href="javascript:void(0)">下装</a>&nbsp;&nbsp;·&nbsp;&nbsp;
				<a href="javascript:void(0)">内衣</a>&nbsp;&nbsp;·&nbsp;&nbsp;<a href="javascript:void(0)">裙装</a>&nbsp;&nbsp;·&nbsp;&nbsp;
				<a href="javascript:void(0)">运动</a>&nbsp;&nbsp;·&nbsp;&nbsp;<a href="javascript:void(0)">家居服</a>
			</p>
			<div class="houseContent mt5">
				<div class="min-banner clearfix" >
					<ul id="beautyBannerId">
						<li><a href="javascript:void(0)"><img src="images/makeup_banner.jpg" alt=""/></a></li>
						<li><a href="javascript:void(0)"><img src="images/makeup_banner.jpg" alt=""/></a></li>
						<li><a href="javascript:void(0)"><img src="images/makeup_banner.jpg" alt=""/></a></li>
					</ul>
				</div>
				<div  class="maximg" id="loadBeautyModule">
				</div>
			</div>
		</section>

		<!--生活馆-->
		<section class="mt20 house">
			<h2><a href="index.life.html"><span class="mall-icon mr5 c-r2">J</span>生活馆</a></h2>
			<p >
				<a href="javascript:void(0)">上装</a>&nbsp;&nbsp;·&nbsp;<a href="product.list.html">下装</a>&nbsp;&nbsp;·&nbsp;&nbsp;
				<a href="javascript:void(0)">内衣</a>&nbsp;&nbsp;·&nbsp;&nbsp;<a href="product.list.html">裙装</a>&nbsp;&nbsp;·&nbsp;&nbsp;
				<a href="javascript:void(0)">运动</a>&nbsp;&nbsp;·&nbsp;&nbsp;<a href="product.list.html">家居服</a>
			</p>
			<div class="houseContent mt5">
				<div class="min-banner clearfix">
					<ul id="lifeBannerId">
						<li><a href="javascript:void(0)"><img src="images/live_banner.jpg" alt=""/></a></li>
						<li><a href="javascript:void(0)"><img src="images/live_banner.jpg" alt=""/></a></li>
						<li><a href="javascript:void(0)"><img src="images/live_banner.jpg" alt=""/></a></li>
					</ul>
				</div>
				<div  class="maximg" id="loadLifeModule">
				</div>
			</div>
		</section>

		<!--品牌推荐-->
		<section class="mt20 brand">
			<h2><span class="mall-icon mr5 c-r2">N</span>品牌推荐</h2>
			<ul class="mt5 clearfix" id="sysBrand">
				<li><a href="#"><img src="images/brand01.jpg" alt=""/></a></li>
			</ul>
		</section>
	</div>




	<!--底部承诺通栏-->
	<div class="promisebar mt30">
		<ul class="grid clearfix">
			<li class="clearfix">
				<span><img src="images/promisebar01.png" width="48" height="48"  alt=""/></span>
				<hgroup>
					<h2>100%正品</h2>
					<h3>正品保证 放心购物</h3>
				</hgroup>
			</li>
			<li class="clearfix">
				<span><img src="images/promisebar02.png" width="48" height="48"  alt=""/></span>
				<hgroup>
					<h2>退换货保障</h2>
					<h3>7天无理由退换货</h3>
				</hgroup>
			</li>
			<li class="clearfix">
				<span><img src="images/promisebar03.png" width="48" height="48"  alt=""/></span>
				<hgroup>
					<h2>极速物流</h2>
					<h3>即送即达，支持货到付款</h3>
				</hgroup>
			</li><li class="clearfix">
			<span><img src="images/promisebar04.png" width="48" height="48"  alt=""/></span>
			<hgroup>
				<h2>精选品牌</h2>
				<h3>品类齐全，轻松购物</h3>
			</hgroup>
		</li>
		</ul>
	</div>
</div>
<!-- 引入页脚 footer.jsp -->

<jsp:include page="commons/footer2.jsp" />


</body>
</html>
