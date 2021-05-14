package com.mycode.common.common.service;

import java.util.List;
import java.util.Map;

public interface CommonService {

    List<Map<String, Object>> getXyList(String dataType);

    List<Map<String, Object>> getZyList(String xyCode);

}
