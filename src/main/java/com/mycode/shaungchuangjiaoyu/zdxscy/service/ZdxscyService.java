package com.mycode.shaungchuangjiaoyu.zdxscy.service;

import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.shaungchuangjiaoyu.zdxscy.domian.Zdxscy;

import java.util.List;
import java.util.Map;

/**
 * 双创教育-指导学生创业
 */
public interface ZdxscyService {

    Map<String, Object> getPageList(Zdxscy obj);

    boolean insert(Zdxscy obj);

    boolean update(Zdxscy obj);

    boolean delete(String objCode);

    boolean toSubimt(String activeShenheCode, List<Zdxscy> objList);

    boolean toShenhe(ShenHeItem item, List<Zdxscy> objList);

}
