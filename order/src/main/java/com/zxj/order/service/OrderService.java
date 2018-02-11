package com.zxj.order.service;

import com.zxj.order.pojo.OrderInfo;

import common.TaotaoResult;

public interface OrderService {
	public TaotaoResult createOrder(OrderInfo orderInfo);
}
