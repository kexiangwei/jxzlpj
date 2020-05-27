package com.mycode.system.menu.service;

import com.mycode.system.menu.domain.Menu;
import com.mycode.system.role.domain.Role;

import java.util.List;
import java.util.Map;

public interface MenuService {

    Map<String, Object> getMenuPageList(Menu menu);

    List<Menu> getMenuTree();

    List<Role> getRoleListByMenuId(Long menuId);
}
