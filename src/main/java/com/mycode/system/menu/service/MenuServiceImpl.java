package com.mycode.system.menu.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.system.menu.domain.MenuCol;
import com.mycode.system.menu.domain.MenuTab;
import com.mycode.system.menu.mapper.MenuMapper;
import com.mycode.system.menu.domain.Menu;
import com.mycode.system.role.domain.Role;
import com.mycode.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther kexiangwei
 * @date 2019/7/13
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public Map<String, Object> getMenuList(Menu menu) {
        Map<String, Object> resultMap = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(menu.getPageIndex(), menu.getPageSize());
        List<Menu> pageList = menuMapper.getMenuList(menu);
        resultMap.put("totalNum",pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public List<Menu> getMenuTree() {
        List<Menu> menuList = menuMapper.getMenuTree();
        return getMenuTree(menuList);
    }
    public List<Menu> getMenuTree(List<Menu> menuList){
        List<Menu> resultMenuList = new ArrayList<>();
        Map<Long,Menu> map = new HashMap<>();
        for (Menu menu : menuList) {
            map.put(menu.getMenuId(),menu);
        }
        for (Menu menu : menuList) {
            if(org.springframework.util.StringUtils.isEmpty(menu.getPid())){
                resultMenuList.add(menu);
            }else{
                Menu parent = map.get(menu.getPid());
                parent.getChildren().add(menu);
            }
        }
        return resultMenuList;
    }

    @Override
    public boolean insertMenu(Menu menu) {
        menu.setMenuId(Long.valueOf(StringUtils.guid(16,true)));
        return menuMapper.insertMenu(menu);
    }

    @Override
    public boolean deleteMenu(String menuId) {
        return menuMapper.deleteMenu(menuId);
    }

    @Override
    public Long insertMenuTab(MenuTab menuTab) {
        Long menuId = Long.valueOf(StringUtils.guid(8,true));
        menuTab.setMenuId(menuId);
        boolean bool = menuMapper.insertMenuTab(menuTab);
        if(bool){
            menuMapper.createMenuTab(menuTab);
            menuMapper.insertMenuCol(menuTab);
        }else{
            return null;
        }
        return menuId;
    }

    @Override
    public List<MenuCol> getMenuColInfo(Long menuId) {
        return menuMapper.getMenuColInfo(menuId);
    }

    @Override
    public List<Role> getRoleListByMenuId(String menuId) {
        return menuMapper.getRoleListByMenuId(menuId);
    }

}
