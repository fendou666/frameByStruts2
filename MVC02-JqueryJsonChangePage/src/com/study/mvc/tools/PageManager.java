package com.study.mvc.tools;

public class PageManager {
    public static int pageMaxRows=8;  //ҳ����ʾ����
    public static int currentPageNo=1;//��ǰҳ��
    public static int maxRows=8;      //����¼����
    private static int maxPageNo=1;   //���ҳ��
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
