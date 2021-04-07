package com.mycode.system.user.mapper;

import com.mycode.common.Course;
import com.mycode.system.menu.domain.Menu;
import com.mycode.system.user.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Mapper
public interface UserMapper {

    List<User> getUserPageList(User user);

    User getUserById(@Param("userId") String userId);

    boolean updateUser(User user);

    //
    List<Map<String, Object>> getRoleListForTransferData(); //

    Set<String> getRoleIdListByUserId(@Param("userId") String userId); //获取用户拥有的角色编号

    boolean grant(@Param("userId") String userId, @Param("roleIds") String[] roleIds); //给用户授权

    boolean deleteRoleByUserId(@Param("userId") String userId); //根据用户编号删除用户绑定的所有角色

    //
    List<Menu> getUserMenu(@Param("userId") String userId); //获取用户拥有的菜单项

    Map<String,Integer> getAuthority(@Param("userId") String userId, @Param("menuId") String menuId); //

    //
    List<Course> getCourseListByUserId(@Param("userId") String userId, @Param("accountType") String accountType, @Param("courseName") String courseName);

}
