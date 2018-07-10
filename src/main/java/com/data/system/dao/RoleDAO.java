package com.data.system.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface RoleDAO {
	public String findUserNameById(int id);
}
