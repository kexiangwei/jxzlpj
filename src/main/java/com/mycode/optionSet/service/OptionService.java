package com.mycode.optionSet.service;

import java.util.List;
import java.util.Map;

/**
 * 通用模块-选项设置
 */
public interface OptionService {

    List<Map<String, Object>> getOptionPageList();

    Map<String, Object> getOption(String name);

    boolean insertOption(String name);

    boolean updateOption(Long code, String name);

    boolean deleteOption(Long code);

}
