package com.mycode.common.common;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommonMapper {

    List<Map<String, Object>> getTableCols(@Param("tableName") String tableName);

    List<Map<String, Object>> getTableDatas(@Param("viewName") String viewName, @Param("userId") String userId);

    List<Map<String, Object>> getCollege();

    List<Map<String, Object>> getMajor(@Param("collegeCode") String collegeCode);

    List<Course> getCourseListByUserId(@Param("userId") String userId, @Param("accountType") String accountType, @Param("courseName") String courseName);

}
