package com.data.system.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.data.system.annotation.FileLog;
import com.data.system.mapper.UserInfoMapper;
import com.data.system.po.UserInfo;
import com.data.system.util.ChatbotSend;
import com.data.system.util.ResponseFormatUtil;

@Service
public class UserInfoService {

	@Autowired
	private UserInfoMapper userInfoMapper;

	@Transactional
	@FileLog
	public JSONObject getUserInfo(int id, String phone) {
		UserInfo info = userInfoMapper.selectByPrimaryKey(id);
		if (!StringUtils.isEmpty(phone)) {
			info.setPhone(phone);
			userInfoMapper.updateByPrimaryKey(info);
			try {
				Thread.sleep(600);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
//			new ChatbotSend().send("出错了出错了", null, true);
		}
		return ResponseFormatUtil.success(info);
	}
}
