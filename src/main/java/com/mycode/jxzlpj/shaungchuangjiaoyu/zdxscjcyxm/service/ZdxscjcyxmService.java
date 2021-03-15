package com.mycode.jxzlpj.shaungchuangjiaoyu.zdxscjcyxm.service;

import com.mycode.jxzlpj.shaungchuangjiaoyu.zdxscjcyxm.domian.Zdxscjcyxm;

import java.util.Map;

/**
 * 双创教育-指导学生参加创业项目
 */
public interface ZdxscjcyxmService {

    Map<String, Object> getPageList(Zdxscjcyxm zdxscjcyxm);

    boolean insert(Zdxscjcyxm zdxscjcyxm);

    boolean update(Zdxscjcyxm zdxscjcyxm);

    boolean delete(String code);

}
