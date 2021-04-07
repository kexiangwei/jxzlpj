package com.mycode.system.menu.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.system.menu.domain.Menu;
import com.mycode.system.menu.mapper.MenuMapper;
import com.mycode.system.role.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getMenuTree() {
        List<Menu> menuList = menuMapper.getMenuTree();
        return Menu.getMenuTree(menuList);
    }

    @Override
    public List<Menu> getParentMenuList(String menuId) {
        return menuMapper.getParentMenuList(menuId);
    }

    @Override
    public List<Menu> getChildMenuList() {
        return menuMapper.getChildMenuList();
    }

    @Override
    public List<Map<String,Object>> getShenHeSetEditFormMenuTree() {
        List<Map<String,Object>> menuList = menuMapper.getShenHeSetEditFormMenuTree(null);
        for (Map<String,Object> map : menuList) {
            map.put("open",true);
            map.put("checked",false);
            map.put("children", menuMapper.getShenHeSetEditFormMenuTree(map.get("id").toString()));
        }
        return menuList;
    }

    @Override
    public List<Role> getRoleListByMenuId(String menuId) {
        return menuMapper.getRoleListByMenuId(menuId);
    }
}
