package com.mycode.jiaoxuepingjia.xspj.controller;

import com.mycode.jiaoxuepingjia.xspj.domain.Course;
import com.mycode.jiaoxuepingjia.xspj.service.XspjService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 教学评价-学生评教
 * @auther kexiangwei
 * @date 2019/10/8
 */
@CrossOrigin
@Controller
public class XspjController {

    @Autowired
    private XspjService xspjService;

    @ResponseBody
    @RequestMapping("/getXspjCourseList.do")
    public JsonResult<Object> getXspjCourseList(Course course){
        Map<String, Object> resultMap = xspjService.getXspjCourseList(course);
        return JsonResult.success(resultMap);
    }

}
