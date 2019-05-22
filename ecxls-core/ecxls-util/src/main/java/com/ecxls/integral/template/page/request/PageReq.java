package com.ecxls.integral.template.page.request;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PageReq implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static final Integer DEFAULT_CURRENT_PAGE = 1;
	
	private static final Integer DEFAULT_PAGE_NUMBER = 10;
	
	private Integer currentPage; //当前页码
	
	//每页显示条数
	private Integer pageSize; 
	
	//排序 如"p_id desc"
	private String  orderBy; 
	
	//Hash Map的坏处是每次都要转换参数
	private Map<String, Object> keywords = new HashMap<String, Object>();
	
	//外部设置关键字的方法
	public void setKeyword(String key, Object value) {
		if (null == value) {
			return;
		}
		keywords.put(key,value);
	}
	//获取
	public Object getKeyword(String key){
		return keywords.get(key);
	}
	
	public Map<String, Object> getKeywords() {
		return keywords;
	}
	public void setKeywords(Map<String, Object> keywords) {
		this.keywords = keywords;
	}
	
	public PageReq(){};
	
	public PageReq(Integer currentPage, Integer pageSize) {
		
		if (currentPage == null || currentPage <= 0) {
			this.currentPage = DEFAULT_CURRENT_PAGE;
		} else {
			this.currentPage = currentPage; 
		}
		
		if (pageSize == null || pageSize <= 0)  {
			this.pageSize = DEFAULT_PAGE_NUMBER;
		} else {
			this.pageSize = pageSize; 
		}
		
	}
	
	public Integer getCurrentPage() {
		return currentPage != null ? currentPage : DEFAULT_CURRENT_PAGE;
	}
	
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	
	public Integer getPageSize() {
		return pageSize != null ? pageSize : DEFAULT_PAGE_NUMBER;
	}
	
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
	public String getOrderBy() {
		return orderBy;
	}
}
