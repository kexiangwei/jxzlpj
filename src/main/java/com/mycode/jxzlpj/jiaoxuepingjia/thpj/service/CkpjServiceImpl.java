package com.mycode.jxzlpj.jiaoxuepingjia.thpj.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.jxzlpj.jiaoxuepingjia.pjset.domain.PjSetTarget;
import com.mycode.jxzlpj.jiaoxuepingjia.pjset.mapper.PjSetTemplateMapper;
import com.mycode.jxzlpj.jiaoxuepingjia.thpj.domian.Ckpj;
import com.mycode.jxzlpj.jiaoxuepingjia.thpj.mapper.CkpjMapper;
import com.mycode.jxzlpj.jiaoxuepingjia.thpj.mapper.ThpjMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 教学评价-同行评教
 */
@Service
public class CkpjServiceImpl implements CkpjService {

    @Autowired
    private CkpjMapper ckpjMapper;
    @Autowired
    private ThpjMapper thpjMapper;
    @Autowired
    private PjSetTemplateMapper pjSetTemplateMapper;

    /*
    查看评教
     */
    @Override
    public Map<String, Object> getCkpjPageList(Ckpj ckpj) {
        Map<String, Object> resultMap = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(ckpj.getPageIndex(), ckpj.getPageSize());
        List<Ckpj> pageList = ckpjMapper.getCkpjPageList(ckpj);
        resultMap.put("totalNum", pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public List<Map<String, Object>> getCkpjDetail(Ckpj ckpj) {
        List<Map<String, Object>> mapList = thpjMapper.getPjzb(ckpj.getTemplateCode());
        List<Map<String, Object>> targetAvgList = ckpjMapper.getThpjTargetAvgList(ckpj.getUserId(),ckpj.getCourseCode());
        List<PjSetTarget> pjSetTargetList = pjSetTemplateMapper.getPjSetTargetListByTemplateCode(ckpj.getTemplateCode());
        pjSetTargetList.stream().forEach(t1 -> {
            targetAvgList.stream().forEach(t2 -> {
                if(t2.get("targetCode").toString().equals(t1.getTargetCode())){
                    t1.setAvgScore(Double.parseDouble(t2.get("avgScore").toString()));
                }
            });
        });
        Map<String, List<PjSetTarget>> targetListByName = pjSetTargetList.stream().collect(Collectors.groupingBy(PjSetTarget::getTargetName, Collectors.toList()));
        mapList.forEach(m -> {
            targetListByName.forEach((k,v) -> {
                if(k.equals(m.get("name").toString())){
                    m.put("targetList",v);
                }
            });
        });
        return mapList;
    }

}
