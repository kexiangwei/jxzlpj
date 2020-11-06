package com.mycode.jiaoxueyanjiu.jiaoxuetuandui.controller;

import com.mycode.jiaoxueyanjiu.jiaoxuetuandui.domain.JiaoXueTuanDuiCountry;
import com.mycode.jiaoxueyanjiu.jiaoxuetuandui.service.JiaoXueTuanDuiCountryService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 教学研究-教学团队-国家级团队
 */
@CrossOrigin
@Controller
@RequestMapping("/jxyj_jxtd_country")
public class JiaoXueTuanDuiCountryController {

    @Autowired
    private JiaoXueTuanDuiCountryService jiaoXueTuanDuiCountryService;

    @ResponseBody
    @RequestMapping("/getPageList.do")
    public JsonResult<Object> getPageList(JiaoXueTuanDuiCountry jiaoXueTuanDuiCountry){
        Map<String, Object> resultMap = jiaoXueTuanDuiCountryService.getPageList(jiaoXueTuanDuiCountry);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/insert.do")
    public JsonResult<Object> insert(JiaoXueTuanDuiCountry jiaoXueTuanDuiCountry){
        boolean bool = jiaoXueTuanDuiCountryService.insert(jiaoXueTuanDuiCountry);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }

    @ResponseBody
    @RequestMapping("/update.do")
    public JsonResult<Object> update(JiaoXueTuanDuiCountry jiaoXueTuanDuiCountry){
        boolean bool = jiaoXueTuanDuiCountryService.update(jiaoXueTuanDuiCountry);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }

    @ResponseBody
    @RequestMapping("/delete.do")
    public JsonResult<Object> delete(@RequestParam("code") String code){
        boolean bool = jiaoXueTuanDuiCountryService.delete(code);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }
}
