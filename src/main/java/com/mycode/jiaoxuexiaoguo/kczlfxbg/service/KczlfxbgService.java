package com.mycode.jiaoxuexiaoguo.kczlfxbg.service;

import com.mycode.jiaoxuexiaoguo.kczlfxbg.doman.Kczlfxbg;

import java.util.Map;

public interface KczlfxbgService {

    Map<String, Object> getPageList(Kczlfxbg kczlfxbg);

    boolean insert(Kczlfxbg kczlfxbg);

    boolean update(Kczlfxbg kczlfxbg);

    boolean delete(String code);

}
