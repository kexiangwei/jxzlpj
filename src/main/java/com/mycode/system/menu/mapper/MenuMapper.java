package com.mycode.system.menu.mapper;

import com.mycode.system.menu.domain.Menu;
import com.mycode.system.role.domain.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MenuMapper {

    List<Menu> getMenuTree();

    List<Menu> getParentMenuList(@Param("menuId") String menuId);

    List<Menu> getChildMenuList();

    List<Map<String,Object>> getShenHeSetEditFormMenuTree(@Param("menuId") String menuId);

    List<Role> getRoleListByMenuId(@Param("menuId") String menuId);
}
