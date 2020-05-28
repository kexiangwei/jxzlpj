package com.mycode.common.hjLevel.service;

import java.util.List;
import java.util.Map;

/**
 * 通用模块-获奖等级设置
 */
public interface HjLevelService {

    //HjLevel
    List<Map<String, Object>> getHjLevelList();

    Map<String, Object> getHjLevelByName(String name);

    boolean insertHjLevel(String name);

    boolean updateHjLevel(String code, String name);

    boolean deleteHjLevel(String code);

    //HjLevelSet
    List<Map<String, Object>> getParentMenuList();

    List<Map<String, Object>> getMenuListByPid(String pid);

    List<Map<String, Object>> getHjLevelSet(String menuId);

    boolean addHjLevelSet(String menuId, String hjLevelCode);

    boolean delHjLevelSet(String menuId, String hjLevelCode);
}
