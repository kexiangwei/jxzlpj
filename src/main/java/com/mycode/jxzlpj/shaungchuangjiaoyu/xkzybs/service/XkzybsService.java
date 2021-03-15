package com.mycode.jxzlpj.shaungchuangjiaoyu.xkzybs.service;

import com.mycode.jxzlpj.shaungchuangjiaoyu.xkzybs.domian.Xkzybs;

import java.util.Map;

/**
 * 双创教育-学科专业比赛
 */
public interface XkzybsService {

    Map<String, Object> getPageList(Xkzybs xkzybs);

    boolean insert(Xkzybs xkzybs);

    boolean update(Xkzybs xkzybs);

    boolean delete(String code);

}
