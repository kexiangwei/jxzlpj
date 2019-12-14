package com.mycode.system.menu.service;

import com.mycode.system.menu.domain.Menu;
import com.mycode.system.menu.domain.MenuCol;
import com.mycode.system.menu.domain.MenuTab;
import com.mycode.system.role.domain.Role;

import java.util.List;
import java.util.Map;

/**
 * @auther kexiangwei
 * @date 2019/7/13
 */
public interface MenuService {

    Map<String, Object> getMenuList(Menu menu);

    List<Menu> getMenuTree();

    boolean insertMenu(Menu menu);

    boolean deleteMenu(String menuId);

    Long insertMenuTab(MenuTab menuTab);

    List<MenuCol> getMenuColInfo(Long menuId);

    List<Role> getRoleListByMenuId(String menuId);
}
