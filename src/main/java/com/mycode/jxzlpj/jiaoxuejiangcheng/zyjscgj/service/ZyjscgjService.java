package com.mycode.jxzlpj.jiaoxuejiangcheng.zyjscgj.service;

import com.mycode.jxzlpj.jiaoxuejiangcheng.zyjscgj.domian.Zyjscgj;

import java.util.Map;

/**
 * 教学奖惩-专业建设成果奖
 */
public interface ZyjscgjService {

    Map<String, Object> getPageList(Zyjscgj zyjscgj);

    boolean insert(Zyjscgj zyjscgj);

    boolean update(Zyjscgj zyjscgj);

    boolean delete(String code);

}
