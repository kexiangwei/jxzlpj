package com.mycode.jxzlpj.jiaoxuexiaoguo.kczlfxbg.service;

import com.mycode.jxzlpj.jiaoxuexiaoguo.kczlfxbg.doman.Kczlfxbg;

import java.util.Map;

public interface KczlfxbgService {

    Map<String, Object> getPageList(Kczlfxbg kczlfxbg);

    Kczlfxbg getKczlfxbg(String code);

    boolean insert(Kczlfxbg kczlfxbg);

    boolean update(Kczlfxbg kczlfxbg);

    boolean delete(String code);
}
