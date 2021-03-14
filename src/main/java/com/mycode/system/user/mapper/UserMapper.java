package com.mycode.system.user.mapper;

import com.mycode.system.menu.domain.Menu;
import com.mycode.system.user.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Mapper
public interface UserMapper {

    List<User> getUserPageList(User user); //获取用户列表

    User getUserById(@Param("userId") String userId); //根据用户编号获取用户信息

    User getUserDetail(User user); //获取用户详情

    boolean updateUser(User user); //修改用户信息

    Set<String> getRoleByUserId(@Param("userId") String userId); //获取用户拥有的角色编号

    boolean grant(@Param("userId") String userId, @Param("roleIdArr") String[] roleIdArr); //给用户授权

    boolean deleteRoleByUserId(@Param("userId") String userId); //根据用户编号删除用户绑定的所有角色

    List<Menu> getUserMenu(@Param("userId") String userId); //获取用户拥有的菜单项

    Map<String,Integer> getAuthority(@Param("userId") String userId, @Param("menuId") String menuId); //根据userId,menuId 查询用户是否拥有指定菜单的提交、审核权限
}
