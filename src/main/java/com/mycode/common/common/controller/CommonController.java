package com.mycode.common.common.controller;

import com.mycode.common.common.service.CommonService;
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
 * 通用模块
 * @auther kexiangwei
 * @date 2019/7/13
 */
@CrossOrigin
@Controller
public class CommonController {

    @Autowired
    private CommonService commonService;

    /**
     * 根据menuId,userId 查询用户是否拥有菜单的提交、审核权限
     * @param menuId
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("/getAuthority.do")
    public JsonResult<Object> getAuthority(@RequestParam("menuId") String menuId,@RequestParam("userId") String userId){
        Map<String, Integer> resultMap = commonService.getAuthority(menuId,userId);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/getCollege.do")
    public JsonResult<Object> getCollege(@RequestParam(value = "stuCode",required = false) String stuCode){
        List<Map<String, Object>> maps = commonService.getCollege(stuCode);
        return JsonResult.success(maps);
    }

    @ResponseBody
    @RequestMapping("/getMajor.do")
    public JsonResult<Object> getMajor(@RequestParam(value = "collegeCode",required = false) String collegeCode){
        List<Map<String, Object>> maps = commonService.getMajor(collegeCode);
        return JsonResult.success(maps);
    }
}
