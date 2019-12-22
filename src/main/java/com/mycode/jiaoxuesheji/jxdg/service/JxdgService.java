package com.mycode.jiaoxuesheji.jxdg.service;

import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.jiaoxuesheji.jxdg.domain.Course;

import java.util.List;
import java.util.Map;

/**
 * 教学设计-教学大纲
 * @auther kexiangwei
 * @date 2019/10/8
 */
public interface JxdgService {

    Map<String, Object> getCourseList(Course course);

    boolean toSubimt(String activeShenheCode, List<Course> courseList);

    boolean toShenhe(ShenHeItem item, List<Course> courseList);
}
