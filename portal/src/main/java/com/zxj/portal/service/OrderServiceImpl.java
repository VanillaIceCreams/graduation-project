package com.zxj.portal.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.zxj.portal.pojo.OrderInfo;

import common.TaotaoResult;
import util.HttpClientUtil;
import util.JsonUtils;

/**
 * 提交订单，调用order的服务生成订单
 * @author Administrator
 *
 */
@Service
public class OrderServiceImpl implements OrderService {
	
	@Value("${ORDER_BASE_URL}")
	private String ORDER_BASE_URL;
	@Value("${ORDER_CREATE_URL}")
	private String ORDER_CREATE_URL;

	@Override
	public String createOrder(OrderInfo orderInfo) {
		//把OrderInfo转换成json
		String json = JsonUtils.objectToJson(orderInfo);
		//提交订单数据
		String jsonResult = HttpClientUtil.doPostJson(ORDER_BASE_URL + ORDER_CREATE_URL, json);
		//转换成java对象
		TaotaoResult taotaoResult = TaotaoResult.format(jsonResult);
		//取订单号
		String orderId = taotaoResult.getData().toString();
		return orderId;
	}

}

