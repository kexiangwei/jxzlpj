package com.mycode.jxzlpj.jiaoxuepingjia.xspj.service;

import com.mycode.jxzlpj.jiaoxuepingjia.xspj.domain.BjpjParams;
import com.mycode.jxzlpj.jiaoxuepingjia.xspj.domain.Xspj;

import java.util.List;
import java.util.Map;

/**
 * 教学评价-学生评教
 */
public interface XspjService {

    Map<String, Object> getPageList(Xspj xspj);

    boolean insert(Xspj xspj, String jsonString);

    Map<String, Object> getPjInfo(String xn, String xq, String courseCode, String teacherCode);

    List<Map<String,Object>> getPjInfoTransferData(String userId);

    boolean insertBjpj(BjpjParams params);

    boolean insertBjpjSuggest(String relationCode, String courseCode, String suggest);

    String selectBjpjSuggest(String relationCode, String courseCode);

    Map<String, Object> getBjpjPageList(Xspj xspj);

    Map<String, Object> getBjpjPjInfo(String courseCode);
}
