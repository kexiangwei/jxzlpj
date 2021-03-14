package com.mycode.system.user.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.system.menu.domain.Menu;
import com.mycode.system.role.domain.Role;
import com.mycode.system.role.mapper.RoleMapper;
import com.mycode.system.user.domain.User;
import com.mycode.system.user.mapper.UserMapper;
import com.mycode.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    public User getUserById(String userId) {
        User user = userMapper.getUserById(userId);
        if(user !=null && StringUtils.isNotEmpty(user.getAccountType())){
            user = userMapper.getUserDetail(user);
        }
        return user;
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
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public boolean grant(String userId, String[] roleIdArr) {
        Set<String> userRoleIdArr = userMapper.getRoleByUserId(userId);
        if(userRoleIdArr != null && !userRoleIdArr.isEmpty()){
            userMapper.deleteRoleByUserId(userId);
        }
        //移除权限
        if(roleIdArr != null && roleIdArr.length == 0){
            return true;
        }
        //授予权限
        return userMapper.grant(userId, roleIdArr);
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
}
