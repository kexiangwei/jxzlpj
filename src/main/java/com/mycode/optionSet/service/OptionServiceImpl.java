package com.mycode.optionSet.service;


import com.mycode.optionSet.mapper.OptionMapper;
import com.mycode.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 通用模块-选项设置
 */
@Service
public class OptionServiceImpl implements OptionService {

    @Autowired
    private OptionMapper optionMapper;

    @Override
    public List<Map<String, Object>> getOptionPageList() {
        return optionMapper.getOptionPageList();
    }

    @Override
    public Map<String, Object> getOption(String name) {
        return optionMapper.getOption(name);
    }

    @Override
    public boolean insertOption(String name) {
        return optionMapper.insertOption(System.currentTimeMillis(), name);
    }

    @Override
    public boolean updateOption(Long code, String name) {
        return optionMapper.updateOption(code,name);
    }

    @Override
    public boolean deleteOption(Long code) {
        return optionMapper.deleteOption(code);
    }
}
