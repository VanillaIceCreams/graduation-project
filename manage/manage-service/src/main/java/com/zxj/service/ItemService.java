package com.zxj.service;

import com.zxj.pojo.TbItem;
import com.zxj.pojo.TbItemDesc;
import com.zxj.pojo.TbItemParamItem;

import common.EasyUiDateGridResult;
import common.TaotaoResult;
import com.zxj.pojo.TbItemParamItemExample.Criteria;
import com.zxj.pojo.UpdatePojo;
public interface ItemService {
	//通过id获取商品
	TbItem getItemById(Long itemId);
	//分页获取商品集合
	EasyUiDateGridResult getItemList(int page, int rows);
	//创建商品以及描述
	TaotaoResult createItem(TbItem item, String desc, String itemParams);
	
	String getItemParamHtml(Long itemId);
	//删除选中商品
	TaotaoResult deleteItem(String ids);
	
	//根据ID查询商品参数
	TaotaoResult queryItemParamById(Long id);
	//根据ID查询商品详细描述
	TaotaoResult queryItemDescById(Long id);
	//根据ID让商品状态改为下架
	TaotaoResult downItemById(String ids);
	//根据ID让商品状态改为上架
	TaotaoResult upItemById(String ids);
	//修改商品，商品详情，商品规格
	TaotaoResult updateItemAndDescAndParam(UpdatePojo pojo);

}
