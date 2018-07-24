package com.data.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.data.system.mapper.UserInfoMapper;
import com.data.system.po.UserInfo;
import com.data.system.util.ResponseFormatUtil;

@Service
public class RoleService {

	@Autowired
	private UserInfoMapper userInfoMapper;
	@Transactional
	public JSONObject getUserName(int id,String phone) {
		UserInfo info = userInfoMapper.selectByPrimaryKey(id);
		if(!StringUtils.isEmpty(phone)){
			info.setPhone(phone);
			userInfoMapper.updateByPrimaryKey(info);
			String a = null;
			a.equals("");
		}
		return ResponseFormatUtil.success(info);
	}
}
