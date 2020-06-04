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
    public Map<String, Object> getMenuPageList(Menu menu) {
        Map<String, Object> resultMap = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(menu.getPageIndex(), menu.getPageSize());
        List<Menu> pageList = menuMapper.getMenuPageList(menu);
        resultMap.put("totalNum",pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public List<Menu> getMenuTree() {
        List<Menu> menuList = menuMapper.getMenuTree();
        return Menu.getMenuTree(menuList);
    }

    @Override
    public List<Menu> getParentMenuList(Long menuId) {
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
            map.put("children", menuMapper.getShenHeSetEditFormMenuTree(Long.valueOf(map.get("id").toString())));
        }
        return menuList;
    }

    @Override
    public List<Role> getRoleListByMenuId(Long menuId) {
        List<Role> roleList = menuMapper.getRoleListByMenuId(menuId);
       /* roleList.forEach((role)-> {
            if (role.getRoleId().equals("999999")) {
                role.setDisabled(true);
            } else {
                role.setDisabled(false);
            }
        });*/
        return roleList;
    }
}
