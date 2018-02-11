package com.zxj.portal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zxj.pojo.TbUser;
import com.zxj.portal.service.UserService;
/**
 * 登陆过滤器
 * @author Administrator
 *
 */
public class LoginInterceptor implements HandlerInterceptor {
	
	@Autowired 
	private UserService userService;

	private String SSO_LOGIN_URL="http://localhost:8085/page/login";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 1、拦截请求url
		// 2、从cookie中取token
		// 3、如果没有toke跳转到登录页面。
		// 4、取到token，需要调用sso系统的服务查询用户信息。
		TbUser user = userService.getUserByToken(request, response);
		// 5、如果用户session已经过期，跳转到登录页面
		if (user == null) {
			response.sendRedirect(SSO_LOGIN_URL+"?redirectURL="+request.getRequestURI());
			return false;
		}
		//把用户对象存入request中
		request.setAttribute("user", user);
		// 6、如果没有过期，放行。
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
