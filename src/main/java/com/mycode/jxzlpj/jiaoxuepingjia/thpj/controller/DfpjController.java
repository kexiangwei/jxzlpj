package com.mycode.jxzlpj.jiaoxuepingjia.thpj.controller;

import com.mycode.jxzlpj.jiaoxuepingjia.pjset.service.PjSetTemplateService;
import com.mycode.jxzlpj.jiaoxuepingjia.thpj.domian.Thpj;
import com.mycode.jxzlpj.jiaoxuepingjia.thpj.domian.ThpjQuery;
import com.mycode.jxzlpj.jiaoxuepingjia.thpj.service.DfpjService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 教学评价-同行评教-打分評教
 */
@CrossOrigin
@Controller
@RequestMapping("/jxpj_thpj")
public class DfpjController {

    @Autowired
    private DfpjService dfpjService;

    @ResponseBody
    @RequestMapping("/getPageList.do")
    public JsonResult<Object> getPageList(ThpjQuery thpjQuery){
        Map<String, Object> resultMap = dfpjService.getPageList(thpjQuery);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/detail.do")
    public JsonResult<Object> detail(@RequestParam("code") String pjCode){
        Thpj thpj = dfpjService.detail(pjCode);
        return JsonResult.success(thpj);
    }

    @ResponseBody
    @RequestMapping("/insert.do")
    public JsonResult<Object> insert(Thpj thpj, @RequestParam("jsonString") String jsonString){
        boolean bool = dfpjService.insert(thpj, jsonString);
        if(!bool){
            return JsonResult.error("保存失败！");
        }
        return JsonResult.success("保存成功！",null);
    }

    @ResponseBody
    @RequestMapping("/update.do")
    public JsonResult<Object> update(Thpj thpj, @RequestParam("jsonString") String jsonString){
        boolean bool = dfpjService.update(thpj,jsonString);
        if(!bool){
            return JsonResult.error("修改失败！");
        }
        return JsonResult.success("修改成功！",null);
    }

    /*@ResponseBody
    @RequestMapping("/delete.do")
    public JsonResult<Object> delete(@RequestParam("code") String pjCode){
        boolean bool = thpjService.delete(pjCode);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }*/

}
