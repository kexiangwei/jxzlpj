package com.mycode.jiaoxuesheji.skjh.service;

import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.jiaoxuesheji.skjh.domian.Skjh;

import java.util.List;
import java.util.Map;

/**
 * 教学设计-授课计划
 */
public interface SkjhService {

    Map<String, Object> getPageList(Skjh skjh);

    boolean insert(Skjh skjh);

    boolean update(Skjh skjh);

    boolean delete(String code);

    boolean toSubimt(String activeShenheCode, List<Skjh> skjhList);

    boolean toShenhe(ShenHeItem item, List<Skjh> skjhList);
}
