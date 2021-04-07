package com.mycode.system.role.service;

import com.mycode.system.role.domain.Role;

import java.util.List;
import java.util.Map;

public interface RoleService {

    Map<String,Object> getRolePageList(Role role);

    List<Role> getRoleList();

    boolean insertOrUpdateRoleMenu(String roleId, String roleName, String[] menuIds);
}
