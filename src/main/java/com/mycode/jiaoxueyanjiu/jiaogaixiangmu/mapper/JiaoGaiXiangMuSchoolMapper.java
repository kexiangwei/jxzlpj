package com.mycode.jiaoxueyanjiu.jiaogaixiangmu.mapper;

import com.mycode.jiaoxueyanjiu.jiaogaixiangmu.domain.FundBudget;
import com.mycode.jiaoxueyanjiu.jiaogaixiangmu.domain.JiaoGaiXiangMuSchool;
import com.mycode.jiaoxueyanjiu.jiaogaixiangmu.domain.Member;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface JiaoGaiXiangMuSchoolMapper {

    //JiaoGaiXiangMuSchool
    List<JiaoGaiXiangMuSchool> getPageList(JiaoGaiXiangMuSchool jiaoGaiXiangMuSchool);

    boolean insert(JiaoGaiXiangMuSchool jiaoGaiXiangMuSchool);

    boolean update(JiaoGaiXiangMuSchool jiaoGaiXiangMuSchool);

    boolean delete(@Param("code") String code);

    //Member
    List<Member> getMemberList(@Param("xmCode") String xmCode);

    boolean insertMember(Member member);

    boolean deleteMember(Member member);

    //FundBudget
    List<FundBudget> getFundBudgetList(String xmCode);

    boolean insertFundBudget(FundBudget fundBudget);

    boolean deleteFundBudget(FundBudget fundBudget);
}
