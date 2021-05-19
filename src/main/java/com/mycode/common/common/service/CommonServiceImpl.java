package com.mycode.common.common.service;

import com.mycode.common.common.mapper.CommonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private CommonMapper commonMapper;

    @Override
    public List<Map<String, Object>> getXyList(String dataType) {
        return commonMapper.getXyList(dataType);
    }

    @Override
    public List<Map<String, Object>> getZyList(String dataType, String xyCode) {
        return commonMapper.getZyList(dataType,xyCode);
    }

    @Override
    public Set<String> getCourseAttrOptions() {
        return commonMapper.getCourseAttrOptions();
    }

    @Override
    public Set<String> getUserTitleOptions() {
        return commonMapper.getUserTitleOptions();
    }

}
