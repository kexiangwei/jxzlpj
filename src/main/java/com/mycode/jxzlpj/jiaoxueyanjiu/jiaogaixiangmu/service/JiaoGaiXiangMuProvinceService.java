package com.mycode.jxzlpj.jiaoxueyanjiu.jiaogaixiangmu.service;

import com.mycode.jxzlpj.jiaoxueyanjiu.jiaogaixiangmu.domain.JiaoGaiXiangMuProvince;

import java.util.Map;

public interface JiaoGaiXiangMuProvinceService {

    Map<String, Object> getPageList(JiaoGaiXiangMuProvince jiaoGaiXiangMuProvince);

    boolean insert(JiaoGaiXiangMuProvince jiaoGaiXiangMuProvince);

    boolean update(JiaoGaiXiangMuProvince jiaoGaiXiangMuProvince);

    boolean delete(String code);
}
