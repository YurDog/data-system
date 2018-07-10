package com.data.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.system.dao.RoleDAO;

@Service
public class RoleService {
	@Autowired
	RoleDAO roleDAO;
	
	public String getUserName(int id) {
		return roleDAO.findUserNameById(id);
	}
}
