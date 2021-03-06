package com.chinasofti.eecuser.model.javabean;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class TableSqlNeed {
	private String sqlNeedFilds			= "";	// sql查询需要的所有字段拼接的字符串----为了分页
	private String sqlAliasNeedFilds 	= "";// sql使用别名查询的所有字段拼接的字符串----为了快速条件查找
	private String sqlTableStr 			= ""; // sql语句中 from后面跟的表
	private String sqlAliasTableStr 	= ""; // sql语句中 from后面跟的表带别名
	private String sqlCountFild 		= "";	// 统计总数量使用的那个字段
	private String sqlOrderByFild 		= ""; // 条件查询排序的字段
	
	public TableSqlNeed() {
	
	}
	
	public void initAllSqlStrData(String [] tableList, String [] aliasTableList, 
									HashMap<String, String[]> tablesNeedFilds, 
									String sqlCountFild,   String sqlOrderByFild){
		if(tableList.length != aliasTableList.length){
			System.out.println("穿参 表数量与表别名数量不同");
			return;
		}else if(tablesNeedFilds.size() == 0){
			System.out.println("获取的字段数量为0 错误");
			return;
		}
		setSqlTableStr_And_sqlAliasTableStr(tableList, aliasTableList);
		setSqlNeedFilds_And_SqlAliasNeedFilds(tablesNeedFilds, null);
		this.sqlCountFild = sqlCountFild;
		this.sqlOrderByFild = sqlOrderByFild;
	}
	
	public void initAllSqlStrData(String [] tableList, String [] aliasTableList, 
			HashMap<String, String[]> tablesNeedFilds, 
			String [] customFilds, 
			String sqlCountFild,   String sqlOrderByFild){
		if(tableList.length != aliasTableList.length){
		System.out.println("穿参 表数量与表别名数量不同");
		return;
		}else if(tablesNeedFilds.size() == 0){
		System.out.println("获取的字段数量为0 错误");
		return;
		}
		setSqlTableStr_And_sqlAliasTableStr(tableList, aliasTableList);
		setSqlNeedFilds_And_SqlAliasNeedFilds(tablesNeedFilds, customFilds);
		this.sqlCountFild = sqlCountFild;
		this.sqlOrderByFild = sqlOrderByFild;
	}
	
	private void setSqlTableStr_And_sqlAliasTableStr(String [] tableList, String [] aliasTableList){
		for(int i=0; i<tableList.length; i++){
			if(i!=tableList.length-1){
				sqlTableStr 		+= tableList[i] + ", ";
				sqlAliasTableStr 	+= tableList[i] + " " + aliasTableList[i] + ", ";
			}else{
				sqlTableStr 		+= tableList[i] + " ";
				sqlAliasTableStr 	+= tableList[i] + " " + aliasTableList[i] + " ";
			}
		}
	}
	// 对自定义字段值进行获取
	private HashMap<String, String> getCustomNeedFilds_AND_aliasCustomNeedFilds(String [] customFilds){
		HashMap<String, String> needFildsMAP = null;
		if(customFilds != null){
			needFildsMAP = new HashMap<String, String>();
			String CustomNeedFilds = "";
			String aliasCustomNeedFilds = "";
			for(int i=0; i<customFilds.length; i++){
				customFilds[i] = customFilds[i].trim();
				aliasCustomNeedFilds += customFilds[i] + ", ";
				int index = customFilds[i].lastIndexOf(' ');
				CustomNeedFilds += customFilds[i].substring(index) + ", ";
			}
//			System.out.println("CustomNeedFilds " + CustomNeedFilds);
//			System.out.println("aliasCustomNeedFilds " + aliasCustomNeedFilds);
			needFildsMAP.put("CustomNeedFilds", CustomNeedFilds);
			needFildsMAP.put("aliasCustomNeedFilds", aliasCustomNeedFilds);
		}
		return needFildsMAP;
	}
	
	private void setSqlNeedFilds_And_SqlAliasNeedFilds(HashMap<String, String[]> tablesNeedFilds,
			String [] customFilds){
		Set<String> keySet = tablesNeedFilds.keySet();
		Iterator<String> iterator = keySet.iterator();
		String tableAlias = "";
		String [] tableFilds = null;
		// 获取自定义字段的值
		if(customFilds !=null){
			HashMap<String, String> needFildsMAP = getCustomNeedFilds_AND_aliasCustomNeedFilds(customFilds);
			sqlNeedFilds 		+= needFildsMAP.get("CustomNeedFilds");
			sqlAliasNeedFilds 	+= needFildsMAP.get("aliasCustomNeedFilds");
		}
		while(iterator.hasNext()){
			tableAlias = iterator.next();
			tableFilds =  tablesNeedFilds.get(tableAlias);
			for(int i=0; i<tableFilds.length; i++){
				if(i!=tableFilds.length-1){
					sqlNeedFilds 		+= tableFilds[i] + ", ";
					sqlAliasNeedFilds 	+= tableAlias + "." + tableFilds[i] + ", ";
				}else{
					sqlNeedFilds 		+= tableFilds[i] + " ";
					sqlAliasNeedFilds 	+= tableAlias + "." + tableFilds[i] + " ";
				}
			}
			sqlNeedFilds += ", ";
			sqlAliasNeedFilds += ", ";
		}
		sqlNeedFilds = sqlNeedFilds.substring(0, sqlNeedFilds.length()-2);
		sqlAliasNeedFilds = sqlAliasNeedFilds.substring(0, sqlAliasNeedFilds.length()-2);
	}
	
	public void executeSqlStr(String condition, int maxPageRows, int currentPageNumbe){
		String countSql = "select count(" + sqlCountFild + ") from " + sqlAliasTableStr + " where 1=1  " + condition; 
		
		String getRS = "select " + sqlNeedFilds 
        + " from  (select "
        + " case when mod(Lineno,"+ maxPageRows+") =0 then Lineno/"+ maxPageRows
        + " 	when mod(Lineno,"+ maxPageRows+")!=0 then trunc(Lineno/"+ maxPageRows+")+1"
        + " end as pageno," + sqlNeedFilds 
        + " from  (select row_number() over(order by  " + sqlOrderByFild + ")  as Lineno,"
        + sqlAliasNeedFilds
        + " from " + sqlAliasTableStr  + " where 1=1 " + condition
        + " )"
        + ")"
        + " where pageno=" + currentPageNumbe;
		
		System.out.println("countsql 是:" + countSql);
		System.out.println("分页结果sql 是:" + getRS);
	}
	
	
	public String getSqlNeedFilds() {
		return sqlNeedFilds;
	}
	public String getSqlAliasNeedFilds() {
		return sqlAliasNeedFilds;
	}
	public String getSqlTableStr() {
		return sqlTableStr;
	}
	public String getSqlAliasTableStr() {
		return sqlAliasTableStr;
	}
	public String getSqlCountFild() {
		return sqlCountFild;
	}
	public String getSqlOrderByFild() {
		return sqlOrderByFild;
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
		
		
		TableSqlNeed test = new TableSqlNeed();
		test.initAllSqlStrData(tableList, aliasTableList, tablesNeedFilds, sqlCountFild, sqlOrderByFild);
		System.out.println("别名查询字段:" + test.getSqlAliasNeedFilds());
		System.out.println("别名查询需要的表:" + test.getSqlAliasTableStr());
		System.out.println("分页查询字段:" + test.getSqlNeedFilds());
		System.out.println("分页查询需要的表:" + test.getSqlTableStr());
	}
}
