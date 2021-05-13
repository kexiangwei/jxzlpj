package com.mycode.jxzlpj.jiaoxuepingjia.thpj.service;

import com.mycode.jxzlpj.jiaoxuepingjia.thpj.domian.Thpj;

public interface DfpjService {


    Thpj detail(String pjCode);

    boolean insert(Thpj thpj, String jsonString);

    boolean update(Thpj thpj, String jsonString);

//    boolean delete(String pjCode);

}
