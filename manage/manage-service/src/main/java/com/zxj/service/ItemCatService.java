package com.zxj.service;

import java.util.List;

import common.EastUiTreeResult;

public interface ItemCatService {
	List<EastUiTreeResult> getItemCarList(long parentId);
	
}
