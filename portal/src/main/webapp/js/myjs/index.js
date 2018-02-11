// JavaScript Document
// JavaScript Document
$(function(){
    //banner图
    loadBanner();
    //加载公告
    loadNotice();
    // //加载今日特卖
    loadShopIndex();
    // //加载最近上传
    loadSaleToday();
    // //加载时尚服饰
    commodityRecommendation();
    // //加载服装馆模块
    loadClothingModule();
    // //加载美妆馆模块
    // loadBeautyModule();
    // //加载生活馆模块
    // loadLifeModule();
    // //加载3d模型展示
    // load3dModule();
    //加载服装馆banner图
    //loadClothingBanner();
    //加载美妆馆banner图
    //loadBeautyBanner();
    //加载生活馆banner图
    //loadLifeBanner();
    //加载品牌推荐
    // loadBrand();
    // $("#notice").slideUp();
    // $("#notice").imgscroll({
		// speed: 40,    //图片滚动速度
		// amount: 0,    //图片滚动过渡时间
		// width: 1,     //图片滚动步数
		// dir: "left"   // "left" 或 "up" 向左或向上滚动
    // });


    $("section.hot figcaption h3").each(function(){
        var maxwidth=30;
        if($(this).text().length>maxwidth){
            $(this).text($(this).text().substring(0,maxwidth));
            $(this).html($(this).html()+'…');
        }
    });

    $(".makeup-prolists .mb-hg h3").each(function(){
        var maxwidth=30;
        if($(this).text().length>maxwidth){
            $(this).text($(this).text().substring(0,maxwidth));
            $(this).html($(this).html()+'…');
        }
    });

    //首页banner轮播
    var len = $("#focus ul li").length;
    var index = 0;
    var picTimer;
    var btn="<div class='btn'>";
    if(len>1){
        for (var i = 0; i < len; i++) {
            btn += "<span></span>";
        }
    }
    btn += "</div>";
    $("#focus").append(btn);
    var bwidth=$(".banner .btn").width();
    $("#focus .btn").css("margin-left",-bwidth/2);
    $("#focus .btn span").eq(0).addClass("active");
    $("#focus .btn span").click(function(){
        index=$(this).index();
        $(this).addClass("active").siblings().removeClass("active");
        $("#focus").find("li").stop(true, false).fadeOut().eq(index).fadeIn();
    });


    $("#focus").hover(function () {
        clearInterval(picTimer);
    }, function () {
        picTimer = setInterval(function () {
            index++;
            if (index == len) { index = 0; }
            showPics(index);
        }, 5000);
    }).trigger("mouseleave");

    function showPics(index) {
        //var nowLeft = -index * 1920;
        //$("#focus ul").stop(true, false).animate({ "left": nowLeft }, 300);
        $("#focus .btn span").eq(index).addClass("active").siblings().removeClass("active");
        $("#focus").find("li").stop(true, false).fadeOut().eq(index).fadeIn();;
    }

    //公告滚动
    $("#notice").hover(function(){
        clearInterval(scrollNotice);
    },function(){
        scrollNotice = setInterval(function(){
            if($("#notice").height() > 20){
                $(this).css({"margin-top":"-20px"});
            }else{
                return false;
            }
        },5000);
    }).trigger("mouseleave");

    var str;
    //首页广告图轮播
    $(".min-banner").unslider({
        delay:5000,
        dots:true
    });
    //str+='</marquee>';
});

