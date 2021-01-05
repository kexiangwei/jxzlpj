package com.mycode.jiaoxuepingjia.xspj.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.jiaoxuepingjia.xspj.domain.BjpjParams;
import com.mycode.jiaoxuepingjia.xspj.domain.Xspj;
import com.mycode.jiaoxuepingjia.xspj.mapper.XspjMapper;
import com.mycode.util.StringUtils;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.DoubleBinaryOperator;

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

    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    @Override
    public boolean insertBjpj(BjpjParams params) {
        boolean bool = xspjMapper.insertBjpj(params);
        if(bool){
            List<Map<String,Object>>  mapList = new ArrayList<>();
            JSONArray jsonArray = JSON.parseArray(params.getTransferSelectedDataArr());
            jsonArray.stream().forEach(obj -> {
                JSONObject jsonObject = JSON.parseObject(obj.toString());
                String targetCode = jsonObject.getString("targetCode");
                int targetScore = jsonObject.getIntValue("targetScore");
                JSONArray arr = JSONArray.parseArray(jsonObject.getString("arr"));
                for (int i = 0; i < arr.size(); i++) {
                    Map<String,Object> map = new HashMap<>();
                    map.put("relationCode",params.getCode());
                    map.put("courseCode",arr.getString(i));
                    map.put("targetCode",targetCode);
                    //计算每道题的得分 = 每道题的分值/2 + 每道题的分值/2 * (课程总数 - 课程排序后的序号 + 1) / 课程总数
                    map.put("score",new Double(targetScore/2 + targetScore/2 * (arr.size() - i) / arr.size()));
                    mapList.add(map);
                }
            });
            bool = xspjMapper.insertBjpjTarget(mapList);
        }
        return bool;
    }

}
