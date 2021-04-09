package com.mycode.jxzlpj.jiaoxuepingjia.thpj.service;

import com.mycode.jxzlpj.jiaoxuepingjia.thpj.domian.Thpj;
import com.mycode.jxzlpj.jiaoxuepingjia.thpj.domian.ThpjQuery;

import java.util.Map;

public interface DfpjService {

    Map<String, Object> getPageList(ThpjQuery thpjQuery);

    Thpj detail(String pjCode);

    boolean insert(Thpj thpj, String jsonString);

    boolean update(Thpj thpj, String jsonString);

//    boolean delete(String pjCode);

}
