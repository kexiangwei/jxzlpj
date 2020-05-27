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

@CrossOrigin
@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ResponseBody
    @RequestMapping("/getRolePageList.do")
    public JsonResult<Object> getRolePageList(Role role){
        Map<String,Object> resultMap = roleService.getRolePageList(role);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/insertOrUpdateRoleMenu.do")
    public JsonResult<Object> insertOrUpdateRoleMenu(@RequestParam(value = "roleId",required = false) Long roleId
            , @RequestParam("roleName") String roleName, String[] menuIdArr){
        boolean bool = roleService.insertOrUpdateRoleMenu(roleId,roleName,menuIdArr);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }
}
