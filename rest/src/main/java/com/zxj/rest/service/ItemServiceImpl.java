package com.zxj.rest.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.zxj.mapper.TbItemDescMapper;
import com.zxj.mapper.TbItemMapper;
import com.zxj.mapper.TbItemParamItemMapper;
import com.zxj.pojo.TbItem;
import com.zxj.pojo.TbItemDesc;
import com.zxj.pojo.TbItemParamItem;
import com.zxj.pojo.TbItemParamItemExample;
import com.zxj.pojo.TbItemParamItemExample.Criteria;
import com.zxj.rest.jedis.JedisClient;

import util.JsonUtils;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private JedisClient jedisClient;
	@Autowired
	private TbItemDescMapper itemDescMapper;
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;
	
	@Value("${REDIS_ITEM_KEY}")
	private String REDIS_ITEM_KEY;
	@Value("${ITEM_BASE_INFO_KEY}")
	private String ITEM_BASE_INFO_KEY;
	@Value("${ITEM_EXPIRE_SECOND}")
	private Integer ITEM_EXPIRE_SECOND;
	@Value("${ITEM_DESC_KEY}")
	private String ITEM_DESC_KEY;
	@Value("${ITEM_PARAM_KEY}")
	private String ITEM_PARAM_KEY;
	/**
	 * 根据ID获取商品表中的信息
	 */
	@Override
	public TbItem getItemById(Long itemId) {
		//查询缓存，如果有缓存，直接返回
		try {
			String json = jedisClient.get(REDIS_ITEM_KEY + ":" + ITEM_BASE_INFO_KEY + ":" + itemId);
			//判断数据是否存在
			if (StringUtils.isNotBlank(json)) {
				// 把json数据转换成java对象
				TbItem item = JsonUtils.jsonToPojo(json, TbItem.class);
				return item;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//根据商品id查询商品基本信息
		TbItem item = itemMapper.selectByPrimaryKey(itemId);
		
		//向redis中添加缓存。
		//添加缓存原则是不能影响正常的业务逻辑
		try {
			//向redis中添加缓存
			jedisClient.set(REDIS_ITEM_KEY + ":" + ITEM_BASE_INFO_KEY + ":" + itemId
					, JsonUtils.objectToJson(item));
			//设置key的过期时间
			jedisClient.expire(REDIS_ITEM_KEY + ":" + ITEM_BASE_INFO_KEY + ":" + itemId, ITEM_EXPIRE_SECOND);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return item;
	}
	/**
	 * 根据ID获取商品详细描述信息
	 * @param itemId
	 * @return
	 */
	@Override
	public TbItemDesc getItemDescById(Long itemId) {
		//查询缓存
		//查询缓存，如果有缓存，直接返回
		try {
			String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":" + ITEM_DESC_KEY);
			//判断数据是否存在
			if (StringUtils.isNotBlank(json)) {
				// 把json数据转换成java对象
				TbItemDesc itemDesc = JsonUtils.jsonToPojo(json, TbItemDesc.class);
				return itemDesc;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//根据商品id查询商品详情
		TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
		//添加缓存
		try {
			//向redis中添加缓存
			jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":" + ITEM_DESC_KEY
					, JsonUtils.objectToJson(itemDesc));
			//设置key的过期时间
			jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":" + ITEM_DESC_KEY, ITEM_EXPIRE_SECOND);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemDesc;
	}
	/**
	 * 根据ID查询商品规格参数
	 * @param itemId
	 * @return
	 */
	@Override
	public TbItemParamItem getItemParamById(Long itemId) {
		//添加缓存逻辑
		//查询缓存
		//查询缓存，如果有缓存，直接返回
		try {
			String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":" + ITEM_PARAM_KEY);
			//判断数据是否存在
			if (StringUtils.isNotBlank(json)) {
				// 把json数据转换成java对象
				TbItemParamItem itemParamitem = JsonUtils.jsonToPojo(json, TbItemParamItem.class);
				return itemParamitem;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 根据商品id查询规格参数
		TbItemParamItemExample example = new TbItemParamItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
		//取规格参数
		if (list != null && list.size() > 0) {
			TbItemParamItem itemParamItem = list.get(0);
			//添加缓存
			try {
				//向redis中添加缓存
				jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":" + ITEM_PARAM_KEY
						, JsonUtils.objectToJson(itemParamItem));
				//设置key的过期时间
				jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":" + ITEM_PARAM_KEY, ITEM_EXPIRE_SECOND);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return itemParamItem;
		}
		return null;
	}


}
