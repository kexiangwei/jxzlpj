package com.mycode.jiaoxuepingjia.thpj.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.jiaoxuepingjia.thpj.domian.Thpj;
import com.mycode.jiaoxuepingjia.thpj.mapper.ThpjMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教学评价-同行评教
 * @auther kexiangwei
 * @date 2019/11/13
 */
@Service
public class ThpjServiceImpl implements ThpjService {

    @Autowired
    private ThpjMapper thpjMapper;

    @Override
    public Map<String, Object> getPageList(Thpj thpj) {
        Map<String, Object> resultMap = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(thpj.getPageIndex(), thpj.getPageSize());
        List<Thpj> pageList = thpjMapper.getPageList(thpj);
        resultMap.put("totalNum",pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public boolean insert(Thpj thpj) {
        return thpjMapper.insert(thpj);
    }

    @Override
    public boolean update(Thpj thpj) {
        return thpjMapper.update(thpj);
    }

    @Override
    public boolean delete(String code) {
        return thpjMapper.delete(code);
    }

}
