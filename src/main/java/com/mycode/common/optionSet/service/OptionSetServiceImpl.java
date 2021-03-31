package com.mycode.common.optionSet.service;


import com.mycode.common.optionSet.mapper.OptionSetMapper;
import com.mycode.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 通用模块-选项设置
 */
@Service
public class OptionSetServiceImpl implements OptionSetService {

    @Autowired
    private OptionSetMapper optionSetMapper;

    @Override
    public List<Map<String, Object>> getOptionSetMenuList(String pid) {
        return optionSetMapper.getOptionSetMenuList(pid);
    }

    @Override
    public List<Map<String, Object>> getOptionSetAttrList(String menuId) {
        return optionSetMapper.getOptionSetAttrList(menuId);
    }

    @Override
    public List<Map<String, Object>> getOptionSetList(String menuId, String attr) {
        return optionSetMapper.getOptionSetList(menuId, attr);
    }

    @Override
    public boolean addOptionSet(String menuId, String attr, String optionCode) {
        return optionSetMapper.addOptionSet(menuId,attr,optionCode);
    }

    @Override
    public boolean delOptionSet(String menuId, String attr, String optionCode) {
        return optionSetMapper.delOptionSet(menuId,attr,optionCode);
    }

    @Override
    public List<Map<String, Object>> getOptionPageList() {
        return optionSetMapper.getOptionPageList();
    }

    @Override
    public Map<String, Object> getOption(String name) {
        return optionSetMapper.getOption(name);
    }

    @Override
    public boolean insertOption(String name) {
        return optionSetMapper.insertOption(StringUtils.uuid(), name);
    }

    @Override
    public boolean updateOption(String code, String name) {
        return optionSetMapper.updateOption(code,name);
    }

    @Override
    public boolean deleteOption(String code) {
        int execNum = optionSetMapper.deleteOption(code); //mybatis一次对多条数据进行操作成功后返回值为 -1
        return execNum < 0;
    }
}