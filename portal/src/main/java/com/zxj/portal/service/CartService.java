package com.zxj.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zxj.portal.pojo.CartItem;

import common.TaotaoResult;

public interface CartService {
	public TaotaoResult addCart(Long itemId, Integer num, 
			HttpServletRequest request, HttpServletResponse response);

	List<CartItem> getCartItems(HttpServletRequest request);

	TaotaoResult updateCartItem(long itemId, Integer num, HttpServletRequest request, HttpServletResponse response);

	TaotaoResult deleteCartItem(long itemId, HttpServletRequest request, HttpServletResponse response);
}
