package com.mycode.jiaoxuepingjia.xspj.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.jiaoxuepingjia.xspj.domain.Xspj;
import com.mycode.jiaoxuepingjia.xspj.mapper.XspjMapper;
import com.mycode.util.StringUtils;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;

/**
 * 教学评价-学生评教
 */
@Service
public class XspjServiceImpl implements XspjService {

    @Autowired
    private XspjMapper xspjMapper;

    @Override
    public Map<String, Object> getPageList(Xspj xspj) {
        Map<String, Object> resultMap = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(xspj.getPageIndex(), xspj.getPageSize());
        List<Xspj> pageList = xspjMapper.getPageList(xspj);
        resultMap.put("totalNum", pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public boolean insert(Xspj xspj, Map<String,Object> paramMap) {
        return xspjMapper.insert(xspj, paramMap);
    }

    @Override
    public Map<String, Object> getPjInfo(String courseCode) {
        Map<String,Object> pjInfo = new HashMap<>();
        List<Map<String, Object>> targetList = xspjMapper.getPjInfo(courseCode);
        OptionalDouble totalAvg = targetList.stream().mapToDouble(m -> Double.parseDouble(m.get("AVG_SCORE").toString())).average();
        List<String> suggestList = xspjMapper.getPjInfoSuggestList(courseCode, targetList.get(0).get("TEMPLATE_CODE").toString());
        pjInfo.put("targetList",targetList);
        pjInfo.put("suggestList",suggestList);
        pjInfo.put("totalAvg",totalAvg);
        return pjInfo;
    }

    @Override
    public List<Map<String,Object>> getPjInfoTransferData(String userId) {
        return xspjMapper.getPjInfoTransferData(userId);
    }

}
