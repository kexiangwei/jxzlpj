package com.mycode.common.common.service;

import com.mycode.common.common.domain.Course;
import com.mycode.common.common.mapper.CommonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private CommonMapper commonMapper;

    @Override
    public List<Map<String, Object>> getCollege() {
        return commonMapper.getCollege();
    }

    @Override
    public List<Map<String, Object>> getMajor(String collegeCode) {
        return commonMapper.getMajor(collegeCode);
    }

    @Override
    public List<Course> getCourseListByUserId(String userId) {
        return commonMapper.getCourseListByUserId(userId);
    }

}
