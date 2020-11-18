package com.mycode.jiaoxuepingjia.thpj.service;

import com.mycode.jiaoxuepingjia.pjset.domain.PjSetTemplate;
import com.mycode.jiaoxuepingjia.thpj.domian.Ckpj;
import com.mycode.jiaoxuepingjia.thpj.domian.Thpj;
import com.mycode.jiaoxuepingjia.thpj.domian.ThpjQuery;

import java.util.List;
import java.util.Map;

/**
 * 教学评价-同行评教
 */
public interface ThpjService {

    List<Map<String, Object>> getCkpjDetail(Ckpj ckpj);

    Map<String, Object> getCkpjPageList(Ckpj ckpj);

    Map<String, Object> getPageList(ThpjQuery thpjQuery);

    Thpj detail(String code);

    boolean insert(Thpj thpj, Map<String,Object> paramMap);

   /* boolean update(Thpj thpj);

    boolean delete(String code);*/


    String getThpjTemplateCode(String code);

    List<Map<String, Object>> getThpjTargetList(String templateCode);


    /*List<Map<String, Object>> getTeacherBar(String menuName, String userId);

    List<Map<String, Object>> getTeacherPie(String menuName, String userId);*/

    List<Map<String, Object>> getTableCols(String tableName);

    List<Map<String, Object>> getTableDatas(String viewName, String userId);

}
