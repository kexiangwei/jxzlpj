package com.mycode.commonset.shenheSet.service;

import com.mycode.commonset.shenheSet.domain.ShenHeObj;
import com.mycode.commonset.shenheSet.domain.ShenHeItem;
import com.mycode.commonset.shenheSet.domain.ShenHe;
import com.mycode.commonset.shenheSet.domain.ShenHeSet;
import com.mycode.commonset.shenheSet.domain.ShenHeNode;

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
