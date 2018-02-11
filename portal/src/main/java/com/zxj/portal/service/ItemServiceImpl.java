package com.zxj.portal.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.zxj.pojo.TbItem;
import com.zxj.pojo.TbItemDesc;
import com.zxj.pojo.TbItemParamItem;
import com.zxj.portal.pojo.PortalItem;

import common.TaotaoResult;
import util.HttpClientUtil;
import util.JsonUtils;

@Service
public class ItemServiceImpl implements ItemService {
	
	
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_ITEM_BASE_URL}")
	private String REST_ITEM_BASE_URL;
	@Value("${REST_ITEM_DESC_URL}")
	private String REST_ITEM_DESC_URL;
	@Value("${REST_ITEM_PARAM_URL}")
	private String REST_ITEM_PARAM_URL;
	/**
	 * 获取商品信息
	 */
	@Override
	public TbItem getItemById(Long itemId) {
		// 根据商品id查询商品基本信息
		String json = HttpClientUtil.doGet(REST_BASE_URL + REST_ITEM_BASE_URL + itemId);
		//转换成java对象
		TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, PortalItem.class);
		//取商品对象
		PortalItem item = (PortalItem) taotaoResult.getData();
		return item;
	}
	/*
	 * 获取商品详情
	 */
	@Override
	public String getItemDescById(Long itemId) {
		//根据商品id调用taotao-rest的服务获得数据
		//http://localhost:8081/rest/item/desc/144766336139977
		String json = HttpClientUtil.doGet(REST_BASE_URL + REST_ITEM_DESC_URL + itemId);
		//转换成java对象
		TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, TbItemDesc.class);
		//取商品描述
		TbItemDesc itemDesc = (TbItemDesc) taotaoResult.getData();
		String desc = itemDesc.getItemDesc();
		return desc;
	}
	
	@Override
	public String getItemParamById(Long itemId) {
		// 根据商品id获得对应的规格参数
		String json = HttpClientUtil.doGet(REST_BASE_URL + REST_ITEM_PARAM_URL + itemId);
		// 转换成java对象
		TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, TbItemParamItem.class);
		// 取规格参数
		TbItemParamItem itemParamItem = (TbItemParamItem) taotaoResult.getData();
		if(itemParamItem!=null){
			String paramJson = itemParamItem.getParamData();
			// 把规格参数的json数据转换成java对象
			// 转换成java对象
			List<Map> mapList = JsonUtils.jsonToList(paramJson, Map.class);
			// 遍历list生成html
			StringBuffer sb = new StringBuffer();
	
			sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
			sb.append("	<tbody>\n");
			for (Map map : mapList) {
				sb.append("		<tr>\n");
				sb.append("			<th class=\"tdTitle\" colspan=\"2\">" + map.get("group") + "</th>\n");
				sb.append("		</tr>\n");
				// 取规格项
				List<Map> mapList2 = (List<Map>) map.get("params");
				for (Map map2 : mapList2) {
					sb.append("		<tr>\n");
					sb.append("			<td class=\"tdTitle\">" + map2.get("k") + "</td>\n");
					sb.append("			<td>" + map2.get("v") + "</td>\n");
					sb.append("		</tr>\n");
				}
			}
			sb.append("	</tbody>\n");
			sb.append("</table>");
	
			return sb.toString();
		}
		return "<h1>本商品没有规格参数</h1>";

	}


}

