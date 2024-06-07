package com.rushi.service;

import java.util.List;

import com.rushi.entity.Role;

public interface RoleService {
	
	public Role createRole(Role role);
	
	public List<Role> getAllRoles();
	
	public Role getRoleById(Integer roleId);
	
	public Role deleteRoleById(Integer roleId);

}
