package com.mycode.system.user.controller;

import com.mycode.system.menu.domain.Menu;
import com.mycode.system.user.domain.User;
import com.mycode.system.user.service.UserService;
import com.mycode.util.JsonResult;
import com.mycode.util.RedisUtil;
import com.wf.captcha.Captcha;
import com.wf.captcha.SpecCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @auther kexiangwei
 * @date 2019/6/24
 */
@CrossOrigin
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/getUserList.do")
    public JsonResult<Object> getUserList(User user){
        Map<String, Object> map = userService.getUserList(user);
        return JsonResult.success(map);
    }
    @ResponseBody
    @RequestMapping("/getUserDetail.do")
    public JsonResult<Object> getUserDetail(@RequestParam("userId") String userId){
        Map<String, Object> resultMap = userService.getUserDetail(userId);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/updateUser.do")
    public JsonResult<Object> updateUser(User user){
        boolean bool = userService.updateUser(user);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
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
