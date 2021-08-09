package com.mycode.jxzlpj.shaungchuangjiaoyu.zdxscjcyxm.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.common.shenheSet.domain.ShenHeV;
import com.mycode.jxzlpj.shaungchuangjiaoyu.zdxscjcyxm.domian.Zdxscjcyxm;
import com.mycode.jxzlpj.shaungchuangjiaoyu.zdxscjcyxm.mapper.ZdxscjcyxmMapper;
import com.mycode.common.shenheSet.mapper.ShenHeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 双创教育-指导学生参加创业项目
 */
@Service
public class ZdxscjcyxmServiceImpl implements ZdxscjcyxmService {

    @Resource
    private ZdxscjcyxmMapper zdxscjcyxmMapper;
    @Resource
    private ShenHeMapper shenHeMapper;

    @Override
    public Map<String, Object> getPageList(Zdxscjcyxm zdxscjcyxm) {
        Map<String, Object> resultMap = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(zdxscjcyxm.getPageIndex(), zdxscjcyxm.getPageSize());
        List<Zdxscjcyxm> pageList = zdxscjcyxmMapper.getPageList(zdxscjcyxm);
        //获取未审核数
        if(com.mycode.util.StringUtils.isNotEmpty(zdxscjcyxm.getShenHeUserId())){
            resultMap.put("unShenHeNum", shenHeMapper.getNotShenHeNum(ShenHeV.v_scjy_zdxscjcyxm_shenhe, zdxscjcyxm));
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
