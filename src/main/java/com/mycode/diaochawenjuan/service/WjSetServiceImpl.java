package com.mycode.diaochawenjuan.service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.diaochawenjuan.domain.WjSet;
import com.mycode.diaochawenjuan.mapper.WjSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WjSetServiceImpl implements WjSetService {

    @Autowired
    private WjSetMapper wjSetMapper;

    @Override
    public Map<String, Object> getWjSetPageList(WjSet wjSet) {
        Map<String, Object> resultMap = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(wjSet.getPageIndex(), wjSet.getPageSize());
        List<WjSet> pageList = wjSetMapper.getWjSetPageList(wjSet);
        resultMap.put("totalNum", pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public List<WjSet> getWjSetInfo(String wjCode) {
        List<WjSet> wjSetList = wjSetMapper.getWjSetQ(wjCode);
        List<Map<String,Object>> wjSetOptList = wjSetMapper.getWjSetOpt();
        wjSetList.forEach(wjSet -> wjSet.setWjSetOptList(wjSetOptList.stream().filter(m -> m.get("Q_CODE").toString().equals(wjSet.getQCode())).collect(Collectors.toList())));
        return wjSetList;
    }

    @Override
    public List<Map<String, Object>> getWjInfo(String wjCode, String userId) {
        return wjSetMapper.getWjInfo(wjCode,userId);
    }

    @Override
    public boolean addWjInfo(WjSet wjSet, String jsonString) {
        Map<String,Object> paramMap = JSON.parseObject(jsonString, Map.class);
        return wjSetMapper.addWjInfo(wjSet,paramMap);
    }
}
