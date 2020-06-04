package com.mycode.jiaoxuepingjia.pjset.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.jiaoxuepingjia.pjset.domain.PjSet;
import com.mycode.jiaoxuepingjia.pjset.domain.PjSetTarget;
import com.mycode.jiaoxuepingjia.pjset.domain.PjSetTemplate;
import com.mycode.jiaoxuepingjia.pjset.mapper.PjSetMapper;
import com.mycode.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教学评价-评教设置
 */
@Service
public class PjSetServiceImpl implements PjSetService {

    @Autowired
    private PjSetMapper pjSetMapper;

    @Override
    public Map<String, Object> getPjSetList(PjSet pjSet) {
        Map<String, Object> resultMap = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(pjSet.getPageIndex(), pjSet.getPageSize());
        List<PjSet> pageList = pjSetMapper.getPjSetList(pjSet);
        resultMap.put("totalNum", pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public Map<String, Object> getPjSetTemplateList(PjSetTemplate pjSetTemplate) {
        Map<String, Object> resultMap = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(pjSetTemplate.getPageIndex(), pjSetTemplate.getPageSize());
        List<PjSetTemplate> pageList = pjSetMapper.getPjSetTemplateList(pjSetTemplate);
        resultMap.put("totalNum",pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public List<PjSetTarget> getPjSetTargetList(PjSetTarget pjSetTarget) {
        return pjSetMapper.getPjSetTargetList(pjSetTarget);
    }

    @Override
    public Map<String, Object> getCurrentTemplate(String templateType) {
        Map<String, Object> resultMap = new HashMap<>();
        String templateCode = pjSetMapper.isPj(templateType); //查看当前是否有可用的模板信息
        boolean isPj = false;
        List<PjSetTarget> pjSetTargetList = null;
        if(StringUtils.isNotEmpty(templateCode)){
            isPj = true;
            pjSetTargetList = pjSetMapper.getPjSetTargetListByTemplateCode(templateCode); //根据模板编号获取模板信息
        }
        resultMap.put("isPj",isPj);
        resultMap.put("targetList",pjSetTargetList);
        return resultMap;
    }
}
