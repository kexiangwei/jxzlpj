package com.mycode.jiaoxueyanjiu.jiaogaixiangmu.mapper;

import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.common.shenhe.domain.ShenHeNode;
import com.mycode.jiaoxueyanjiu.jiaogaixiangmu.domain.FundBudget;
import com.mycode.jiaoxueyanjiu.jiaogaixiangmu.domain.JiaoGaiXiangMu;
import com.mycode.jiaoxueyanjiu.jiaogaixiangmu.domain.Member;
import com.mycode.jiaoxueyanjiu.jiaogaixiangmu.domain.ZjshItem;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface JiaoGaiXiangMuMapper {

    @ResultType(Integer.class)
    @Select("SELECT COUNT(0) FROM SYS_USER_ROLE WHERE ROLE_ID = 1 AND USER_ID = #{shenHeUserId}")
    Integer isJwcGly(@Param("shenHeUserId") Integer shenHeUserId);

    @ResultType(Integer.class)
    @Select("SELECT COUNT(0) FROM SYS_USER_ROLE WHERE ROLE_ID = 7 AND USER_ID = #{shenHeUserId}")
    Integer isZjAccount(@Param("shenHeUserId") Integer shenHeUserId);

    int getNotShenHeNum(@Param("shenHeUserId") Integer shenHeUserId,@Param("isZjshAccount") Integer isZjshAccount);

    List<JiaoGaiXiangMu> getPageList(JiaoGaiXiangMu jiaoGaiXiangMu);

    boolean insert(JiaoGaiXiangMu jiaoGaiXiangMu);

    boolean update(JiaoGaiXiangMu jiaoGaiXiangMu);

    boolean delete(@Param("code") String code);

    //专家评审
    @ResultType(ZjshItem.class)
    @Select("SELECT * FROM JXYJ_JGXM_ZJSH WHERE XM_CODE = #{xmCode} AND BATCH_NUM = #{batchNum}")
    List<ZjshItem> getZjshProcess(@Param("xmCode") String xmCode, @Param("batchNum") Integer batchNum);

    Integer isZjshAll(@Param("relationCode") String relationCode, @Param("batchNum") Integer batchNum);

    boolean toZjShenhe(ShenHeItem item);

    //Member
    List<Member> getMemberList(@Param("xmCode") String xmCode);

    boolean insertMember(Member member);

    boolean deleteMember(Member member);

    //FundBudget
    List<FundBudget> getFundBudgetList(String xmCode);

    boolean insertFundBudget(FundBudget fundBudget);

    boolean deleteFundBudget(FundBudget fundBudget);
}
