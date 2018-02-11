package com.zxj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping("/")
	public String goToIndex(){
		return "index";
	}
	
	
	@RequestMapping("/{page}")
	public String goToPage(@PathVariable String page){
		return page;
		
	}
}
