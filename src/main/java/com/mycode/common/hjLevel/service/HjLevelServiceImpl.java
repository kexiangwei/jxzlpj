package com.mycode.common.hjLevel.service;


import com.mycode.common.hjLevel.mapper.HjLevelMapper;
import com.mycode.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 通用模块-获奖等级设置
 */
@Service
public class HjLevelServiceImpl implements HjLevelService {

    @Autowired
    private HjLevelMapper hjLevelMapper;

    @Override
    public List<Map<String, Object>> getParentMenuList() {
        return hjLevelMapper.getParentMenuList();
    }

    @Override
    public List<Map<String, Object>> getMenuListByPid(String pid) {
        return hjLevelMapper.getMenuListByPid(pid);
    }

    @Override
    public List<Map<String, Object>> getHjLevelSet(String menuId) {
        return hjLevelMapper.getHjLevelSet(menuId);
    }

    @Override
    public boolean addHjLevelSet(String menuId, String hjLevelCode) {
        return hjLevelMapper.addHjLevelSet(menuId,hjLevelCode);
    }

    @Override
    public boolean delHjLevelSet(String menuId, String hjLevelCode) {
        return hjLevelMapper.delHjLevelSet(menuId,hjLevelCode);
    }

    @Override
    public List<Map<String, Object>> getHjLevelList() {
        return hjLevelMapper.getHjLevelList();
    }

    @Override
    public Map<String, Object> getHjLevelByName(String name) {
        return hjLevelMapper.getHjLevelByName(name);
    }

    @Override
    public boolean insertHjLevel(String name) {
        return hjLevelMapper.insertHjLevel(StringUtils.guid(16,true), name);
    }

    @Override
    public boolean updateHjLevel(String code, String name) {
        return hjLevelMapper.updateHjLevel(code,name);
    }

    @Override
    public boolean deleteHjLevel(String code) {
        return hjLevelMapper.deleteHjLevel(code);
    }
}
