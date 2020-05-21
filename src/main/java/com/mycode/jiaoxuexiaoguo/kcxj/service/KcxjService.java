package com.mycode.jiaoxuexiaoguo.kcxj.service;

import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.jiaoxuexiaoguo.kcxj.domian.Kcxj;

import java.util.List;
import java.util.Map;

public interface KcxjService {

    Map<String, Object> getPageList(Kcxj kcxj);

    boolean insert(Kcxj kcxj);

    boolean update(Kcxj kcxj);

    boolean delete(String code);

    boolean toSubimt(String activeShenheCode, List<Kcxj> kcxjList);

    boolean toShenhe(ShenHeItem item, List<Kcxj> kcxjList);
}