//加载公告
function loadNotice(){
    var url="http://localhost:8081/rest/content/getContent";
    $.ajax({
        url: url,
        type: 'get',
        dataType: 'jsonp',
        data : {"content":"公告"},
        jsonp: "callback",
        jsonpCallback:"getNotice",
        success:function(req){
            if(req!=null){
                var str='';
                str+='<marquee  id="marquee" onmouseout="this.start();" onmouseover="this.stop();" direction="left"  scrollamount=6 >';
                $.each(req, function(i,obj){
                    var url="https://www.baidu.com/";
                    if(i==req.length-1){
                        str+='<li  style="border-right:0">';
                    }else{
                        str+='<li>';
                    }
                    str+='<a href="'+url+'" target="_blansk">'+obj.title+'</a>';

                    str+='</li>';
                });
                $("#notice").html(str);
            }
        }
    });
}
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
                            +'<h3><a href="product.list.html">';
                        if(obj.IMG_TYPE ){
                            str+='<span class="mall-icon mr10">'+obj.IMG_TYPE+'</span>';
                        }else{
                            str+='';
                        }
                        str+=obj.COMMODITY_TYPE_NAME+'</a></h3>'
                            +' <ul class="nav-dd clearfix">';
                        //再遍历第二级的
                        $.each(obj.s2List, function(i2,obj2){
                            if(obj2.COMMODITY_TYPE_NAME){
                                str+='<li><a href="product.list.html">'+obj2.COMMODITY_TYPE_NAME+'</a></li>';
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

//加载banner
function loadBanner(){
    var req = '';
    var url="http://localhost:8081/rest/content/getContent";
    $.ajax({
        url: url,
        type: 'get',
        dataType: 'jsonp',
        data : {"content":"轮播图"},
        jsonp: "callback",
        jsonpCallback:"getCarousel",
        success:function(data){
            req = data;
            if(req!=null){
                    var str='';
                    $.each(req, function(i,obj){
                        str+='<li><a href="'+obj.url+'"  target="_blank" style="background-image:url('+obj.pic+')"></a></li>';
                    });
                    $("#clearfixindexid").html(str);
                }

        },
        error:function(data){
            res = data;
        }
    });
}


//加载首页今日特卖商品
function loadShopIndex(){
    var url="http://localhost:8081/rest/content/getContent";
    $.ajax({
        url: url,
        type: 'get',
        dataType: 'jsonp',
        data : {"content":"今日特卖"},
        jsonp: "callback",
        jsonpCallback:"getSuper",
        success:function(data){
            if(data){
                if(data.length>0){
                    var str='';
                    $.each(data, function(i,obj){
                        //str+='<figure onClick="window.open()"></figure>'
                        var maxwidth=30;
                        var name=obj.title;
                        if(name.length > maxwidth){
                            name=name.substring(0,maxwidth)+"...";
                        }
                        var COMMODITY_IMG = obj.COMMODITY_IMG_300;
                        if(!COMMODITY_IMG){
                            COMMODITY_IMG = obj.COMMODITY_IMG;
                        }
                        str+='<figure onClick="editCommodity('+obj.id+')"><img src="'+obj.pic+'" alt=""/></a><figcaption><h3>'+name+'</h3><h4><small>￥</small>';
                        if(null != obj.subTitle){
                            str += obj.subTitle+'</h4> </figcaption></figure>';
                        }else{
                            str += obj.subTitle+'</h4> </figcaption></figure>';
                        }
                    });
                    $("#hotListsId").html(str);
                }
            }
        }
    });
}
//加载今日上新
function loadSaleToday(){
    var url="http://localhost:8081/rest/content/getContent";
    $.ajax({
        url: url,
        type: 'get',
        dataType: 'jsonp',
        data : {"content":"时尚服饰"},
        jsonp: "callback",
        jsonpCallback:"getNew",
        success:function(html){
            if(html){
                if(html!=null && html.length>0){
                    var str='';
                    var i=1;
                    $.each(html, function(i,obj){
                        //str+='<figure onClick="window.open()"></figure>'
                        if(i < 10){
                            var maxwidth=30;
                            var name=obj.title;
                            if(name.length > maxwidth){
                                name=name.substring(0,maxwidth)+"...";
                            }
                            var COMMODITY_IMG = obj.pic;
                            if(!COMMODITY_IMG){
                                COMMODITY_IMG = obj.COMMODITY_IMG;
                            }
                            str+='<figure style="margin-top:3px;" onClick="editCommodity('+obj.id+')"><img src="'+obj.pic+'" alt=""/></a><figcaption><h3>'+name+'</h3><h4><small>￥</small>';

                            if(null != obj.subTitle){
                                str += obj.subTitle+'</h4> </figcaption></figure>';
                            }else{
                                str += obj.subTitle+'</h4> </figcaption></figure>';
                            }
                        }
                        i++;
                    });
                    $("#loadSaleToday_ID").html(str);
                }
            }
        }
    });
}
//时尚服饰
function commodityRecommendation(){
    var url="http://localhost:8081/rest/content/getContent";
    $.ajax({
        url: url,
        type: 'get',
        dataType: 'jsonp',
        data : {"content":"时尚服饰"},
        jsonp: "callback",
        jsonpCallback:"getFashion",
        success:function(html){

                if(html!=null && html.length>0){
                    var str='';
                    var i=1;
                    $.each(html, function(i,obj){
                        if(i < 10){
                            var maxwidth=30;
                            var name=obj.title;
                            if(name.length > maxwidth){
                                name=name.substring(0,maxwidth)+"...";
                            }
                            var COMMODITY_IMG = obj.pic;
                            if(!COMMODITY_IMG){
                                COMMODITY_IMG = obj.pic;
                            }
                            str+='<figure  >';
                            str+='<div class="pic">';
                            str+='<div>';
                            // if(obj.CM_WHETHER_MODELING == 1){
                            //     if(obj.MODEL_STATUS <3 || null == obj.MODEL_STATUS){
                            //         str+='<a href="javaScript:void();" onclick="toDesign('+obj.id+')"><i></i>我要设计</a>';
                            //     }
                            //     str+='<a href="javaScript:void();" onclick="editInvestment('+obj.id+');" class="model"><i></i>我要投资</a>';
                            // }
                            str+='</div>';
                            str+='<a href=""><img onClick="editCommodity('+obj.id+')" src="'+obj.pic+'" alt=""/></a>';
                            str+='</div>';
                            str+='<figcaption>';
                            str+='<h3 onClick="editCommodity('+obj.id+')">'+name+'</h3>';
                            str+='<h4><small>￥';
                            if(null != obj.subTitle){
                                str += obj.subTitle;
                            }else{
                                str += obj.subTitle ;
                            }
                            str+='</small></h4></figcaption></figure>';
                        }
                        i++;
                    });
                    $("#loadRecommendation_ID").html(str);
                }

        }
    });
}
//商品详情
function editCommodity(Id){
    var str=basePath+'eshop/commodity/proInfo.do?ID='+Id;
    window.open(str);
}
//我要投资
function editInvestment(Id){
    if(isLogin){
        if(isD){
            var str=basePath+'eshop/commodity/proInfo.do?openType=2&ID='+Id;
            window.open(str);
        }else{
            alert("您还不是合伙人，请前往合伙人申请中心");
            location.href= basePath + "eshop/joinController/toJoinChoice.do"
        }
    }else{
        alert("未登录，无法投资");
        location.href= basePath + "eshop/loginController/toLogin.do?type=1";
    }

}
//商品列表
function showCommodityList(Id){
    var str=basePath+'eshop/commodity/cmTypeList.do?hrefType=cmodule&COMMODITY_MODULE='+Id;
    window.open(str);
}
//根据href跳转
function showCommodityList_href(url){
    var str=url;
    window.open(str);
}

//加载服装馆模块
function loadClothingModule(){
    var param = {};
    param["LOCATION_TYPE"] = "2";
    var url="http://localhost:8081/rest/content/getContent";
    $.ajax({
        url: url,
        type: 'get',
        dataType: 'jsonp',
        data : {"content":"服装馆"},
        jsonp: "callback",
        jsonpCallback:"getClothes",
        success:function(html){
            if(html){
                if(html!=null && html.length>0){
                    var str='';
                    $.each(html, function(i,obj){
                        if(i<=1){
                            str+='<figure onClick="showCommodityList_href(\''+1+'\');" class="fig1-house"><img src="'+obj.pic+'" alt=""/></a>'
                            str+='</figure>'
                        }
                    });
                    $("#houseContentLists").html(str);
                }

                // if(html.cmtList!=null && html.cmtList.length>0){
                //     var str='';
                //     $.each(html.cmtList, function(i,obj){
                //         var cmTypeURLR1 = basePath+'eshop/commodity/cmTypeList.do?hrefType=ctype&COMMODITY_TYPE_CODE='+obj.COMMODITY_TYPE_CODE;
                //         str+='<a href="'+cmTypeURLR1+'">'+obj.COMMODITY_TYPE_NAME+'</a>&nbsp;&nbsp;·&nbsp'
                //     });
                //     $("#cmTypeList_01").html(str);
                // }
                    var str='';
                    $.each(html, function(i,obj){
                        if(i>1){
                            str+='<li><a href="'+1+'"  target="_blank"><img src="'+obj.pic+'" alt=""/></a></li>'
                        }
                    });
                    $("#clothingBannerId").html(str);

            }
        }
    });
}

//加载美妆馆模块
function loadBeautyModule(){
    var param = {};
    param["LOCATION_TYPE"] = "2";
    $.ajax({
        type : "POST",
        async : false,
        data : param,
        url : basePath+"eshop/commoditymodule/loadModuleList.do?MODEL_TYPE=CT201608090123"+"&limit=6",
        dataType : "json",
        success : function(html) {
            if(html.msg){
                if(html.list!=null && html.list.length>0){
                    var rowHt='';
                    $.each(html.list, function(i,obj){
                        rowHt+='<div  onClick="showCommodityList_href(\''+obj.MODEL_SPECIALURL+'\');"><img src="'+basePathImg+""+obj.MODEL_IMAGE+'"></div>'
                        //obj.MODEL_MESSAGE
                        //obj.MODULE_NAME
                    });
                    $("#loadBeautyModule").html(rowHt);
                }
                if(html.bannerList!=null && html.bannerList.length>0){
                    var str='';
                    $.each(html.bannerList, function(i,obj){
                        if(obj.JUMP_TYPE=='02'){
                            str+='<li><a href="'+obj.JUMP_ADDRESS+'" target="_blank"><img src="'+basePathImg+''+obj.BANNER_IMAGE+'" alt=""/></a></li>'
                        }else if(obj.JUMP_TYPE=='03'){
                            str+='<li><a href="'+obj.JUMP_ADDRESS+'"><img src="'+basePathImg+''+obj.BANNER_IMAGE+'" alt=""/></a></li>'
                        }else{
                            str+='<li><a href="#" onclick="javascript:return false;"><img src="'+basePathImg+''+obj.BANNER_IMAGE+'" alt=""/></a></li>'
                        }
                        //str+='<li><a href="#"><img src="'+basePathImg+''+obj.BANNER_IMAGE+'" alt=""/></a></li>'
                    });
                    $("#beautyBannerId").html(str);
                }

                if(html.cmtList!=null && html.cmtList.length>0){
                    var str='';
                    $.each(html.cmtList, function(i,obj){
                        var cmTypeURLR1 = basePath+'eshop/commodity/cmTypeList.do?hrefType=ctype&COMMODITY_TYPE_CODE='+obj.COMMODITY_TYPE_CODE;
                        str+='<a href="'+cmTypeURLR1+'">'+obj.COMMODITY_TYPE_NAME+'</a>&nbsp;&nbsp;·&nbsp'
                    });
                    $("#cmTypeList_02").html(str);
                }
            }
        }
    });
}

//加载生活馆模块
function loadLifeModule(){
    var param = {};
    param["LOCATION_TYPE"] = "2";
    $.ajax({
        type : "POST",
        async : false,
        data : param,
        url : basePath+"eshop/commoditymodule/loadModuleList.do?MODEL_TYPE=CT201608090124"+"&limit=6",
        dataType : "json",
        success : function(html) {
            if(html.msg){
                if(html.list!=null && html.list.length>0){
                    var rowHt='';
                    $.each(html.list, function(i,obj){
                        rowHt+='<div  onClick="showCommodityList_href(\''+obj.MODEL_SPECIALURL+'\');"><img src="'+basePathImg+""+obj.MODEL_IMAGE+'"></div>';
                        //obj.MODEL_MESSAGE
                        //obj.MODULE_NAME
                    });
                    $("#loadLifeModule").html(rowHt);
                }
                if(html.bannerList!=null && html.bannerList.length>0){
                    var str='';
                    $.each(html.bannerList, function(i,obj){
                        if(obj.JUMP_TYPE=='02'){
                            str+='<li><a href="'+obj.JUMP_ADDRESS+'"  target="_blank"><img src="'+basePathImg+''+obj.BANNER_IMAGE+'" alt=""/></a></li>'
                        }else if(obj.JUMP_TYPE=='03'){
                            str+='<li><a href="'+obj.JUMP_ADDRESS+'"><img src="'+basePathImg+''+obj.BANNER_IMAGE+'" alt=""/></a></li>'
                        }else{
                            str+='<li><a href="#" onclick="javascript:return false;"><img src="'+basePathImg+''+obj.BANNER_IMAGE+'" alt=""/></a></li>'
                        }
                        //str+='<li><a href="#"><img src="'+basePathImg+''+obj.BANNER_IMAGE+'" alt=""/></a></li>'
                    });
                    $("#lifeBannerId").html(str);
                }
                if(html.cmtList!=null && html.cmtList.length>0){
                    var str='';
                    $.each(html.cmtList, function(i,obj){
                        var cmTypeURLR1 = basePath+'eshop/commodity/cmTypeList.do?hrefType=ctype&COMMODITY_TYPE_CODE='+obj.COMMODITY_TYPE_CODE;
                        str+='<a href="'+cmTypeURLR1+'">'+obj.COMMODITY_TYPE_NAME+'</a>&nbsp;&nbsp;·&nbsp'
                    });
                    $("#cmTypeList_03").html(str);
                }
            }
        }
    });
}

//加载3d模型展示
function load3dModule(){
    var param = {};
    param["LOCATION_TYPE"] = "2";
    $.ajax({
        type : "POST",
        async : false,
        data : param,
        url : basePath+"eshop/commoditymodule/loadModuleList.do?MODEL_TYPE=CT201703030164"+"&limit=6",
        dataType : "json",
        success : function(html) {
            if(html.msg){
                if(html.bannerList!=null && html.bannerList.length>0){
                    var str='';
                    $.each(html.bannerList, function(i,obj){
                        str+='<a href="'+obj.JUMP_ADDRESS+'"><img src="'+basePathImg +obj.BANNER_IMAGE+'" alt=""/></a> '
                    });
                    $("#modelAreaData").html(str);
                }
            }
        }
    });
}

//加载品牌推荐
function loadBrand(){
    var url=basePath+'eshop/sysbrand/loadBrand.do';
    jQuery.ajax({
        type: "post",
        data: "",
        url: url,
        dataType: "json",
        cache: false,
        success: function(req){
            if(req!=null){
                if(req.varList!=null && req.varList.length>0){
                    var str='';
                    $.each(req.varList, function(i,obj){
                        str+='<li><a href="javaScript:isSelectBrand('+obj.ID+',\''+obj.BRAND_CODE+'\');"><img src="'+basePathImg+''+obj.BRAND_PATH+'" alt=""/></a></li>'
                    });
                    $("#sysBrand").html(str);
                }
            }

        }
    });
}

function isSelectBrand(COMMODITY_BRAND,code){
    window.location.href = basePath+'eshop/commodity/cmBrandList.do?COMMODITY_BRAND='+COMMODITY_BRAND;
}
//商品详情
function toDesign(Id){
    window.location.href = basePath+'cp2m/toUpDesignDemo.do?ID='+Id;
}
