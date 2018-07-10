package com.data.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.data.system.service.RoleService;

@RestController
public class TestController {
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/test")
	public String test(@RequestParam(name = "name", required = true) String name) {
		String userName = roleService.getUserName(1);
		return name + "success" + userName;
	}
}
