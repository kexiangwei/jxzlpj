package com.mycode.common.shenhe.service;

import com.mycode.system.menu.domain.Menu;
import com.mycode.system.role.domain.Role;
import com.mycode.common.shenhe.domain.ShenHe;
import com.mycode.common.shenhe.domain.ShenHeSet;
import com.mycode.common.shenhe.domain.ShenHeNode;

import java.util.List;
import java.util.Map;

/**
 * @auther kexiangwei
 * @date 2019/7/13
 */
public interface ShenHeService {

    Map<String, Object> getShenHeList(ShenHeSet shenHe);

    List<Menu> getMenuParentList(Long menuId);

    List<Menu> getMenuListForShenHe();

    boolean addShenhe(ShenHeSet shenHe);

    boolean updateShenheByCode(ShenHeSet shenHe);

    List<ShenHeNode> getShenHeNodeList(String shenheCode);

    List<Role> getRoleListByMenuId(Long menuId);

    String addShenHeNode(ShenHeNode node);

    boolean updateShenHeNodeByCode(ShenHeNode node);

    boolean deleteShenHeNodeByCode(String nodeCode);

    List<ShenHe> getShenheProcess(String relationCode);

    boolean batchDelete(String[] codeArr);

    String getActiveShenheCode(Integer menuId);
}
