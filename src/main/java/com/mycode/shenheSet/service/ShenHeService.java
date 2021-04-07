package com.mycode.shenheSet.service;

import com.mycode.shenheSet.domain.ShenHeObj;
import com.mycode.shenheSet.domain.ShenHeItem;
import com.mycode.shenheSet.domain.ShenHe;

import java.util.List;

public interface ShenHeService {

    List<ShenHe> getShenheProcess(String relationCode);

    String getActiveShenheCode(Integer menuId);

    boolean toSubimt(String activeShenheCode, List<ShenHeObj> objList);

    boolean toShenhe(ShenHeItem item, List<ShenHeObj> objList);
}
