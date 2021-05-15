package com.mycode.jxzlpj.jiaoxuexiaoguo.kczlfxbg.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.jxzlpj.jiaoxuexiaoguo.kczlfxbg.doman.Kczlfxbg;
import com.mycode.jxzlpj.jiaoxuexiaoguo.kczlfxbg.mapper.KczlfxbgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KczlfxbgServiceImpl implements KczlfxbgService {

    @Autowired
    private KczlfxbgMapper kczlfxbgMapper;

    @Override
    public Map<String, Object> getPageList(Kczlfxbg kczlfxbg) {
        Page<Object> pageInfo = PageHelper.startPage(kczlfxbg.getPageIndex(), kczlfxbg.getPageSize());
        List<Kczlfxbg> pageList = kczlfxbgMapper.getPageList(kczlfxbg);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("totalNum", pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public Kczlfxbg getKczlfxbg(String code) {
        return kczlfxbgMapper.getKczlfxbg(code);
    }

    @Override
    public boolean insert(Kczlfxbg bg) {
        bg.setCode(bg.getXn() + ("3".equals(bg.getXq())?"03-":"12-") + bg.getCourseCode() + "-" + bg.getUserId());
        return kczlfxbgMapper.insert(bg);
    }

    @Override
    public boolean update(Kczlfxbg kczlfxbg) {
        return kczlfxbgMapper.update(kczlfxbg);
    }

    @Override
    public boolean delete(String code) {
        return kczlfxbgMapper.delete(code);
    }

}
