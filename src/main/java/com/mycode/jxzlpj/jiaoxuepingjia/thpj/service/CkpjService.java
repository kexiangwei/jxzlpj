package com.mycode.jxzlpj.jiaoxuepingjia.thpj.service;

import com.mycode.jxzlpj.jiaoxuepingjia.thpj.domian.Ckpj;

import java.util.List;
import java.util.Map;

/**
 * 教学评价-同行评教
 */
public interface CkpjService {

    Map<String, Object> getCkpjPageList(Ckpj ckpj);

    Map<String, Object> getPjInfo(String courseCode, String skjsCode);
}
