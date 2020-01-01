package com.mycode.jiaoxuesheji.jiaoan.service;

import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.jiaoxuesheji.jiaoan.domian.JiaoAn;

import java.util.List;
import java.util.Map;

/**
 * 教学设计-教案
 * @auther kexiangwei
 * @date 2019/11/13
 */
public interface JiaoAnService {

    Map<String, Object> getPageList(JiaoAn jiaoAn);

    boolean insert(JiaoAn jiaoAn);

    boolean update(JiaoAn jiaoAn);

    boolean delete(String code);

    boolean toSubimt(String activeShenheCode, List<JiaoAn> jiaoAnList);

    boolean toShenhe(ShenHeItem item, List<JiaoAn> jiaoAnList);
}
