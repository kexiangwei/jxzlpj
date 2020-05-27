package com.mycode.system.user.service;

import com.mycode.system.menu.domain.Menu;
import com.mycode.system.user.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    Map<String, Object> getUserPageList(User user);

    Map<String, Object> getUserDetail(String userId);

    User getUserById(String userId);

    boolean updateUser(User user);

    Map<String, Object> toGrant(String userId);

    boolean grant(String userId, String[] roleIdArr);

    List<Menu> getUserMenu(String userId);
}
