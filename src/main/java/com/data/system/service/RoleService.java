package com.data.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.data.system.mapper.UserInfoMapper;
import com.data.system.po.UserInfo;
import com.data.system.util.ResponseFormatUtil;

@Service
public class RoleService {

	@Autowired
	private UserInfoMapper userInfoMapper;

	public JSONObject getUserName(int id) {
		UserInfo info = userInfoMapper.selectByPrimaryKey(id);
		return ResponseFormatUtil.success(info);
	}
}
