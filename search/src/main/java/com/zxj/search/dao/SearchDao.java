package com.zxj.search.dao;

import org.apache.solr.client.solrj.SolrQuery;

import com.zxj.search.pojo.SearchResult;

public interface SearchDao {
	public SearchResult search(SolrQuery query) throws Exception ;
}
