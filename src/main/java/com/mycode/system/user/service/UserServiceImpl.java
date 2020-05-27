package com.mycode.system.user.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.system.menu.domain.Menu;
import com.mycode.system.role.domain.Role;
import com.mycode.system.role.mapper.RoleMapper;
import com.mycode.system.user.domain.User;
import com.mycode.system.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

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
    public Map<String, Object> getUserDetail(String userId) {
        User userById = userMapper.getUserById(userId);
        return userMapper.getUserDetail(userById);
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
        List<Role> dbRoleList = roleMapper.getRolePageList(new Role());
        List<Map<String, Object>> roleList = new ArrayList<>();
        for (Role dbRole : dbRoleList) {
            Map<String, Object> role = new HashMap<>();
            role.put("title",dbRole.getRoleName());
            role.put("value",dbRole.getRoleId());
            roleList.add(role);
        }
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("roleArr",roleList); //待选角色列表
        resultMap.put("userRoleIdArr",userMapper.getRoleByUserId(userId)); //已选角色列表
        return resultMap;
    }

    @Override
    public boolean grant(String userId, String[] roleIdArr) {
        userMapper.deleteRoleByUserId(userId); //若用户未绑定角色，会返回0，故不能使用if(bool){}
        if(roleIdArr ==null || roleIdArr.length ==0){
            return true;
        }
        return userMapper.grant(userId,roleIdArr);
    }

    @Override
    public List<Menu> getUserMenu(String userId) {
        List<Menu> menuList = userMapper.getUserMenu(userId);
        return Menu.getMenuTree(menuList);
    }
}
