package com.mycode.common.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private CommonMapper commonMapper;

    @Override
    public List<Map<String, Object>> getXyList() {
        return commonMapper.getXyList();
    }

    @Override
    public List<Map<String, Object>> getZyList(String xyCode) {
        return commonMapper.getZyList(xyCode);
    }

    @Override
    public List<Map<String, Object>> getTableCols(String tableName) {
        return commonMapper.getTableCols(tableName);
    }

    @Override
    public List<Map<String, Object>> getTableDatas(String viewName, String userId) {
        return commonMapper.getTableDatas(viewName,userId);
    }

}
