package com.zxj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zxj.pojo.TbContent;
import com.zxj.service.ContentService;

import common.EasyUiDateGridResult;
import common.TaotaoResult;

@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult insertContent(TbContent content) {
		TaotaoResult result = contentService.insertContent(content);
		return result;
	}
	/**
	 * 用内容板块ID查询内容
	 * @param categoryId
	 * @return
	 */
	@RequestMapping("/query/list")
	@ResponseBody
	public EasyUiDateGridResult queryContentListByCategoryId(Long categoryId,Integer page,Integer rows) {
		EasyUiDateGridResult result = contentService.queryContentListByCategoryId(categoryId,page,rows);
		return result;
	}
	
	
	@RequestMapping("/delete")
	@ResponseBody
	public TaotaoResult deleteById(String ids) {
		TaotaoResult result = contentService.deleteById(ids);
		return result;
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public TaotaoResult edit(TbContent content) {
		TaotaoResult result = contentService.edit(content);
		return result;
	}

}

