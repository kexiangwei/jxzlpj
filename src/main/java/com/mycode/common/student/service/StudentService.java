package com.mycode.common.student.service;

import com.mycode.common.student.domian.Student;

import java.util.List;
import java.util.Map;

/**
 * 双创教育-学生信息
 */
public interface StudentService {

    List<Map<String, Object>> getCollege();

    List<Map<String, Object>> getMajor(String collegeCode);

    List<Student> getStudentInfo(String relationCode);

    boolean addStudentInfo(Student student);

    boolean delStudentInfo(String relationCode, String studentCode);

}
