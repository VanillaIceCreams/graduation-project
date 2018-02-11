package com.zxj.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxj.mapper.TbItemCatMapper;
import com.zxj.pojo.TbItemCat;
import com.zxj.pojo.TbItemCatExample;
import com.zxj.pojo.TbItemCatExample.Criteria;

import common.EastUiTreeResult;

@Service
public class ItemCatServiceImpl implements ItemCatService{
	
	@Autowired
	private TbItemCatMapper itemCatMapper;

	@Override
	public List<EastUiTreeResult> getItemCarList(long parentId) {
		//创建一个查询语句对象
		TbItemCatExample example = new TbItemCatExample();
		//创建一个基础where子句对象
		Criteria criteria = example.createCriteria();
		//设置where子句
		criteria.andParentIdEqualTo(parentId);
		//查询
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		//创建 List<EastUiTreeResult>
		 List<EastUiTreeResult> list2 = new ArrayList<EastUiTreeResult>();
		//构造EastUiTreeResult
		for (TbItemCat tbItemCat : list) {
			EastUiTreeResult result = new EastUiTreeResult(tbItemCat.getId(), tbItemCat.getName(), tbItemCat.getIsParent()?"closed":"open");
			list2.add(result);
		}
		return list2;
	}



}
