package com.mycode.jiaoxueyanjiu.jiaogaixiangmu.mapper;

import com.mycode.common.shenhe.domain.ShenHeNode;
import com.mycode.jiaoxueyanjiu.jiaogaixiangmu.domain.FundBudget;
import com.mycode.jiaoxueyanjiu.jiaogaixiangmu.domain.JiaoGaiXiangMu;
import com.mycode.jiaoxueyanjiu.jiaogaixiangmu.domain.Member;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 教学研究-教改项目
 * @auther kexiangwei
 * @date 2019/11/13
 */
@Mapper
public interface JiaoGaiXiangMuMapper {

    List<JiaoGaiXiangMu> getPageList(JiaoGaiXiangMu jiaoGaiXiangMu);

    int getNotShenHeNum(@Param("shenHeUserId") Integer shenHeUserId);

    boolean insert(JiaoGaiXiangMu jiaoGaiXiangMu);

    boolean update(JiaoGaiXiangMu jiaoGaiXiangMu);

    boolean delete(@Param("code") String code);

    boolean batchSubimt(@Param("jiaoGaiXiangMuList") List<JiaoGaiXiangMu> jiaoGaiXiangMuList);

    ShenHeNode getShenheNode(@Param("relationCode") String relationCode, @Param("userId") Integer userId);

    int isShenhePass(@Param("relationCode") String relationCode, @Param("batchNum") Integer batchNum);

    List<Member> getMemberList(@Param("xmCode") String xmCode);

    boolean insertMember(Member member);

    boolean deleteMember(Member member);

    List<FundBudget> getFundBudgetList(String xmCode);

    boolean insertFundBudget(FundBudget fundBudget);

    boolean deleteFundBudget(FundBudget fundBudget);
}
