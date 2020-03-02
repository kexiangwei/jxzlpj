package com.mycode.shaungchuangjiaoyu.bkssqzl.service;

import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.shaungchuangjiaoyu.Stu;
import com.mycode.shaungchuangjiaoyu.bkssqzl.domian.Bkssqzl;

import java.util.List;
import java.util.Map;

/**
 * 双创教育-本科生申请专利
 */
public interface BkssqzlService {

    Map<String, Object> getPageList(Bkssqzl obj);

    boolean insert(Bkssqzl obj);

    boolean update(Bkssqzl obj);

    boolean delete(String objCode);

    boolean toSubimt(String activeShenheCode, List<Bkssqzl> objList);

    boolean toShenhe(ShenHeItem item, List<Bkssqzl> objList);

}
