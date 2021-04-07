package com.mycode.shenheSet.service;

import com.mycode.shenheSet.domain.ShenHeSet;

import java.util.Map;

public interface ShenHeSetService {

    Map<String, Object> getShenHeSetPageList(ShenHeSet shenHeSet);

    boolean addShenheSet(ShenHeSet shenHeSet);

    boolean updateShenheSetByCode(ShenHeSet shenHeSet);

    boolean batchDeleteShenHeSet(String[] shenheCodes);

}
