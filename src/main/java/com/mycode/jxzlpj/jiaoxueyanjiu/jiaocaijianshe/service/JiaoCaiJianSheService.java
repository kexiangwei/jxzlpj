package com.mycode.jxzlpj.jiaoxueyanjiu.jiaocaijianshe.service;

import com.mycode.jxzlpj.jiaoxueyanjiu.jiaocaijianshe.domian.JiaoCaiJianShe;

import java.util.Map;

/**
 * 教学研究-教材建设
 */
public interface JiaoCaiJianSheService {

    Map<String, Object> getPageList(JiaoCaiJianShe jiaoCaiJianShe);

    boolean insert(JiaoCaiJianShe jiaoCaiJianShe);

    boolean update(JiaoCaiJianShe jiaoCaiJianShe);

    boolean delete(String code);
}
