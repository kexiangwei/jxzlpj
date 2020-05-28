package com.mycode.system.menu.mapper;

import com.mycode.system.menu.domain.Menu;
import com.mycode.system.role.domain.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MenuMapper {

    List<Menu> getMenuPageList(Menu menu);

    List<Menu> getMenuTree();

    List<Menu> getParentMenuList(@Param("menuId") Long menuId);

    List<Menu> getChildMenuList();

    List<Role> getRoleListByMenuId(@Param("menuId") Long menuId);

}
