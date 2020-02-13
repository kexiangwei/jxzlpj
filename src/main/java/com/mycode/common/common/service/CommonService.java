package com.mycode.common.common.service;

import java.util.List;
import java.util.Map;

/**
 * @auther kexiangwei
 * @date 2019/7/13
 */
public interface CommonService {

    Map<String, Integer> getAuthority(String menuId, String userId);

    List<Map<String, Object>> getCollege();

    List<Map<String, Object>> getMajor(String collegeCode);
}
