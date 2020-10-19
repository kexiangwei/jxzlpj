package com.mycode.jiaoxuesheji.kcjxssfa.service;

import com.mycode.jiaoxuesheji.kcjxssfa.domian.Course;
import com.mycode.jiaoxuesheji.kcjxssfa.domian.Kcjxssfa;
import com.mycode.jiaoxuesheji.kcjxssfa.domian.KcjxssfaItem;

import java.util.List;
import java.util.Map;

/**
 * 教学设计-课程教学实施方案
 */
public interface KcjxssfaService {

    Map<String, Object> getPageList(Kcjxssfa kcjxssfa);

    List<KcjxssfaItem> getItemListByRelationCode(String relationCode);

    List<Course> getCourseListByUserId(String userId);

    boolean insert(KcjxssfaItem item);

    /*boolean update(Kcjxssfa kcjxssfa);

    boolean delete(String code);*/
}
