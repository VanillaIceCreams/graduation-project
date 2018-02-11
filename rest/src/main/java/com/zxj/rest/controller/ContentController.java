package com.zxj.rest.controller;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zxj.pojo.TbContent;
import com.zxj.rest.service.ContentService;

import common.TaotaoResult;
import util.ExceptionUtil;
import util.JsonUtils;

@Controller
public class ContentController {

	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/content/{cid}")
	@ResponseBody
	public TaotaoResult getContentList(@PathVariable Long cid) {
		try {
			List<TbContent> list = contentService.getContentList(cid);
			return TaotaoResult.ok(list);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}



    @RequestMapping(value="/content/getContent",produces= MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
    @ResponseBody
    public String getNotice(String callback,String content) throws UnsupportedEncodingException {
	    //设置编码
        content = new String(content.getBytes("iso8859-1"),"utf-8");
        List<TbContent> result = contentService.getContent(content);
        if (StringUtils.isBlank(callback)) {
            //需要把result转换成字符串
            String json = JsonUtils.objectToJson(result);
            return json;
        }
        //如果字符串不为空，需要支持jsonp调用
        //需要把result转换成字符串
        String json = JsonUtils.objectToJson(result);

        return callback + "(" + json + ");";
    }



}
