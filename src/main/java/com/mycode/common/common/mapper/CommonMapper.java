package com.mycode.common.common.mapper;

import com.mycode.common.common.domain.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommonMapper {

    List<Map<String, Object>> getCollege();

    List<Map<String, Object>> getMajor(@Param("collegeCode") String collegeCode);

    List<Course> getCourseListByUserId(@Param("userId") String userId, @Param("courseName") String courseName);

}
