package com.mycode.common.common;

import java.util.List;
import java.util.Map;

public interface CommonService {

    List<Map<String, Object>> getXyList();

    List<Map<String, Object>> getZyList(String xyCode);

    List<Map<String, Object>> getTableCols(String tableName);

    List<Map<String, Object>> getTableDatas(String viewName, String userId);

}
