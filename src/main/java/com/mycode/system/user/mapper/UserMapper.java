package com.mycode.system.user.mapper;

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

    User getUserDetail(User user);

    boolean updateUser(User user);

    Set<String> getRoleByUserId(@Param("userId") String userId);

    boolean grant(@Param("userId") String userId, @Param("roleIdArr") String[] roleIdArr);

    boolean deleteRoleByUserId(@Param("userId") String userId);

    List<Menu> getUserMenu(@Param("userId") String userId);

    Map<String,Integer> getAuthority(@Param("menuId") String menuId, @Param("userId") String userId);
}
