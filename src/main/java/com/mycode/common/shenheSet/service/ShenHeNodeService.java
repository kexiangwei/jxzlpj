package com.mycode.common.shenheSet.service;

import com.mycode.common.shenheSet.domain.*;

import java.util.List;

public interface ShenHeNodeService {

    List<ShenHeNode> getShenHeNodeList(String shenheCode);

    String addShenHeNode(ShenHeNode shenHeNode);

    boolean updateShenHeNodeByCode(ShenHeNode shenHeNode);

    boolean deleteShenHeNodeByCode(String nodeCode);

}
