package com.mycode.jiaoxuejiangcheng.jyjxcgj.service;

import com.mycode.jiaoxuejiangcheng.jyjxcgj.domian.Jyjxcgj;

import java.util.Map;

/**
 * 教学奖惩-教育教学成果奖
 */
public interface JyjxcgjService {

    Map<String, Object> getPageList(Jyjxcgj jyjxcgj);

    boolean insert(Jyjxcgj jyjxcgj);

    boolean update(Jyjxcgj jyjxcgj);

    boolean delete(String code);
}
