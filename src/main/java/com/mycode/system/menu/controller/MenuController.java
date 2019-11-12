package com.mycode.system.menu.controller;

import com.mycode.system.menu.domain.Menu;
import com.mycode.system.menu.domain.MenuCol;
import com.mycode.system.menu.domain.MenuTab;
import com.mycode.system.menu.service.MenuService;
import com.mycode.system.role.domain.Role;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @auther kexiangwei
 * @date 2019/6/24
 */
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

    @ResponseBody
    @RequestMapping("/getMenuList.do")
    public JsonResult<Object> getMenuList(Menu menu){
        Map<String, Object> resultMap = menuService.getMenuList(menu);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/getMenuColInfo.do")
    public JsonResult<Object> getMenuColInfo(Long menuId){
        List<MenuCol>  menuColList = menuService.getMenuColInfo(menuId);
        return JsonResult.success(menuColList);
    }

    @ResponseBody
    @RequestMapping("/insertMenu.do")
    public JsonResult<Object> insertMenu(Menu menu){
        boolean bool = menuService.insertMenu(menu);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }
    @ResponseBody
    @RequestMapping("/deleteMenu.do")
    public JsonResult<Object> deleteMenu(@RequestParam("menuId") String menuId){
        //查看是否关联了角色
        List<Role> roleList = menuService.getRoleListByMenuId(menuId);
        Set<String> collect = roleList.stream().map(Role::getRoleName).collect(Collectors.toSet());
        if(!roleList.isEmpty()){
            return JsonResult.error(400,"已关联角色"+collect);
        }
        //执行删除
        boolean bool = menuService.deleteMenu(menuId);
        if(!bool){
            return JsonResult.error("删除失败");
        }
        return JsonResult.success("删除成功");
    }
    @ResponseBody
    @RequestMapping("/insertMenuTab.do")
    public JsonResult<Object> insertMenuTab(MenuTab menuTab){
        Long menuId = menuService.insertMenuTab(menuTab);
        if(StringUtils.isEmpty(menuId)){
            return JsonResult.error();
        }
        return JsonResult.success(menuId);
    }
}
