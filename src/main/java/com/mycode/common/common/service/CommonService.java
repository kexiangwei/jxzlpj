package com.mycode.common.common.service;

import com.mycode.common.common.domain.Course;

import java.util.List;
import java.util.Map;

public interface CommonService {

    List<Map<String, Object>> getCollege();

    List<Map<String, Object>> getMajor(String collegeCode);

    List<Course> getCourseListByUserId(String userId);

}
