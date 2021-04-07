package com.mycode.shenheSet.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.shenheSet.domain.ShenHeSet;
import com.mycode.shenheSet.mapper.ShenHeSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShenHeSetServiceImpl implements ShenHeSetService {

    @Autowired
    private ShenHeSetMapper shenHeSetMapper;

    @Override
    public Map<String, Object> getShenHeSetPageList(ShenHeSet shenHeSet) {
        Map<String, Object> resultMap = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(shenHeSet.getPageIndex(), shenHeSet.getPageSize());
        List<ShenHeSet> pageList = shenHeSetMapper.getShenHeSetPageList(shenHeSet);
        resultMap.put("totalNum", pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public boolean addShenheSet(ShenHeSet shenHeSet) {
        List<ShenHeSet> shenHeSetPageList = shenHeSetMapper.getShenHeSetPageList(shenHeSet);
        if(shenHeSetPageList != null && shenHeSetPageList.size() > 0){ //如果该业务模块已经设置了审核流程
            shenHeSetMapper.updateShenheSetStatusByMenuId(shenHeSet.getMenuId()); //则把之前的审核流程状态设置为“已禁用”
        }
        return shenHeSetMapper.addShenheSet(shenHeSet);
    }

    @Override
    public boolean updateShenheSetByCode(ShenHeSet shenHeSet) {
        return shenHeSetMapper.updateShenheSetByCode(shenHeSet);
    }

    @Override
    public boolean batchDeleteShenHeSet(String[] shenheCodes) {
        int execNum = shenHeSetMapper.batchDeleteShenHeSet(shenheCodes);
        return execNum == -1; //mybatis一次对多条数据进行操作,成功后返回值为 -1
    }
}
