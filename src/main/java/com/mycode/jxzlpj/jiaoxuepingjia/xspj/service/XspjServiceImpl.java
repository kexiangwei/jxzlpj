package com.mycode.jxzlpj.jiaoxuepingjia.xspj.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.jxzlpj.jiaoxuepingjia.xspj.domain.Xspj;
import com.mycode.jxzlpj.jiaoxuepingjia.xspj.mapper.XspjMapper;
import com.mycode.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

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
    @Transactional
    public boolean insert(Xspj xspj, String jsonString) {
        xspj.setCode(xspj.getXn() + ("3".equals(xspj.getXq())?"-03-":"-12-") + xspj.getCourseCode() + "-"+ xspj.getUserId());
        boolean bool = xspjMapper.insert(xspj);
        if(bool){
            Map<String,Object> paramMap = JSON.parseObject(jsonString, Map.class);
            bool = xspjMapper.insertItem(xspj, paramMap);
        }
        return bool;
    }

    @Override
    public Map<String, Object> getPjInfo(String xn, String xq, String courseCode, String teacherCode) {
        Map<String,Object> pjInfo = xspjMapper.getPjInfo(xn,xq,courseCode,teacherCode);
        List<String> suggestList = xspjMapper.getPjInfoSuggestList(xn,xq,courseCode,teacherCode);
        pjInfo.put("suggestList",suggestList);
        return pjInfo;
    }

    @Override
    public List<Map<String,Object>> getBjpjTransferData(String userId) {
        return xspjMapper.getBjpjTransferData(userId);
    }

    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    @Override
    public boolean insertBjpj(Xspj xspj) {

        List<Map<String,Object>> xspjList = new ArrayList<>();
        JSONObject pjSuggestJson = JSON.parseObject(xspj.getPjSuggestJsonString());
        for (Map.Entry<String, Object> pjSuggest : pjSuggestJson.entrySet()) {
            Map<String,Object> map = new HashMap<>();
            map.put("code",xspj.getXn() + ("3".equals(xspj.getXq())?"-03-":"-12-") + pjSuggest.getKey() + "-" + xspj.getUserId());
            map.put("xn",xspj.getXn());
            map.put("xq",xspj.getXq());
            map.put("courseCode",pjSuggest.getKey());
            map.put("pjSuggest",StringUtils.isNotEmpty(pjSuggest.getValue().toString())?pjSuggest.getValue().toString():"无建议或意见");
            map.put("userId",xspj.getUserId());
            map.put("userName",xspj.getUserName());
            xspjList.add(map);
        }
        boolean bool = xspjMapper.insertBjpj(xspjList);
        if(bool){
            List<Map<String,Object>> itemList = new ArrayList<>();
            JSONArray jsonArray = JSON.parseArray(xspj.getTransferDatas());
            jsonArray.stream().forEach(obj -> {
                JSONObject jsonObject = JSON.parseObject(obj.toString());
                String targetCode = jsonObject.getString("targetCode");
                int targetScore = jsonObject.getIntValue("targetScore");
                JSONArray arr = JSONArray.parseArray(jsonObject.getString("arr"));
                for (int i = 0; i < arr.size(); i++) {
                    Map<String,Object> map = new HashMap<>();
                    map.put("relationCode",xspj.getXn() + ("3".equals(xspj.getXq())?"-03-":"-12-") + arr.getString(i) + "-" + xspj.getUserId());
                    map.put("templateCode",xspj.getTemplateCode());
                    map.put("targetCode",targetCode);
                    //计算每道题的得分 = 每道题的分值/2 + 每道题的分值/2 * (课程总数 - 课程排序后的序号 + 1) / 课程总数
                    map.put("score",new Double(targetScore/2 + targetScore/2 * (arr.size() - i) / arr.size()));
                    itemList.add(map);
                }
            });
            bool = xspjMapper.insertBjpjItem(itemList);
        }
        return bool;
    }

    @Override
    public List<Map<String, Object>> getBjpjPjSuggestList(String userId, String templateCode) {
        return xspjMapper.getBjpjPjSuggestList(userId,templateCode);
    }

    @Override
    public Map<String, Object> getBjpjPageList(Xspj xspj) {
        Map<String, Object> resultMap = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(xspj.getPageIndex(), xspj.getPageSize());
        List<Xspj> pageList = xspjMapper.getBjpjPageList(xspj);
        resultMap.put("totalNum", pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public Map<String, Object> getBjpjPjInfo(String xn, String xq, String courseCode, String teacherCode) {
        Map<String,Object> pjInfo = xspjMapper.getBjpjPjInfo(xn,xq,courseCode,teacherCode);
        List<String> suggestList = xspjMapper.getBjpjPjInfoSuggestList(xn,xq,courseCode,teacherCode);
        pjInfo.put("suggestList",suggestList);
        return pjInfo;
    }

}
