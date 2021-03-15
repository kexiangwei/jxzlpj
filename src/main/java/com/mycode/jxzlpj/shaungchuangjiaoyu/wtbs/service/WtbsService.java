package com.mycode.jxzlpj.shaungchuangjiaoyu.wtbs.service;

import com.mycode.jxzlpj.shaungchuangjiaoyu.wtbs.domian.Wtbs;

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
