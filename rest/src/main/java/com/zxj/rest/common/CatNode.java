package com.zxj.rest.common;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

//表示一个节点
public class CatNode {
	
	//这个注解的意思是，当将该对象转换成json时候，key不用属性名而是注解中的名字
	@JsonProperty("u")
	private String url;
	
	@JsonProperty("n")
	private String name;
	
	//这个items指的是 "/products/852.html|礼帽"
	@JsonProperty("i")
	private List items;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List getItems() {
		return items;
	}
	public void setItems(List items) {
		this.items = items;
	}
	
}


