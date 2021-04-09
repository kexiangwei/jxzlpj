package com.mycode.jxzlpj.jiaoxuepingjia.thpj.service;

import com.mycode.jxzlpj.jiaoxuepingjia.pjset.domain.PjSetTarget;
import com.mycode.jxzlpj.jiaoxuepingjia.pjset.mapper.PjSetTemplateMapper;
import com.mycode.jxzlpj.jiaoxuepingjia.thpj.mapper.ThpjMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 教学评价-同行评教
 */
@Service
public class ThpjServiceImpl implements ThpjService {

    @Autowired
    private ThpjMapper thpjMapper;
    @Autowired
    private PjSetTemplateMapper pjSetTemplateMapper;

    @Override
    public String getThpjTemplateCode(String pjCode) {
        return thpjMapper.getThpjTemplateCode(pjCode);
    }

    @Override
    public List<Map<String, Object>> getThpjTargetList(String templateCode) {
        //
        List<Map<String, Object>> mapList = thpjMapper.getPjzb(templateCode);
        //
        List<PjSetTarget> pjSetTargetList = pjSetTemplateMapper.getPjSetTargetListByTemplateCode(templateCode);
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

    /*
    同行评教-比较评价
     */
    @Override
    public Integer isTopFull(String userId) {
        return thpjMapper.isTopFull(userId);
    }

    @Override
    public boolean submit(String code) {
        return thpjMapper.submit(code);
    }
}
