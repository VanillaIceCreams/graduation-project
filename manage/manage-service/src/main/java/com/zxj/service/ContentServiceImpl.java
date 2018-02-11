package com.zxj.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxj.mapper.TbContentMapper;
import com.zxj.pojo.TbContent;
import com.zxj.pojo.TbContentExample;
import com.zxj.pojo.TbItem;
import com.zxj.pojo.TbContentExample.Criteria;
import com.zxj.pojo.TbItemParam;
import com.zxj.pojo.TbItemParamExample;

import common.EasyUiDateGridResult;
import common.TaotaoResult;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;
	
	@Override
	public TaotaoResult insertContent(TbContent content) {
		if (StringUtils.isNotBlank(content.getPic())){
			content.setPic("http://localhost:8080"+content.getPic());
		}
		if (StringUtils.isNotBlank(content.getPic())){
			content.setPic2("http://localhost:8080"+content.getPic2());
		}

		content.setCreated(new Date());
		content.setUpdated(new Date());
		//插入数据
		contentMapper.insert(content);
		
		return TaotaoResult.ok();
	}

	@Override
	public EasyUiDateGridResult queryContentListByCategoryId(Long categoryId, Integer page, Integer rows) {
		
		// 分页操作
		PageHelper.startPage(page, rows);
		// 创建条件对象（默认查询全部）
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		// 执行查询
		List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
		// 获取分页信息
		PageInfo<TbContent> pageinfo = new PageInfo<TbContent>(list);
		// 创建分页pojo
		EasyUiDateGridResult result = new EasyUiDateGridResult();
		// 设置总记录数
		result.setTotal(pageinfo.getTotal());
		// 设置查询出的对象集合
		result.setRows(list);
		return result;
	}

	@Override
	public TaotaoResult deleteById(String ids) {
		
		if(ids.contains(",")){
			String[] ss = ids.split(",");
			for (String string : ss) {
				Long l = Long.parseLong(string);
				contentMapper.deleteByPrimaryKey(l);
				
			}
		}else{
			contentMapper.deleteByPrimaryKey(Long.parseLong(ids));
		}
		
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult edit(TbContent content) {
		contentMapper.updateByPrimaryKeySelective(content);
		return TaotaoResult.ok();
	}

}

