package com.mycode.system.role.mapper;

import com.mycode.system.menu.domain.Menu;
import com.mycode.system.role.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoleMapper {

    List<Role> getRolePageList(Role role);

    boolean addRole(@Param("roleId") Long roleId, @Param("roleName") String roleName);

    boolean updateRole(@Param("roleId") Long roleId, @Param("roleName") String roleName);

    List<Menu> getMenuListByRoleId(@Param("roleId") Long roleId);

    boolean addRoleMenu(@Param("roleId") Long roleId, @Param("menuIdArr") String[] menuIdArr);

    boolean deleteRoleMenuByRoleId(@Param("roleId") Long roleId);

}
