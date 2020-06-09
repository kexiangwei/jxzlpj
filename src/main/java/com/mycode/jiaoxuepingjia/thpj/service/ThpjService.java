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

    List<Map<String, Object>> getThpjTargetList();

    boolean insert(Thpj thpj);

    boolean update(Thpj thpj);

    boolean delete(String code);
}
