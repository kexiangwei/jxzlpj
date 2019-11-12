package com.mycode.diaochawenjuan.service;

import com.mycode.diaochawenjuan.domian.Question;
import com.mycode.diaochawenjuan.domian.WjSet;

import java.util.List;
import java.util.Map;

/**
 * @auther kexiangwei
 * @date 2019/10/8
 */
public interface WjSetService {

    Map<String, Object> getWjSetPageList(WjSet wjSet);

    Map<String, Object> getQuestionPageList(Question question);

    List<Map<String, Object>> getOption(String qCode);
}
