package com.mycode.jiaoxuepingjia.thpj.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.jiaoxuepingjia.pjset.domain.PjSetTarget;
import com.mycode.jiaoxuepingjia.pjset.domain.PjSetTemplate;
import com.mycode.jiaoxuepingjia.pjset.mapper.PjSetMapper;
import com.mycode.jiaoxuepingjia.thpj.domian.Ckpj;
import com.mycode.jiaoxuepingjia.thpj.domian.Thpj;
import com.mycode.jiaoxuepingjia.thpj.domian.ThpjQuery;
import com.mycode.jiaoxuepingjia.thpj.mapper.ThpjMapper;
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
public class ThpjServiceImpl implements ThpjService {

    @Autowired
    private ThpjMapper thpjMapper;
    @Autowired
    private PjSetMapper pjSetMapper;

    /*
    查看评教
     */
    @Override
    public Map<String, Object> getCkpjPageList(Ckpj ckpj) {
        Map<String, Object> resultMap = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(ckpj.getPageIndex(), ckpj.getPageSize());
        List<Ckpj> pageList = thpjMapper.getCkpjPageList(ckpj);
        resultMap.put("totalNum", pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public List<Map<String, Object>> getCkpjDetail(Ckpj ckpj) {
        List<Map<String, Object>> mapList = thpjMapper.getPjzb(ckpj.getTemplateCode());
        List<Map<String, Object>> targetAvgList = thpjMapper.getThpjTargetAvgList(ckpj.getUserId(),ckpj.getCourseCode());
        List<PjSetTarget> pjSetTargetList = pjSetMapper.getPjSetTargetListByTemplateCode(ckpj.getTemplateCode());
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

    /*
    同行评教
     */
    @Override
    public Map<String, Object> getPageList(ThpjQuery thpjQuery) {
        Map<String, Object> resultMap = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(thpjQuery.getPageIndex(), thpjQuery.getPageSize());
        List<ThpjQuery> pageList = thpjMapper.getPageList(thpjQuery);
        resultMap.put("totalNum", pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public Thpj detail(String code) {
        Thpj thpj = thpjMapper.getThpjInfo(code);
        if(thpj != null){
            List<Map<String,Object>> thpjItemList = thpjMapper.getThpjItemListByRelationCode(code);
            thpj.setThpjItemList(thpjItemList);
        }
        return thpj;
    }

    @Override
    public boolean insert(Thpj thpj, Map<String, Object> paramMap) {
        boolean bool = thpjMapper.insert(thpj);
        if(bool){
            List<PjSetTarget> pjSetTargetList = pjSetMapper.getPjSetTargetListByTemplateCode(thpj.getTemplateCode());
            bool = thpjMapper.insertTarget(thpj.getCode(), pjSetTargetList, paramMap);
        }
        return bool;
    }

   @Override
    public boolean update(Thpj thpj, Map<String, Object> paramMap) {
       boolean bool = thpjMapper.deleteTargetByRelationCode(thpj.getCode()); //删除以前的记录
       List<PjSetTarget> pjSetTargetList = pjSetMapper.getPjSetTargetListByTemplateCode(thpj.getTemplateCode());
       bool = thpjMapper.insertTarget(thpj.getCode(), pjSetTargetList, paramMap); //然后再重新录入
       if(bool){
           bool = thpjMapper.resetSubmit(thpj.getCode());
       }
       return bool;
    }

    /*@Override
    public boolean delete(String code) {
        return thpjMapper.delete(code);
    }*/

    @Override
    public String getThpjTemplateCode(String code) {
        return thpjMapper.getThpjTemplateCode(code);
    }

    @Override
    public List<Map<String, Object>> getThpjTargetList(String templateCode) {
        List<Map<String, Object>> mapList = thpjMapper.getPjzb(templateCode);
        List<PjSetTarget> pjSetTargetList = pjSetMapper.getPjSetTargetListByTemplateCode(templateCode);
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
