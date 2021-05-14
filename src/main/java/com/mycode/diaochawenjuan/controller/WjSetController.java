package com.mycode.diaochawenjuan.controller;

import com.mycode.diaochawenjuan.domain.WjSet;
import com.mycode.diaochawenjuan.service.WjSetService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
public class WjSetController {

    @Autowired
    private WjSetService wjSetService;

    @ResponseBody
    @RequestMapping("/getWjSetPageList.do")
    public JsonResult<Object> getWjSetPageList(WjSet wjSet){
        Map<String,Object> resultMap = wjSetService.getWjSetPageList(wjSet);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/getWjSetInfo.do")
    public JsonResult<Object> getWjSetInfo(@RequestParam("wjCode") String wjCode){
        List<WjSet> wjSetList = wjSetService.getWjSetInfo(wjCode);
        return JsonResult.success(wjSetList);
    }

    @ResponseBody
    @RequestMapping("/getWjInfo.do")
    public JsonResult<Object> getWjInfo(@RequestParam("wjCode") String wjCode, @RequestParam("userId") String userId){
        List<Map<String,Object>> wjInfo = wjSetService.getWjInfo(wjCode,userId);
        return JsonResult.success(wjInfo);
    }

    @ResponseBody
    @RequestMapping("/addWjInfo.do")
    public JsonResult<Object> addWjInfo(WjSet wjSet/*, @RequestParam("qList") List<Map<String,Object>> qList*/, @RequestParam("jsonString") String jsonString){
//        qList.forEach(System.out::println);
        boolean bool = wjSetService.addWjInfo(wjSet,jsonString);
        if(!bool){
            return JsonResult.error("提交失败");
        }
        return JsonResult.success("提交成功",null);
    }
}
