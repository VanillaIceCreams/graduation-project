package com.zxj.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	/**
	 * 展示登录页面
	 */
	@RequestMapping("/page/login")
	public String showLogin(String redirectURL,Model model) {
		int type = 1;
		if (redirectURL==null){
			redirectURL = "";
		}
		model.addAttribute("redirect",redirectURL);
		model.addAttribute("type",type);
		return "login";
	}
	/**
	 * 展示注册页面
	 */
	@RequestMapping("/page/register")
	public String showRegister() {
		return "login";
	}
}
