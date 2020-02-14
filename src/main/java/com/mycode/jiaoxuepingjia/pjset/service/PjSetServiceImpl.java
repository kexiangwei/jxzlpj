package com.mycode.jiaoxuepingjia.pjset.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.jiaoxuepingjia.pjset.domain.PjSetTarget;
import com.mycode.jiaoxuepingjia.pjset.domain.PjSet;
import com.mycode.jiaoxuepingjia.pjset.domain.PjSetTemplate;
import com.mycode.jiaoxuepingjia.pjset.mapper.PjSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 教学评价-评教设置
 * @auther kexiangwei
 * @date 2019/10/8
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
        resultMap.put("totalNum",pageInfo.getTotal());
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
    public Map<String, Object> getThpjTemplate(PjSetTarget pjSetTarget) {
        Map<String, Object> resultMap = new HashMap<>();
        List<PjSetTarget> pjSetTargetList = pjSetMapper.getPjSetTargetList(pjSetTarget);
        List<PjSetTarget> teacherTargets = pjSetTargetList.stream().filter(t -> t.getTargetType().equals("teacher")).collect(Collectors.toList());
        List<PjSetTarget> studentTargets = pjSetTargetList.stream().filter(t -> t.getTargetType().equals("student")).collect(Collectors.toList());
        resultMap.put("teacherTargets",teacherTargets);
        resultMap.put("studentTargets",studentTargets);
        return resultMap;
    }

    @Override
    public Map<String, Object> getXspjTemplate() {
        Map<String, Object> resultMap = new HashMap<>();
        String templateCode = pjSetMapper.isPj();
        boolean isPj = false;
        List<PjSetTarget> pjSetTargetList = null;
        if(!StringUtils.isEmpty(templateCode)){
            isPj = true;
            pjSetTargetList = pjSetMapper.getPjSetTargetListByTemplateCode(templateCode);
        }
        resultMap.put("isPj",isPj);
        resultMap.put("targetList",pjSetTargetList);
        return resultMap;
    }
}
