package com.chinasofti.eecuser.model.javabean;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class TableSqlNeed {
	private String sqlNeedFilds			= "";	// sql��ѯ��Ҫ�������ֶ�ƴ�ӵ��ַ���----Ϊ�˷�ҳ
	private String sqlAliasNeedFilds 	= "";// sqlʹ�ñ�����ѯ�������ֶ�ƴ�ӵ��ַ���----Ϊ�˿�����������
	private String sqlTableStr 			= ""; // sql����� from������ı�
	private String sqlAliasTableStr 	= ""; // sql����� from������ı�������
	private String sqlCountFild 		= "";	// ͳ��������ʹ�õ��Ǹ��ֶ�
	private String sqlOrderByFild 		= ""; // ������ѯ������ֶ�
	
	public TableSqlNeed() {
	
	}
	
	public void initAllSqlStrData(String [] tableList, String [] aliasTableList, 
									HashMap<String, String[]> tablesNeedFilds, 
									String sqlCountFild,   String sqlOrderByFild){
		if(tableList.length != aliasTableList.length){
			System.out.println("���� �������������������ͬ");
			return;
		}else if(tablesNeedFilds.size() == 0){
			System.out.println("��ȡ���ֶ�����Ϊ0 ����");
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
		System.out.println("���� �������������������ͬ");
		return;
		}else if(tablesNeedFilds.size() == 0){
		System.out.println("��ȡ���ֶ�����Ϊ0 ����");
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
	// ���Զ����ֶ�ֵ���л�ȡ
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
		// ��ȡ�Զ����ֶε�ֵ
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
		
		System.out.println("countsql ��:" + countSql);
		System.out.println("��ҳ���sql ��:" + getRS);
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
		String sqlCountFild 		= "eec_id";	// ͳ��������ʹ�õ��Ǹ��ֶ�
		String sqlOrderByFild 		= "u.eec_id"; // ������ѯ������ֶ�
		
		
		TableSqlNeed test = new TableSqlNeed();
		test.initAllSqlStrData(tableList, aliasTableList, tablesNeedFilds, sqlCountFild, sqlOrderByFild);
		System.out.println("������ѯ�ֶ�:" + test.getSqlAliasNeedFilds());
		System.out.println("������ѯ��Ҫ�ı�:" + test.getSqlAliasTableStr());
		System.out.println("��ҳ��ѯ�ֶ�:" + test.getSqlNeedFilds());
		System.out.println("��ҳ��ѯ��Ҫ�ı�:" + test.getSqlTableStr());
	}
}