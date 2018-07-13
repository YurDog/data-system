//package com.data.system.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.data.system.service.RoleService;
//import com.wordnik.swagger.annotations.ApiOperation;
//import com.wordnik.swagger.annotations.ApiParam;
//import com.wordnik.swagger.annotations.ApiResponse;
//import com.wordnik.swagger.annotations.ApiResponses;
//
//@RestController
//@RequestMapping(value = "/my", produces = "application/json;charset=UTF-8")
//public class TestController {
//	
//	@Autowired
//	private RoleService roleService;
//	
//	@RequestMapping("/test")
//	@ApiResponses({@ApiResponse(code=200,message="成功"),@ApiResponse(code=400,message="失败")})
//	@ApiOperation(value="测试value",httpMethod="GET",notes="测试note")
//	public String test(@RequestParam(name = "name", required = true)@ApiParam(value="用户名",name="name") String name) {
//		String userName = roleService.getUserName(1);
//		return name + "success" + userName;
//	}
//}
