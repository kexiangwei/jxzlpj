package com.mycode.shaungchuangjiaoyu.wtbs.service;

import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.shaungchuangjiaoyu.Stu;
import com.mycode.shaungchuangjiaoyu.wtbs.domian.Wtbs;

import java.util.List;
import java.util.Map;

/**
 * 双创教育-文、体类比赛
 */
public interface WtbsService {

    Map<String, Object> getPageList(Wtbs obj);

    boolean insert(Wtbs obj);

    boolean update(Wtbs obj);

    boolean delete(String objCode);

    boolean toSubimt(String activeShenheCode, List<Wtbs> objList);

    boolean toShenhe(ShenHeItem item, List<Wtbs> objList);

    List<Stu> getStuInfo(String relationCode);

    boolean addStuInfo(Stu stu);

    boolean delStuInfo(String relationCode, String stuCode);
}
