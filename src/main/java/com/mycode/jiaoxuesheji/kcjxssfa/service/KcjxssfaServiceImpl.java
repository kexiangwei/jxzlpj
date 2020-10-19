package com.mycode.jiaoxuesheji.kcjxssfa.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.jiaoxuesheji.kcjxssfa.domian.Course;
import com.mycode.jiaoxuesheji.kcjxssfa.domian.Kcjxssfa;
import com.mycode.jiaoxuesheji.kcjxssfa.domian.KcjxssfaItem;
import com.mycode.jiaoxuesheji.kcjxssfa.mapper.KcjxssfaMapper;
import com.mycode.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教学设计-课程教学实施方案
 */
@Service
public class KcjxssfaServiceImpl implements KcjxssfaService {

    @Autowired
    private KcjxssfaMapper kcjxssfaMapper;

    @Override
    public Map<String, Object> getPageList(Kcjxssfa kcjxssfa) {
        Map<String, Object> map = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(kcjxssfa.getPageIndex(), kcjxssfa.getPageSize());
        List<Kcjxssfa> pageList = kcjxssfaMapper.getPageList(kcjxssfa);
        map.put("totalNum", pageInfo.getTotal());
        map.put("pageList", pageList);
        return map;
    }

    @Override
    public List<KcjxssfaItem> getItemListByRelationCode(String relationCode) {
        return kcjxssfaMapper.getItemListByRelationCode(relationCode);
    }

    @Override
    public List<Course> getCourseListByUserId(String userId) {
        return kcjxssfaMapper.getCourseListByUserId(userId);
    }

    @Override
    public boolean insert(KcjxssfaItem item) {
        if(StringUtils.isEmpty(item.getCode())){
            List<Kcjxssfa> pageList = kcjxssfaMapper.getPageList(item);
            if(pageList.size() == 0){
                item.setCode(StringUtils.uuid());
                boolean bool = kcjxssfaMapper.insert(item);
                if(!bool){
                    return false;
                }
            } else {
                item.setCode(pageList.get(0).getCode());
            }
        }
        return kcjxssfaMapper.insertItem(item);
    }

    /*@Override
    public boolean update(Kcjxssfa kcjxssfa) {
        return kcjxssfaMapper.update(kcjxssfa);
    }

    @Override
    public boolean delete(String code) {
        return kcjxssfaMapper.delete(code);
    }*/
}
