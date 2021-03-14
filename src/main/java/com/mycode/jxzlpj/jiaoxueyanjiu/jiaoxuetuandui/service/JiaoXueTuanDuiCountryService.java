package com.mycode.jxzlpj.jiaoxueyanjiu.jiaoxuetuandui.service;

import com.mycode.jxzlpj.jiaoxueyanjiu.jiaoxuetuandui.domain.JiaoXueTuanDuiCountry;

import java.util.Map;

/**
 * 教学研究-教学团队-国家级团队
 */
public interface JiaoXueTuanDuiCountryService {

    Map<String, Object> getPageList(JiaoXueTuanDuiCountry jiaoXueTuanDuiCountry);

    boolean insert(JiaoXueTuanDuiCountry jiaoXueTuanDuiCountry);

    boolean update(JiaoXueTuanDuiCountry jiaoXueTuanDuiCountry);

    boolean delete(String code);

}
