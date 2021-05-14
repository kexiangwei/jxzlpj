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
 * 教学质量评价-通用接口
 */
@CrossOrigin
@Controller
public class CommonController {

    @Autowired
    private CommonService commonService;

    /**
     * 获取学院列表
     * @param dataType 数据类型，可选值【xy,kc,js,xs】,默认获取学院中的学院信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/getXyList.do")
    public JsonResult<Object> getXyList(@RequestParam(value = "dataType",required = false, defaultValue = "xy") String dataType){
        List<Map<String, Object>> mapList = commonService.getXyList(dataType);
        return JsonResult.success(mapList);
    }

    @ResponseBody
    @RequestMapping("/getZyList.do")
    public JsonResult<Object> getZyList(@RequestParam(value = "xyCode",required = false) String xyCode){
        List<Map<String, Object>> maps = commonService.getZyList(xyCode);
        return JsonResult.success(maps);
    }

}
