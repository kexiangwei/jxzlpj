package com.mycode.common.teacher.controller;

import com.mycode.common.teacher.domain.Teacher;
import com.mycode.common.teacher.service.TeacherService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 双创教育-指导教师信息
 */
@CrossOrigin
@Controller
@RequestMapping("/scjx")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @ResponseBody
    @RequestMapping("/getTeacherInfo.do")
    public JsonResult<Object> getTeacherInfo(@RequestParam("relationCode") String relationCode){
        List<Teacher> teacherInfo = teacherService.getTeacherInfo(relationCode);
        return JsonResult.success(teacherInfo);
    }

    @ResponseBody
    @RequestMapping("/addTeacherInfo.do")
    public JsonResult<Object> addTeacherInfo(Teacher teacherInfo){
        boolean bool = teacherService.addTeacherInfo(teacherInfo);
        if(!bool){
            return JsonResult.error("新增失败");
        }
        return JsonResult.success("新增成功",null);
    }

    @ResponseBody
    @RequestMapping("/delTeacherInfo.do")
    public JsonResult<Object> delTeacherInfo(@RequestParam("relationCode") String relationCode, @RequestParam("teacherCode") String teacherCode){
        boolean bool = teacherService.delTeacherInfo(relationCode, teacherCode);
        if(!bool){
            return JsonResult.error("删除失败");
        }
        return JsonResult.success("删除成功",null);
    }

}
