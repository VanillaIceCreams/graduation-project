package com.zxj.search.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zxj.search.pojo.SearchResult;
import com.zxj.search.service.ItemSearchService;

import common.TaotaoResult;
import util.ExceptionUtil;

@Controller
public class ItemSearchController {
	@Autowired
	private ItemSearchService itemSearchService;

	@RequestMapping("/importall")
	@ResponseBody
	public TaotaoResult importAll() {
		try {
			TaotaoResult result = itemSearchService.importItems();
			System.out.println(123);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}

	@RequestMapping("/q")
	@ResponseBody
	public SearchResult search(@RequestParam(defaultValue = "") String keyword,
			@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "30") Integer rows) {
		try {
			//转换字符集
			keyword = new String(keyword.getBytes("iso8859-1"), "utf-8");
			SearchResult searchResult = itemSearchService.search(keyword, page, rows);
			return searchResult;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		

	}

}
