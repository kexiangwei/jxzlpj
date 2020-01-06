package com.mycode.jiaoxuepingjia.thpj.service;

import com.mycode.jiaoxuepingjia.thpj.domian.Thpj;

import java.util.Map;

/**
 * 教学评价-同行评教
 * @auther kexiangwei
 * @date 2019/11/13
 */
public interface ThpjService {

    Map<String, Object> getPageList(Thpj thpj);

    boolean insert(Thpj thpj);

    boolean update(Thpj thpj);

    boolean delete(String code);
}
