package com.mycode.system.user.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.system.menu.domain.Menu;
import com.mycode.system.role.domain.Role;
import com.mycode.system.user.domain.User;
import com.mycode.system.role.mapper.RoleMapper;
import com.mycode.system.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @auther kexiangwei
 * @date 2019/7/13
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Map<String, Object> getUserList(User user) {
        Map<String, Object> map = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(user.getPageIndex(), user.getPageSize());
        List<User> pageList = userMapper.getUserList(user);
        map.put("pageList", pageList);
        map.put("totalNum",pageInfo.getTotal());
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
        List<Role> dbRoleList = roleMapper.getRoleList(new Role());
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
        userMapper.deleteRoleByUserId(userId);
        return userMapper.grant(userId,roleIdArr);
    }

    @Override
    public List<Menu> getUserMenu(String userId) {
        List<Menu> menuList = userMapper.getUserMenu(userId);
        return getMenuTree(menuList);
    }

    public List<Menu> getMenuTree(List<Menu> menuList){
        List<Menu> resultMenuList = new ArrayList<>();
        Map<Long,Menu> map = new HashMap<>();
        for (Menu menu : menuList) {
            map.put(menu.getMenuId(),menu);
        }
        for (Menu menu : menuList) {
            if(StringUtils.isEmpty(menu.getPid())){
                resultMenuList.add(menu);
            }else{
                Menu parent = map.get(menu.getPid());
                parent.getChildren().add(menu);
            }
        }
        return resultMenuList;
    }
}
