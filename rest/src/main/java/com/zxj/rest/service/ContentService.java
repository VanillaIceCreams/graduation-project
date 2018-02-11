package com.zxj.rest.service;

import java.util.List;

import com.zxj.pojo.TbContent;
import common.TaotaoResult;

public interface ContentService {
	public List<TbContent> getContentList(Long cid);

    List<TbContent> getContent(String Content);


}
