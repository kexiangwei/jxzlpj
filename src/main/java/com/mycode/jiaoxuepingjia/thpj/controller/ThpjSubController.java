package com.mycode.jiaoxuepingjia.thpj.controller;

import com.mycode.jiaoxuepingjia.thpj.service.ThpjService;
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
 * 教学评价-同行评教
 */
@CrossOrigin
@Controller
@RequestMapping("/thpj")
public class ThpjSubController {

    @Autowired
    private ThpjService thpjService;

    @ResponseBody
    @RequestMapping("/getThpjTargetList.do")
    public JsonResult<Object> getThpjTargetList(){
        List<Map<String, Object>> thpjTargetList = thpjService.getThpjTargetList();
        return JsonResult.success(thpjTargetList);
    }

    @ResponseBody
    @RequestMapping("/getTeacherBar.do")
    public JsonResult<Object> getTeacherBar(@RequestParam("menuName") String menuName, @RequestParam("userId") String userId){
        List<Map<String, Object>> teacherBar = thpjService.getTeacherBar(menuName, userId);
        return JsonResult.success(teacherBar);
    }

    @ResponseBody
    @RequestMapping("/getTeacherPie.do")
    public JsonResult<Object> getTeacherPie(@RequestParam("menuName") String menuName, @RequestParam("userId") String userId){
        List<Map<String, Object>> teacherPie = thpjService.getTeacherPie(menuName, userId);
        return JsonResult.success(teacherPie);
    }

    @ResponseBody
    @RequestMapping("/getTeacherTab.do")
    public JsonResult<Object> getTeacherTab(@RequestParam("menuName") String menuName, @RequestParam("userId") String userId){
        List<Map<String, Object>> teacherTab = thpjService.getTeacherTab(menuName, userId);
        return JsonResult.success(teacherTab);
    }

    @ResponseBody
    @RequestMapping("/getTeacherTabData.do")
    public JsonResult<Object> getTeacherTabData(@RequestParam("menuName") String menuName, @RequestParam("userId") String userId){
        List<Map<String, Object>> teacherTab = thpjService.getTeacherTabData(menuName, userId);
        return JsonResult.success(teacherTab);
    }

}
