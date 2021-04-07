package com.mycode.shenheSet.service;

import com.mycode.shenheSet.domain.*;

import java.util.List;

public interface ShenHeNodeService {

    List<ShenHeNode> getShenHeNodeList(String shenheCode);

    String addShenHeNode(ShenHeNode shenHeNode);

    boolean updateShenHeNodeByCode(ShenHeNode shenHeNode);

    boolean deleteShenHeNodeByCode(String nodeCode);

}
