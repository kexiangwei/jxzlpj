package com.mycode.jiaoxuepingjia.xspj.service;

import com.mycode.jiaoxuepingjia.xspj.domain.Xspj;

import java.util.List;
import java.util.Map;

/**
 * 教学评价-学生评教
 */
public interface XspjService {

    Map<String, Object> getPageList(Xspj xspj);

    boolean insert(Xspj xspj, String templateCode, Map<String,Object> paramMap);

    Map<String, Object> getPjInfo(String courseCode);
}
