package com.mycode.jiaoxueyanjiu.jiaogailunwen.service;

import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.jiaoxueyanjiu.jiaogailunwen.domian.JiaoGaiLunWen;

import java.util.List;
import java.util.Map;

/**
 * 教学研究-教改论文
 * @auther kexiangwei
 * @date 2019/7/13
 */
public interface JiaoGaiLunWenService {

    Map<String, Object> getPageList(JiaoGaiLunWen jiaoGaiLunWen);

    JiaoGaiLunWen get(JiaoGaiLunWen jiaoGaiLunWen);

    boolean insert(JiaoGaiLunWen jiaoGaiLunWen);

    boolean update(JiaoGaiLunWen jiaoGaiLunWen);

    boolean delete(String code);

    boolean toSubimt(String activeShenheCode, List<JiaoGaiLunWen> jiaoGaiLunWenList);

    boolean toShenhe(ShenHeItem item, List<JiaoGaiLunWen> jiaoGaiLunWenList);
}
