package com.mycode.system.user.service;

import com.mycode.common.common.domain.Course;
import com.mycode.system.menu.domain.Menu;
import com.mycode.system.user.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    Map<String, Object> getUserPageList(User user);

    User getUserById(String userId);

    boolean updateUser(User user);

    Map<String, Object> toGrant(String userId);

    boolean grant(String userId, String[] roleIds);

    List<Menu> getUserMenu(String userId);

    Map<String, Integer> getAuthority(String userId, String menuId);

    List<Course> getCourseListByUserId(String userId, String accountType, String courseName);

}
