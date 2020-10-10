package com.mycode.jiaoxuejiangcheng.jxjbgbs.service;

import com.mycode.commonset.shenheSet.domain.ShenHeItem;
import com.mycode.jiaoxuejiangcheng.jxjbgbs.domian.Jxjbgbs;

import java.util.List;
import java.util.Map;

/**
 * 教学奖惩-教学基本功比赛
 */
public interface JxjbgbsService {

    Map<String, Object> getPageList(Jxjbgbs obj);

    boolean insert(Jxjbgbs obj);

    boolean update(Jxjbgbs obj);

    boolean delete(String objCode);

    boolean toSubimt(String activeShenheCode, List<Jxjbgbs> objList);

    boolean toShenhe(ShenHeItem item, List<Jxjbgbs> objList);
}
