package com.mycode.shaungchuangjiaoyu.xkzybs.service;

import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.shaungchuangjiaoyu.xkzybs.domian.Xkzybs;

import java.util.List;
import java.util.Map;

/**
 * 双创教育-学科专业比赛
 */
public interface XkzybsService {

    Map<String, Object> getPageList(Xkzybs obj);

    boolean insert(Xkzybs obj);

    boolean update(Xkzybs obj);

    boolean delete(String objCode);

    boolean toSubimt(String activeShenheCode, List<Xkzybs> objList);

    boolean toShenhe(ShenHeItem item, List<Xkzybs> objList);

}
