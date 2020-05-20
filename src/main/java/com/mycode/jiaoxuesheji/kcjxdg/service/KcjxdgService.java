package com.mycode.jiaoxuesheji.kcjxdg.service;

import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.jiaoxuesheji.kcjxdg.domian.Kcjxdg;
import com.mycode.jiaoxueyanjiu.jiaocaijianshe.domian.JiaoCaiJianShe;

import java.util.List;
import java.util.Map;

/**
 * 教学设计-课程教学大纲
 */
public interface KcjxdgService {

    Map<String, Object> getPageList(Kcjxdg kcjxdg);

    boolean insert(Kcjxdg kcjxdg);

    boolean update(Kcjxdg kcjxdg);

    boolean delete(String code);

    boolean toSubimt(String activeShenheCode, List<Kcjxdg> kcjxdgList);

    boolean toShenhe(ShenHeItem item, List<Kcjxdg> kcjxdgList);
}
