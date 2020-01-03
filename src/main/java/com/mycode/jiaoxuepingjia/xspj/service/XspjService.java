package com.mycode.jiaoxuepingjia.xspj.service;

import com.mycode.jiaoxuepingjia.xspj.domain.Course;

import java.util.List;
import java.util.Map;

/**
 * 教学评价-学生评教
 * @auther kexiangwei
 * @date 2019/10/8
 */
public interface XspjService {

    Map<String, Object> getXspjCourseList(Course course);

}
