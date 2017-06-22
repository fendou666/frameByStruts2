package com.chinasofti.eecuser.model.javabean;

public class SqlDataPage {
	
	

	

	private int pageMaxRows; 	// ÿҳ�������������
	private int allRows; 		// ���и���������������
	private int currentPage;	// ��ǰ��pageҳ��
	private int maxPageIndex;	// ���pageҳ��
	
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
		System.out.println("��ǰҳ���� " + currentPage + " ָ��ҳ��Ϊ" + pageIndex);
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
