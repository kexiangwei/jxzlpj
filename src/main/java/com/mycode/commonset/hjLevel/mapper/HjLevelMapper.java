package com.mycode.commonset.hjLevel.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 通用模块-获奖等级设置
 */
@Mapper
public interface HjLevelMapper {

    List<Map<String, Object>> getHjLevelList();

    Map<String, Object> getHjLevelByName(@Param("name") String name);

    boolean insertHjLevel(@Param("code") String code,@Param("name") String name);

    boolean updateHjLevel(@Param("code") String code, @Param("name") String name);

    boolean deleteHjLevel(@Param("code") String code);

    List<Map<String, Object>> getParentMenuList();

    List<Map<String, Object>> getMenuListByPid(@Param("pid") String pid);

    List<Map<String, Object>> getHjLevelSet(@Param("menuId") String menuId);

    boolean addHjLevelSet(@Param("menuId") String menuId, @Param("hjLevelCode") String hjLevelCode);

    boolean delHjLevelSet(@Param("menuId") String menuId, @Param("hjLevelCode") String hjLevelCode);
}
