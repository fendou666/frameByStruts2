package com.chinasofti.eecuser.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import sun.security.pkcs11.Secmod.DbMode;

import com.chinasofti.eecuser.model.javabean.MsgList;
import com.chinasofti.eecuser.model.javabean.UserAceessANW;
import com.chinasofti.eecuser.tools.jdbc.DBUtil;

public class DAOUserAccessANWImp implements IDAOUserAccessANW {
	private String errInfo;
	
	public String getErrInfo() {
		return errInfo;
	}
	
	@Override
	public boolean accessAnswer(UserAceessANW UA, String answer3) {
		boolean ret = false;
		String sql = "select answer1, answer2, answer3 from QSTVerify "
				+ "WHERE QSTVerify_id="
				+ "(SELECT QSTVerify_id FROM EECUSER WHERE eec_id=?)";
		ArrayList objList = new ArrayList();
		objList.add(UA.getEec_id());
		System.out.println("�һ������ѧ����" + UA.getEec_id());
		ResultSet rs = DBUtil.getJDBC().queryDate(sql, objList);
		if(DBUtil.getJDBC().getErrInfo()==null){
			if(rs!=null){
				try {
					while(rs.next()){
						if(rs.getString("answer1").equals(UA.getAnswer1())
							&&	rs.getString("answer2").equals(UA.getAnswer1())
							&&	rs.getString("answer3").equals(answer3)
								){
							ret = true;
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ret;
	}
	public boolean insertDateData(){
		String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		String sql = "INSERT INTO EECMSG VALUES(msgSeq.nextval, 170000001,'' ,'�����Ǹ���',"  
            + "to_date('" + dateStr + "','yyyy-mm-dd hh24:mi:ss'))";
		int upLine = DBUtil.getJDBC().updateSql(sql, null);
		System.out.println("��������" + upLine);
		return false;
	}
	
	public void getDateData(){
		String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		String sql = "SELECT MSG_ID, EEC_ID, title, msgContent, msgTime FROM EECMSG WHERE MSG_ID=?";
		ArrayList objList = new ArrayList();
		objList.add(13);
		ResultSet rs = DBUtil.getJDBC().queryDate(sql, objList);
		// ��������nullֵ����ֱ�ӵ�����
		try {
			while(rs.next()){
				String sqlDate =  rs.getString("msgTime");
				System.out.println("ʱ����" + sqlDate.substring(0, sqlDate.length()-2));
				/*MsgList msglist = new MsgList();
				sqlDate = sqlDate.substring(0, sqlDate.length()-2);
				Date ds =  new SimpleDateFormat().parse(sqlDate);
				msglist.setMsgTime(ds);
				Date gs =  msglist.getMsgTime();
				System.out.println(gs.toString());*/
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public static void main(String[] args) {
		new DAOUserAccessANWImp().getDateData();
	}
}
