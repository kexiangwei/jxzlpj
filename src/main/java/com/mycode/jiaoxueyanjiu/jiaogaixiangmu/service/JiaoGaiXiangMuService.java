package com.mycode.jiaoxueyanjiu.jiaogaixiangmu.service;

import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.jiaoxueyanjiu.jiaogaixiangmu.domain.JiaoGaiXiangMu;

import java.util.List;
import java.util.Map;

/**
 * 教学研究-教改项目
 * @auther kexiangwei
 * @date 2019/11/13
 */
public interface JiaoGaiXiangMuService {

    Map<String, Object> getPageList(JiaoGaiXiangMu jiaoGaiXiangMu);

    boolean insert(JiaoGaiXiangMu jiaoGaiXiangMu);

    boolean update(JiaoGaiXiangMu jiaoGaiXiangMu);

    boolean delete(String code);

    boolean toSubimt(String activeShenheCode, List<JiaoGaiXiangMu> jiaoGaiXiangMuList);

    boolean toShenhe(ShenHeItem item, List<JiaoGaiXiangMu> jiaoGaiXiangMuList);
}
