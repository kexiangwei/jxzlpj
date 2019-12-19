package com.mycode.jiaoxueyanjiu.jiaoxuetuandui.service;

import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.jiaoxueyanjiu.jiaoxuetuandui.domain.JiaoXueTuanDui;
import com.mycode.jiaoxueyanjiu.jiaoxuetuandui.domain.PingShen;
import com.mycode.jiaoxueyanjiu.jiaoxuetuandui.domain.PingShenTemplate;

import java.util.List;
import java.util.Map;

/**
 * 教学研究-教学团队
 * @auther kexiangwei
 * @date 2019/11/13
 */
public interface JiaoXueTuanDuiService {

    Map<String, Object> getPageList(JiaoXueTuanDui jiaoXueTuanDui);

    boolean insert(JiaoXueTuanDui jiaoXueTuanDui);

    boolean update(JiaoXueTuanDui jiaoXueTuanDui);

    boolean delete(String code);

    boolean toSubimt(String activeShenheCode, List<JiaoXueTuanDui> jiaoXueTuanDuiList);

    boolean toShenhe(ShenHeItem item, List<JiaoXueTuanDui> jiaoXueTuanDuiList);

    List<PingShenTemplate> getPingShenTemplate();

    List<PingShen> getPingShenInfo(String relationCode, Integer batchNum, String pingshenType, String userId);

    boolean insertPingShenInfo(PingShen pingShen);

    List<Map<String, Object>> getMemberList(String relationCode);

    boolean insertMember(String relationCode, String userId, String userName);

    boolean deleteMember(String relationCode, String userId);
}
