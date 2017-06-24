package com.chinasofti.eecuser.model.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import oracle.jdbc.OracleTypes;

import com.chinasofti.eecuser.model.javabean.TableSqlNeed;
import com.chinasofti.eecuser.model.javabean.TableSqlNeedBase;
import com.chinasofti.eecuser.model.javabean.UserInfo;
import com.chinasofti.eecuser.tools.jdbc.DBUtil;

public class PublicDAOImp implements IPublicDAO {
	
	public List<UserInfo> queryDataTest() {
		// 需要查询的表列表
		String [] tableList 		= {"eecuser", "eecrole"};
		
		// 表的别名
		String [] aliasTableList 	= {"u", "r"};
		// 每个别名表对应需要查找的字段
		HashMap<String, String[]> tablesNeedFilds	= new HashMap<String, String[]>();
		tablesNeedFilds.put("u", new String[]{"class_Id", "EEC_Id", "EEC_Name", "sex", "age", 
			"email", "telephone"});
		// TODO 如果这个表没有需要查询的字段， 应该是存空数组？？？
		tablesNeedFilds.put("r", new String[]{"role_name"});
		// 统计总数量使用的那个字段
		String sqlCountFild 		= "u.eec_id";	
		// 条件查询排序的字段
		String sqlOrderByFild 		= "u.eec_id"; 
		// 这个类，对所有字段进行拼接， 方便下面直接调用
		TableSqlNeed test = new TableSqlNeed();
		test.initAllSqlStrData(tableList, aliasTableList, tablesNeedFilds, sqlCountFild, sqlOrderByFild);
		
		// where 需要的条件语句
		String conditionStr = " u.role_id=r.role_id  AND isdelete=0 AND u.role_id>=3004 AND u.role_id<=3005 AND u.role_id<=3004 AND u.eec_id=170000003 AND u.eec_name='谢文兵'";
		// 返回的用户列表
		List<UserInfo> userList = null;
		String sql = "{?=call eecQueryPageRows(?,?,?,?,?,?,?,?,?,?)}";
		try {
			// 函数存储过程通过这中方式调用
			CallableStatement  pc = DBUtil.getJDBC().getConn().prepareCall(sql);
			pc.registerOutParameter(1, OracleTypes.CURSOR);
			pc.setObject(2, 10);
			pc.setObject(3, 1);
			pc.setObject(4, test.getSqlCountFild());
			pc.setObject(5, test.getSqlOrderByFild());
			pc.setObject(6, conditionStr);
			pc.setObject(7, test.getSqlAliasNeedFilds());
			pc.setObject(8, test.getSqlAliasTableStr());
			pc.setObject(9, test.getSqlNeedFilds());
			pc.setObject(10, test.getSqlTableStr());
			pc.registerOutParameter(11, OracleTypes.INTEGER);
			pc.execute();
			
			ResultSet rs = (ResultSet)pc.getObject(1);
			if(rs!=null){
				// 设置总记录条数
				int recordAll = ((int)pc.getObject(11));
				System.out.println("查询到的数据 条数是" + recordAll);
				// 设置最大页码
				userList = new ArrayList<UserInfo>();
				
				while(rs.next()){
					if(rs.isFirst()){
						System.out.println("数据库返回多条数据第一次执行");
					}
					System.out.println("id 为 "  + rs.getString("eec_id"));
					System.out.println("class_id 为：" + rs.getString("class_Id"));
					userList.add(new UserInfo(
							Integer.parseInt(rs.getString("class_Id")==null?"0000":rs.getString("class_Id")),
							Integer.parseInt(rs.getString("EEC_Id")),
							rs.getString("EEC_Name"),
							rs.getString("sex"),
							Integer.parseInt(rs.getString("age")),
							rs.getString("email"),
							Long.parseLong(rs.getString("telephone")),
							rs.getString("role_name")
					));
				}
			}
			System.out.println("获取到sql数量是" + pc.getObject(11));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (userList!=null && userList.size() == 0){
			userList = null;
		}
		return userList;  
 	}
	
	private void showUserList(List<UserInfo> userList){
		Iterator<UserInfo> iterator = userList.iterator();
		UserInfo u = null;
		while(iterator.hasNext()){
			u = iterator.next();
			System.out.println("eec_id \t eec_name \t eec_roleId \t eec_roleName");
			System.out.println(u.getId()+"\t" + u.getName()+"\t" +  u.getRoleId()+"\t" + u.getRoleName()+"\t" );
		}
	}
	
	public static void main(String[] args) {
		PublicDAOImp pbt = new PublicDAOImp();
		List<UserInfo> userList = pbt.queryDataTest();
		pbt.showUserList(userList);
	}
	
}
