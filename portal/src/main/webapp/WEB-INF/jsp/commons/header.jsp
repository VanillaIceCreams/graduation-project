<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="/js/base-v1.js" charset="utf-8"></script>
<!--shortcut start-->
<jsp:include page="shortcut.jsp" />
<!--shortcut end-->
<div id="o-header-2013">
		<div id="search-2013">
			<div class="i-search ld">
				<ul id="shelper" class="hide">
				</ul>
				<div class="form">
					<input type="text" class="text" accesskey="s" id="key" autocomplete="off" onkeydown="javascript:if(event.keyCode==13) search('key');">
					<input type="button" value="搜索" class="button" onclick="search('key');return false;" clstag="homepage|keycount|home2013|03a">
				</div>
			</div>
			<div id="hotwords" clstag="homepage|keycount|home2013|03b"></div>
		</div>
		<!--search end-->
		<div id="my360buy-2013">
			<dl>
				<dt class="ld"><s></s><a href="/cart/cart.html" clstag="homepage|keycount|home2012|04a">我的购物车</a><b></b></dt>
				<dd>
					<div class="loading-style1"></div>
				</dd>
			</dl>
		</div>
		<!--my360buy end-->
		<div id="settleup-2013" clstag="homepage|keycount|home2013|05a">
			<dl>
				<dt class="ld"><s></s><span class="shopping"><span id="shopping-amount"></span></span><a href="/cart/cart.html" id="settleup-url">去购物车结算</a> <b></b> </dt>
			</dl>
		</div>
		<!--settleup end-->
	</div>
	<!--header end-->

</div>
<script type="text/javascript">
(function(){if(pageConfig.navId){var object=document.getElementById("nav-"+pageConfig.navId);if(object)object.className+=" curr";}})();
</script>