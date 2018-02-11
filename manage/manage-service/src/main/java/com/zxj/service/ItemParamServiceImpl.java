package com.zxj.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxj.mapper.TbItemParamMapper;
import com.zxj.pojo.TbItem;
import com.zxj.pojo.TbItemParam;
import com.zxj.pojo.TbItemParamExample;
import com.zxj.pojo.TbItemParamExample.Criteria;

import common.EasyUiDateGridResult;
import common.TaotaoResult;

@Service
public class ItemParamServiceImpl implements ItemParamService {
	@Autowired
	TbItemParamMapper itemParamMapper;

	// 通过商品类目ID查找商品类目规格参数模板
	@Override
	public TaotaoResult getItemParamByCid(long cid) {
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		if (list != null && list.size() > 0) {
			TaotaoResult ok = TaotaoResult.ok(list.get(0));
			return ok;
		}
		System.out.println(111);
		return TaotaoResult.ok();
	}

	// 分页显示商品类目规格参数模板
	@Override
	public EasyUiDateGridResult getItemList(Integer page, Integer rows) {

		// 分页操作
		PageHelper.startPage(page, rows);
		// 创建条件对象（默认查询全部）
		TbItemParamExample example = new TbItemParamExample();
		// 执行查询
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		// 获取分页信息
		PageInfo<TbItemParam> pageinfo = new PageInfo<TbItemParam>(list);
		// 创建分页pojo
		EasyUiDateGridResult result = new EasyUiDateGridResult();
		// 设置总记录数
		result.setTotal(pageinfo.getTotal());
		// 设置查询出的对象集合
		result.setRows(list);
		System.out.println(list.get(0).getParamData());
		return result;
	}

	@Override
	public TaotaoResult saveItemParam(long cid, String paramData) {
		TbItemParam itemParam  =  new TbItemParam();
		itemParam.setItemCatId(cid);
		itemParam.setParamData(paramData);
		itemParam.setCreated(new Date());
		itemParam.setUpdated(new Date());
		itemParamMapper.insert(itemParam);
		return TaotaoResult.ok();
		

	}

	@Override
	public TaotaoResult deleteItemParam(String ids) {
		
		if(ids.contains(",")){
			String[] ss = ids.split(",");
			for (String string : ss) {
				Long l = Long.parseLong(string);
				itemParamMapper.deleteByPrimaryKey(l);			
			}
		}else{			
			itemParamMapper.deleteByPrimaryKey(Long.parseLong(ids));
		}
		
		return TaotaoResult.ok();
	}
	
	

}
