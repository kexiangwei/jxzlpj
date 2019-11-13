package com.mycode.jiaoxueyanjiu.jiaocaijianshe.service;

import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.jiaoxueyanjiu.jiaocaijianshe.domian.JiaoCaiJianShe;
import com.mycode.jiaoxueyanjiu.jiaogailunwen.domian.JiaoGaiLunWen;

import java.util.List;
import java.util.Map;

/**
 * 教学研究-教材建设
 * @auther kexiangwei
 * @date 2019/11/13
 */
public interface JiaoCaiJianSheService {

    Map<String, Object> getPageList(JiaoCaiJianShe jiaoCaiJianShe);

    boolean insert(JiaoCaiJianShe jiaoCaiJianShe);

    boolean update(JiaoCaiJianShe jiaoCaiJianShe);

    boolean delete(String code);

    boolean toSubimt(String activeShenheCode, List<JiaoCaiJianShe> jiaoCaiJianSheList);

    boolean toShenhe(ShenHeItem item, List<JiaoCaiJianShe> jiaoCaiJianSheList);
}
