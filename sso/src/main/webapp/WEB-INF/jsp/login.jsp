<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
    <title>登录淘淘</title>
    <link href="/css/showLoading.css" rel="stylesheet" type="text/css">
    <link href="/css/common.css" rel="stylesheet" type="text/css">
    <link href="/css/signLogin.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>

    <script type="text/javascript" src="/js/jquery.showLoading.js"></script>
    <script type="text/javascript" src="/js/jquery.showLoading.min.js"></script>
    <script type="text/javascript" src="/js/jquery.tips.js"></script>
    <script type="text/javascript" src="/js/signLogin.js"></script>
</head>
<body>

<!-- 引入页首 -->
<header class="sign_header">
    <div class="grid">
        <div class="fl w49">
            <a href="javascript:void(0)"><h1>多维世界商城</h1></a>
        </div>
        <ul>
            <li><a href="javascript:void(0)" class="c-r3"><span class="Regular-icon mr15" style="vertical-align:inherit"></span>手机微商城</a></li>
            <li><a href="javascript:void(0)"   target="_blank">新手指南</a></li>
            <li><a href="javascript:void(0)">我的试衣间</a></li>
            <li><a href="http://localhost:8082/zxjShop.html">首页</a></li>
        </ul>
    </div>
</header>

<div class="sign-banner">
    <div class="grid clearfix pt20 pb20">
        <div class="signLogin-Box">
            <ul class="signLogin-title clearfix">
                <li class="active" id="login">账号登录</li>
                <li id="register">账号注册</li>
            </ul>
            <div class="signLogin-main">
                <form id="loginForm">
                    <ul>
                        <li><input type="text" name="username" id="USERNAME" class="login-user" placeholder="手机号/用户名/注册邮箱"/></li>
                        <li><input type="password" name="password" id="PASSWORD" class="login-pass" placeholder="请输入密码"/></li>
                        <li class="clearfix">
                            <span class="fl"><input type="checkbox" id="autoLogin" value="1" class="mr5 vm"><label for="autoLogin">自动登录</label></span>
                            <a href="javascript:void(0)" class="fr">忘记密码？</a>
                        </li>
                        <li class="login-btn-one">
                            <a href="javascript:void(0)" class="login-btn" onclick="login()">登录</a>
                        </li>
                    </ul>
                </form>

                <form class="none" id="registerForm">
                    <input type="hidden" id="idCodeSSS" value="">
                    <input type="hidden" id="type" value="1">
                    <input type="hidden" id="currentTime" value="1493177963494">
                    <input type="hidden" value="" id="idCode2" />
                    <ul>

                        <li id="NAME_input" style="display: none;"><input type="text"  name="USERNAME"  onblur=" checkName()" placeholder="用户名必须字符开头" class="user"><span>*</span></li>
                        <li><input type="text" id="PHONE" name="username" class="login-tel" onblur="checkPhone();" placeholder="请输入手机号"/><span>*</span></li>
                        <li class="verfycode"><input type="text"  id="idCode" placeholder="请填写验证码"/><span>*</span>
                            <a href="javascript:void(0)"  onclick="getIdCode(this);"  class="gray-btn">获取手机验证码</a></li>
                        <li><input type="password" id="PASSWORD2" name="password" class="login-pass" placeholder="请输入密码"/><span>*</span></li>
                        <li><input type="password" id="biganPASSWORD" class="login-pass" placeholder="请再次输入密码"/><span>*</span></li>

                        <li><input type="checkbox" value="1" name="xieyi" id="sign-promise" class="mr5 vm"><label for="sign-promise"  >我已阅读并同意<a href="javascript:void(0)"  target="_blank">《多维世界商城使用协议》</a></label></li>
                        <li>
                            <a href="javascript:void(0)" class="login-btn" onclick="toRegister()">注册</a>
                        </li>
                    </ul>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- 引入页脚 -->



<footer class="sign_footer tc">
    <div class="grid">
        <p class="f16">
            <a >关于我们</a>  丨
            <a>友情链接</a>  丨
            <a >诚征英才</a>  丨
            <a >支付方式</a>  丨
            <a >售后服务</a>  丨
            <a >特色服务</a></p>
        <p class="c-88 mt20">Copyright 2016-2018 www.zzokk.com All rights Reserved 湘ICP备16004812号  公安网安备110112119号</p>
    </div>
</footer>

</body>
<script type="text/javascript">
    var redirectUrl = "${redirect}";//重定向到的url
    var type = "${type}"; //判断是去登录页面还是注册页面

</script>
</html>