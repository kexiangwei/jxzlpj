package com.mycode.shaungchuangjiaoyu.zdxscy.service;

import com.mycode.shaungchuangjiaoyu.zdxscy.domian.Zdxscy;

import java.util.Map;

/**
 * 双创教育-指导学生创业
 */
public interface ZdxscyService {

    Map<String, Object> getPageList(Zdxscy zdxscy);

    boolean insert(Zdxscy zdxscy);

    boolean update(Zdxscy zdxscy);

    boolean delete(String code);

}
