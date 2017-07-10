package com.chinasofti.eecuser.model.javabean;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


// 此文件做失败

public class TableSqlNeedBase {
	private String sqlNeedFilds			= "";	// sql查询需要的所有字段拼接的字符串----为了分页
	private String sqlTableStr 			= ""; // sql语句中 from后面跟的表
	private String sqlCountFild 		= "";	// 统计总数量使用的那个字段, 这个字段也是分页orderby的那个字段
	
	public TableSqlNeedBase() {
	
	}
	public void initAllSqlStrData(String [] tableList, 
									HashMap<String, String[]> tablesNeedFilds, 
									String sqlCountFild){
		if(tableList.length == 0){
			System.out.println("穿参 表数量为0错误");
			return;
		}else if(tablesNeedFilds.size() == 0){
			System.out.println("获取的字段数量为0 错误");
			return;
		}
		setSqlTableStr(tableList);
		setSqlNeedFilds(tablesNeedFilds);
		this.sqlCountFild = sqlCountFild;
	}
	private void setSqlTableStr(String [] tableList){
		for(int i=0; i<tableList.length; i++){
			if(i!=tableList.length-1){
				sqlTableStr 		+= tableList[i] + ", ";
			}else{
				sqlTableStr 		+= tableList[i] + " ";
			}
		}
	}
	
	private void setSqlNeedFilds(HashMap<String, String[]> tablesNeedFilds){
		Set<String> keySet = tablesNeedFilds.keySet();
		Iterator<String> iterator = keySet.iterator();
		String tableAlias = "";
		String [] tableFilds = null;
		while(iterator.hasNext()){
			tableAlias = iterator.next();
			tableFilds =  tablesNeedFilds.get(tableAlias);
			for(int i=0; i<tableFilds.length; i++){
				if(i!=tableFilds.length-1){
					sqlNeedFilds 		+= tableFilds[i] + ", ";
				}else{
					sqlNeedFilds 		+= tableFilds[i] + " ";
				}
			}
			sqlNeedFilds += ", ";
		}
		sqlNeedFilds = sqlNeedFilds.substring(0, sqlNeedFilds.length()-2);
	}
	
	
	public static void main(String[] args) {
		String [] tableList 		= {"eecuser", "eecrole"};
		String [] aliasTableList 	= {"u", "r"};
		HashMap<String, String[]> tablesNeedFilds	= new HashMap<String, String[]>();
		tablesNeedFilds.put("u", new String[]{"class_Id", "EEC_Id", "EEC_Name", "sex", "age", 
			"email", "telephone"});
		tablesNeedFilds.put("r", new String[]{"role_name"});
		String sqlCountFild 		= "eec_id";	// 统计总数量使用的那个字段
		String sqlOrderByFild 		= "u.eec_id"; // 条件查询排序的字段
		
		
		TableSqlNeedBase test = new TableSqlNeedBase();
		test.initAllSqlStrData(tableList,  tablesNeedFilds, sqlCountFild);
		System.out.println("分页查询字段:" + test.getSqlNeedFilds());
		System.out.println("分页查询需要的表:" + test.getSqlTableStr());
	}
	
	public String getSqlNeedFilds() {
		return sqlNeedFilds;
	}
	public void setSqlNeedFilds(String sqlNeedFilds) {
		this.sqlNeedFilds = sqlNeedFilds;
	}
	public String getSqlTableStr() {
		return sqlTableStr;
	}
	public void setSqlTableStr(String sqlTableStr) {
		this.sqlTableStr = sqlTableStr;
	}
	public String getSqlCountFild() {
		return sqlCountFild;
	}
	public void setSqlCountFild(String sqlCountFild) {
		this.sqlCountFild = sqlCountFild;
	}
	
}
