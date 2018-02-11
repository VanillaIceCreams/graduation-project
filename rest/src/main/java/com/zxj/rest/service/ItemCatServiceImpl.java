package com.zxj.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.zxj.mapper.TbItemCatMapper;
import com.zxj.pojo.TbItemCat;
import com.zxj.pojo.TbItemCatExample;
import com.zxj.pojo.TbItemCatExample.Criteria;
import com.zxj.rest.common.CatNode;
import com.zxj.rest.common.ItemCatResult;
import com.zxj.rest.jedis.JedisClient;

import util.JsonUtils;

@Service
public class ItemCatServiceImpl implements ItemCatService {
	@Autowired
	TbItemCatMapper itemCatMapper;
	@Autowired
	JedisClient jedisClient;
	@Value("REDIS_ITEM_CAT")//redis的key
	private String REDIS_ITEM_CAT;
	@Override
	public ItemCatResult getItemCatList() {
		//查询数据库前先找redis缓存
		try {
			String json = jedisClient.get(REDIS_ITEM_CAT);
			if(!StringUtils.isBlank(json)){
				System.out.println("查缓存");
				return JsonUtils.jsonToPojo(json, ItemCatResult.class);
			}
		} catch (Exception e) {
			
		}
		
		//调用递归方法查询商品分类列表
		List catList = getItemCatList(0l);
		//返回结果
		ItemCatResult result = new ItemCatResult();
		result.setData(catList);
		
		//将结果缓存入redis
		jedisClient.set(REDIS_ITEM_CAT, JsonUtils.objectToJson(result));
		System.out.println("查询了数据库");
		return result;
	}
	
	private List getItemCatList(Long parentId) {
		//根据parentId查询列表
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		List resultList = new ArrayList<>();
		for (TbItemCat tbItemCat : list) {
			//如果是父节点
			if (tbItemCat.getIsParent()) {
				CatNode node = new CatNode();
				node.setUrl("/products/"+tbItemCat.getId()+".html");
				//如果当前节点为第一级节点
				if (tbItemCat.getParentId() == 0) {
					node.setName("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
				} else {
					node.setName(tbItemCat.getName());
				}
				node.setItems(getItemCatList(tbItemCat.getId()));
				//把node添加到列表
				resultList.add(node);
			} else {
				//如果是叶子节点
				String item = "/products/"+tbItemCat.getId()+".html|" + tbItemCat.getName();
				resultList.add(item);
			}
		}
		return resultList;
	}

}
