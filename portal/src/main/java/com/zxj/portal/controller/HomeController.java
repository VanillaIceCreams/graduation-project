package com.zxj.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping("/zxjShop.html")
	public String goHome(){
		return "index";		
	}
}
