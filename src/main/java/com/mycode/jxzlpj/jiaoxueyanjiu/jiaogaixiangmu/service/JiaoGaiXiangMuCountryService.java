package com.mycode.jxzlpj.jiaoxueyanjiu.jiaogaixiangmu.service;

import com.mycode.jxzlpj.jiaoxueyanjiu.jiaogaixiangmu.domain.JiaoGaiXiangMuCountry;

import java.util.Map;

public interface JiaoGaiXiangMuCountryService {

    Map<String, Object> getPageList(JiaoGaiXiangMuCountry jiaoGaiXiangMuCountry);

    boolean insert(JiaoGaiXiangMuCountry jiaoGaiXiangMuCountry);

    boolean update(JiaoGaiXiangMuCountry jiaoGaiXiangMuCountry);

    boolean delete(String code);
}
