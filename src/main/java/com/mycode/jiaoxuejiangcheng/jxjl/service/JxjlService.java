package com.mycode.jiaoxuejiangcheng.jxjl.service;

import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.jiaoxuejiangcheng.jxjl.domian.Jxjl;

import java.util.List;
import java.util.Map;

/**
 * 教学奖惩-教学奖励
 */
public interface JxjlService {

    Map<String, Object> getPageList(Jxjl obj);

    boolean insert(Jxjl obj);

    boolean update(Jxjl obj);

    boolean delete(String objCode);

    boolean toSubimt(String activeShenheCode, List<Jxjl> objList);

    boolean toShenhe(ShenHeItem item, List<Jxjl> objList);
}
