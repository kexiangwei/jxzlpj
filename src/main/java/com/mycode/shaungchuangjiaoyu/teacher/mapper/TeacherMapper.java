package com.mycode.shaungchuangjiaoyu.teacher.mapper;

import com.mycode.shaungchuangjiaoyu.teacher.domain.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 双创教育-指导教师信息
 */
@Mapper
public interface TeacherMapper {

    List<Teacher> getTeacherInfo(@Param("relationCode") String relationCode);

    boolean addTeacherInfo(Teacher teacherInfo);

    boolean delTeacherInfo(@Param("relationCode") String relationCode, @Param("teacherCode") String teacherCode);

}
