package com.zxj.service;

import common.EasyUiDateGridResult;
import common.TaotaoResult;

public interface ItemParamService {

	TaotaoResult getItemParamByCid(long cid);

	EasyUiDateGridResult getItemList(Integer page, Integer rows);

	TaotaoResult saveItemParam(long cid, String paramData);

	TaotaoResult deleteItemParam(String ids);

	
	
}
