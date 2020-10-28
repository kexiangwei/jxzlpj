package com.mycode.jiaoxuexiaoguo.sjfx.service;

import com.mycode.commonset.shenheSet.domain.ShenHeItem;
import com.mycode.jiaoxuexiaoguo.sjfx.domian.Sjfx;

import java.util.List;
import java.util.Map;

public interface SjfxService {

    Map<String, Object> getPageList(Sjfx sjfx);

    boolean insert(Sjfx sjfx);

    boolean update(Sjfx sjfx);

    boolean delete(String code);

    boolean toSubimt(String activeShenheCode, List<Sjfx> sjfxList);

    boolean toShenhe(ShenHeItem item, List<Sjfx> sjfxList);
}
