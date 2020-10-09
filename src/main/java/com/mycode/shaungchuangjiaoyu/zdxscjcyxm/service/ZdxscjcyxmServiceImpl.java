package com.mycode.shaungchuangjiaoyu.zdxscjcyxm.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.shaungchuangjiaoyu.zdxscjcyxm.domian.Zdxscjcyxm;
import com.mycode.shaungchuangjiaoyu.zdxscjcyxm.mapper.ZdxscjcyxmMapper;
import com.mycode.shenhe.mapper.ShenHeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 双创教育-指导学生参加创业项目
 */
@Service
public class ZdxscjcyxmServiceImpl implements ZdxscjcyxmService {

    @Autowired
    private ZdxscjcyxmMapper zdxscjcyxmMapper;
    @Autowired
    private ShenHeMapper shenHeMapper;

    @Override
    public Map<String, Object> getPageList(Zdxscjcyxm zdxscjcyxm) {
        Map<String, Object> resultMap = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(zdxscjcyxm.getPageIndex(), zdxscjcyxm.getPageSize());
        List<Zdxscjcyxm> pageList = zdxscjcyxmMapper.getPageList(zdxscjcyxm);
        //获取未审核数
        if(com.mycode.util.StringUtils.isNotEmpty(zdxscjcyxm.getShenHeUserId())){
            resultMap.put("unShenHeNum", shenHeMapper.getNotShenHeNum("v_scjy_zdxscjcyxm_shenhe", zdxscjcyxm.getShenHeUserId()));
        }
        resultMap.put("totalNum", pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public boolean insert(Zdxscjcyxm zdxscjcyxm) {
        return zdxscjcyxmMapper.insert(zdxscjcyxm);
    }

    @Override
    public boolean update(Zdxscjcyxm zdxscjcyxm) {
        return zdxscjcyxmMapper.update(zdxscjcyxm);
    }

    @Override
    public boolean delete(String code) {
        return zdxscjcyxmMapper.delete(code);
    }

}
