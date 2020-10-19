package com.mycode.jiaoxuesheji.kcjxdg.service;

import com.mycode.jiaoxuesheji.kcjxdg.domian.Kcjxdg;
import com.mycode.shaungchuangjiaoyu.wtbs.domian.Wtbs;

import java.util.Map;

/**
 * 教学设计-课程教学大纲
 */
public interface KcjxdgService {

    Map<String, Object> getPageList(Kcjxdg kcjxdg);

    boolean insert(Kcjxdg kcjxdg);

    boolean update(Kcjxdg kcjxdg);

    boolean delete(String code);
}
