package com.mycode.common.common.service;

import java.util.List;
import java.util.Map;

public interface CommonService {

    Map<String, Integer> getAuthority(String menuId, String userId);

    List<Map<String, Object>> getCollege(String stuCode);

    List<Map<String, Object>> getMajor(String collegeCode);
}
