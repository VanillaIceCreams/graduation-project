package com.zxj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zxj.service.ItemParamService;

import common.EasyUiDateGridResult;
import common.TaotaoResult;

@Controller
public class ItemParamController {
	@Autowired
	ItemParamService itemParamService;
	
	@RequestMapping("/item/param/query/itemcatid/{cid}")
	@ResponseBody
	public TaotaoResult getItemParamByCid(@PathVariable long cid) {
		TaotaoResult result = itemParamService.getItemParamByCid(cid);
		return result;
	}
	
	
	//EasyUiDateGrid商品规格参数  列表分页查询
	@RequestMapping("item/param/list")
	@ResponseBody
	private EasyUiDateGridResult getItemParamById(Integer page,Integer rows){
		EasyUiDateGridResult result = itemParamService.getItemList(page, rows);
		return result;		
	}
	
	//保存规格参数模板
	@RequestMapping("/item/param/save/{cid}")
	@ResponseBody
	private TaotaoResult saveItemParam(@PathVariable long cid,String paramData){
		TaotaoResult result = itemParamService.saveItemParam(cid,paramData);
		return result;		
	}
	
	//保存规格参数模板
	@RequestMapping("/item/param/delete")
	@ResponseBody
	private TaotaoResult deleteItemParam(String ids){
		TaotaoResult result = itemParamService.deleteItemParam(ids);
		return result;		
	}
	


}
