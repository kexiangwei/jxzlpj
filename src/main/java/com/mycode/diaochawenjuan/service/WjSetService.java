package com.mycode.diaochawenjuan.service;

import com.mycode.diaochawenjuan.domain.WjSet;

import java.util.List;
import java.util.Map;

public interface WjSetService {

    Map<String, Object> getWjSetPageList(WjSet wjSet);

    List<WjSet> getWjSetInfo(String wjCode);

    List<Map<String, Object>> getWjInfo(String wjCode, String userId);

    boolean addWjInfo(WjSet wjSet, String jsonString);
}
