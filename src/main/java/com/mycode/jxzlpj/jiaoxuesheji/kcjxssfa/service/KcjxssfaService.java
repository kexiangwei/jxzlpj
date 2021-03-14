package com.mycode.jxzlpj.jiaoxuesheji.kcjxssfa.service;

import com.mycode.jxzlpj.jiaoxuesheji.kcjxssfa.domian.Kcjxssfa;
import com.mycode.jxzlpj.jiaoxuesheji.kcjxssfa.domian.KcjxssfaItem;

import java.util.List;
import java.util.Map;

/**
 * 教学设计-课程教学实施方案
 */
public interface KcjxssfaService {

    Map<String, Object> getPageList(Kcjxssfa kcjxssfa);

    List<KcjxssfaItem> getItemListByRelationCode(String relationCode);

    boolean insert(KcjxssfaItem item);

    /*boolean update(Kcjxssfa kcjxssfa);

    boolean delete(String code);*/
}
