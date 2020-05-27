package com.mycode.system.role.service;

import com.mycode.system.role.domain.Role;

import java.util.List;
import java.util.Map;

public interface RoleService {

    Map<String,Object> getRolePageList(Role role);

    boolean insertOrUpdateRoleMenu(Long roleId, String roleName, String[] menuIdArr);

}
