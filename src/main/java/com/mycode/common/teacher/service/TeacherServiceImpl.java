package com.mycode.common.teacher.service;

import com.mycode.common.teacher.domain.Teacher;
import com.mycode.common.teacher.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 双创教育-指导教师信息
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public List<Teacher> getTeacherInfo(String relationCode) {
        return teacherMapper.getTeacherInfo(relationCode);
    }

    @Override
    public boolean addTeacherInfo(Teacher teacherInfo) {
        return teacherMapper.addTeacherInfo(teacherInfo);
    }

    @Override
    public boolean delTeacherInfo(String relationCode, String teacherCode) {
        return teacherMapper.delTeacherInfo(relationCode, teacherCode);
    }

}
