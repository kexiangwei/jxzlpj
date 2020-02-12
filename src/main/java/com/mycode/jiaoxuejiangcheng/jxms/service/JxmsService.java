package com.mycode.jiaoxuejiangcheng.jxms.service;

import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.jiaoxuejiangcheng.jxms.domian.Jxms;

import java.util.List;
import java.util.Map;

/**
 * 教学奖惩-教学名师
 */
public interface JxmsService {

    Map<String, Object> getPageList(Jxms obj);

    boolean insert(Jxms obj);

    boolean update(Jxms obj);

    boolean delete(String objCode);

    boolean toSubimt(String activeShenheCode, List<Jxms> objList);

    boolean toShenhe(ShenHeItem item, List<Jxms> objList);
}
