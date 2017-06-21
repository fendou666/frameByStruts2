package com.study.mvc.tools;

public class PageManager {
    public static int pageMaxRows=8;  //页最大表示行数
    public static int currentPageNo=1;//当前页码
    public static int maxRows=8;      //最大记录条数
    private static int maxPageNo=1;   //最大页码
    public static boolean initFlg=false;
    
    public static void initPageInfo(int _pageMaxRows,int _currentPageNo,int _maxRows){
    	pageMaxRows=_pageMaxRows;
    	currentPageNo=_currentPageNo;
    	maxRows=_maxRows;
    	initFlg=true;
    }
    
    public static int getMaxPageNo(){
    	if(maxRows % pageMaxRows==0){
    		maxPageNo= maxRows/pageMaxRows;
    	}
    	else{
    		maxPageNo=(int)(maxRows/pageMaxRows)+1;
    	}
    	return maxPageNo;
    }
    
    public static int getNextPageNo(){
    	getMaxPageNo();
    	if(currentPageNo>=maxPageNo){
    		currentPageNo=maxPageNo;
    	}
    	else{
    		currentPageNo=currentPageNo+1;
    	}
    	return currentPageNo;
    }
    
    public static int getPrePageNo(){
    	if(currentPageNo<=1){
    		currentPageNo=1;
    	}
    	else{
    		currentPageNo=currentPageNo-1;
    	}
    	return currentPageNo;
    }
    
    public static void main(String[] args) {
		
	}
}
