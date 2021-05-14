package com.mycode.common.shenheSet.service;

import com.mycode.common.shenheSet.domain.ShenHeObj;
import com.mycode.common.shenheSet.domain.ShenHeItem;
import com.mycode.common.shenheSet.domain.ShenHe;

import java.util.List;

public interface ShenHeService {

    List<ShenHe> getShenheProcess(String relationCode);

    String getActiveShenheCode(Integer menuId);

    boolean toSubimt(String activeShenheCode, List<ShenHeObj> objList);

    boolean toShenhe(ShenHeItem item, List<ShenHeObj> objList);
}
