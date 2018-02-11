package com.zxj.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zxj.pojo.TbItem;
import com.zxj.pojo.TbItemDesc;
import com.zxj.pojo.TbItemParamItem;
import com.zxj.rest.service.ItemService;

import common.TaotaoResult;
import util.ExceptionUtil;

@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	/**
	 * 查询商品基本信息
	 * <p>Title: getItemById</p>
	 * <p>Description: </p>
	 * @param itemId
	 * @return
	 */
	@RequestMapping("/base/{itemId}")
	@ResponseBody
	public TaotaoResult getItemById(@PathVariable Long itemId) {
		try {
			TbItem item = itemService.getItemById(itemId);
			return TaotaoResult.ok(item);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
	/**
	 * 查询商品详细信息
	 * @param itemId
	 * @return
	 */
	@RequestMapping("/desc/{itemId}")
	@ResponseBody
	public TaotaoResult getItemDescById(@PathVariable Long itemId) {
		try {
			TbItemDesc itemDesc = itemService.getItemDescById(itemId);
			return TaotaoResult.ok(itemDesc);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
	/**
	 * 查询商品规格参数
	 * @param itemId
	 * @return
	 */
	@RequestMapping("/param/{itemId}")
	@ResponseBody
	public TaotaoResult getItemParamById(@PathVariable Long itemId) {
		try {
			TbItemParamItem itemParamItem = itemService.getItemParamById(itemId);
			return TaotaoResult.ok(itemParamItem);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}

}
