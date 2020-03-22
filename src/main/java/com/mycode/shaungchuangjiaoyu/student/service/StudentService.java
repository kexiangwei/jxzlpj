package com.mycode.shaungchuangjiaoyu.student.service;

import com.mycode.shaungchuangjiaoyu.student.domian.Student;

import java.util.List;

/**
 * 双创教育-学生信息
 */
public interface StudentService {

    List<Student> getStudentInfo(String relationCode);

    boolean addStudentInfo(Student student);

    boolean delStudentInfo(String relationCode, String studentCode);

}
