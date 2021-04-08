package com.mycode.optionSet.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 通用模块-选项设置
 */
@Mapper
public interface OptionMapper {

    List<Map<String, Object>> getOptionPageList();

    Map<String, Object> getOption(@Param("name") String name);

    boolean insertOption(@Param("code") String code, @Param("name") String name);

    boolean updateOption(@Param("code") String code, @Param("name") String name);

    int deleteOption(@Param("code") String code);

}