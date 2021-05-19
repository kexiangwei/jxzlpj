package com.mycode.jxzlpj.jiaoxuesheji.kcjxssfa.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.jxzlpj.jiaoxuesheji.kcjxssfa.domian.Kcjxssfa;
import com.mycode.jxzlpj.jiaoxuesheji.kcjxssfa.domian.KcjxssfaItem;
import com.mycode.jxzlpj.jiaoxuesheji.kcjxssfa.mapper.KcjxssfaMapper;
import com.mycode.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教学设计-课程教学实施方案
 */
@Service
public class KcjxssfaServiceImpl implements KcjxssfaService {

    @Autowired
    private KcjxssfaMapper kcjxssfaMapper;

    @Override
    public Map<String, Object> getPageList(Kcjxssfa kcjxssfa) {
        Map<String, Object> map = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(kcjxssfa.getPageIndex(), kcjxssfa.getPageSize());
        List<Kcjxssfa> pageList = kcjxssfaMapper.getPageList(kcjxssfa);
        map.put("totalNum", pageInfo.getTotal());
        map.put("pageList", pageList);
        return map;
    }

    @Override
    public List<KcjxssfaItem> getItemList(String relationCode) {
        return kcjxssfaMapper.getItemList(relationCode);
    }

    @Override
    @Transactional
    public boolean insert(KcjxssfaItem item) {
        boolean bool = false;
        if(StringUtils.isEmpty(item.getCode())){
            item.setCode(item.getXn() + ("3".equals(item.getXq())?"03-":"12-") + item.getCourseCode() + "-" + item.getUserId());
            bool = kcjxssfaMapper.insert(item);
        }
        item.setItemCode(item.getXn() + ("3".equals(item.getXq())?"03-":"12-") + item.getCourseCode() + "-" + item.getSkjsCode() + "-" + item.getUserId());
        bool = kcjxssfaMapper.insertItem(item);
        return bool;
    }

    /*@Override
    public boolean update(Kcjxssfa kcjxssfa) {
        return kcjxssfaMapper.update(kcjxssfa);
    }

    @Override
    public boolean delete(String code) {
        return kcjxssfaMapper.delete(code);
    }*/
}
