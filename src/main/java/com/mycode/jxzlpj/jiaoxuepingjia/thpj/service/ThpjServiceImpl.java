package com.mycode.jxzlpj.jiaoxuepingjia.thpj.service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.jxzlpj.jiaoxuepingjia.pjset.domain.PjSetTarget;
import com.mycode.jxzlpj.jiaoxuepingjia.pjset.mapper.PjSetTemplateMapper;
import com.mycode.jxzlpj.jiaoxuepingjia.thpj.domian.Thpj;
import com.mycode.jxzlpj.jiaoxuepingjia.thpj.mapper.ThpjMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
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
    public Map<String, Object> getPageList(Thpj thpj) {
        Map<String, Object> resultMap = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(thpj.getPageIndex(), thpj.getPageSize());
        List<Thpj> pageList = thpjMapper.getPageList(thpj);
        resultMap.put("totalNum", pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public String getThpjTemplateCode(String pjCode) {
        return thpjMapper.getThpjTemplateCode(pjCode);
    }

    @Override
    public List<Map<String, Object>> getThpjTemplate(String templateCode) {
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

    @Override
    public Thpj detail(String pjCode) {
        Thpj thpj = thpjMapper.getThpjInfo(pjCode);
        if(thpj != null){
            List<Map<String,Object>> thpjItemList = thpjMapper.getThpjItemList(pjCode);
            thpj.setThpjItemList(thpjItemList);
        }
        return thpj;
    }

    @Override
    @Transactional
    public boolean insert(Thpj thpj, String jsonString) {
        thpj.setCode(thpj.getXn() + ("3".equals(thpj.getXq())?"03-":"12-") + thpj.getCourseCode() + "-" + thpj.getSkjsCode() + "-" + thpj.getUserId()+ "-" + thpj.getPjType());
        boolean bool = thpjMapper.insert(thpj);
        if(bool){
            List<PjSetTarget> pjSetTargetList = pjSetTemplateMapper.getPjSetTargetListByTemplateCode(thpj.getTemplateCode());
            Map<String,Object> paramMap = JSON.parseObject(jsonString, Map.class);
            bool = thpjMapper.insertTarget(thpj.getCode(), thpj.getTemplateCode(), pjSetTargetList, paramMap);
        }
        return bool;
    }

    @Override
    @Transactional
    public boolean update(Thpj thpj, String jsonString) {
        boolean bool = thpjMapper.deleteItems(thpj.getCode()); //删除以前的记录
        List<PjSetTarget> pjSetTargetList = pjSetTemplateMapper.getPjSetTargetListByTemplateCode(thpj.getTemplateCode());
        Map<String,Object> paramMap = JSON.parseObject(jsonString, Map.class);
        bool = thpjMapper.insertTarget(thpj.getCode(), thpj.getTemplateCode(), pjSetTargetList, paramMap); //然后再重新录入
        if(bool){
            bool = thpjMapper.resetSubmit(thpj.getCode());
        }
        return bool;
    }

    /*
    同行评教-比较评价
     */
    @Override
    public Integer isTopFull(String userId) {
        return thpjMapper.isTopFull(userId);
    }

    @Override
    public boolean submit(String pjCode) {
        return thpjMapper.submit(pjCode);
    }
}
