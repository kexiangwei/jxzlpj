package com.mycode.common.shenheSet.controller;

import com.mycode.common.shenheSet.domain.ShenHeSet;
import com.mycode.common.shenheSet.service.ShenHeService;
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
    private ShenHeService shenHeService;

    @ResponseBody
    @RequestMapping("/getShenHeSetPageList.do")
    public JsonResult<Object> getShenHeSetPageList(ShenHeSet shenHeSet){
        Map<String, Object> resultMap = shenHeService.getShenHeSetPageList(shenHeSet);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/addShenheSet.do")
    public JsonResult<Object> addShenheSet(ShenHeSet shenHeSet){
        boolean bool = shenHeService.addShenheSet(shenHeSet);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }

    @ResponseBody
    @RequestMapping("/updateShenheSetByCode.do")
    public JsonResult<Object> updateShenheSetByCode(ShenHeSet shenHeSet){
        boolean bool = shenHeService.updateShenheSetByCode(shenHeSet);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }

    @ResponseBody
    @RequestMapping("/batchDeleteShenHeSet.do")
    public JsonResult<Object> batchDeleteShenHeSet(String[] codeArr){
        boolean bool = shenHeService.batchDeleteShenHeSet(codeArr);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }

}
