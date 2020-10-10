package com.mycode.commonset.optionSet.controller;

import com.mycode.commonset.optionSet.service.OptionSetService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 通用模块-选项设置
 */
@CrossOrigin
@RestController
@RequestMapping("/optionset")
public class OptionSetController {

    @Autowired
    private OptionSetService optionSetService;

    @RequestMapping("/getOptionSetMenuList.do")
    public JsonResult<Object> getOptionSetMenuList(@RequestParam(value = "pid",required = false) String pid){
        List<Map<String, Object>> mapList = optionSetService.getOptionSetMenuList(pid);
        return JsonResult.success(mapList);
    }

    @RequestMapping("/getOptionSetList.do")
    public JsonResult<Object> getOptionSetList(@RequestParam("menuId") String menuId){
        List<Map<String, Object>> mapList = optionSetService.getOptionSetList(menuId);
        return JsonResult.success(mapList);
    }

    @RequestMapping("/addOptionSet.do")
    public JsonResult<Object> addOptionSet(@RequestParam("menuId") String menuId,@RequestParam("optionCode") String optionCode){
        boolean bool = optionSetService.addOptionSet(menuId,optionCode);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }

    @RequestMapping("/delOptionSet.do")
    public JsonResult<Object> delOptionSet(@RequestParam("menuId") String menuId,@RequestParam("optionCode") String optionCode){
        boolean bool = optionSetService.delOptionSet(menuId,optionCode);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }

}
