package com.mycode.jxzlpj.jiaoxueyanjiu.jiaoxuetuandui.controller;

import com.mycode.jxzlpj.jiaoxueyanjiu.jiaoxuetuandui.domain.JiaoXueTuanDuiSchool;
import com.mycode.jxzlpj.jiaoxueyanjiu.jiaoxuetuandui.service.JiaoXueTuanDuiSchoolService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 教学研究-教学团队-校级团队
 */
@CrossOrigin
@Controller
@RequestMapping("/jxyj_jxtd_school")
public class JiaoXueTuanDuiSchoolController {

    @Autowired
    private JiaoXueTuanDuiSchoolService jiaoXueTuanDuiSchoolService;

    @ResponseBody
    @RequestMapping("/getPageList.do")
    public JsonResult<Object> getPageList(JiaoXueTuanDuiSchool jiaoXueTuanDuiSchool){
        Map<String, Object> resultMap = jiaoXueTuanDuiSchoolService.getPageList(jiaoXueTuanDuiSchool);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/insert.do")
    public JsonResult<Object> insert(JiaoXueTuanDuiSchool jiaoXueTuanDuiSchool){
        boolean bool = jiaoXueTuanDuiSchoolService.insert(jiaoXueTuanDuiSchool);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }

    @ResponseBody
    @RequestMapping("/update.do")
    public JsonResult<Object> update(JiaoXueTuanDuiSchool jiaoXueTuanDuiSchool){
        boolean bool = jiaoXueTuanDuiSchoolService.update(jiaoXueTuanDuiSchool);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }

    @ResponseBody
    @RequestMapping("/delete.do")
    public JsonResult<Object> delete(@RequestParam("code") String code){
        boolean bool = jiaoXueTuanDuiSchoolService.delete(code);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }
}
