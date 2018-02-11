<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/4/29
  Time: 22:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<header class="header">
    <!--引用公共头部  -->
    <!--我的js脚本-->
    <script type="text/javascript">
        function login() {
            return location.href = "http://localhost:8085/page/login";
        }
        function regist() {
            return location.href = "http://localhost:8085/page/register";
        }
        function search(a) {
            var b = "http://localhost:8082/search.html?q=" + encodeURIComponent($("#keywords").val());
            return window.location.href = b;
        }
        function getCookie(cookie_name)
        {
            var allcookies = document.cookie;
            var cookie_pos = allcookies.indexOf(cookie_name);   //索引的长度

            // 如果找到了索引，就代表cookie存在，
            // 反之，就说明不存在。
            if (cookie_pos != -1)
            {
                // 把cookie_pos放在值的开始，只要给值加1即可。
                cookie_pos += cookie_name.length + 1;      //这里容易出问题，所以请大家参考的时候自己好好研究一下
                var cookie_end = allcookies.indexOf(";", cookie_pos);

                if (cookie_end == -1)
                {
                    cookie_end = allcookies.length;
                }

                var value = unescape(allcookies.substring(cookie_pos, cookie_end));         //这里就可以得到你想要的cookie的值了。。。
            }
            return value;
        }
        // 获取cookie值/user/token/{token}
        var cookie_val = getCookie("TT_TOKEN");
        if(cookie_val){
            $.ajax({
                url: "http://localhost:8085/user/token/"+cookie_val,
                type: 'get',
                dataType: 'jsonp',
                jsonp: "callback",
                jsonpCallback:"getUser",
                success:function(data){
                    $("#users").html("用户名:<span>"+data.data.username+"</span> | <a href=\"http://localhost:8085/user/logout/"+cookie_val+"\">退出</a>")

                }
            })
        }
    </script>

    <div class="header-top">
        <div class="grid clearfix">
            <div class="fl">
                您好，欢迎光临我的世界商城！&nbsp;&nbsp;[ <span id="users"><a
                    href="javascript:login();">登录</a> | <a
                    href="javascript:regist();">免费注册</a></span> ]
            </div>
            <ul class="fr">
                <li><a href="javascript:void(0)"><span
                        class="mall-icon mr5">H</span>会员中心</a></li>
                <li><a href="javascript:void(0)"><span
                        class="mall-icon mr5">I</span>收藏夹</a></li>
                <li><a href="javascript:void(0)"><span
                        class="mall-icon mr5">Z</span>我的订单</a></li>
                <li><a href="javascript:void(0)"><span
                        class="mall-icon mr5">F</span>合伙加盟</a></li>
            </ul>
        </div>
    </div>

    <!-- 公共调用处 -->
    <script type="text/javascript" src="js/index_Common.js"></script>


    <!--引用搜索栏模块  -->


    <script type="text/javascript" src="js/searchBar.js"></script>
    <div class="header-bottom01">
        <div class="grid clearfix pr">
            <a href="javascript:void(0)" class="fl"><h1>我的世界商城</h1></a>
            <div class="search">
                <ul class="nf">
                </ul>
                <form action="" method="post" target="_blank" id="keywordForm">
                    <p class="search_input clearfix">
                        <input type="text" placeholder="请输入商品名称/型号" id="keywords" name="keywords" value="" autocomplete="on"/>
                        <input type="button" onclick="search('key');return false;" value="搜索">
                    </p>
                </form>
                <p id="search">

                </p>
            </div>
            <a href="/cart/cart.html" class="myOrder">
                <span class="mall-icon c-r1 mr10" style="font-size:1.2em">l</span>
                <i id="icon-cart"></i>我的购物车<span class="mall-icon ml15 c-c1"></span>
            </a>
        </div>
    </div>
