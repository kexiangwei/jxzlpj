package com.mycode.jxzlpj.jiaoxueyanjiu.jiaoxuetuandui.service;

import com.mycode.jxzlpj.jiaoxueyanjiu.jiaoxuetuandui.domain.JiaoXueTuanDuiProvince;

import java.util.Map;

public interface JiaoXueTuanDuiProvinceService {

    Map<String, Object> getPageList(JiaoXueTuanDuiProvince jiaoXueTuanDuiProvince);

    boolean insert(JiaoXueTuanDuiProvince jiaoXueTuanDuiProvince);

    boolean update(JiaoXueTuanDuiProvince jiaoXueTuanDuiProvince);

    boolean delete(String code);

}
