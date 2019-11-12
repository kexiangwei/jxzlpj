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

    List<Menu> getMenuTree();

    Map<String, Object> getMenuList(Menu menu);

    List<MenuCol> getMenuColInfo(Long menuId);

    boolean insertMenu(Menu menu);

    List<Role> getRoleListByMenuId(String menuId);

    boolean deleteMenu(String menuId);

    Long insertMenuTab(MenuTab menuTab);
}
