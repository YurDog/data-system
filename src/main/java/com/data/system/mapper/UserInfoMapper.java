package com.data.system.mapper;

import com.data.system.po.UserInfo;
import java.util.List;

import org.springframework.stereotype.Repository;
@Repository
public interface UserInfoMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(UserInfo record);

	UserInfo selectByPrimaryKey(Integer id);

	List<UserInfo> selectAll();

	int updateByPrimaryKey(UserInfo record);
}