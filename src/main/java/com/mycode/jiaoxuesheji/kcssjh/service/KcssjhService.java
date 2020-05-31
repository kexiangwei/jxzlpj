package com.mycode.jiaoxuesheji.kcssjh.service;

import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.jiaoxuesheji.kcssjh.domian.Kcssjh;

import java.util.List;
import java.util.Map;

/**
 * 教学设计-课程实施计划
 */
public interface KcssjhService {

    Map<String, Object> getPageList(Kcssjh kcssjh);

    boolean insert(Kcssjh kcssjh);

    boolean update(Kcssjh kcssjh);

    boolean delete(String code);

    boolean toSubimt(String activeShenheCode, List<Kcssjh> objList);

    boolean toShenhe(ShenHeItem item, List<Kcssjh> objList);
}
