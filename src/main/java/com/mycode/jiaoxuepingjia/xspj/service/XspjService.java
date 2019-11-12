package com.mycode.jiaoxuepingjia.xspj.service;

import com.mycode.jiaoxuepingjia.xspj.domain.Target;
import com.mycode.jiaoxuepingjia.xspj.domain.Course;
import com.mycode.jiaoxuepingjia.xspj.domain.XspjSet;

import java.util.List;
import java.util.Map;

/**
 * @auther kexiangwei
 * @date 2019/10/8
 */
public interface XspjService {

    Map<String, Object> getXspjSetList(XspjSet xspjSet);

    Map<String, Object> getXspjTemplateList(Integer pageIndex, Integer pageSize);

    List<Target> getXspjTargetList();

    Map<String, Object> getXspjCourseList(Course course);

    Map<String, Object> getXspjTemplate();
}
