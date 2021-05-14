package com.mycode.common.shenheSet.controller;

import com.mycode.common.shenheSet.domain.ShenHeSet;
import com.mycode.common.shenheSet.service.ShenHeSetService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@CrossOrigin
@Controller
public class ShenHeSetController {

    @Autowired
    private ShenHeSetService shenHeSetService;

    @ResponseBody
    @RequestMapping("/getShenHeSetPageList.do")
    public JsonResult<Object> getShenHeSetPageList(ShenHeSet shenHeSet){
        Map<String, Object> resultMap = shenHeSetService.getShenHeSetPageList(shenHeSet);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/addShenheSet.do")
    public JsonResult<Object> addShenheSet(ShenHeSet shenHeSet){
        boolean bool = shenHeSetService.addShenheSet(shenHeSet);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }

    @ResponseBody
    @RequestMapping("/updateShenheSetByCode.do")
    public JsonResult<Object> updateShenheSetByCode(ShenHeSet shenHeSet){
        boolean bool = shenHeSetService.updateShenheSetByCode(shenHeSet);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }

    @ResponseBody
    @RequestMapping("/batchDeleteShenHeSet.do")
    public JsonResult<Object> batchDeleteShenHeSet(String[] shenheCodes){
        boolean bool = shenHeSetService.batchDeleteShenHeSet(shenheCodes);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }

}
