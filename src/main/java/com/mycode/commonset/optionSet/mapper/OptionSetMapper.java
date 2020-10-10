package com.mycode.commonset.optionSet.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 通用模块-选项设置
 */
@Mapper
public interface OptionSetMapper {

    //OptionSet
    List<Map<String, Object>> getOptionSetMenuList(@Param("pid") String pid);

    List<Map<String, Object>> getOptionSetList(@Param("menuId") String menuId);

    boolean addOptionSet(@Param("menuId") String menuId, @Param("optionCode") String optionCode);

    boolean delOptionSet(@Param("menuId") String menuId, @Param("optionCode") String optionCode);

    //Option
    List<Map<String, Object>> getOptionPageList();

    Map<String, Object> getOption(@Param("name") String name);

    boolean insertOption(@Param("code") String code,@Param("name") String name);

    boolean updateOption(@Param("code") String code, @Param("name") String name);

    boolean deleteOption(@Param("code") String code);
}
