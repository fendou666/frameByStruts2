package com.chinasofti.eecuser.model.dao;

import java.util.List;
import java.util.Map;

import com.chinasofti.eecuser.model.javabean.UserInfo;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Long eecId);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Long eecId);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
    
    List<UserInfo> queryDataByCondition(Map<String, Object> parms);
}