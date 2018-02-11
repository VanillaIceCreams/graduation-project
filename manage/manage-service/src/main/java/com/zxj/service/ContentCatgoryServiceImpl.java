package com.zxj.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxj.mapper.TbContentCategoryMapper;
import com.zxj.pojo.TbContentCategory;
import com.zxj.pojo.TbContentCategoryExample;
import com.zxj.pojo.TbContentCategoryExample.Criteria;

import common.EastUiTreeResult;
import common.TaotaoResult;

@Service
public class ContentCatgoryServiceImpl implements ContentCatgoryService {

	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;
	
	@Override
	public List<EastUiTreeResult> getContentCatList(Long parentId) {
		//根据parentId查询子节点列表
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		//转换成EastUiTreeResult列表
		List<EastUiTreeResult> resultList = new ArrayList<>();
		for (TbContentCategory tbContentCategory : list) {
			//创建一EastUiTreeResult节点
			EastUiTreeResult node = new EastUiTreeResult();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			node.setState(tbContentCategory.getIsParent()?"closed":"open");
			//添加到列表
			resultList.add(node);
		}
		return resultList;
	}

	@Override
	public TaotaoResult createContentCategory(Long parentId, String name) {
		//创建新节点
		TbContentCategory contentCategory = new TbContentCategory();
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		contentCategory.setParentId(parentId);
		contentCategory.setName(name);
		contentCategory.setStatus(1);
		contentCategory.setIsParent(false);
		contentCategory.setSortOrder(1);
		contentCategoryMapper.insertSelective(contentCategory);
		//判断父节点是否IsParent=false
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(parentId);
		
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		if(list!=null&&list.size()>0){
			TbContentCategory parentContentCategory = list.get(0);
			if(parentContentCategory.getIsParent()==false){
				parentContentCategory.setIsParent(true);
				parentContentCategory.setUpdated(new Date());
				contentCategoryMapper.updateByPrimaryKeySelective(parentContentCategory);
			}
		}	
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult updateContentCategory(Long id, String name) {
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(id);
		TbContentCategory ContentCategory = contentCategoryMapper.selectByExample(example).get(0);
		ContentCategory.setName(name);
		ContentCategory.setUpdated(new Date());
		contentCategoryMapper.updateByPrimaryKeySelective(ContentCategory);
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult deleteContentCategory(Long id, Long parentId) {
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(id);
		TbContentCategory contentCategory = contentCategoryMapper.selectByExample(example).get(0);		

		if(contentCategory.getIsParent()==true){
			delete(contentCategory.getId());
		}
		contentCategoryMapper.deleteByPrimaryKey(id);
		return TaotaoResult.ok();
	}
	
	public void delete(Long id){
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(id);
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getIsParent()==true){
				delete((list.get(i).getId()));
			}
			contentCategoryMapper.deleteByPrimaryKey(list.get(i).getId());
		}
		
	}

}

