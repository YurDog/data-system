package com.data.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.data.system.annotation.FileLog;
import com.data.system.mapper.UserInfoMapper;
import com.data.system.po.UserInfo;
import com.data.system.util.ResponseFormatUtil;

@Service
@Transactional
public class UserInfoService {

	@Autowired
	private UserInfoMapper userInfoMapper;

	@FileLog
	public JSONObject getUserInfo(int id, String phone) {
		UserInfo info = userInfoMapper.selectByPrimaryKey(id);
		return ResponseFormatUtil.success(info);
	}
}
