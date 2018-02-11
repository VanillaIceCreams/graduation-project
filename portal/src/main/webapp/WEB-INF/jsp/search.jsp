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
    <link href="css/mycss/page.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="/js/myjs/jquery-1.7.2.min.js"></script>

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

<%--<jsp:include page="commons/header.jsp" />--%>





<!--产品列表-->
<div class="content">
	<div class="grid">
		<div class="location mt20">
			<a href="">首页</a>&nbsp;&nbsp;
			&nbsp;&nbsp;<span>关键字：&nbsp;&nbsp;"${q}"</span>
		</div>
		<!--风格类型选择-->
		<div class="mt20 productClassify">
			<div class="classifyChoice clearfix">
				<h3>品牌：</h3>
				<ul class="classifyChoice-brand clearfix">
					<li onclick="isSelectBrand('PP201606230007','10');">
						<input type="checkbox" name="classifyChoice-brand" />
						<label for="vvic">vvic</label>
					</li>


					<li onclick="isSelectBrand('PP201607060011','13');">
						<input type="checkbox" name="classifyChoice-brand" />
						<label for="FANCOSE">FANCOSE</label>
					</li>


					<li onclick="isSelectBrand('PP201607120021','23');">
						<input type="checkbox" name="classifyChoice-brand" />
						<label for="景儿服饰">景儿服饰</label>
					</li>


					<li onclick="isSelectBrand('PP201607200029','31');">
						<input type="checkbox" name="classifyChoice-brand" />
						<label for="锦珮首饰">锦珮首饰</label>
					</li>


					<li onclick="isSelectBrand('PP201608150051','53');">
						<input type="checkbox" name="classifyChoice-brand" />
						<label for="L.RUBY">L.RUBY</label>
					</li>


					<li onclick="isSelectBrand('PP201608190062','64');">
						<input type="checkbox" name="classifyChoice-brand" />
						<label for="巴拉兔">巴拉兔</label>
					</li>


					<li onclick="isSelectBrand('PP201609130084','94');">
						<input type="checkbox" name="classifyChoice-brand" />
						<label for="南极人">南极人</label>
					</li>


					<li onclick="isSelectBrand('PP201610100122','103');">
						<input type="checkbox" name="classifyChoice-brand" />
						<label for="AFS JEEP">AFS JEEP</label>
					</li>


					<li onclick="isSelectBrand('PP201610120128','109');">
						<input type="checkbox" name="classifyChoice-brand" />
						<label for="汐灿">汐灿</label>
					</li>


					<li onclick="isSelectBrand('PP201610130129','110');">
						<input type="checkbox" name="classifyChoice-brand" />
						<label for="抱抱我">抱抱我</label>
					</li>


					<li onclick="isSelectBrand('PP201611300153','136');">
						<input type="checkbox" name="classifyChoice-brand" />
						<label for="THXGIVING">THXGIVING</label>
					</li>


					<li onclick="isSelectBrand('PP201612030154','137');">
						<input type="checkbox" name="classifyChoice-brand" />
						<label for="咔吶咪">咔吶咪</label>
					</li>


					<li onclick="isSelectBrand('PP201612130156','140');">
						<input type="checkbox" name="classifyChoice-brand" />
						<label for="石狮诺驰">石狮诺驰</label>
					</li>


					<li onclick="isSelectBrand('PP201612230159','143');">
						<input type="checkbox" name="classifyChoice-brand" />
						<label for="ROSEPEPPAR/萝西帕柏">ROSEPEPPAR/萝西帕柏</label>
					</li>


					<li onclick="isSelectBrand('PP201701040160','144');">
						<input type="checkbox" name="classifyChoice-brand" />
						<label for="兰芝服饰">兰芝服饰</label>
					</li>


					<li onclick="isSelectBrand('PP201702060162','146');">
						<input type="checkbox" name="classifyChoice-brand" />
						<label for="俏娇诗">俏娇诗</label>
					</li>


					<li onclick="isSelectBrand('PP201702090165','149');">
						<input type="checkbox" name="classifyChoice-brand" />
						<label for="银素">银素</label>
					</li>


					<li onclick="isSelectBrand('PP201702100166','150');">
						<input type="checkbox" name="classifyChoice-brand" />
						<label for="小红人">小红人</label>
					</li>


					<li onclick="isSelectBrand('PP201702210171','157');">
						<input type="checkbox" name="classifyChoice-brand" />
						<label for="MaraCarol/玛拉咔啰">MaraCarol/玛拉咔啰</label>
					</li>


					<li onclick="isSelectBrand('PP201702280178','164');">
						<input type="checkbox" name="classifyChoice-brand" />
						<label for="M－MAICCO/墨麦客">M－MAICCO/墨麦客</label>
					</li>


					<li onclick="isSelectBrand('PP201703020180','166');">
						<input type="checkbox" name="classifyChoice-brand" />
						<label for="缤妮娅">缤妮娅</label>
					</li>


				</ul>



				<div class="selectbtn">
					<a href="javascript:void(0)" id="selectmore">更多<span class="mall-icon ml5" style="font-size:1.1em">5</span></a>
					<!-- a href="javascript:void(0)" id="selectmul">多选<span class="ml5" style="font-size:1.1em">+</span></a -->
				</div>




			</div>


			<div class="classifyChoice clearfix">
				<h3>类型：</h3>
				<ul>



					<li  onclick="isSelectType('CT201606030030');">T恤</li>




					<li  onclick="isSelectType('CT201606030034');">针织衫/毛衣</li>




					<li  onclick="isSelectType('CT201606280046');">上衣</li>




					<li  onclick="isSelectType('CT201606280048');">裙装</li>




					<li  onclick="isSelectType('CT201606290049');">套装</li>




					<li  onclick="isSelectType('CT201606300050');">外套</li>




					<li  onclick="isSelectType('CT201607050052');">女童</li>




					<li  onclick="isSelectType('CT201607180061');">配饰</li>




					<li  onclick="isSelectType('CT201608120069');">首饰</li>




					<li  onclick="isSelectType('CT201610210104');">毛呢外套</li>




					<li  onclick="isSelectType('CT201610210106');">针织衫</li>




					<li  onclick="isSelectType('CT201610210107');">卫衣/绒衫</li>




					<li  onclick="isSelectType('CT201610210114');">衬衫</li>




					<li  onclick="isSelectType('CT201610250148');">儿童毛衣/针织衫</li>



				</ul>
			</div>


		</div>

		<div class="mt20 productSort clearfix">
			<ul>
				<li><a href="javaScript:setSort(1,'','','');">销量</a></li>
				<li><a href="javaScript:setSort('',1,'','');">评论</a></li>
				<li><a href="javaScript:setSort('','',1,'');">上架时间</a></li>
				<li><a href="javaScript:setSort('','','',1);">价格</a></li>
				<li class="pricerange">
					<input type="number" id="input_min_money" value="" class="input-bgray"/>&nbsp;&nbsp;-&nbsp;&nbsp;<input type="number" id="input_max_money" value="" class="input-bgray"/>&nbsp;&nbsp;<a href="javaScript:setSort('','','','');" class="sure-min">确定</a>
				</li>
			</ul>
		</div>

		<!--产品表格列表-->
		<div class="productLists mt20 clearfix lists-colum4">
			<c:forEach var="ItemSearch" items="${itemList}">
				<figure  class="productItem">
					<div class="pic">
						<a onclick="editCommodity(${ItemSearch.id})" href="javaScript:void(0);"><img src="${ItemSearch.image}"  /></a>
					</div>
					<figcaption onclick="editCommodity(${ItemSearch.id})">
						<div style="height: 42px;"><h4>${ItemSearch.title}   </h4></div>
						<h3>￥${ItemSearch.price} </h3>
					</figcaption>
				</figure>
			</c:forEach>


		</div>
        <!--分页-->
        <ul class="page" maxshowpageitem="7" pagelistcount="30"  id="page" style="margin-left: 30%"></ul>

	</div>


</div>
<!-- 引入页脚 footer.jsp -->

<jsp:include page="commons/footer2.jsp" />
<script type="text/javascript" src="/js/myjs/product_list.js"></script>
<script type="text/javascript" src="/js/myjs/page.js"></script>
<script type="text/javascript">
	//加载商品列表
	var totalPages = "${totalPages}";
    var nowpage ="${page}";
    var q ="${q}";
    totalPages = Number(totalPages);
    nowpage = Number(nowpage);



    function tt(dd){
        //alert(dd);
    }
    var GG = {
        "kk":function(pageCount){
            if (pageCount!=nowpage){
                var url = "http://localhost:8082/search.html?page="+pageCount+"&rows="+30+"&q="+q;
                window.location=url;
            }
        }
    };

    $("#page").initPage(totalPages*30,nowpage,GG.kk);

    //商品详情
    function editCommodity(Id) {
        var str = "http://localhost:8082/item/" + Id+".html";
        window.open(str);
    }


</script>
</body>
</html>
