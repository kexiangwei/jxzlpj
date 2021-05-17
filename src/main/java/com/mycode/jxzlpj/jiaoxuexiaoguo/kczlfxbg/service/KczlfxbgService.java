package com.mycode.jxzlpj.jiaoxuexiaoguo.kczlfxbg.service;

import com.mycode.jxzlpj.jiaoxuexiaoguo.kczlfxbg.doman.Kczlfxbg;

import java.util.List;
import java.util.Map;

public interface KczlfxbgService {

    Map<String, Object> getPageList(Kczlfxbg kczlfxbg);

    Map<String,Object> getKczlfxbg(String code);

    boolean insert(Kczlfxbg kczlfxbg);

    boolean insert2(Map<String, Object> params);

    boolean update(Kczlfxbg kczlfxbg);

    boolean update2(Map<String, Object> params);

    boolean submit(String code);
}
