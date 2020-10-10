package com.mycode.jiaoxuejiangcheng.kcjscgj.service;

import com.mycode.jiaoxuejiangcheng.kcjscgj.domian.Kcjscgj;

import java.util.Map;

/**
 * 教学奖惩-课程建设成果奖
 */
public interface KcjscgjService {

    Map<String, Object> getPageList(Kcjscgj kcjscgj);

    boolean insert(Kcjscgj kcjscgj);

    boolean update(Kcjscgj kcjscgj);

    boolean delete(String code);

}
