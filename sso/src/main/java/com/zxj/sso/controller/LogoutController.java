package com.zxj.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zxj.sso.service.LogoutService;

@Controller
public class LogoutController {
	@Autowired
	LogoutService logoutService;

	@RequestMapping("/user/logout/{token}")
	public String logout(@PathVariable String token,HttpServletRequest request,
			HttpServletResponse response) {
			System.out.println(token);
			logoutService.logout(token,request,response);

			return "login";

		
	}

}
