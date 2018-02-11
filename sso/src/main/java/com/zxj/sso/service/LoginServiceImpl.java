package com.zxj.sso.service;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.zxj.mapper.TbUserMapper;
import com.zxj.pojo.TbUser;
import com.zxj.pojo.TbUserExample;
import com.zxj.pojo.TbUserExample.Criteria;
import com.zxj.sso.jedis.JedisClient;

import common.TaotaoResult;
import util.CookieUtils;
import util.JsonUtils;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private TbUserMapper userMapper;
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${REDIS_SESSION_KEY}")
	private String REDIS_SESSION_KEY;
	@Value("${SESSION_EXPIRE}")
	private Integer SESSION_EXPIRE;
	
	@Override
	public TaotaoResult login(String username, String password, HttpServletRequest request,
			HttpServletResponse response) {
		//校验用户名密码是否正确
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<TbUser> list = userMapper.selectByExample(example);
		//取用户信息
		if (list == null || list.isEmpty()) {
			return TaotaoResult.build(400, "用户名或密码错误");
		}
		TbUser user = list.get(0);
		//校验密码
		if(!user.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))) {
			return TaotaoResult.build(400, "用户名或密码错误");
		}
		//登录成功
		//生成token
		String token = UUID.randomUUID().toString();
		//把用户信息写入redis
		
		
		user.setPassword(null);//已经校验完毕，把密码改成null，以防被偷
		
		//键值对的key为:REDIS_SESSION:{TOKEN}
		//键值对的value为:user对象的json格式
		jedisClient.set(REDIS_SESSION_KEY + ":" + token, JsonUtils.objectToJson(user));
		//设置token的过期时间
		jedisClient.expire(REDIS_SESSION_KEY + ":" + token, SESSION_EXPIRE);
		//写cookie
		CookieUtils.setCookie(request, response, "TT_TOKEN", token);

		return TaotaoResult.ok(token);
	}
	/**
	 * 	参数：String token
		根据token查询redis，查询到结果返回用户对象，更新过期时间。如果查询不到结果，返回Session已经过期，状态码400.
		返回值：TaotaoResult
	 */
	@Override
	public TaotaoResult getUserByToken(String token) {
		// 根据token取用户信息
		String json = jedisClient.get(REDIS_SESSION_KEY + ":" + token);
		//判断是否查询到结果
		if (StringUtils.isBlank(json)) {
			return TaotaoResult.build(400, "用户session已经过期");
		}
		//把json转换成java对象
		TbUser user = JsonUtils.jsonToPojo(json, TbUser.class);
		//更新session的过期时间
		jedisClient.expire(REDIS_SESSION_KEY + ":" + token, SESSION_EXPIRE);
		
		return TaotaoResult.ok(user);
	}


}
