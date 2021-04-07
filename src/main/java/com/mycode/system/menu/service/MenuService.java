package com.mycode.system.menu.service;

import com.mycode.system.menu.domain.Menu;
import com.mycode.system.role.domain.Role;

import java.util.List;
import java.util.Map;

public interface MenuService {

    List<Menu> getMenuTree();

    List<Menu> getParentMenuList(String menuId);

    List<Menu> getChildMenuList();

    List<Map<String,Object>> getShenHeSetEditFormMenuTree();

    List<Role> getRoleListByMenuId(String menuId);
}
