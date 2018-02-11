package com.zxj.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.TaotaoResult;
//安全退出
public interface LogoutService {

	void logout(String token, HttpServletRequest request, HttpServletResponse response);
}
