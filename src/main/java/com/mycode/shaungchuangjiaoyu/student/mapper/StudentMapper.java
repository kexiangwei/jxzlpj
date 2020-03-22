package com.mycode.shaungchuangjiaoyu.student.mapper;

import com.mycode.shaungchuangjiaoyu.student.domian.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 双创教育-学生信息
 */
@Mapper
public interface StudentMapper {

    List<Student> getStudentInfo(@Param("relationCode") String relationCode);

    boolean addStudentInfo(Student student);

    boolean delStudentInfo(@Param("relationCode") String relationCode, @Param("studentCode") String studentCode);

}
