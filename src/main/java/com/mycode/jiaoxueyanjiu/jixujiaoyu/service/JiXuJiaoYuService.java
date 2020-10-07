package com.mycode.jiaoxueyanjiu.jixujiaoyu.service;

import com.mycode.jiaoxueyanjiu.jixujiaoyu.domian.JiXuJiaoYu;

import java.util.Map;

/**
 * 教学研究-继续教育
 */
public interface JiXuJiaoYuService {

    Map<String, Object> getPageList(JiXuJiaoYu jiXuJiaoYu);

    boolean insert(JiXuJiaoYu jiXuJiaoYu);

    boolean update(JiXuJiaoYu jiXuJiaoYu);

    boolean delete(String code);

}
