// JavaScript Document

$(function() {

	$(".lists-colum4 figcaption h4").each(function () {
		var maxwidth = 40;
		if ($(this).text().length > maxwidth) {
			$(this).text($(this).text().substring(0, maxwidth));
			$(this).html($(this).html() + '…');
		}
	});

	//分类选择切换状态
	$(".classifyChoice li").click(function () {
		$(this).addClass("cur").siblings().removeClass("cur");
	})


	//品牌选择查看更多
	$(".classifyChoice-brand li:gt(10)").hide(); //将第10个以后的品牌隐藏
	$("#selectmore").click(function () {
		var _this = $(this);
		var _li = $(".classifyChoice-brand li:gt(8)");
		if (_li.is(":hidden")) {
			_this.addClass("select").html("收起<span class='mall-icon ml5' style='font-size:1.1em'>6</span>");
			$(".classifyChoice-brand li").show();
		} else {
			_this.removeClass("select").html("更多<span class='mall-icon ml5' style='font-size:1.1em'>5</span>");
			_li.hide();
		}

	})



})


