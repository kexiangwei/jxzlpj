package com.mycode.common.common.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Mapper
public interface CommonMapper {

    List<Map<String, Object>> getXyList(@Param("dataType") String dataType);

    List<Map<String, Object>> getZyList(@Param("dataType") String dataType, @Param("xyCode") String xyCode);

    Set<String> getCourseAttrOptions();

    Set<String> getUserTitleOptions();
}
