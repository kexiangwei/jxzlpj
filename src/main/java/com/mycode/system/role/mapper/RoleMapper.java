package com.mycode.system.role.mapper;

import com.mycode.system.menu.domain.Menu;
import com.mycode.system.role.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface RoleMapper {

    List<Role> getRolePageList(Role role);

    List<Role> getRoleList();

    boolean insertRole(@Param("roleId") String roleId, @Param("roleName") String roleName);

    boolean updateRole(@Param("roleId") String roleId, @Param("roleName") String roleName);

    List<Menu> getMenuListByRoleId(@Param("roleId") String roleId);

    boolean addRoleMenu(@Param("roleId") String roleId, @Param("menuIds") String[] menuIds);

    boolean deleteRoleMenuByRoleId(@Param("roleId") String roleId);
}
