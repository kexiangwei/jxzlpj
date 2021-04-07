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

    @ResponseBody
    @RequestMapping("/getMenuTree.do")
    public JsonResult<Object> getMenuTree(){
        List<Menu> menuList = menuService.getMenuTree();
        return JsonResult.success(menuList);
    }

    /**
     *
     * @param menuId
     * @return 通用设置-审核流程-一级菜单下拉选项
     */
    @ResponseBody
    @RequestMapping("/getParentMenuList.do")
    public JsonResult<Object> getParentMenuList(@RequestParam(value = "menuId",required = false) String menuId){
        List<Menu> menuList = menuService.getParentMenuList(menuId);
        return JsonResult.success(menuList);
    }

    /**
     *
     * @return 通用设置-审核流程-二级菜单下拉选项
     */
    @ResponseBody
    @RequestMapping("/getChildMenuList.do")
    public JsonResult<Object> getChildMenuList(){
        List<Menu> menuList = menuService.getChildMenuList();
        return JsonResult.success(menuList);
    }

    /**
     *
     * @return 通用设置-审核流程-新增&修改页面下拉菜单
     * @说明：为满足layui-treeSelect 插件的数据格式，写的这个接口
     */
    @ResponseBody
    @RequestMapping("/getShenHeSetEditFormMenuTree.do")
    public List<Map<String,Object>> getShenHeSetEditFormMenuTree(){
        return menuService.getShenHeSetEditFormMenuTree();
    }

    /**
     *
     * @param menuId 菜单编号
     * @return 通用设置-审核流程-节点设置-拥有当前菜单审核权限的用户组下拉选项
     */
    @ResponseBody
    @RequestMapping("/getRoleListByMenuId.do")
    public JsonResult<Object> getRoleListByMenuId(@RequestParam("menuId") String menuId){
        List<Role> roleList = menuService.getRoleListByMenuId(menuId);
        return JsonResult.success(roleList);
    }
}
