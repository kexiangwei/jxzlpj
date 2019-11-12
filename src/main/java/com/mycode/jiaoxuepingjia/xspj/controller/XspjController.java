package com.mycode.jiaoxuepingjia.xspj.controller;

import com.mycode.jiaoxuepingjia.xspj.domain.Target;
import com.mycode.jiaoxuepingjia.xspj.domain.Course;
import com.mycode.jiaoxuepingjia.xspj.domain.XspjSet;
import com.mycode.jiaoxuepingjia.xspj.service.XspjService;
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
 * 学生评教
 * @auther kexiangwei
 * @date 2019/10/8
 */
@CrossOrigin
@Controller
public class XspjController {

    @Autowired
    private XspjService xspjService;

    @ResponseBody
    @RequestMapping("/getXspjSetList.do")
    public JsonResult<Object> getXspjSetList(XspjSet xspjSet){
        Map<String, Object> resultMap = xspjService.getXspjSetList(xspjSet);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/getXspjTemplateList.do")
    public JsonResult<Object> getXspjTemplateList(@RequestParam("pageIndex") Integer pageIndex,@RequestParam("pageSize") Integer pageSize){
        Map<String, Object> resultMap = xspjService.getXspjTemplateList(pageIndex,pageSize);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/getXspjTargetList.do")
    public JsonResult<Object> getXspjTargetList(){
        List<Target> targets = xspjService.getXspjTargetList();
        return JsonResult.success(targets);
    }

    @ResponseBody
    @RequestMapping("/getXspjCourseList.do")
    public JsonResult<Object> getXspjCourseList(Course course){
        Map<String, Object> resultMap = xspjService.getXspjCourseList(course);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/getXspjTemplate.do")
    public JsonResult<Object> getXspjTemplate(){
        Map<String, Object> resultMap = xspjService.getXspjTemplate();
        return JsonResult.success(resultMap);
    }
}
