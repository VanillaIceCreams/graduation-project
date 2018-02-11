package com.zxj.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.zxj.mapper.TbUserMapper;
import com.zxj.sso.jedis.JedisClient;

import common.TaotaoResult;
import util.CookieUtils;
@Service
public class LogoutServiceImpl implements LogoutService{
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${REDIS_SESSION_KEY}")
	private String REDIS_SESSION_KEY;

	
	@Override
	public void logout(String token,HttpServletRequest request,
			HttpServletResponse response) {
		
		// 根据token取用户信息
		String json = jedisClient.get(REDIS_SESSION_KEY + ":" + token);
		//若查到结果
		if (!StringUtils.isBlank(json)) {
			//删除缓存
			jedisClient.del(REDIS_SESSION_KEY + ":" + token);	
			//删除cookie
			CookieUtils.deleteCookie(request, response, "TT_TOKEN");
		}
		System.out.println(jedisClient.get(REDIS_SESSION_KEY + ":" + token));
		
		
			
	}
	
}
