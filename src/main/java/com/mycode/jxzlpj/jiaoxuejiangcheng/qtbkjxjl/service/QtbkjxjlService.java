package com.mycode.jxzlpj.jiaoxuejiangcheng.qtbkjxjl.service;

import com.mycode.jxzlpj.jiaoxuejiangcheng.qtbkjxjl.domian.Qtbkjxjl;

import java.util.Map;

/**
 * 教学奖惩-其他本科教学奖励
 */
public interface QtbkjxjlService {

    Map<String, Object> getPageList(Qtbkjxjl qtbkjxjl);

    boolean insert(Qtbkjxjl qtbkjxjl);

    boolean update(Qtbkjxjl qtbkjxjl);

    boolean delete(String code);

}
