/**
 * 
 */
package com.netPoint.applications.site.controller;

import java.util.List;

import org.displaytag.pagination.PaginatedList;
import org.displaytag.properties.SortOrderEnum;
import org.springframework.stereotype.Component;

/**
 * @author Faliherizo
 *
 */
@Component
public class PaginatedListAdapter<T> implements PaginatedList {
	private List<T> data;
	private int pageNumber = 0;
	private int objectsPerPage=10;
	private int fullListSize;
	private String sortCriterion;
	private SortOrderEnum sortDirection;
	
	/**
	 * To map with parameters
	 */
	private Integer page = 0;
	private String sort;
	private  String dir;
	
	public PaginatedListAdapter() {
		super();
	}
	@Override
	public List<?> getList() {
		return data;
	}

	@Override
	public int getPageNumber() {
		return pageNumber;
	}

	@Override
	public int getFullListSize() {
		return fullListSize;
	}

	@Override
	public String getSortCriterion() {
		return sortCriterion;
	}

	@Override
	public SortOrderEnum getSortDirection() {
		return sortDirection;
	}

	@Override
	public String getSearchId() {
		return null;
	}
	
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public void setFullListSize(int fullListSize) {
		this.fullListSize = fullListSize;
	}
	public void setSortCriterion(String sortCriterion) {
		this.sortCriterion = sortCriterion;
	}
	public void setSortDirection(SortOrderEnum sortDirection) {
		this.sortDirection = sortDirection;
	}
	@Override
	public int getObjectsPerPage() {
		return objectsPerPage;
	}
	public void setObjectsPerPage(int objectsPerPage) {
		this.objectsPerPage = objectsPerPage;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		setSortCriterion(sort);
		this.sort = sort;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		setSortDirection(("asc".equals(dir))?SortOrderEnum.ASCENDING:SortOrderEnum.DESCENDING);
		this.dir = dir;
	}

}
