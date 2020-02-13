package com.mycode.jiaoxuejiangcheng.jxsg.service;

import com.mycode.jiaoxuejiangcheng.jxsg.domian.Jxsg;

import java.util.Map;

/**
 * 教学奖惩-教学事故
 */
public interface JxsgService {

    int isAdmin(String userId);

    Map<String, Object> getPageList(Jxsg obj);

    boolean insert(Jxsg obj);

    boolean update(Jxsg obj);

    boolean delete(String objCode);
}
