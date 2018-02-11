package com.zxj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zxj.pojo.TbItem;
import com.zxj.pojo.TbItemDesc;
import com.zxj.pojo.TbItemParamItem;
import com.zxj.pojo.UpdatePojo;
import com.zxj.service.ItemService;

import common.EasyUiDateGridResult;
import common.TaotaoResult;
import util.JsonUtils;

@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;
	
//	@RequestMapping("item/{itemId}")
//	@ResponseBody
//	private TbItem getItemById(@PathVariable Long itemId){
//		TbItem item = itemService.getItemById(itemId);
//		return item;		
//	}
	
	//EasyUiDateGrid商品列表分页查询
	@RequestMapping("item/list")
	@ResponseBody
	private EasyUiDateGridResult getItemById(Integer page,Integer rows){
		EasyUiDateGridResult result = itemService.getItemList(page, rows);
		return result;		
	}
	
	//接收新商品数据,并插入数据库
	//限定只接收POST方式提交的数据
	@RequestMapping(value = "item/save",method = RequestMethod.POST)
	@ResponseBody
	private TaotaoResult createItem(TbItem item, String desc,String itemParams){
		TaotaoResult result = itemService.createItem(item, desc,itemParams);
		return result;		
	}
	
	
	//接收商品id，调用Service查询规格参数，返回html片段。把html片段传递给jsp。
	@RequestMapping("item/{itemId}")
	public String showItemParam(@PathVariable Long itemId, Model model) {
		String html = itemService.getItemParamHtml(itemId);
		model.addAttribute("html", html);
		return "itemparam";
	}
	
	
	//接收商品id，调用Service删除商品，并且返回TaotaoResult
	@RequestMapping(value = "/rest/item/delete",method = RequestMethod.POST)
	@ResponseBody
	private TaotaoResult deleteItem(String ids){
		TaotaoResult result = itemService.deleteItem(ids);
		return result;		
	}

	//跳转到编辑视图
	@RequestMapping(value = "/rest/page/item-edit")
	private String showEditPage(){
		return "item-edit";		
	}
	
	
	
	//接收商品id，调用Service获取商品规格
	@RequestMapping(value = "/rest/item/param/item/query/{id}")
	@ResponseBody
	private TaotaoResult queryItemParamById(@PathVariable Long id){
		TaotaoResult result = itemService.queryItemParamById(id);
		return result;		
	}
	
	//接收商品id，调用Service获取商品描述
	@RequestMapping(value = "/rest/item/query/item/desc/{id}")
	@ResponseBody
	private TaotaoResult queryItemDescById(@PathVariable Long id){
		TaotaoResult result = itemService.queryItemDescById(id);
		return result;		
	}
	
	//接收商品id，调用Service让商品状态改为下架
	@RequestMapping(value = "/rest/item/instock")
	@ResponseBody
	private TaotaoResult downItemById(String ids){
		TaotaoResult result = itemService.downItemById(ids);
		return result;		
	}
	
	//接收商品id，调用Service让商品状态改为上架
	@RequestMapping(value = "/rest/item/reshelf")
	@ResponseBody
	private TaotaoResult upItemById(String ids){
		TaotaoResult result = itemService.upItemById(ids);
		return result;		
	}
	
	//接收整张表单，调用Service修改商品，商品详情，商品规格
	@RequestMapping(value = "/rest/item/update")
	@ResponseBody
	private TaotaoResult updateItemAndDescAndParam(UpdatePojo pojo){
		TaotaoResult result = itemService.updateItemAndDescAndParam(pojo);
		return TaotaoResult.ok();		
	}
	
	
}
