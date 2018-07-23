package com.data.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.data.system.po.UserInfo;
import com.data.system.service.RoleService;

@RestController
@RequestMapping(value = "/my", produces = "application/json;charset=UTF-8")
public class TestController {

	@Autowired
	private RoleService roleService;

	@RequestMapping("/test")
	public String test(@RequestParam(name = "id", required = true) Integer id) {
		System.out.println("aaaaa");
		UserInfo userInfo = roleService.getUserName(id);
		System.out.println("bbbb" + userInfo);
		return userInfo ==null? "":userInfo.getUserName();
	}
}
