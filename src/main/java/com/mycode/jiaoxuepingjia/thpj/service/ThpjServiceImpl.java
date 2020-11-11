package com.mycode.jiaoxuepingjia.thpj.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.jiaoxuepingjia.pjset.domain.PjSetTarget;
import com.mycode.jiaoxuepingjia.pjset.domain.PjSetTemplate;
import com.mycode.jiaoxuepingjia.pjset.mapper.PjSetMapper;
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

   /* @Override
    public boolean update(Thpj thpj) {
        return thpjMapper.update(thpj);
    }

    @Override
    public boolean delete(String code) {
        return thpjMapper.delete(code);
    }*/

    @Override
    public String getThpjTemplateCode(String code) {
        return thpjMapper.getThpjTemplateCode(code);
    }

    @Override
    public List<Map<String, Object>> getThpjTargetList(String templateCode) {
        List<Map<String, Object>> thpjTargetList = thpjMapper.getThpjTargetList(templateCode);
        List<PjSetTarget> pjSetTargetList = pjSetMapper.getPjSetTargetListByTemplateCode(templateCode);
        Map<String, List<PjSetTarget>> targetListByName = pjSetTargetList.stream().collect(Collectors.groupingBy(PjSetTarget::getTargetName, Collectors.toList()));
        thpjTargetList.forEach(m -> {
            targetListByName.forEach((k,v) -> {
                if(k.equals(m.get("name").toString())){
                    m.put("targetList",v);
                }
            });
        });
        return thpjTargetList;
    }

    @Override
    public List<Map<String, Object>> getTeacherBar(String menuName, String userId) {
        return thpjMapper.getTeacherBar(menuName,userId);
    }

    @Override
    public List<Map<String, Object>> getTeacherPie(String menuName, String userId) {
        return thpjMapper.getTeacherPie(menuName,userId);
    }

    @Override
    public List<Map<String, Object>> getTeacherTab(String menuName) {
        return thpjMapper.getTeacherTab(menuName);
    }

    @Override
    public List<Map<String, Object>> getTeacherTabData(String menuName, String userId, String status) {
        return thpjMapper.getTeacherTabData(menuName,userId,status);
    }

}
