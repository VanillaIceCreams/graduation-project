package com.zxj.portal.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.zxj.pojo.TbItem;
import com.zxj.portal.pojo.CartItem;

import common.TaotaoResult;
import util.CookieUtils;
import util.JsonUtils;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private ItemService itemService;
	@Value("${COOKIE_EXPIRE}")
	private Integer COOKIE_EXPIRE;

	@Override
	public TaotaoResult addCart(Long itemId, Integer num, 
			HttpServletRequest request, HttpServletResponse response) {
		// 1、接收商品id
		// 2、从cookie中购物车商品列表
		List<CartItem> itemList = getCartItemList(request);
		// 3、从商品列表中查询列表是否存在此商品
		boolean haveFlg = false;
		for (CartItem cartItem : itemList) {
			//如果商品存在数量相加
			// 4、如果存在商品的数量加上参数中的商品数量
			if (cartItem.getId().longValue() == itemId) {
				cartItem.setNum(cartItem.getNum() + num);
				haveFlg = true;
				break;
			}
		}
		// 5、如果不存在，调用rest服务，根据商品id获得商品数据。
		if (!haveFlg) {
			TbItem item = itemService.getItemById(itemId);
			//转换成CartItem
			CartItem cartItem = new CartItem();
			cartItem.setId(itemId);
			cartItem.setNum(num);
			cartItem.setPrice(item.getPrice());
			cartItem.setTitle(item.getTitle());
			if (StringUtils.isNotBlank(item.getImage())) {
				String image = item.getImage();
				String[] strings = image.split(",");
				cartItem.setImage(strings[0]);
			}
			//添加到购物车商品列表
			// 6、把商品数据添加到列表中
			itemList.add(cartItem);
		}
		// 7、把购物车商品列表写入cookie
		CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(itemList), COOKIE_EXPIRE, true);
		// 8、返回TaotaoResult
		return TaotaoResult.ok();
	}
	
	/**
	 * 取购物车商品列表
	 * <p>Title: getCartItemList</p>
	 * <p>Description: </p>
	 * @param request
	 * @return
	 */
	private List<CartItem> getCartItemList(HttpServletRequest request) {
		try {
			//从cookie中取商品列表
			String json = CookieUtils.getCookieValue(request, "TT_CART", true);
			if(json!=null&&!json.equals("")){
				//把json转换成java对象
				List<CartItem> list = JsonUtils.jsonToList(json, CartItem.class);
				
				return list;
			}
			return new ArrayList<CartItem>();
			
		} catch (Exception e) {
			return new ArrayList<CartItem>();
		}
		
	}
	/**
	 * 展示购物车
	 */
	@Override
	public List<CartItem> getCartItems(HttpServletRequest request) {
		List<CartItem> list = getCartItemList(request);
		return list;
	}
	/*
	 * 修改购物车
	 */
	@Override
	public TaotaoResult updateCartItem(long itemId, Integer num, HttpServletRequest request,
			HttpServletResponse response) {
		// 从cookie中取购物车商品列表
		List<CartItem> itemList = getCartItemList(request);
		//根据商品id查询商品
		for (CartItem cartItem : itemList) {
			if (cartItem.getId() == itemId) {
				//更新数量
				cartItem.setNum(num);
				break;
			}
		}
		//写入cookie
		CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(itemList), COOKIE_EXPIRE, true);
		return TaotaoResult.ok();
	}
	/**
	 * 删除购物车商品
	 * @param itemId
	 * @param request
	 * @param response
	 * @return
	 */
	@Override
	public TaotaoResult deleteCartItem(long itemId, HttpServletRequest request, HttpServletResponse response) {
		// 1、接收商品id
		// 2、从cookie中取购物车商品列表
		List<CartItem> itemList = getCartItemList(request);
		// 3、找到对应id的商品
		for (CartItem cartItem : itemList) {
			if (cartItem.getId() == itemId) {
				// 4、删除商品。
				itemList.remove(cartItem);
				break;
			}
		}
		// 5、再重新把商品列表写入cookie。
		CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(itemList), COOKIE_EXPIRE, true);
		// 6、返回成功
		return TaotaoResult.ok();
	}


}

