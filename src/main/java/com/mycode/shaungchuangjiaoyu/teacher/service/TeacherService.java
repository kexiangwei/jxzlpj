package com.mycode.shaungchuangjiaoyu.teacher.service;

import com.mycode.shaungchuangjiaoyu.teacher.domain.Teacher;

import java.util.List;

/**
 * 双创教育-指导教师信息
 */
public interface TeacherService {

    List<Teacher> getTeacherInfo(String relationCode);

    boolean addTeacherInfo(Teacher teacherInfo);

    boolean delTeacherInfo(String relationCode, String teacherCode);

}
