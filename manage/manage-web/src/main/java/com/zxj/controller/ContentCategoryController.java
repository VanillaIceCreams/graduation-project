package com.zxj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zxj.service.ContentCatgoryService;

import common.EastUiTreeResult;
import common.TaotaoResult;

@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

	@Autowired
	private ContentCatgoryService contentCatgoryService;
	/*
	 * 获取列表
	 */
	@RequestMapping("/list")
	@ResponseBody
	public List<EastUiTreeResult> getContentCatList(@RequestParam(value="id", defaultValue="0")Long parentId) {
		List<EastUiTreeResult> list = contentCatgoryService.getContentCatList(parentId);
		return list;
		
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public TaotaoResult createContentCategory(Long parentId,String name) {
		TaotaoResult result = contentCatgoryService.createContentCategory(parentId,name);
		return result;
		
	}
	@RequestMapping("/update")
	@ResponseBody
	public TaotaoResult updateContentCategory(Long id,String name) {
		TaotaoResult result = contentCatgoryService.updateContentCategory(id,name);
		return result;
		
	}
	@RequestMapping("/delete")
	@ResponseBody
	public TaotaoResult deleteContentCategory(Long id,Long parentId) {
		TaotaoResult result = contentCatgoryService.deleteContentCategory(id,parentId);
		return result;
		
	}
	
}

