package com.zxj.rest.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.support.json.JSONUtils;
import com.zxj.rest.common.ItemCatResult;
import com.zxj.rest.service.ItemCatService;

import util.JsonUtils;
@Controller
public class getItemCatListController {
	@Autowired
	ItemCatService  itemCatService;
	
	
	@RequestMapping(value="/item/cat/list",produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	@ResponseBody
	public String getItemCatList(String callback) {
		ItemCatResult result = itemCatService.getItemCatList();

		if (StringUtils.isBlank(callback)) {
			//需要把result转换成字符串
			String json = JsonUtils.objectToJson(result);
			return json;
		}
		//如果字符串不为空，需要支持jsonp调用
		//需要把result转换成字符串
		String json = JsonUtils.objectToJson(result);
		return callback + "(" + json + ");";

	}

}
