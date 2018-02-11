package com.zxj.search.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Repository;

import com.zxj.search.pojo.ItemSearch;
import com.zxj.search.pojo.SearchResult;
@Repository
public class SearchDaoImpl implements SearchDao{

	@Override
	public SearchResult search(SolrQuery query) throws Exception {
		//获取服务
		HttpSolrServer server =new HttpSolrServer("http://localhost:8083/solr");
		//执行查询
		QueryResponse response = server.query(query);
		//获取查询结果集
		SolrDocumentList results = response.getResults();
		//遍历结果集,并将内容封装入ItemSearch，再装入集合中
		List<ItemSearch> itemList = new ArrayList<ItemSearch>();
		for (SolrDocument solrDocument : results) {
			ItemSearch item =  new ItemSearch();
			item.setCategory_name((String) solrDocument.get("item_category_name"));
			item.setId((String) solrDocument.get("id"));
			item.setImage((String) solrDocument.get("item_image"));
			item.setPrice((Long) solrDocument.get("item_price"));
			item.setSell_point((String) solrDocument.get("item_sell_point"));
			//获取高亮对象
			Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
			//获取item_title中拥有高亮部分标题的集合
			List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
			//定义一个标题属性
			String itemTitle = "";
			
			//若集合不为空，获取高亮标题，否则获取无高亮的标题
			if(list!=null&&list.size()>0){
				itemTitle = list.get(0);
			}else{
				itemTitle = (String) solrDocument.get("item_item_title");
			}
			item.setTitle(itemTitle);
			//将结果设置入itemList
			itemList.add(item);
		}
		System.out.println(itemList.size());
		//将商品集合装入SearchResult对象中
		SearchResult searchResult = new SearchResult();
		searchResult.setItemList(itemList);
		//查询结果总数量
		searchResult.setRecordCount(results.getNumFound());

		return searchResult;
	}

}
