package com.mycode.jxzlpj.jiaoxuepingjia.thpj.controller;

import com.mycode.jxzlpj.jiaoxuepingjia.thpj.domian.Ckpj;
import com.mycode.jxzlpj.jiaoxuepingjia.thpj.service.CkpjService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 教学评价-同行评教-查看评教模块
 */
@CrossOrigin
@Controller
@RequestMapping("/thpj")
public class CkpjController {

    @Autowired
    private CkpjService ckpjService;

    @ResponseBody
    @RequestMapping("/getCkpjPageList.do")
    public JsonResult<Object> getCkpjPageList(Ckpj ckpj){
        Map<String, Object> resultMap = ckpjService.getCkpjPageList(ckpj);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/getPjInfo.do")
    public JsonResult<Object> getPjInfo(@RequestParam("courseCode") String courseCode
            , @RequestParam("skjsCode") String skjsCode){
        Map<String, Object> resultMap = ckpjService.getPjInfo(courseCode,skjsCode);
        return JsonResult.success(resultMap);
    }
}
