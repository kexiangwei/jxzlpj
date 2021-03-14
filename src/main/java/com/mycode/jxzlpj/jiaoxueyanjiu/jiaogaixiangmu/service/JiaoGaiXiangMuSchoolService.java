package com.mycode.jxzlpj.jiaoxueyanjiu.jiaogaixiangmu.service;

import com.mycode.jxzlpj.jiaoxueyanjiu.jiaogaixiangmu.domain.FundBudget;
import com.mycode.jxzlpj.jiaoxueyanjiu.jiaogaixiangmu.domain.JiaoGaiXiangMuSchool;
import com.mycode.jxzlpj.jiaoxueyanjiu.jiaogaixiangmu.domain.Member;

import java.util.List;
import java.util.Map;

public interface JiaoGaiXiangMuSchoolService {

    Map<String, Object> getPageList(JiaoGaiXiangMuSchool jiaoGaiXiangMuSchool);

    boolean insert(JiaoGaiXiangMuSchool jiaoGaiXiangMuSchool);

    boolean update(JiaoGaiXiangMuSchool jiaoGaiXiangMuSchool);

    boolean delete(String code);

    //Member
    List<Member> getMemberList(String xmCode);

    boolean insertMember(Member member);

    boolean deleteMember(Member member);

    //FundBudget
    List<FundBudget> getFundBudgetList(String xmCode);

    boolean insertFundBudget(FundBudget fundBudget);

    boolean deleteFundBudget(FundBudget fundBudget);
}
