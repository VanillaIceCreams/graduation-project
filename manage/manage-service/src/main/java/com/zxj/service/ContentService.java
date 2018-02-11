package com.zxj.service;

import com.zxj.pojo.TbContent;

import common.EasyUiDateGridResult;
import common.TaotaoResult;

public interface ContentService {
	public TaotaoResult insertContent(TbContent content);
	//根据板块找内容
	public EasyUiDateGridResult queryContentListByCategoryId(Long categoryId, Integer page, Integer rows);
	//根据ID删内容
	public TaotaoResult deleteById(String ids);
	//修改内容
	public TaotaoResult edit(TbContent content);
}
