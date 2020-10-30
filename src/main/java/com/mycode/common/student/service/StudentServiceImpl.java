package com.mycode.common.student.service;

import com.mycode.common.student.domian.Student;
import com.mycode.common.student.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 双创教育-学生信息
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> getStudentInfo(String relationCode) {
        return studentMapper.getStudentInfo(relationCode);
    }

    @Override
    public boolean addStudentInfo(Student student) {
        return studentMapper.addStudentInfo(student);
    }

    @Override
    public boolean delStudentInfo(String relationCode, String studentCode) {
        return studentMapper.delStudentInfo(relationCode, studentCode);
    }

}
