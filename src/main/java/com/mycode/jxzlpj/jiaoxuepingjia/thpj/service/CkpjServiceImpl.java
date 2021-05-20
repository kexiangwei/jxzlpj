package com.mycode.jxzlpj.jiaoxuepingjia.thpj.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.jxzlpj.jiaoxuepingjia.thpj.domian.Ckpj;
import com.mycode.jxzlpj.jiaoxuepingjia.thpj.mapper.CkpjMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教学评价-同行评教
 */
@Service
public class CkpjServiceImpl implements CkpjService {

    @Autowired
    private CkpjMapper ckpjMapper;

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
    public Map<String, Object> getPjInfo(String courseCode, String skjsCode) {
        Map<String, Object> resultMap = ckpjMapper.getPjInfo(courseCode,skjsCode);
        List<Map<String, Object>> mapList = ckpjMapper.getPjInfoSuggestList(courseCode,skjsCode);
        resultMap.put("suggestList", mapList);
        return resultMap;
    }

}
