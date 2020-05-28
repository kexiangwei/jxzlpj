package com.mycode.common.hjLevel.controller;

import com.mycode.common.hjLevel.service.HjLevelService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 通用模块-获奖等级设置
 */
@CrossOrigin
@RestController
@RequestMapping("/hjLevel")
public class HjLevelSetController {

    @Autowired
    private HjLevelService hjLevelService;

    @RequestMapping("/getParentMenuList.do")
    public JsonResult<Object> getParentMenuList(){
        List<Map<String, Object>> parentMenuList = hjLevelService.getParentMenuList();
        return JsonResult.success(parentMenuList);
    }

    @RequestMapping("/getMenuListByPid.do")
    public JsonResult<Object> getMenuListByPid(@RequestParam("pid") String pid){
        List<Map<String, Object>> menuListByPid = hjLevelService.getMenuListByPid(pid);
        return JsonResult.success(menuListByPid);
    }

    @RequestMapping("/getHjLevelSet.do")
    public JsonResult<Object> getHjLevelSet(@RequestParam("menuId") String menuId){
        List<Map<String, Object>> hjLevelSet = hjLevelService.getHjLevelSet(menuId);
        return JsonResult.success(hjLevelSet);
    }

    @RequestMapping("/addHjLevelSet.do")
    public JsonResult<Object> addHjLevelSet(@RequestParam("menuId") String menuId,@RequestParam("hjLevelCode") String hjLevelCode){
        boolean bool = hjLevelService.addHjLevelSet(menuId,hjLevelCode);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }

    @RequestMapping("/delHjLevelSet.do")
    public JsonResult<Object> delHjLevelSet(@RequestParam("menuId") String menuId,@RequestParam("hjLevelCode") String hjLevelCode){
        boolean bool = hjLevelService.delHjLevelSet(menuId,hjLevelCode);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }

}
