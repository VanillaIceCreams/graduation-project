package com.zxj.service;

import java.util.List;

import common.EastUiTreeResult;
import common.TaotaoResult;

public interface ContentCatgoryService {
	public List<EastUiTreeResult> getContentCatList(Long parentId);
	//创建板块
	public TaotaoResult createContentCategory(Long parentId, String name);
	//修改板块名
	public TaotaoResult updateContentCategory(Long id, String name);
	//级联删除节点
	public TaotaoResult deleteContentCategory(Long id, Long parentId);
}
