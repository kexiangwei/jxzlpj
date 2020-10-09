package com.mycode.shaungchuangjiaoyu.wtbs.service;

import com.mycode.shaungchuangjiaoyu.wtbs.domian.Wtbs;

import java.util.Map;

/**
 * 双创教育-文、体类比赛
 */
public interface WtbsService {

    Map<String, Object> getPageList(Wtbs wtbs);

    boolean insert(Wtbs wtbs);

    boolean update(Wtbs wtbs);

    boolean delete(String code);
}
