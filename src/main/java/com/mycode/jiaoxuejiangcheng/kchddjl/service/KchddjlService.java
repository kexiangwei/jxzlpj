package com.mycode.jiaoxuejiangcheng.kchddjl.service;

import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.jiaoxuejiangcheng.kchddjl.domian.Kchddjl;

import java.util.List;
import java.util.Map;

/**
 * 教学奖惩-课程获得的奖励
 */
public interface KchddjlService {

    Map<String, Object> getPageList(Kchddjl obj);

    boolean insert(Kchddjl obj);

    boolean update(Kchddjl obj);

    boolean delete(String objCode);

    boolean toSubimt(String activeShenheCode, List<Kchddjl> objList);

    boolean toShenhe(ShenHeItem item, List<Kchddjl> objList);
}
