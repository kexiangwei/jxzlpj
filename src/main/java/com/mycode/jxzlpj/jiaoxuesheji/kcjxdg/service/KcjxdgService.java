package com.mycode.jxzlpj.jiaoxuesheji.kcjxdg.service;

import com.mycode.jxzlpj.jiaoxuesheji.kcjxdg.domian.Kcjxdg;

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
