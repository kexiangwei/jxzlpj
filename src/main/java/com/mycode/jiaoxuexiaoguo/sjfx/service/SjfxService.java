package com.mycode.jiaoxuexiaoguo.sjfx.service;

import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.jiaoxuexiaoguo.sjfx.domian.Sjfx;

import java.util.List;
import java.util.Map;

/**
 * 教学效果-试卷分析
 * @auther kexiangwei
 * @date 2019/11/13
 */
public interface SjfxService {

    Map<String, Object> getPageList(Sjfx sjfx);

    boolean insert(Sjfx sjfx);

    boolean batchImport(List<Map<String, Object>> mapList, String userId, String userName);

    boolean update(Sjfx sjfx);

    boolean delete(String code);

    boolean toSubimt(String activeShenheCode, List<Sjfx> sjfxList);

    boolean toShenhe(ShenHeItem item, List<Sjfx> sjfxList);
}
