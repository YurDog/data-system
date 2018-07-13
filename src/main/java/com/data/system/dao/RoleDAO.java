package com.data.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.data.system.po.Role;

@Repository
public interface RoleDAO {
	public String findUserNameById(int id);

	public void inserRole(@Param("role") Role role);

	public List<Role> findRolesByIds(@Param("ids") int[] ids);
}
