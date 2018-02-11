package search;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

public class SorljTest {
	public static void main(String[] args) {
		HttpSolrServer server =new HttpSolrServer("http://localhost:8083/solr");
		try {
			//创建文档对象
			SolrInputDocument document = new SolrInputDocument();
			/*
			 * 添加
			 */
			//向文档对象中添加域
			document.addField("item_category_name", "情趣用品");
			document.addField("item_sell_point", "爽");
			document.addField("id", "3");
			document.addField("item_image", "dasdasdasdsda");
			server.add(document);
			server.commit();
			
			/*
			 * 查询
			 */
			
//			SolrQuery query = new SolrQuery();
//			query.setQuery("id:1");
//			QueryResponse response = server.query(query);
//			SolrDocumentList results = response.getResults();
//			for (SolrDocument solrDocument : results) {
//				System.out.println(solrDocument.get("id"));
//				System.out.println(solrDocument.get("product_sell_point"));
//				System.out.println(solrDocument.get("product_category_name"));
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
