package com.mycode.common.common.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommonMapper {

    List<Map<String, Object>> getXyList();

    List<Map<String, Object>> getZyList(@Param("xyCode") String xyCode);

    List<Map<String, Object>> getTableCols(@Param("tableName") String tableName);

    List<Map<String, Object>> getTableDatas(@Param("viewName") String viewName, @Param("userId") String userId);

}
