package com.mycode.system.menu.controller;

import com.mycode.system.menu.domain.Menu;
import com.mycode.system.menu.service.MenuService;
import com.mycode.system.role.domain.Role;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
public class MenuController {

    @Autowired
    private MenuService menuService;

   /* @ResponseBody
    @RequestMapping("/getMenuPageList.do")
    public JsonResult<Object> getMenuPageList(Menu menu){
        Map<String, Object> resultMap = menuService.getMenuPageList(menu);
        return JsonResult.success(resultMap);
    }*/

    @ResponseBody
    @RequestMapping("/getMenuTree.do")
    public JsonResult<Object> getMenuTree(){
        List<Menu> menuList = menuService.getMenuTree();
        return JsonResult.success(menuList);
    }

    /**
     * 筛选出拥有当前菜单审核权限的角色
     * @param menuId 菜单编号
     * @return
     */
    @ResponseBody
    @RequestMapping("/getRoleListByMenuId.do")
    public JsonResult<Object> getRoleListByMenuId(@RequestParam("menuId") Long menuId){
        List<Role> roleList = menuService.getRoleListByMenuId(menuId);
        return JsonResult.success(roleList);
    }
}
