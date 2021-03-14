package com.mycode.jxzlpj.jiaoxuexiaoguo.sybg.service;

import com.mycode.jxzlpj.jiaoxuexiaoguo.sybg.domian.Sybg;

import java.util.Map;

public interface SybgService {

    Map<String, Object> getPageList(Sybg sybg);

    boolean insert(Sybg sybg);

    boolean update(Sybg sybg);

    boolean delete(String code);

}
