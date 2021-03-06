package com.mycode.jiaoxueyanjiu.jiaoxuetuandui.service;

import com.mycode.common.shenheSet.domain.ShenHeItem;
import com.mycode.jiaoxueyanjiu.jiaoxuetuandui.domain.JiaoXueTuanDui;
import com.mycode.jiaoxueyanjiu.jiaoxuetuandui.domain.PingShen;
import com.mycode.jiaoxueyanjiu.jiaoxuetuandui.domain.PingShenTemplate;

import java.util.List;
import java.util.Map;

/**
 * 教学研究-教学团队
 */
public interface JiaoXueTuanDuiService {

    Map<String, Object> getPageList(JiaoXueTuanDui jiaoXueTuanDui);

    boolean insert(JiaoXueTuanDui jiaoXueTuanDui);

    boolean update(JiaoXueTuanDui jiaoXueTuanDui);

    boolean delete(String code);

    boolean toSubimt(String activeShenheCode, List<JiaoXueTuanDui> jiaoXueTuanDuiList);

    boolean toShenhe(ShenHeItem item, List<JiaoXueTuanDui> jiaoXueTuanDuiList);

    List<PingShenTemplate> getPingShenTemplate();

    List<PingShen> getPingShenInfo(String relationCode, Integer batchNum, String userId);

    boolean insertPingShenInfo(PingShen pingShen);

}
