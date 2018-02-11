package com.zxj.search.service;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.zxj.search.dao.SearchDao;
import com.zxj.search.mapper.ItemSearchMapper;
import com.zxj.search.pojo.ItemSearch;
import com.zxj.search.pojo.SearchResult;

import common.TaotaoResult;
@Service
public class ItemSearchSerciveImpl implements ItemSearchService{
	@Autowired
	ItemSearchMapper itemSearchMapper;
	@Autowired
	SolrServer solrServer;
	@Autowired
	SearchDao searchDao;
	@Value("${TITLE}")
	String title;
	@Value("${ID}")
	String id;
	@Value("${CATEGORY_NAME}")
	String category_name;
	@Value("${PRICE}")
	String price;
	@Value("${SELL_POINT}")
	String sell_point;
	@Value("${IMAGE}")
	String image;	
	@Value("${DESC}")
	String desc;
	//将数据导入solr
	public TaotaoResult  importItems() throws SolrServerException, IOException {
		List<ItemSearch> list = itemSearchMapper.searchItemList();
		for (ItemSearch itemSearch : list) {
			SolrInputDocument solrDocument = new SolrInputDocument();
			solrDocument.addField(id, itemSearch.getId());
			solrDocument.addField(title, itemSearch.getTitle());
			solrDocument.addField(category_name, itemSearch.getCategory_name());
			solrDocument.addField(price, itemSearch.getPrice()+"");
			solrDocument.addField(sell_point, itemSearch.getSell_point());
			solrDocument.addField(image, itemSearch.getImage());
			solrDocument.addField(desc, itemSearch.getItem_desc());
			
			solrServer.add(solrDocument);
		}
		solrServer.commit();
		return TaotaoResult.ok();
	}
	
	
	//查询数据
	public SearchResult search(String keyword,int page, int rows)  {

		try {
			//新建查询
			SolrQuery query = new SolrQuery();
			//设置查询关键字
			query.setQuery(keyword);
			//设置分页条件
			query.setStart((page-1)*rows);
			query.setRows(rows);
			//设置查询默认域
			query.set("df", "item_title");
			//设置高亮
			query.setHighlight(true);
			query.addHighlightField("item_title");
			query.setHighlightSimplePre("<font class=\"skcolor_ljg\">");
			query.setHighlightSimplePost("</font>");

			SearchResult searchResult = searchDao.search(query);
			
			//计算总页数
			Long recordCount = searchResult.getRecordCount();
			int pageCount = (int) (recordCount / rows);
			if (recordCount % rows > 0) {
				pageCount++;
			}
			searchResult.setPageCount(pageCount);
			searchResult.setCurPage(page);

			return searchResult;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	
	}
}
