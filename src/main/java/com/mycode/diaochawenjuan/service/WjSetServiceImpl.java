package com.mycode.diaochawenjuan.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.diaochawenjuan.domian.Question;
import com.mycode.diaochawenjuan.domian.WjSet;
import com.mycode.diaochawenjuan.mapper.WjSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther kexiangwei
 * @date 2019/10/8
 */
@Service
public class WjSetServiceImpl implements WjSetService {

    @Autowired
    private WjSetMapper wjSetMapper;

    @Override
    public Map<String, Object> getWjSetPageList(WjSet wjSet) {
        {
            Map<String, Object> resultMap = new HashMap<>();
            Page<Object> pageInfo = PageHelper.startPage(wjSet.getPageIndex(), wjSet.getPageSize());
            List<WjSet> pageList = wjSetMapper.getWjSetPageList(wjSet);
            resultMap.put("totalNum",pageInfo.getTotal());
            resultMap.put("pageList", pageList);
            return resultMap;
        }
    }

    @Override
    public Map<String, Object> getQuestionPageList(Question question) {
        Map<String, Object> resultMap = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(question.getPageIndex(), question.getPageSize());
        List<Question> pageList = wjSetMapper.getQuestionPageList(question);
        resultMap.put("totalNum",pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public List<Map<String, Object>> getOption(String qCode) {
        return wjSetMapper.getOption(qCode);
    }
}
