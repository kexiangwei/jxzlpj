package com.mycode.jiaoxuepingjia.thpj.service;

import com.mycode.jiaoxuepingjia.thpj.domian.Ckpj;
import com.mycode.jiaoxuepingjia.thpj.domian.Thpj;
import com.mycode.jiaoxuepingjia.thpj.domian.ThpjQuery;

import java.util.List;
import java.util.Map;

/**
 * 教学评价-同行评教
 */
public interface ThpjService {

    /*
    查看评教
     */
    Map<String, Object> getCkpjPageList(Ckpj ckpj);

    List<Map<String, Object>> getCkpjDetail(Ckpj ckpj);

    /*
    同行评教
     */
    Map<String, Object> getPageList(ThpjQuery thpjQuery);

    Thpj detail(String code);

    boolean insert(Thpj thpj, Map<String,Object> paramMap);

    boolean update(Thpj thpj, Map<String,Object> paramMap);

    /*boolean delete(String code);*/

    String getThpjTemplateCode(String code);

    List<Map<String, Object>> getThpjTargetList(String templateCode);

    /*
    同行评教-比较评价
     */
    Integer isFull(String userId);
}
