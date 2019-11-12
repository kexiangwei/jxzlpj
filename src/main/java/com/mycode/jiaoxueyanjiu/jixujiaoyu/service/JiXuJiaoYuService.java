package com.mycode.jiaoxueyanjiu.jixujiaoyu.service;

import com.mycode.jiaoxueyanjiu.jixujiaoyu.domian.JiXuJiaoYu;
import com.mycode.common.shenhe.domain.ShenHeItem;

import java.util.List;
import java.util.Map;

/**
 * 教学研究-继续教育
 * @auther kexiangwei
 * @date 2019/7/13
 */
public interface JiXuJiaoYuService {

    Map<String, Object> getPageList(JiXuJiaoYu jiXuJiaoYu);

    boolean insert(JiXuJiaoYu jiXuJiaoYu);

    boolean update(JiXuJiaoYu jiXuJiaoYu);

    boolean delete(String code);

    boolean toSubimt(String activeShenheCode, List<JiXuJiaoYu> jiXuJiaoYuList);

    boolean toShenhe(ShenHeItem item, List<JiXuJiaoYu> jiXuJiaoYuList);
}
