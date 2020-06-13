package com.mycode.jiaoxuepingjia.thpj.service;

import com.mycode.jiaoxuepingjia.thpj.domian.Thpj;
import com.mycode.jiaoxuepingjia.thpj.domian.ThpjQuery;

import java.util.List;
import java.util.Map;

/**
 * 教学评价-同行评教
 */
public interface ThpjService {

    Map<String, Object> getPageList(ThpjQuery thpjQuery);

    boolean insert(Thpj thpj);

    boolean update(Thpj thpj);

    boolean delete(String code);

    List<Map<String, Object>> getThpjTargetList();

    List<Map<String, Object>> getTeacherBar(String menuName, String userId);

    List<Map<String, Object>> getTeacherPie(String menuName, String userId);

    List<Map<String, Object>> getTeacherTab(String menuName, String userId);

    List<Map<String, Object>> getTeacherTabData(String menuName, String userId);

}
