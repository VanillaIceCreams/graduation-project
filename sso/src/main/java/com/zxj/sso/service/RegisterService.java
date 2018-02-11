package com.zxj.sso.service;

import com.zxj.pojo.TbUser;

import common.TaotaoResult;

public interface RegisterService {
	public TaotaoResult checkData(String param, int type);

	TaotaoResult register(TbUser user);
}
