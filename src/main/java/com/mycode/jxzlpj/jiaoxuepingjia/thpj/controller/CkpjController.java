package com.mycode.jxzlpj.jiaoxuepingjia.thpj.controller;

import com.mycode.jxzlpj.jiaoxuepingjia.thpj.domian.Ckpj;
import com.mycode.jxzlpj.jiaoxuepingjia.thpj.service.ThpjService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 教学评价-同行评教-查看评教模块
 */
@CrossOrigin
@Controller
@RequestMapping("/jxpj_thpj")
public class CkpjController {

    @Autowired
    private ThpjService thpjService;

    @ResponseBody
    @RequestMapping("/getCkpjPageList.do")
    public JsonResult<Object> getCkpjPageList(Ckpj ckpj){
        Map<String, Object> resultMap = thpjService.getCkpjPageList(ckpj);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/getCkpjDetail.do")
    public JsonResult<Object> getCkpjDetail(Ckpj ckpj){
        List<Map<String, Object>> mapList = thpjService.getCkpjDetail(ckpj);
        return JsonResult.success(mapList);
    }

}
