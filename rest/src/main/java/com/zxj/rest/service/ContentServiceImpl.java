package com.zxj.rest.service;

import java.util.List;

import com.zxj.mapper.TbContentCategoryMapper;
import com.zxj.pojo.*;
import common.TaotaoResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.zxj.mapper.TbContentMapper;
import com.zxj.pojo.TbContentExample.Criteria;
import com.zxj.rest.jedis.JedisClient;

import util.JsonUtils;

@Service
public class ContentServiceImpl implements ContentService{
	@Autowired
	TbContentMapper contentMapper;
	@Autowired
	TbContentCategoryMapper contentCategoryMapper;
	@Autowired
	JedisClient jedisClient;
	@Value("REDIS_CONTENT_KEY")//redis的hash的key
	private String REDIS_CONTENT_KEY;
	
	@Override
	public List<TbContent> getContentList(Long cid) {
		//添加缓存
		//查询数据库之前先查询缓存，如果有直接返回
		try {
			//从redis中取缓存数据
			String json = jedisClient.hget(REDIS_CONTENT_KEY, cid+"");
			if (!StringUtils.isBlank(json)) {
				//把json转换成List
				List<TbContent> list = JsonUtils.jsonToList(json, TbContent.class);
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 根据cid查询内容列表
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(cid);
		//执行查询
		List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
		
		//返回结果之前，向缓存中添加数据
		try {
			//为了规范key可以使用hash
			//定义一个保存内容的key，hash中每个项就是cid
			//value是list，需要把list转换成json数据。
			jedisClient.hset(REDIS_CONTENT_KEY, cid+"", JsonUtils.objectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public  List<TbContent> getContent(String content) {
        try {
            TbContentCategoryExample contentCategoryExample =  new TbContentCategoryExample();
            contentCategoryExample.createCriteria().andNameEqualTo(content);
            TbContentCategory tbContentCategory = contentCategoryMapper.selectByExample(contentCategoryExample).get(0);
            TbContentExample contentExample = new TbContentExample();
            contentExample.createCriteria().andCategoryIdEqualTo(tbContentCategory.getId());
            List<TbContent> tbContents = contentMapper.selectByExample(contentExample);
            return tbContents;
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }
	}




}
