package com.data.system.mapper;

import com.data.system.po.UserLevel;
import java.util.List;

import org.springframework.stereotype.Repository;
@Repository
public interface UserLevelMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(UserLevel record);

	UserLevel selectByPrimaryKey(Integer id);

	List<UserLevel> selectAll();

	int updateByPrimaryKey(UserLevel record);
}