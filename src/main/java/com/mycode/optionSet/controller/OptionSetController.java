package com.mycode.optionSet.controller;

import com.mycode.optionSet.service.OptionSetService;
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

    /**
     * 获取模块下拉选项
     * @param pid 获取二级模块时传递的一级模块编号
     * @return
     */
    @RequestMapping("/getOptionSetMenuList.do")
    public JsonResult<Object> getOptionSetMenuList(@RequestParam(value = "pid",required = false) String pid){
        List<Map<String, Object>> mapList = optionSetService.getOptionSetMenuList(pid);
        return JsonResult.success(mapList);
    }

    /**
     * 获取模块属性
     * @param menuId
     * @return
     */
    @RequestMapping("/getOptionSetAttrList.do")
    public JsonResult<Object> getOptionSetAttrList(@RequestParam(value = "menuId") String menuId){
        List<Map<String, Object>> mapList = optionSetService.getOptionSetAttrList(menuId);
        return JsonResult.success(mapList);
    }

    /**
     * 获取已选选项
     * @param menuId
     * @param attr
     * @return
     */
    @RequestMapping("/getOptionSetList.do")
    public JsonResult<Object> getOptionSetList(@RequestParam("menuId") String menuId, @RequestParam("attr") String attr){
        List<Map<String, Object>> mapList = optionSetService.getOptionSetList(menuId,attr);
        return JsonResult.success(mapList);
    }

    @RequestMapping("/addOptionSet.do")
    public JsonResult<Object> addOptionSet(@RequestParam("menuId") String menuId, @RequestParam("attr") String attr, @RequestParam("optionCode") String optionCode){
        boolean bool = optionSetService.addOptionSet(menuId,attr,optionCode);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }

    @RequestMapping("/delOptionSet.do")
    public JsonResult<Object> delOptionSet(@RequestParam("menuId") String menuId, @RequestParam("attr") String attr, @RequestParam("optionCode") String optionCode){
        boolean bool = optionSetService.delOptionSet(menuId,attr,optionCode);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }

}
