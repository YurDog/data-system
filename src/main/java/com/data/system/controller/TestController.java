package com.data.system.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.data.system.service.UserInfoService;

@RestController
@RequestMapping(value = "/my", produces = "application/json;charset=UTF-8")
public class TestController {

	Logger logger = LoggerFactory.getLogger(TestController.class);

	@Autowired
	private UserInfoService userInfoService;

	@RequestMapping("/test")
	public String test(@RequestParam(name = "id", required = true) Integer id, @RequestParam(name = "phone", required = false) String phone) throws Exception {
		JSONObject result = userInfoService.getUserInfo(id, phone);
		return result.toJSONString();
	}
}
