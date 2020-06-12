package com.mycode.jiaoxuepingjia.thpj.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.jiaoxuepingjia.pjset.domain.PjSetTarget;
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
    public List<Map<String, Object>> getThpjTargetList() {
        List<Map<String, Object>> thpjTargetList = thpjMapper.getThpjTargetList();
        List<PjSetTarget> pjSetTargetList = thpjMapper.getPjSetTargetList();
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
    public boolean insert(Thpj thpj) {
        return thpjMapper.insert(thpj);
    }

    @Override
    public boolean update(Thpj thpj) {
        return thpjMapper.update(thpj);
    }

    @Override
    public boolean delete(String code) {
        return thpjMapper.delete(code);
    }

    @Override
    public List<Map<String, Object>> getTeacherInfo(String menuName, String userId) {
        return thpjMapper.getTeacherInfo(menuName,userId);
    }

}
