package com.data.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.system.mapper.UserInfoMapper;
import com.data.system.po.UserInfo;

@Service
public class RoleService {
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	public UserInfo getUserName(int id) {
		return userInfoMapper.selectByPrimaryKey(id);
	}
}
