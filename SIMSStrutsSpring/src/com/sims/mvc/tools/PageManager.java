package com.sims.mvc.tools;

public class PageManager {
	private static int maxPageRows = 8;  //每页最大容量
	private static int curPageNo = 1; //当前页
	private static int maxRowNumber = 0; //最大行数
	public static boolean initFlag = false;
	
	public static void PageManagerInit(int _maxPageRows, int _curPageNo){
		maxPageRows = _maxPageRows;
		curPageNo = _curPageNo;
	}
	
	public static void setCurPageNo(int num)
	{
		curPageNo = num;
	}
	
	public static int getCurPageNo(){
		return curPageNo;
	}
	
	
	public static int getMaxPageRows(){
		return maxPageRows;
	}
	public static void setMaxRowNumber(int num){
		maxRowNumber = num;
	}
	public static int getMaxPageNo(){
		return maxRowNumber / maxPageRows + 1;
	}
	
	
	public static int getNextPageNo(){
		if(curPageNo >= getMaxPageNo()){
			return getMaxPageNo();
		}else{
			return curPageNo + 1;
		}
	}
	
	public static int getPrePageNo(){
		if(curPageNo <= 1){
			return 1;
		}else{
			return curPageNo - 1;
		}
	}
	public static void setAppointNo(int num){
		curPageNo = num;
	}
	
	
	public static int setFindPage(String page){
		System.out.println("page----------" + page);
		if(page.equals("first")){
			setCurPageNo(1);
		}else if(page.equals("pre")){
			setCurPageNo(getPrePageNo());
		}else if(page.equals("nxt")){
			setCurPageNo(getNextPageNo());
		}else if(page.equals("last")){
			setCurPageNo(getMaxPageNo());
		}else {
			int num = Integer.parseInt(page);
			if(num >= getMaxPageNo()){
				setCurPageNo(getMaxPageNo());
			}else if(num <=1){
				setCurPageNo(1);
			}else{
				setCurPageNo(num);
			}
		}
		System.out.println("curpage-----------" +  getCurPageNo());
		System.out.println("maxpage-----------" +  getMaxPageNo());
		return getCurPageNo();
	}
	

}
