package com.mycode.common.common.service;


import com.mycode.common.common.mapper.CommonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @auther kexiangwei
 * @date 2019/7/13
 */
@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private CommonMapper commonMapper;

    @Override
    public Map<String, Integer> getAuthority(String menuId, String userId) {
        return commonMapper.getAuthority(menuId,userId);
    }
}
