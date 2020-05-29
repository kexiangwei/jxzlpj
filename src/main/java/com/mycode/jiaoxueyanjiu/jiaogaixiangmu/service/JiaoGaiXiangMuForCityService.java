package com.mycode.jiaoxueyanjiu.jiaogaixiangmu.service;

import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.jiaoxueyanjiu.jiaogaixiangmu.domain.FundBudget;
import com.mycode.jiaoxueyanjiu.jiaogaixiangmu.domain.JiaoGaiXiangMu;
import com.mycode.jiaoxueyanjiu.jiaogaixiangmu.domain.JiaoGaiXiangMuForCity;
import com.mycode.jiaoxueyanjiu.jiaogaixiangmu.domain.Member;

import java.util.List;
import java.util.Map;

public interface JiaoGaiXiangMuForCityService {

    Map<String, Object> getPageList(JiaoGaiXiangMuForCity jiaoGaiXiangMuForCity);

    boolean insert(JiaoGaiXiangMuForCity jiaoGaiXiangMuForCity);

    boolean update(JiaoGaiXiangMuForCity jiaoGaiXiangMuForCity);

    boolean delete(String code);

    boolean toSubimt(String activeShenheCode, List<JiaoGaiXiangMuForCity> objList);

    boolean toShenhe(ShenHeItem item, List<JiaoGaiXiangMuForCity> objList);
}
