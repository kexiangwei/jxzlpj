package com.mycode.jiaoxuejiangcheng.jyjxcgj.service;

import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.jiaoxuejiangcheng.jyjxcgj.domian.Jyjxcgj;

import java.util.List;
import java.util.Map;

/**
 * 教学奖惩-教育教学成果奖
 */
public interface JyjxcgjService {

    Map<String, Object> getPageList(Jyjxcgj obj);

    boolean insert(Jyjxcgj obj);

    boolean update(Jyjxcgj obj);

    boolean delete(String objCode);

    boolean toSubimt(String activeShenheCode, List<Jyjxcgj> objList);

    boolean toShenhe(ShenHeItem item, List<Jyjxcgj> objList);
}
