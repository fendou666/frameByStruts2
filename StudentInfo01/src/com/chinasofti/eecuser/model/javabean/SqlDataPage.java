package com.chinasofti.eecuser.model.javabean;

public class SqlDataPage {
	
	

	

	private int pageMaxRows; 	// 每页包含的最大数据
	private int allRows; 		// 所有复合条件的总数据
	private int currentPage;	// 当前的page页面
	private int maxPageIndex;	// 最大page页面
	
	public SqlDataPage() {
		
	}
	public SqlDataPage(int pageMaxRows, int allRows, int currentPage,
			int maxPageIndex) {
		this.pageMaxRows = pageMaxRows;
		this.allRows = allRows;
		this.currentPage = currentPage;
		this.maxPageIndex = maxPageIndex;
	}
	
	public int getPageMaxRows() {
		return pageMaxRows;
	}
	public void setPageMaxRows(int pageMaxRows) {
		this.pageMaxRows = pageMaxRows;
	}
	public int getAllRows() {
		return allRows;
	}
	public void setAllRows(int allRows) {
		this.allRows = allRows;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getMaxPageIndex() {
		return maxPageIndex;
	}
	public void setMaxPageIndex(int maxPageIndex) {
		this.maxPageIndex = maxPageIndex;
	}
	
	public int getFirstPage(){
		return currentPage = 1;
	}
	
	public int getPrePage(){
		currentPage--;
		if(currentPage == 0){
			currentPage = 1;
		}
		return currentPage;
	}
	
	
	public int getNextPage(){
		currentPage++;
		if(currentPage > maxPageIndex){
			currentPage = maxPageIndex;
		}
		return currentPage;
	}
	
	public int getLastPage(){
		return currentPage = maxPageIndex;
	}
	public int getCustomPage(int pageIndex){
		if(pageIndex<1){
			currentPage = 1;
		}else if(pageIndex>maxPageIndex){
			currentPage = maxPageIndex;
		}else{
			currentPage = pageIndex;
		}
		System.out.println("当前页面是 " + currentPage + " 指定页面为" + pageIndex);
		return currentPage;
	}
	
	public void setMaxPageIndexByAllRows() {
		if(allRows%pageMaxRows == 0){
			this.maxPageIndex = allRows/pageMaxRows;
		}else{
			this.maxPageIndex = allRows/pageMaxRows + 1;
		}
		
	}
	
	@Override
	public String toString() {
		return "SqlDataPage [pageMaxRows=" + pageMaxRows + ", allRows="
				+ allRows + ", currentPage=" + currentPage + ", maxPageIndex="
				+ maxPageIndex + "]";
	}
}
