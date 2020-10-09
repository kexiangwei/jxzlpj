package com.mycode.jiaoxueyanjiu.jiaogailunwen.service;

import com.mycode.jiaoxueyanjiu.jiaogailunwen.domian.JiaoGaiLunWen;

import java.util.Map;

/**
 * 教学研究-教改论文
 */
public interface JiaoGaiLunWenService {

    Map<String, Object> getPageList(JiaoGaiLunWen jiaoGaiLunWen);

    boolean insert(JiaoGaiLunWen jiaoGaiLunWen);

    boolean update(JiaoGaiLunWen jiaoGaiLunWen);

    boolean delete(String code);
}
