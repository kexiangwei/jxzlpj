package com.mycode.commonset.hjLevel.controller;

import com.mycode.commonset.hjLevel.service.HjLevelService;
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
public class HjLevelController {

    @Autowired
    private HjLevelService hjLevelService;

    @RequestMapping("/getHjLevelList.do")
    public JsonResult<Object> getHjLevelList(){
        List<Map<String, Object>> hjLevelList = hjLevelService.getHjLevelList();
        return JsonResult.success(hjLevelList);
    }

    @RequestMapping("/insertHjLevel.do")
    public JsonResult<Object> insertHjLevel(@RequestParam("name") String name){
        Map<String, Object> hjLevelByName = hjLevelService.getHjLevelByName(name);
        if(hjLevelByName !=null){
            return JsonResult.error("选项已存在！");
        }
        boolean bool = hjLevelService.insertHjLevel(name);
        if(!bool){
            return JsonResult.error("新增失败");
        }
        return JsonResult.success("新增成功",null);
    }

    @RequestMapping("/updateHjLevel.do")
    public JsonResult<Object> updateHjLevel(@RequestParam("code") String code, @RequestParam("name") String name){
        Map<String, Object> hjLevelByName = hjLevelService.getHjLevelByName(name);
        if(hjLevelByName !=null){
            return JsonResult.error("选项已存在！");
        }
        boolean bool = hjLevelService.updateHjLevel(code,name);
        if(!bool){
            return JsonResult.error("更新失败");
        }
        return JsonResult.success("更新成功",null);
    }

    @RequestMapping("/deleteHjLevel.do")
    public JsonResult<Object> deleteHjLevel(@RequestParam("code") String code){
        boolean bool = hjLevelService.deleteHjLevel(code);
        if(!bool){
            return JsonResult.error("删除失败");
        }
        return JsonResult.success("删除成功",null);
    }

}
