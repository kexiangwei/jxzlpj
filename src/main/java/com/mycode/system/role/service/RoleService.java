package com.mycode.system.role.service;

import com.mycode.system.role.domain.Role;

import java.util.Map;

/**
 * @auther kexiangwei
 * @date 2019/7/13
 */
public interface RoleService {

    Map<String,Object> getRoleList(Role role);

    boolean insertOrUodateRoleMenu(String roleId, String roleName, String[] menuIdArr);

}
