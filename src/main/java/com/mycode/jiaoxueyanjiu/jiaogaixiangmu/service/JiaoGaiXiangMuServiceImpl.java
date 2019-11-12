package com.mycode.jiaoxueyanjiu.jiaogaixiangmu.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.jiaoxueyanjiu.jiaogaixiangmu.domain.JiaoGaiXiangMu;
import com.mycode.jiaoxueyanjiu.jiaogaixiangmu.mapper.JiaoGaiXiangMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther kexiangwei
 * @date 2019/8/19
 */
@Service
public class JiaoGaiXiangMuServiceImpl implements JiaoGaiXiangMuService {

    @Autowired
    private JiaoGaiXiangMapper jiaoGaiXiangMapper;

    @Override
    public Map<String, Object> getList(JiaoGaiXiangMu jiaoGaiXiangMu) {
        Map<String, Object> map = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(jiaoGaiXiangMu.getPageIndex(), jiaoGaiXiangMu.getPageSize());
        List<JiaoGaiXiangMu> pageList = jiaoGaiXiangMapper.getList(jiaoGaiXiangMu);
        map.put("totalNum",pageInfo.getTotal());
        map.put("pageList", pageList);
        return map;
    }

    @Override
    public boolean insert(JiaoGaiXiangMu jiaoGaiXiangMu) {
        return jiaoGaiXiangMapper.insert(jiaoGaiXiangMu);
    }
}
