package com.mycode.common.student.controller;

import com.mycode.common.student.domian.Student;
import com.mycode.common.student.service.StudentService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 双创教育-学生信息
 */
@CrossOrigin
@RestController
@RequestMapping("/common")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/getStudentInfo.do")
    public JsonResult<Object> getStudentInfo(@RequestParam("relationCode") String relationCode){
        List<Student> studentInfo = studentService.getStudentInfo(relationCode);
        return JsonResult.success(studentInfo);
    }

    @RequestMapping("/addStudentInfo.do")
    public JsonResult<Object> addStudentInfo(Student student){
        boolean bool = studentService.addStudentInfo(student);
        if(!bool){
            return JsonResult.error("新增失败");
        }
        return JsonResult.success("新增成功",null);
    }

    @RequestMapping("/delStudentInfo.do")
    public JsonResult<Object> delStudentInfo(@RequestParam("relationCode") String relationCode, @RequestParam("studentCode") String studentCode){
        boolean bool = studentService.delStudentInfo(relationCode, studentCode);
        if(!bool){
            return JsonResult.error("删除失败");
        }
        return JsonResult.success("删除成功",null);
    }
}
