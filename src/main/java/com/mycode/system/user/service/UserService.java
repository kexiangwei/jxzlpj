package com.mycode.system.user.service;

import com.mycode.system.menu.domain.Menu;
import com.mycode.system.user.domain.User;

import java.util.List;
import java.util.Map;

/**
 * @auther kexiangwei
 * @date 2019/7/13
 */
public interface UserService {

    User getUserById(String userId);

    Map<String, Object> getUserList(User user);

    Map<String, Object> getUserDetail(String userId);

    boolean updateUser(User user);

    Map<String, Object> toGrant(String userId);

    boolean grant(String userId, String[] roleIdArr);

    List<Menu> getUserMenu(String userId);
}
