package com.mycode.diaochawenjuan.service;

import com.mycode.diaochawenjuan.domian.WjQuestion;
import com.mycode.diaochawenjuan.domian.WjSet;

import java.util.Map;

/**
 * 调查问卷
 * @auther kexiangwei
 * @date 2019/10/8
 */
public interface WjService {

    Map<String, Object> getWjSetPageList(WjSet wjSet);

    Map<String, Object> getWjQuestionPageList(WjQuestion wjQuestion);

}
