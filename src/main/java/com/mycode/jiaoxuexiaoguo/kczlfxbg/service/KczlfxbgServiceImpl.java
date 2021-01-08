package com.mycode.jiaoxuexiaoguo.kczlfxbg.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.common.Course;
import com.mycode.jiaoxuexiaoguo.kczlfxbg.doman.Kczlfxbg;
import com.mycode.jiaoxuexiaoguo.kczlfxbg.mapper.KczlfxbgMapper;
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
        List<Course> pageList = kczlfxbgMapper.getPageList(kczlfxbg);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("totalNum", pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public boolean insert(Kczlfxbg kczlfxbg) {
        return kczlfxbgMapper.insert(kczlfxbg);
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
