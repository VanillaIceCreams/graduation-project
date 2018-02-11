package com.zxj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zxj.service.ItemCatService;
import com.zxj.service.ItemCatServiceImpl;

import common.EastUiTreeResult;

@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
	@Autowired
	private ItemCatService itemCatService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<EastUiTreeResult> getItemCatList(@RequestParam(value="id", defaultValue="0")Long parentId) {
		List<EastUiTreeResult> list = itemCatService.getItemCarList(parentId);
		
		return list;
	}
	
}

