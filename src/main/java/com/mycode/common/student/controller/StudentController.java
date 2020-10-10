package com.mycode.common.student.controller;

import com.mycode.common.student.domian.Student;
import com.mycode.common.student.service.StudentService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 双创教育-学生信息
 */
@CrossOrigin
@RestController
@RequestMapping("/common")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @ResponseBody
    @RequestMapping("/getCollege.do")
    public JsonResult<Object> getCollege(){
        List<Map<String, Object>> maps = studentService.getCollege();
        return JsonResult.success(maps);
    }

    /**
     *
     * @param collegeCode
     * @return
     */
    @ResponseBody
    @RequestMapping("/getMajor.do")
    public JsonResult<Object> getMajor(@RequestParam(value = "collegeCode",required = false) String collegeCode){
        List<Map<String, Object>> maps = studentService.getMajor(collegeCode);
        return JsonResult.success(maps);
    }

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
