package com.zxj.portal.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.zxj.portal.pojo.SearchResult;

import util.HttpClientUtil;
import util.JsonUtils;

@Controller
public class SearchController {
	@RequestMapping("/search.html")
	public String goSearch(@RequestParam(defaultValue = "") String q,
			@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "30") Integer rows, Model model) throws UnsupportedEncodingException {
		// 将get请求参数转码
		q = new String(q.getBytes("iso8859-1"), "utf-8");
		//通过webservice服务获取搜索信息
		String json = HttpClientUtil.doGet("http://localhost:8084/search/q?keyword=" + q + "&page="+page+"&rows="+rows);
		
		//将获得的json字符串转为对象
		SearchResult searchResult = JsonUtils.jsonToPojo(json, SearchResult.class);
		if(searchResult != null){
		//将对象设置入model传到前台
		model.addAttribute("totalPages", searchResult.getPageCount());
		model.addAttribute("page", searchResult.getCurPage());
		model.addAttribute("itemList", searchResult.getItemList());
		model.addAttribute("q",q);
		}
		return "search";
	}
}
