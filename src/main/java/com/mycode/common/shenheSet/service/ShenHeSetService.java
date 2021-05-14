package com.mycode.common.shenheSet.service;

import com.mycode.common.shenheSet.domain.ShenHeSet;

import java.util.Map;

public interface ShenHeSetService {

    Map<String, Object> getShenHeSetPageList(ShenHeSet shenHeSet);

    boolean addShenheSet(ShenHeSet shenHeSet);

    boolean updateShenheSetByCode(ShenHeSet shenHeSet);

    boolean batchDeleteShenHeSet(String[] shenheCodes);

}
