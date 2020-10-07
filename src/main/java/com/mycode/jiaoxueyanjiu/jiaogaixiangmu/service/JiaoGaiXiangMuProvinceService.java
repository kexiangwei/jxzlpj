package com.mycode.jiaoxueyanjiu.jiaogaixiangmu.service;

import com.mycode.jiaoxueyanjiu.jiaogaixiangmu.domain.JiaoGaiXiangMuProvince;

import java.util.Map;

public interface JiaoGaiXiangMuProvinceService {

    Map<String, Object> getPageList(JiaoGaiXiangMuProvince jiaoGaiXiangMuProvince);

    boolean insert(JiaoGaiXiangMuProvince jiaoGaiXiangMuProvince);

    boolean update(JiaoGaiXiangMuProvince jiaoGaiXiangMuProvince);

    boolean delete(String code);
}
