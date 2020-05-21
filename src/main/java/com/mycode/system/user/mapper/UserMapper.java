package com.mycode.system.user.mapper;

import com.mycode.system.menu.domain.Menu;
import com.mycode.system.user.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Mapper
public interface UserMapper {

    List<User> getUserList(User user);

    Map<String, Object> getUserDetail(User user);

    @ResultType(User.class)
    @Select("select * from sys_user where user_id = #{userId}")
    User getUserById(@Param("userId") String userId);

    boolean updateUser(User user);

    boolean grant(@Param("userId") String userId, @Param("roleIdArr") String[] roleIdArr);

    @ResultType(String.class)
    @Select("select role_id from sys_user_role where user_id = #{userId}")
    Set<String> getRoleByUserId(@Param("userId") String userId);

    @Delete("delete from sys_user_role where user_id = #{userId}")
    boolean deleteRoleByUserId(@Param("userId") String userId);

    List<Menu> getUserMenu(@Param("userId") String userId);
}
