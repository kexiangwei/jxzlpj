package com.mycode.common.shenhe.service;

import com.mycode.system.menu.domain.Menu;
import com.mycode.system.role.domain.Role;
import com.mycode.common.shenhe.domain.ShenHe;
import com.mycode.common.shenhe.domain.ShenHeSet;
import com.mycode.common.shenhe.domain.ShenHeNode;

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

    //根据relationCode 获取审核流程
    List<ShenHe> getShenheProcess(String relationCode);
    //根据业务模块编号menuId 获取当前处于激活状态的审核流程编号
    String getActiveShenheCode(Integer menuId);

}
