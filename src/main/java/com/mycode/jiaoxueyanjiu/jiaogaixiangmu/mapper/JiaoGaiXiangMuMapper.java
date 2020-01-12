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

/**
 * 教学研究-教改项目
 * @auther kexiangwei
 * @date 2019/11/13
 */
@Mapper
public interface JiaoGaiXiangMuMapper {

    List<JiaoGaiXiangMu> getPageList(JiaoGaiXiangMu jiaoGaiXiangMu);

    int getNotShenHeNum(@Param("shenHeUserId") Integer shenHeUserId,@Param("isZjshAccount") Integer isZjshAccount);

    @ResultType(Integer.class)
    @Select("SELECT COUNT(0) FROM SYS_USER_ROLE WHERE ROLE_ID = 2 AND USER_ID = #{shenHeUserId}")
    Integer isJwcGly(@Param("shenHeUserId") Integer shenHeUserId);

    @ResultType(Integer.class)
    @Select("SELECT COUNT(0) FROM JXYJ_JGXM_ZJ WHERE USER_ID = #{shenHeUserId}")
//    @Select("SELECT COUNT(0) FROM SYS_USER WHERE ACCOUNT_TYPE = '校外专家审核账号' AND USER_ID = #{shenHeUserId}")
    Integer isZjshAccount(@Param("shenHeUserId") Integer shenHeUserId);

    boolean insert(JiaoGaiXiangMu jiaoGaiXiangMu);

    boolean update(JiaoGaiXiangMu jiaoGaiXiangMu);

    boolean delete(@Param("code") String code);

    boolean batchSubimt(@Param("jiaoGaiXiangMuList") List<JiaoGaiXiangMu> jiaoGaiXiangMuList);

    ShenHeNode getShenheNode(@Param("relationCode") String relationCode, @Param("userId") Integer userId);

    int isShenhePass(@Param("relationCode") String relationCode, @Param("batchNum") Integer batchNum);

    Integer isZjshAll(@Param("relationCode") String relationCode, @Param("batchNum") Integer batchNum);

    boolean toZjShenhe(ShenHeItem item);

    @ResultType(ZjshItem.class)
    @Select("SELECT * FROM JXYJ_JGXM_ZJSH WHERE XM_CODE = #{xmCode} AND BATCH_NUM = #{batchNum}")
    List<ZjshItem> getZjshProcess(@Param("xmCode") String xmCode, @Param("batchNum") Integer batchNum);

    List<Member> getMemberList(@Param("xmCode") String xmCode);

    boolean insertMember(Member member);

    boolean deleteMember(Member member);

    List<FundBudget> getFundBudgetList(String xmCode);

    boolean insertFundBudget(FundBudget fundBudget);

    boolean deleteFundBudget(FundBudget fundBudget);
}
