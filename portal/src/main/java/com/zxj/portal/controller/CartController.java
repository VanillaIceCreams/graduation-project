package com.zxj.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zxj.portal.pojo.CartItem;
import com.zxj.portal.service.CartService;

import common.TaotaoResult;

@Controller
public class CartController {

	@Autowired
	private CartService cartService;
	/**
	 * 添加到购物车
	 * @param itemId
	 * @param num
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping("/cart/add/{itemId}")
	public String addCart(@PathVariable Long itemId, Integer num,
			HttpServletResponse response, HttpServletRequest request) {
		TaotaoResult result = cartService.addCart(itemId, num, request, response);
		return "cart-success";
	}
	/**
	 * 展示购物车
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/cart/cart")
	public String showCartList(HttpServletRequest request, Model model) {
		List<CartItem> list = cartService.getCartItems(request);
		//把商品列表传递给jsp
		model.addAttribute("cartList", list);
		return "cart";
	}
	/**
	 * 在购物车中修改商品数量
	 * @param itemId
	 * @param num
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping("/cart/update/num/{itemId}/{num}")
	@ResponseBody
	public TaotaoResult updateCartItemNum(@PathVariable Long itemId, @PathVariable Integer num,
			HttpServletResponse response, HttpServletRequest request) {
		TaotaoResult result = cartService.updateCartItem(itemId, num, request, response);
		return result;
	}
	/**
	 * 删除购物车中商品
	 * @param itemId
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping("/cart/delete/{itemId}")
	public String deleteCartItem(@PathVariable Long itemId,
			HttpServletResponse response, HttpServletRequest request) {
		TaotaoResult result = cartService.deleteCartItem(itemId, request, response);
		return "redirect:/cart/cart.html";
	}

}

