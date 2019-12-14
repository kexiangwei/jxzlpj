package com.mycode.system.role.mapper;

import com.mycode.system.menu.domain.Menu;
import com.mycode.system.role.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @auther kexiangwei
 * @date 2019/6/24
 */
@Mapper
public interface RoleMapper {

    List<Role> getRoleList(Role role);

    List<Role> getRoleListByMenuId(@Param("menuId") Long menuId);

    @Insert("insert into sys_role values (#{roleId},#{roleName})")
    boolean addRole(@Param("roleId") String roleId, @Param("roleName") String roleName);

    @Update("update sys_role set role_name = #{roleName} where role_id = #{roleId}")
    boolean updateRole(@Param("roleId") String roleId, @Param("roleName") String roleName);

    @ResultType(Menu.class)
    @Select("select DISTINCT m.* from sys_role_menu rm LEFT JOIN sys_menu m on m.menu_id = rm.menu_id " +
            "WHERE role_id = #{roleId}")
    List<Menu> getMenuByRoleId(@Param("roleId") String roleId);

    boolean addRoleMenu(@Param("roleId") String roleId, @Param("menuIdArr") String[] menuIdArr);

    @Delete("delete from sys_role_menu where role_id = #{roleId}")
    boolean deleteRoleMenuByRoleId(@Param("roleId") String roleId);

}
