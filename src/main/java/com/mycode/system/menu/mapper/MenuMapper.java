package com.mycode.system.menu.mapper;

import com.mycode.system.menu.domain.Menu;
import com.mycode.system.menu.domain.MenuCol;
import com.mycode.system.menu.domain.MenuTab;
import com.mycode.system.role.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @auther kexiangwei
 * @date 2019/6/24
 */
@Mapper
public interface MenuMapper {

    List<Menu> getMenuList(Menu menu);

    @Select("select menu_id,menu_id id,menu_name,menu_name title,pid,url,icon from sys_menu order by MENU_ID")
    List<Menu> getMenuTree();

    boolean insertMenu(Menu menu);

    @Delete("delete from SYS_MENU where menu_id = #{menuId}")
    boolean deleteMenu(@Param("menuId") String menuId);

    boolean insertMenuTab(MenuTab menuTab);

    boolean createMenuTab(MenuTab menuTab);

    List<MenuCol> getMenuColInfo(Long menuId);

    boolean insertMenuCol(MenuTab menuTab);

    @Select("SELECT r.* FROM sys_role_menu rm LEFT JOIN sys_role r ON r.role_id = rm.role_id WHERE menu_id = #{menuId}")
    List<Role> getRoleListByMenuId(@Param("menuId") String menuId);
}
