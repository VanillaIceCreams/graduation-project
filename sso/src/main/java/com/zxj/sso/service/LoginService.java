package com.zxj.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.TaotaoResult;

public interface LoginService {
	public TaotaoResult login(String username, String password, HttpServletRequest request,
			HttpServletResponse response);

	TaotaoResult getUserByToken(String token);
}
