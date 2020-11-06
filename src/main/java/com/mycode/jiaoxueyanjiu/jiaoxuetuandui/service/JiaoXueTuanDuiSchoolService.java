package com.mycode.jiaoxueyanjiu.jiaoxuetuandui.service;

import com.mycode.jiaoxueyanjiu.jiaoxuetuandui.domain.JiaoXueTuanDuiSchool;

import java.util.Map;

public interface JiaoXueTuanDuiSchoolService {

    Map<String, Object> getPageList(JiaoXueTuanDuiSchool jiaoXueTuanDuiSchool);

    boolean insert(JiaoXueTuanDuiSchool jiaoXueTuanDuiSchool);

    boolean update(JiaoXueTuanDuiSchool jiaoXueTuanDuiSchool);

    boolean delete(String code);

}
