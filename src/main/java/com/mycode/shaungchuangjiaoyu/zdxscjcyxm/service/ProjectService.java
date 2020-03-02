package com.mycode.shaungchuangjiaoyu.zdxscjcyxm.service;

import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.shaungchuangjiaoyu.zdxscjcyxm.domian.Project;

import java.util.List;
import java.util.Map;

/**
 * 双创教育-指导学生参加创业项目
 */
public interface ProjectService {

    Map<String, Object> getPageList(Project obj);

    boolean insert(Project obj);

    boolean update(Project obj);

    boolean delete(String objCode);

    boolean toSubimt(String activeShenheCode, List<Project> objList);

    boolean toShenhe(ShenHeItem item, List<Project> objList);

}
