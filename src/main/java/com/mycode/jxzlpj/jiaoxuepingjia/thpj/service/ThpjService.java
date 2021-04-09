package com.mycode.jxzlpj.jiaoxuepingjia.thpj.service;

import java.util.List;
import java.util.Map;

/**
 * 教学评价-同行评教
 */
public interface ThpjService {

    String getThpjTemplateCode(String pjCode);

    List<Map<String, Object>> getThpjTargetList(String templateCode);

    /*
    同行评教-比较评价
     */
    Integer isTopFull(String userId);

    boolean submit(String code);
}
