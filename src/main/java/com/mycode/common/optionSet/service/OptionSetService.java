package com.mycode.common.optionSet.service;

import java.util.List;
import java.util.Map;

/**
 * 通用模块-选项设置
 */
public interface OptionSetService {

    //OptionSet
    List<Map<String, Object>> getOptionSetMenuList(String pid);

    List<Map<String, Object>> getOptionSetAttrList(String menuId);

    List<Map<String, Object>> getOptionSetList(String menuId, String attr);

    boolean addOptionSet(String menuId, String attr, String optionCode);

    boolean delOptionSet(String menuId, String attr, String optionCode);

    //Option
    List<Map<String, Object>> getOptionPageList();

    Map<String, Object> getOption(String name);

    boolean insertOption(String name);

    boolean updateOption(String code, String name);

    boolean deleteOption(String code);

}
