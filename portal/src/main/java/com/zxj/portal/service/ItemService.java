package com.zxj.portal.service;

import com.zxj.pojo.TbItem;

public interface ItemService {
	public TbItem getItemById(Long itemId);

	String getItemDescById(Long itemId);

	String getItemParamById(Long itemId);
}
