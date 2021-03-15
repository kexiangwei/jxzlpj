package com.mycode.jxzlpj.jiaoxuejiangcheng.jsgrcgj.service;

import com.mycode.jxzlpj.jiaoxuejiangcheng.jsgrcgj.domian.Jsgrcgj;

import java.util.Map;

/**
 * 教学奖惩-教师个人成果奖
 */
public interface JsgrcgjService {

    Map<String, Object> getPageList(Jsgrcgj jsgrcgj);

    boolean insert(Jsgrcgj jsgrcgj);

    boolean update(Jsgrcgj jsgrcgj);

    boolean delete(String code);

}
