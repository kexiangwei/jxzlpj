package com.mycode.common.common.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @auther kexiangwei
 * @date 2019/7/13
 */
@Mapper
public interface CommonMapper {

    Map<String,Integer> getAuthority(@Param("menuId") String menuId, @Param("userId") String userId);

    List<Map<String, Object>> getCollege(@Param("stuCode") String stuCode);

    List<Map<String, Object>> getMajor(@Param("collegeCode") String collegeCode);
}
