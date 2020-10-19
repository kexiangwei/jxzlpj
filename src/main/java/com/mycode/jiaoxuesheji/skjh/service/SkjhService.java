package com.mycode.jiaoxuesheji.skjh.service;

import com.mycode.commonset.shenheSet.domain.ShenHeItem;
import com.mycode.jiaoxuesheji.skjh.domian.Skjh;
import com.mycode.jiaoxuesheji.skjh.domian.SkjhItem;

import java.util.List;
import java.util.Map;

/**
 * 教学设计-授课计划
 */
public interface SkjhService {

    Map<String, Object> getPageList(Skjh skjh);

    List<SkjhItem> getSkjhItemList(String relationCode);

    boolean insert(SkjhItem skjhItem);

    boolean update(Skjh skjh);

    boolean delete(String code);

    boolean toSubimt(String activeShenheCode, List<Skjh> skjhList);

    boolean toShenhe(ShenHeItem item, List<Skjh> skjhList);
}
