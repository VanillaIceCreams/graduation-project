package com.zxj.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zxj.portal.pojo.PortalItem;
import com.zxj.portal.service.ItemService;

@Controller
public class ItemController {
	@Autowired
	ItemService itemService;
	/**
	 * 返回商品信息
	 * @param itemId
	 * @param model
	 * @return
	 */
	@RequestMapping("/item/{itemId}")
	public String getItemById(@PathVariable Long itemId, Model model) {
		PortalItem item = (PortalItem) itemService.getItemById(itemId);
		model.addAttribute("item", item);
		return "item";
	}
	/**
	 * 返回商品详情
	 * @param itemId
	 * @return
	 */
	@RequestMapping(value = "/item/desc/{itemId}", produces = MediaType.TEXT_HTML_VALUE + ";charset=utf-8")
	@ResponseBody
	public String getItemDesc(@PathVariable Long itemId) {
		String desc = itemService.getItemDescById(itemId);
		return desc;
	}
	/**
	 * 返回商品参数
	 * @param itemId
	 * @return
	 */
	@RequestMapping(value="/item/param/{itemId}", produces=MediaType.TEXT_HTML_VALUE+";charset=utf-8")
	@ResponseBody
	public String getItemParam(@PathVariable Long itemId) {
		String paramHtml = itemService.getItemParamById(itemId);
		return paramHtml;
	}

}
