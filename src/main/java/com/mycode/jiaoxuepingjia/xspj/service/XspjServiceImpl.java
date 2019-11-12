package com.mycode.jiaoxuepingjia.xspj.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.jiaoxuepingjia.xspj.domain.Target;
import com.mycode.jiaoxuepingjia.xspj.domain.Course;
import com.mycode.jiaoxuepingjia.xspj.domain.XspjSet;
import com.mycode.jiaoxuepingjia.xspj.mapper.XspjMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @auther kexiangwei
 * @date 2019/10/8
 */
@Service
public class XspjServiceImpl implements XspjService {

    @Autowired
    private XspjMapper xspjMapper;

    @Override
    public Map<String, Object> getXspjSetList(XspjSet xspjSet) {
        Map<String, Object> resultMap = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(xspjSet.getPageIndex(), xspjSet.getPageSize());
        List<XspjSet> pageList = xspjMapper.getXspjSetList(xspjSet);
        resultMap.put("totalNum",pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public Map<String, Object> getXspjTemplateList(Integer pageIndex, Integer pageSize) {
        Map<String, Object> resultMap = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(pageIndex, pageSize);
        List<Map<String, Object>> pageList = xspjMapper.getXspjTemplateList();
        resultMap.put("totalNum",pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public List<Target> getXspjTargetList() {
        return xspjMapper.getXspjTargetList();
    }

    @Override
    public Map<String, Object> getXspjCourseList(Course course) {
        Map<String, Object> resultMap = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(course.getPageIndex(), course.getPageSize());
        List<Course> pageList = xspjMapper.getXspjCourseList(course);
        resultMap.put("totalNum",pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public Map<String, Object> getXspjTemplate() {
        Map<String, Object> resultMap = new HashMap<>();
        List<Target> targets = xspjMapper.getXspjTargetListByTemplateCode();
        List<Target> teacherTargets = targets.stream().filter(t -> t.getType().equals("teacher")).collect(Collectors.toList());
        List<Target> myselfTargets = targets.stream().filter(t -> t.getType().equals("myself")).collect(Collectors.toList());
        resultMap.put("teacherTargets",teacherTargets);
        resultMap.put("myselfTargets",myselfTargets);
        return resultMap;
    }
}
