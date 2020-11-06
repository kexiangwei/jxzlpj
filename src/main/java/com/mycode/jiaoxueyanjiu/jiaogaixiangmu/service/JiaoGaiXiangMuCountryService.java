package com.mycode.jiaoxueyanjiu.jiaogaixiangmu.service;

import com.mycode.jiaoxueyanjiu.jiaogaixiangmu.domain.JiaoGaiXiangMuCountry;

import java.util.Map;

public interface JiaoGaiXiangMuCountryService {

    Map<String, Object> getPageList(JiaoGaiXiangMuCountry jiaoGaiXiangMuCountry);

    boolean insert(JiaoGaiXiangMuCountry jiaoGaiXiangMuCountry);

    boolean update(JiaoGaiXiangMuCountry jiaoGaiXiangMuCountry);

    boolean delete(String code);
}
