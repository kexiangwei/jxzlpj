package com.mycode.system.user.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycode.system.menu.domain.Menu;
import com.mycode.system.user.domain.User;
import com.mycode.system.user.service.UserService;
import com.mycode.util.JsonResult;

@CrossOrigin
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/getUserPageList.do")
    public JsonResult<Object> getUserPageList(User user){
        Map<String, Object> resultMap = userService.getUserPageList(user);
        return JsonResult.success(resultMap);
    }

    /*@ResponseBody
    @RequestMapping("/getUserById.do")
    public JsonResult<Object> getUserById(@RequestParam("userId") String userId){
        User user = userService.getUserById(userId);
        return JsonResult.success(user);
    }*/

    @ResponseBody
    @RequestMapping("/updateUser.do")
    public JsonResult<Object> updateUser(User user){
        boolean bool = userService.updateUser(user);
        if(!bool){
            return JsonResult.error("修改失败");
        }
        return JsonResult.success("修改成功",null);
    }

    @ResponseBody
    @RequestMapping("/toGrant.do")
    public JsonResult<Object> toGrant(@RequestParam("userId") String userId){
        Map<String,Object> resultMap = userService.toGrant(userId);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/grant.do")
    public JsonResult<Object> grant(@RequestParam("userId") String userId,String[] roleIdArr){
        boolean bool = userService.grant(userId,roleIdArr);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }

    @ResponseBody
    @RequestMapping("/getUserMenu.do")
    public JsonResult<Object> getUserMenu(@RequestParam("userId") String userId){
        List<Menu> menuList = userService.getUserMenu(userId);
        return JsonResult.success(menuList);
    }
}
