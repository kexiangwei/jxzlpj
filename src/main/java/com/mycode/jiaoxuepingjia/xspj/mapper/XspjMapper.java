package com.mycode.jiaoxuepingjia.xspj.mapper;

import com.mycode.jiaoxuepingjia.xspj.domain.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 教学评价-学生评教
 * @auther kexiangwei
 * @date 2019/10/8
 */
@Mapper
public interface XspjMapper {

    List<Course> getXspjCourseList(Course course);
}
