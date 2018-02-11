package com.zxj.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxj.mapper.TbItemDescMapper;
import com.zxj.mapper.TbItemMapper;
import com.zxj.mapper.TbItemParamItemMapper;
import com.zxj.pojo.TbItem;
import com.zxj.pojo.TbItemDesc;
import com.zxj.pojo.TbItemDescExample;
import com.zxj.pojo.TbItemExample;

import com.zxj.pojo.TbItemParamItem;
import com.zxj.pojo.TbItemParamItemExample;
import com.zxj.pojo.TbItemParamItemExample.Criteria;
import com.zxj.pojo.UpdatePojo;

import common.EasyUiDateGridResult;
import common.TaotaoResult;
import util.IDUtils;
import util.JsonUtils;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;

	@Override
	public TbItem getItemById(Long itemId) {
		PageHelper.startPage(0, 10);
		TbItem item = itemMapper.selectByPrimaryKey(itemId);
		// TbItemExample example = new TbItemExample();
		// // 创建查询条件
		// Criteria criteria = example.createCriteria();
		// Criteria.andIdEqualTo(itemId);
		// List<TbItem> list = itemMapper.selectByExample(example);
		// // 判断list中是否为空
		// TbItem item = null;
		// if (list != null && list.size() > 0) {
		// item = list.get(0);
		// }
		return item;
	}

	// EasyUiDateGrid商品列表分页分页
	@Override
	public EasyUiDateGridResult getItemList(int page, int rows) {
		// 分页操作
		PageHelper.startPage(page, rows);
		// 创建条件对象（默认查询全部）
		TbItemExample example = new TbItemExample();
		// 执行查询
		List<TbItem> list = itemMapper.selectByExample(example);
		// 获取分页信息
		PageInfo<TbItem> pageinfo = new PageInfo<>(list);

		// 创建分页pojo
		EasyUiDateGridResult result = new EasyUiDateGridResult();
		// 设置总记录数
		result.setTotal(pageinfo.getTotal());
		// 设置查询出的对象集合
		result.setRows(list);

		return result;
	}

	// 1、接收TbItem对象，String desc 商品描述两个参数。
	// 2、在TbItem对象中补全属性，包括id、status、create、update。
	// 3、调用Mapper的insert方法插入数据。
	// 4、创建一个TbItemDesc对象，补全属性。
	// 5、调用Mapper方法插入到商品描述表。
	// 6、返回TaotaoResult对象。
	@Override
	public TaotaoResult createItem(TbItem item, String desc,String itemParams) {
		long id = IDUtils.genItemId();
		Date date = new Date();
		
		// 设置商品ID
		item.setId(id);
		// 设置状态 1.上架 2.下架 3.删除
		item.setStatus((byte) 1);
		// 设置创建时间,更新时间
		
		item.setUpdated(date);
		item.setCreated(date);
		// 插入商品表
		itemMapper.insert(item);

		// 创建商品描述对象,设置描述，创建时间，更新时间
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(id);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		//插入表中
		itemDescMapper.insert(itemDesc);

		
		//创建商品参数对象，设置值
		TbItemParamItem itemParamItem = new TbItemParamItem();
		itemParamItem.setItemId(id);
		itemParamItem.setParamData(itemParams);
		itemParamItem.setCreated(date);
		itemParamItem.setUpdated(date);
		//插入表中
		itemParamItemMapper.insert(itemParamItem);
		
		
		// 返回一个拥有
		// status = 200;
		// msg = "OK";
		// data = data;
		// 的对象
		return TaotaoResult.ok();
	}

	@Override
	public String getItemParamHtml(Long itemId) {
		// 根据商品类目id查询规格参数
		TbItemParamItemExample example = new TbItemParamItemExample();
		Criteria citeria = example.createCriteria();
		citeria.andItemIdEqualTo(itemId);
		// 执行查询
		List<TbItemParamItem> list = itemParamItemMapper.selectByExample(example);
		if (list == null || list.isEmpty()) {
			return "";
		}
		// 取规格参数
		TbItemParamItem itemParamItem = list.get(0);
		// 取json数据
		String paramData = itemParamItem.getParamData();
		// 转换成java对象
		List<Map> mapList = JsonUtils.jsonToList(paramData, Map.class);
		// 遍历list生成html
		StringBuffer sb = new StringBuffer();

		sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
		sb.append("	<tbody>\n");
		for (Map map : mapList) {
			sb.append("		<tr>\n");
			sb.append("			<th class=\"tdTitle\" colspan=\"2\">" + map.get("group") + "</th>\n");
			sb.append("		</tr>\n");
			// 取规格项
			List<Map> mapList2 = (List<Map>) map.get("params");
			for (Map map2 : mapList2) {
				sb.append("		<tr>\n");
				sb.append("			<td class=\"tdTitle\">" + map2.get("k") + "</td>\n");
				sb.append("			<td>" + map2.get("v") + "</td>\n");
				sb.append("		</tr>\n");
			}
		}
		sb.append("	</tbody>\n");
		sb.append("</table>");

		return sb.toString();

	}

	@Override
	public TaotaoResult deleteItem(String ids) {
		try {
			TbItemExample itemExample = new TbItemExample();
			TbItemExample.Criteria criteria = itemExample.createCriteria();
			if(ids.contains(",")){
				String[] ss = ids.split(",");
				for (String string : ss) {
					Long l = Long.parseLong(string);
					criteria.andIdEqualTo(l);			
					itemMapper.deleteByExample(itemExample);
				}
			}else{
				criteria.andIdEqualTo(Long.parseLong(ids));			
				itemMapper.deleteByExample(itemExample);
			}
			return TaotaoResult.ok();
		} catch (Exception e) {
			return TaotaoResult.build(400, "出错");
		}
	}

	@Override
	public TaotaoResult queryItemParamById(Long id) {
		// 根据商品类目id查询规格参数
		TbItemParamItemExample example = new TbItemParamItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(id);
		// 执行查询
		List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
		if (list == null || list.isEmpty()) {
			return TaotaoResult.build(400, "没有参数");
		}
		// 取规格参数
		TbItemParamItem itemParamItem = list.get(0);
		
		
		return TaotaoResult.ok(itemParamItem);
	}

	@Override
	public TaotaoResult queryItemDescById(Long id) {
		
		TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(id);
		
		return TaotaoResult.ok(itemDesc);
	}

	
	@Override
	public TaotaoResult downItemById(String ids) {
		if(ids.contains(",")){
			String[] ss = ids.split(",");
			for (String string : ss) {
				Long l = Long.parseLong(string);
				TbItem item = itemMapper.selectByPrimaryKey(l);
				if(item.getStatus()==1){
					item.setStatus((byte) 2);
					itemMapper.updateByPrimaryKey(item);
				}
				
			}
		}else{
			TbItem item = itemMapper.selectByPrimaryKey(Long.parseLong(ids));
			if(item.getStatus()==1){
				item.setStatus((byte) 2);
				itemMapper.updateByPrimaryKey(item);
			}
		}
		
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult upItemById(String ids) {
		if(ids.contains(",")){
			String[] ss = ids.split(",");
			for (String string : ss) {
				Long l = Long.parseLong(string);
				TbItem item = itemMapper.selectByPrimaryKey(l);
				if(item.getStatus()==2){
					item.setStatus((byte) 1);
					itemMapper.updateByPrimaryKey(item);
				}
				
			}
		}else{
			TbItem item = itemMapper.selectByPrimaryKey(Long.parseLong(ids));
			if(item.getStatus()==2){
				item.setStatus((byte) 1);
				itemMapper.updateByPrimaryKey(item);
			}
		}
		
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult updateItemAndDescAndParam(UpdatePojo pojo) {
		//更新商品表
		TbItem item = new TbItem();		
		item.setBarcode(pojo.getBarcode());
		item.setCid(pojo.getCid());
		item.setId(pojo.getId());
		item.setImage(pojo.getImage());
		item.setNum(pojo.getNum());
		item.setSellPoint(pojo.getSellPoint());
		item.setTitle(pojo.getTitle());
		item.setUpdated(new Date());
		itemMapper.updateByPrimaryKeySelective(item);
		//更新商品规格表		
		TbItemParamItem itemParamItem = new TbItemParamItem();	
		itemParamItem.setId(pojo.getItemParamId());
		itemParamItem.setParamData(pojo.getItemParams());
		itemParamItem.setUpdated(new Date());
		//itemParamItem.setItemId(pojo.getId());
		itemParamItemMapper.updateByPrimaryKeySelective(itemParamItem);
		
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(pojo.getId());
		itemDesc.setItemDesc(pojo.getDesc());
		itemDesc.setUpdated(new Date());
		itemDescMapper.updateByPrimaryKeySelective(itemDesc);
		
		return TaotaoResult.ok();
	}

}
