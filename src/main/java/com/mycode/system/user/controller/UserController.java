package com.mycode.system.user.controller;

import java.util.List;
import java.util.Map;

import com.mycode.common.Course;
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

    /**
     * 获取用户分页列表
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping("/getUserPageList.do")
    public JsonResult<Object> getUserPageList(User user){
        Map<String, Object> resultMap = userService.getUserPageList(user);
        return JsonResult.success(resultMap);
    }

    /**
     * 根据用户编号获取用户信息
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("/getUserById.do")
    public JsonResult<Object> getUserById(@RequestParam("userId") String userId){
        User user = userService.getUserById(userId);
        return JsonResult.success(user);
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateUser.do")
    public JsonResult<Object> updateUser(User user){
        boolean bool = userService.updateUser(user);
        if(!bool){
            return JsonResult.error("修改失败！");
        }
        return JsonResult.success("修改成功！",null);
    }

    /**
     * 获取授权数据
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("/toGrant.do")
    public JsonResult<Object> toGrant(@RequestParam("userId") String userId){
        Map<String,Object> resultMap = userService.toGrant(userId);
        return JsonResult.success(resultMap);
    }

    /**
     * 授权
     * @param userId
     * @param roleIds
     * @return
     */
    @ResponseBody
    @RequestMapping("/grant.do")
    public JsonResult<Object> grant(@RequestParam("userId") String userId, String[] roleIds){
        boolean bool = userService.grant(userId,roleIds);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }

    /**
     * 获取用户拥有的菜单项
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("/getUserMenu.do")
    public JsonResult<Object> getUserMenu(@RequestParam("userId") String userId){
        List<Menu> menuList = userService.getUserMenu(userId);
        return JsonResult.success(menuList);
    }

    /**
     * 查询用户是否拥有指定菜单的提交、审核权限
     * @param userId
     * @param menuId
     * @return
     */
    @ResponseBody
    @RequestMapping("/getUserAuth.do")
    public JsonResult<Object> getAuthority(@RequestParam("userId") String userId, @RequestParam("menuId") String menuId){
        Map<String, Integer> resultMap = userService.getAuthority(userId,menuId);
        return JsonResult.success(resultMap);
    }

    /**
     * 获取用户关联的课程信息
     * @param userId
     * @param accountType
     * @param courseName
     * @return
     */
    @ResponseBody
    @RequestMapping("/getCourseListByUserId.do")
    public JsonResult<Object> getCourseListByUserId(@RequestParam("userId") String userId
            ,@RequestParam(value = "accountType", required = false, defaultValue = "teacher") String accountType
            ,@RequestParam(value = "courseName", required = false) String courseName){
        List<Course> courseList = userService.getCourseListByUserId(userId, accountType, courseName);
        return JsonResult.success(courseList);
    }
}
