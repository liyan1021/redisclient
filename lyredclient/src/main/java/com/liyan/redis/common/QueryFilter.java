package com.liyan.redis.common;

import java.util.HashMap;

public class QueryFilter {
	private int pageSize ;
	private int pageNo; 
	private HashMap<String,Integer> orderBy ;
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public HashMap<String, Integer> getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(HashMap<String, Integer> orderBy) {
		this.orderBy = orderBy;
	}
	
	
}
