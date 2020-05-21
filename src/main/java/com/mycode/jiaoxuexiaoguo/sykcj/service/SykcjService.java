package com.mycode.jiaoxuexiaoguo.sykcj.service;

import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.jiaoxuexiaoguo.sykcj.domian.Sykcj;

import java.util.List;
import java.util.Map;

public interface SykcjService {

    Map<String, Object> getPageList(Sykcj sykcj);

    boolean insert(Sykcj sykcj);

    boolean update(Sykcj sykcj);

    boolean delete(String code);

    boolean toSubimt(String activeShenheCode, List<Sykcj> sykcjList);

    boolean toShenhe(ShenHeItem item, List<Sykcj> sykcjList);
}
