package com.mycode.shenhe.service;

import com.mycode.shenhe.domain.ShenHeObj;
import com.mycode.shenhe.domain.ShenHeItem;
import com.mycode.shenhe.domain.ShenHe;
import com.mycode.shenhe.domain.ShenHeSet;
import com.mycode.shenhe.domain.ShenHeNode;

import java.util.List;
import java.util.Map;

public interface ShenHeService {

    //ShenHeSet
    Map<String, Object> getShenHeSetPageList(ShenHeSet shenHeSet);

    boolean addShenheSet(ShenHeSet shenHeSet);

    boolean updateShenheSetByCode(ShenHeSet shenHeSet);

    boolean batchDeleteShenHeSet(String[] codeArr);

    //ShenHeNode
    List<ShenHeNode> getShenHeNodeList(String shenheCode);

    String addShenHeNode(ShenHeNode shenHeNode);

    boolean updateShenHeNodeByCode(ShenHeNode shenHeNode);

    boolean deleteShenHeNodeByCode(String nodeCode);

    //ShenHe
    List<ShenHe> getShenheProcess(String relationCode);

    String getActiveShenheCode(Integer menuId);

    boolean toSubimt(String activeShenheCode, List<ShenHeObj> objList);

    boolean toShenhe(ShenHeItem item, List<ShenHeObj> objList);
}
