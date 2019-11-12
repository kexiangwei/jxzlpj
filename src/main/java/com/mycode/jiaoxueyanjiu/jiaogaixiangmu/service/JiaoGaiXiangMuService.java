package com.mycode.jiaoxueyanjiu.jiaogaixiangmu.service;

import com.mycode.jiaoxueyanjiu.jiaogaixiangmu.domain.JiaoGaiXiangMu;

import java.util.Map;

/**
 * @auther kexiangwei
 * @date 2019/8/19
 */
public interface JiaoGaiXiangMuService {

    Map<String, Object> getList(JiaoGaiXiangMu jiaoGaiXiangMu);

    boolean insert(JiaoGaiXiangMu jiaoGaiXiangMu);
}
