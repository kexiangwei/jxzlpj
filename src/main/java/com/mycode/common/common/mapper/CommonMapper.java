package com.mycode.common.common.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommonMapper {

    Map<String,Integer> getAuthority(@Param("menuId") String menuId, @Param("userId") String userId);

    List<Map<String, Object>> getCollege();

    List<Map<String, Object>> getMajor(@Param("collegeCode") String collegeCode);
}
