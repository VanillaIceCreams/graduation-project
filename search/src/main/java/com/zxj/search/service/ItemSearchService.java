package com.zxj.search.service;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.stereotype.Service;

import com.zxj.search.pojo.SearchResult;

import common.TaotaoResult;
@Service
public interface ItemSearchService {

	
	public TaotaoResult  importItems() throws SolrServerException, IOException;

	public SearchResult search(String keyword,int page, int rows);
}
