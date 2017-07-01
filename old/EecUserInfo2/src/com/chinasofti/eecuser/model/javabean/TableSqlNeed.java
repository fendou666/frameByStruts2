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
									String sqlCountFild, String sqlOrderByFild){
		if(tableList.length != aliasTableList.length){
			System.out.println("���� �������������������ͬ");
			return;
		}else if(tablesNeedFilds.size() == 0){
			System.out.println("��ȡ���ֶ�����Ϊ0 ����");
			return;
		}
		setSqlTableStr_And_sqlAliasTableStr(tableList, aliasTableList);
		setSqlNeedFilds_And_SqlAliasNeedFilds(tablesNeedFilds);
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
	
	private void setSqlNeedFilds_And_SqlAliasNeedFilds(HashMap<String, String[]> tablesNeedFilds){
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