package com.mycode.common.common.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CommonService {

    List<Map<String, Object>> getXyList(String dataType);

    List<Map<String, Object>> getZyList(String xyCode);

    Set<String> getCourseAttrOptions();

    Set<String> getUserTitleOptions();
}
