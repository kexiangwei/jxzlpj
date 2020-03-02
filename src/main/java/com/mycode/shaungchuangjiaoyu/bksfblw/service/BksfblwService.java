package com.mycode.shaungchuangjiaoyu.bksfblw.service;

import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.shaungchuangjiaoyu.Stu;
import com.mycode.shaungchuangjiaoyu.bksfblw.domian.Bksfblw;

import java.util.List;
import java.util.Map;

/**
 * 双创教育-本科生发表论文
 */
public interface BksfblwService {

    Map<String, Object> getPageList(Bksfblw obj);

    boolean insert(Bksfblw obj);

    boolean update(Bksfblw obj);

    boolean delete(String objCode);

    boolean toSubimt(String activeShenheCode, List<Bksfblw> objList);

    boolean toShenhe(ShenHeItem item, List<Bksfblw> objList);

}
