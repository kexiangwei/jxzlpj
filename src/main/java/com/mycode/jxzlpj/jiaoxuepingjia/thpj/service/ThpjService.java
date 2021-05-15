package com.mycode.jxzlpj.jiaoxuepingjia.thpj.service;

import com.mycode.jxzlpj.jiaoxuepingjia.thpj.domian.Thpj;

import java.util.List;
import java.util.Map;

/**
 * 教学评价-同行评教
 */
public interface ThpjService {


    Map<String, Object> getPageList(Thpj thpj);

    String getThpjTemplateCode(String pjCode);

    List<Map<String, Object>> getThpjTemplate(String templateCode);

    Thpj detail(String pjCode);

    boolean insert(Thpj thpj, String jsonString);

    boolean update(Thpj thpj, String jsonString);

    /*
    同行评教-比较评价
     */
    Integer isTopFull(String userId);

    boolean submit(String pjCode);

    Map<String, Object> ckpj(Thpj thpj);
}
