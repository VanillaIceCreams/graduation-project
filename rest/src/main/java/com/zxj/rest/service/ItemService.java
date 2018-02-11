package com.zxj.rest.service;

import com.zxj.pojo.TbItem;
import com.zxj.pojo.TbItemDesc;
import com.zxj.pojo.TbItemParamItem;

public interface ItemService {
	public TbItem getItemById(Long itemId);

	TbItemParamItem getItemParamById(Long itemId);

	TbItemDesc getItemDescById(Long itemId);
}
