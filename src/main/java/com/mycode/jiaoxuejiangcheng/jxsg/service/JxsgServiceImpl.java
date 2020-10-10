package com.mycode.jiaoxuejiangcheng.jxsg.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.jiaoxuejiangcheng.jxsg.domian.Jxsg;
import com.mycode.jiaoxuejiangcheng.jxsg.mapper.JxsgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教学奖惩-教学事故
 */
@Service
public class JxsgServiceImpl implements JxsgService {

    @Autowired
    private JxsgMapper jxsgMapper;

    @Override
    public int isAdmin(String userId) {
        return jxsgMapper.isAdmin(userId);
    }

    @Override
    public Map<String, Object> getPageList(Jxsg obj) {
        Map<String, Object> resultMap = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(obj.getPageIndex(), obj.getPageSize());
        List<Jxsg> pageList = jxsgMapper.getPageList(obj);
        resultMap.put("totalNum",pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public boolean insert(Jxsg obj) {
        return jxsgMapper.insert(obj);
    }

    @Override
    public boolean update(Jxsg obj) {
        return jxsgMapper.update(obj);
    }

    @Override
    public boolean delete(String objCode) {
        return jxsgMapper.delete(objCode);
    }

}
