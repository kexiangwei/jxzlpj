package com.mycode.jxzlpj.jiaoxuexiaoguo.kczlfxbg.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.jxzlpj.jiaoxuexiaoguo.kczlfxbg.doman.Kczlfxbg;
import com.mycode.jxzlpj.jiaoxuexiaoguo.kczlfxbg.mapper.KczlfxbgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class KczlfxbgServiceImpl implements KczlfxbgService {

    @Autowired
    private KczlfxbgMapper kczlfxbgMapper;

    @Override
    public Map<String, Object> getPageList(Kczlfxbg kczlfxbg) {
        Page<Object> pageInfo = PageHelper.startPage(kczlfxbg.getPageIndex(), kczlfxbg.getPageSize());
        List<Kczlfxbg> pageList = kczlfxbgMapper.getPageList(kczlfxbg);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("totalNum", pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public Map<String,Object> getKczlfxbg(String code) {
        Kczlfxbg bg = kczlfxbgMapper.getKczlfxbg(code);
        List<Map<String,Object>> bgA1List = kczlfxbgMapper.getKczlfxbgA1(code);
        List<Map<String,Object>> bgA2List = kczlfxbgMapper.getKczlfxbgA2(code);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("bg", bg);
        resultMap.put("bgA1List", bgA1List);
        resultMap.put("bgA2List", bgA2List);
        return resultMap;
    }

    @Override
    public boolean insert(Kczlfxbg bg) {
        bg.setCode(bg.getXn() + ("3".equals(bg.getXq())?"03-":"12-") + bg.getCourseCode() + "-" + bg.getUserId());
        return kczlfxbgMapper.insert(bg);
    }

    @Override
    @Transactional
    public boolean insert2(Map<String, Object> params) {
        Kczlfxbg bg = JSONObject.parseObject(JSONObject.toJSONString(params), Kczlfxbg.class); //用fastjson将map转成实体类
        bg.setCode(bg.getXn() + ("3".equals(bg.getXq())?"03-":"12-") + bg.getCourseCode() + "-" + bg.getUserId());
        boolean bool = kczlfxbgMapper.insert(bg);
        if(bool){
            //执行a1新增操作
            bool = kczlfxbgMapper.insertA1(bg.getCode(),this.getBgA1Params(params));
            //执行a2新增操作
            bool = kczlfxbgMapper.insertA2(bg.getCode(),this.getBgA2Params(params));
        }
        return bool;
    }

    @Override
    public boolean update(Kczlfxbg kczlfxbg) {
        return kczlfxbgMapper.update(kczlfxbg);
    }

    @Override
    @Transactional
    public boolean update2(Map<String, Object> params) {
        Kczlfxbg bg = JSONObject.parseObject(JSONObject.toJSONString(params), Kczlfxbg.class); //用fastjson将map转成实体类
        boolean bool = kczlfxbgMapper.update(bg);
        if(bool){
            //执行a1删除操作
            bool = kczlfxbgMapper.deleteA1(bg.getCode());
            //执行a1新增操作
            bool = kczlfxbgMapper.insertA1(bg.getCode(),this.getBgA1Params(params));
            //执行a2删除操作
            bool = kczlfxbgMapper.deleteA2(bg.getCode());
            //执行a2新增操作
            bool = kczlfxbgMapper.insertA2(bg.getCode(),this.getBgA2Params(params));
        }
        return bool;
    }

    @Override
    public boolean submit(String code) {
        return kczlfxbgMapper.submit(code);
    }

    //
    private List<SortedMap<String, Object>> getBgA1Params(Map<String, Object> params){
        //提取a1开头的属性
        TreeMap<String, Object> treeMap = new TreeMap();
        Set<String> stringSet = new HashSet<>();
        params.forEach((k,v) -> {
            if(k.startsWith("a1")){
                treeMap.put(k,v);
                stringSet.add(k.substring(0,4));
            }
        });
        //按照指定前缀分组
        List<SortedMap<String, Object>> maplist = new ArrayList<>();
        stringSet.forEach(prefix -> {
            maplist.add(treeMap.subMap(prefix, prefix + Character.MAX_VALUE ));
        });
        return maplist;
    }

    //
    private List<SortedMap<String, Object>> getBgA2Params(Map<String, Object> params){
        //提取a2开头的属性
        TreeMap<String, Object> treeMap2 = new TreeMap();
        Set<String> stringSet2 = new HashSet<>();
        params.forEach((k,v) -> {
            if(k.startsWith("a2")){
                treeMap2.put(k,v);
                stringSet2.add(k.substring(0,4));
            }
        });
        //按照指定前缀分组
        List<SortedMap<String, Object>> maplist2 = new ArrayList<>();
        stringSet2.forEach(prefix -> {
            maplist2.add(treeMap2.subMap(prefix, prefix + Character.MAX_VALUE ));
        });
        return maplist2;
    }

}
