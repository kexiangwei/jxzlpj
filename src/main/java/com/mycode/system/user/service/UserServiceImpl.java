package com.mycode.system.user.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.common.common.domain.Course;
import com.mycode.system.menu.domain.Menu;
import com.mycode.system.user.domain.User;
import com.mycode.system.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<String, Object> getUserPageList(User user) {
        Map<String, Object> map = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(user.getPageIndex(), user.getPageSize());
        List<User> pageList = userMapper.getUserPageList(user);
        map.put("totalNum", pageInfo.getTotal());
        map.put("pageList", pageList);
        return map;
    }

    @Override
    public User getUserById(String userId) {
        return userMapper.getUserById(userId);
    }

    @Override
    public boolean updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public Map<String, Object> toGrant(String userId) {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("transferData",userMapper.getRoleListForTransferData()); //待选角色列表
        resultMap.put("selectedRoleIdList",userMapper.getRoleIdListByUserId(userId)); //已选角色编号列表
        return resultMap;
    }

    @Override
    @Transactional
    public boolean grant(String userId, String[] roleIds) {
        Set<String> selectedRoleIdList = userMapper.getRoleIdListByUserId(userId);
        if(selectedRoleIdList != null && !selectedRoleIdList.isEmpty()){
            userMapper.deleteRoleByUserId(userId);
        }
        //移除权限场景
        if(roleIds != null && roleIds.length == 0){
            return true;
        }
        return userMapper.grant(userId, roleIds); //授予权限场景
    }

    @Override
    public List<Menu> getUserMenu(String userId) {
        List<Menu> menuList = userMapper.getUserMenu(userId);
        return Menu.getMenuTree(menuList);
    }

    @Override
    public Map<String, Integer> getAuthority(String userId, String menuId) {
        return userMapper.getAuthority(userId,menuId);
    }

    @Override
    public List<Course> getCourseListByUserId(String userId, String accountType, String courseName) {
        return userMapper.getCourseListByUserId(userId, accountType, courseName);
    }
}
