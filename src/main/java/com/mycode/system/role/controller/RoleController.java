package com.mycode.system.role.controller;

import com.mycode.system.role.domain.Role;
import com.mycode.system.role.service.RoleService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @auther kexiangwei
 * @date 2019/6/24
 */
@CrossOrigin
@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ResponseBody
    @RequestMapping("/getRoleList.do")
    public JsonResult<Object> getRoleList(Role role){
        Map<String,Object> resultMap = roleService.getRoleList(role);
        return JsonResult.success(resultMap);
    }

    /**
     * 筛选出拥有当前菜单审核权限的角色
     * @param menuId 菜单编号
     * @return
     */
    @ResponseBody
    @RequestMapping("/getRoleListByMenuId.do")
    public JsonResult<Object> getRoleListByMenuId(@RequestParam("menuId") Long menuId){
        List<Role> roleList = roleService.getRoleListByMenuId(menuId);
        return JsonResult.success(roleList);
    }

    @ResponseBody
    @RequestMapping("/insertOrUodateRoleMenu.do")
    public JsonResult<Object> insertOrUodateRoleMenu(@RequestParam(value = "roleId",required = false) String roleId
            , @RequestParam("roleName") String roleName,String[] menuIdArr){
        boolean bool = roleService.insertOrUodateRoleMenu(roleId,roleName,menuIdArr);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }
}
