package com.mycode.jiaoxueyanjiu.jiaogaixiangmu.service;

import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.jiaoxueyanjiu.jiaogaixiangmu.domain.FundBudget;
import com.mycode.jiaoxueyanjiu.jiaogaixiangmu.domain.JiaoGaiXiangMu;
import com.mycode.jiaoxueyanjiu.jiaogaixiangmu.domain.Member;
import com.mycode.jiaoxueyanjiu.jiaogaixiangmu.domain.ZjshItem;

import java.util.List;
import java.util.Map;

public interface JiaoGaiXiangMuService {

    Map<String, Object> getPageList(JiaoGaiXiangMu jiaoGaiXiangMu);

    boolean insert(JiaoGaiXiangMu jiaoGaiXiangMu);

    boolean update(JiaoGaiXiangMu jiaoGaiXiangMu);

    boolean delete(String code);

    boolean toSubimt(String activeShenheCode, List<JiaoGaiXiangMu> jiaoGaiXiangMuList);

    boolean toShenhe(ShenHeItem item, List<JiaoGaiXiangMu> jiaoGaiXiangMuList);

    /*List<ZjshItem> getZjshProcess(String xmCode, Integer batchNum);*/

    //Member
    List<Member> getMemberList(String xmCode);

    boolean insertMember(Member member);

    boolean deleteMember(Member member);

    //FundBudget
    List<FundBudget> getFundBudgetList(String xmCode);

    boolean insertFundBudget(FundBudget fundBudget);

    boolean deleteFundBudget(FundBudget fundBudget);
}
