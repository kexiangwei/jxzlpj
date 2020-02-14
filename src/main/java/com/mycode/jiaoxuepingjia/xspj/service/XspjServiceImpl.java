package com.mycode.jiaoxuepingjia.xspj.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.jiaoxuepingjia.xspj.domain.Course;
import com.mycode.jiaoxuepingjia.xspj.mapper.XspjMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 教学评价-学生评教
 */
@Service
public class XspjServiceImpl implements XspjService {

    @Autowired
    private XspjMapper xspjMapper;

    @Override
    public Map<String, Object> getCourseList(Course course) {
        Map<String, Object> resultMap = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(course.getPageIndex(), course.getPageSize());
        List<Course> pageList = xspjMapper.getCourseList(course);
        resultMap.put("totalNum",pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

}