</header>
<nav class="main-nav">
    <h2 class="none">主体导航</h2>
    <ul class="grid levelMenu pr">
        <li class="nav-allSort">
            <h2 onclick="commodityMenu();" style="cursor:pointer;">全部商品</h2>
            <div class="nav-dl commodityMenu none" id ="cmTypeDiv">
                <div class="nav-dt">
                    <h3><a href="product.list.html"><span class="mall-icon mr10">q</span>精品服饰</a></h3>
                    <ul class="nav-dd clearfix">
                        <li><a href="product.list.html">男装</a></li>
                        <li><a href="product.list.html">女装</a></li>
                        <li><a href="product.list.html">内衣</a></li>
                        <li><a href="product.list.html">童装</a></li>
                        <li><a href="product.list.html">童装</a></li>

                    </ul>
                </div>
                <div class="nav-dt">
                    <h3><a href="product.list.html"><img src="images/face-icon.png"  alt="" style="vertical-align:middle; margin-right:6px;"/>美颜美妆</a></h3>
                    <ul class="nav-dd clearfix">
                        <li><a href="#product.list.html">个人护理</a></li>
                        <li><a href="product.list.html">化妆美容</a></li>
                        <li><a href="product.list.html">香水彩妆</a></li>
                    </ul>
                </div>
                <div class="nav-dt">
                    <h3><a href="product.list.html"><span class="mall-icon mr10">t</span>食品保健</a></h3>
                    <ul class="nav-dd clearfix">
                        <li><a href="product.list.html">零食</a></li>
                        <li><a href="product.list.html">烟酒</a></li>
                        <li><a href="product.list.html">生鲜特产</a></li>
                    </ul>
                </div>
                <div class="nav-dt">
                    <h3><a href="product.list.html"><span class="mall-icon mr10">0</span>潮流箱包</a></h3>
                    <ul class="nav-dd clearfix">
                        <li><a href="product.list.html">精品女包</a></li>
                        <li><a href="product.list.html">钱包卡包</a></li>
                        <li><a href="product.list.html">旅行箱包</a></li>
                    </ul>
                </div>
                <div class="nav-dt">
                    <h3><a href="product.list.html"><span class="mall-icon mr10">u</span>其他</a></h3>
                    <ul class="nav-dd clearfix">
                        <li><a href="product.list.html">礼品</a></li>
                        <li><a href="product.list.html">家纺</a></li>
                        <li><a href="product.list.html">毛巾</a></li>
                        <li><a href="product.list.html">儿童玩具</a></li>
                    </ul>
                </div>

            </div>
        </li>
        <li><a href="javascript:void(0)" id="indexMenuButton" style="color:#e45454;" >首页</a></li>
        <li><a href="javascript:void(0)" id="fzgMenuButton" >服装馆</a></li>
        <li><a href="javascript:void(0)" id="mzgMenuButton" >美妆馆</a></li>
        <li><a href="javascript:void(0)" id="shgMenuButton" >生活馆</a></li>

        <li><a href="javascript:void(0)">3D空间</a></li>
    </ul>
</nav>

<script>
    //加载全部商品类型
    function loadShopCMType(){
        $.ajax({
            type : "POST",
            async : false,
            data : {},
            url : basePath+'eshop/commoditytype/shopIndexType.do?z='+((new Date()).getTime()+Math.random()),
            dataType : "json",
            success : function(html) {
                if(html.msg){
                    if(html.list!=null && html.list.length>0){
                        var str='';
                        //先遍历出第一级的
                        $.each(html.list, function(i,obj){
                            //alert(obj.COMMODITY_NAME);
                            //str+='<figure onClick="window.open()"></figure>'
                            str+=' <div class="nav-dt">'
                                +'<h3>';
                            if(obj.IMG_TYPE ){
                                var cmTypeURLR1 = basePath+'eshop/commodity/cmTypeList.do?hrefType=ctype&COMMODITY_TYPE_CODE='+obj.COMMODITY_TYPE_CODE;
                                str+='<a href="'+cmTypeURLR1+'">'
                                    +'<span class="mall-icon mr10">'+obj.IMG_TYPE+'</span>';
                            }else{
                                str+='';
                            }
                            str+=obj.COMMODITY_TYPE_NAME+'</a></h3>'
                                +' <ul class="nav-dd clearfix">';
                            //再遍历第二级的
                            $.each(obj.s2List, function(i2,obj2){
                                if(obj2.COMMODITY_TYPE_NAME){
                                    var cmTypeURLS2 = basePath+'eshop/commodity/cmTypeList.do?hrefType=ctype&COMMODITY_TYPE_CODE='+obj2.COMMODITY_TYPE_CODE;
                                    str+='<li><a href="'+cmTypeURLS2+'">'+obj2.COMMODITY_TYPE_NAME+'</a></li>';
                                }else{
                                    str+='';
                                }
                            });
                            str+=' </ul>'
                                +' </div>';
                        });
                        $("#cmTypeDiv").html(str);
                    }
                }
            }
        });
    }
</script>
<script type="text/javascript">
    function commodityMenu(){
        $(".commodityMenu").toggle(10);
    }
</script>