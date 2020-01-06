package com.mycode.diaochawenjuan.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.diaochawenjuan.domian.WjQuestion;
import com.mycode.diaochawenjuan.domian.WjSet;
import com.mycode.diaochawenjuan.mapper.WjMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 调查问卷
 * @auther kexiangwei
 * @date 2019/10/8
 */
@Service
public class WjServiceImpl implements WjService {

    @Autowired
    private WjMapper wjMapper;

    @Override
    public Map<String, Object> getWjSetPageList(WjSet wjSet) {
        Map<String, Object> resultMap = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(wjSet.getPageIndex(), wjSet.getPageSize());
        List<WjSet> pageList = wjMapper.getWjSetPageList(wjSet);
        if(pageList !=null && pageList.size() >0){
            pageList.forEach((wj) ->{
                wj.setWjQuestionList(wjMapper.getWjQuestionPageList(new WjQuestion(wj.getWjCode())));
            });
        }
        resultMap.put("totalNum",pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public Map<String, Object> getWjQuestionPageList(WjQuestion wjQuestion) {
        Map<String, Object> resultMap = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(wjQuestion.getPageIndex(), wjQuestion.getPageSize());
        List<WjQuestion> pageList = wjMapper.getWjQuestionPageList(wjQuestion);
        resultMap.put("totalNum",pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

}
