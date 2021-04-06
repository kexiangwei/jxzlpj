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
    public List<Map<String, Object>> getTableCols(String tableName) {
        return commonMapper.getTableCols(tableName);
    }

    @Override
    public List<Map<String, Object>> getTableDatas(String viewName, String userId) {
        return commonMapper.getTableDatas(viewName,userId);
    }

    @Override
    public List<Map<String, Object>> getCollege() {
        return commonMapper.getCollege();
    }

    @Override
    public List<Map<String, Object>> getMajor(String collegeCode) {
        return commonMapper.getMajor(collegeCode);
    }

    @Override
    public List<Course> getCourseListByUserId(String userId, String accountType, String courseName) {
        return commonMapper.getCourseListByUserId(userId, accountType, courseName);
    }

}
