package com.mycode.jiaoxueyanjiu.jiaoxuetuandui.mapper;

import com.mycode.common.shenhe.domain.ShenHeNode;
import com.mycode.jiaoxueyanjiu.jiaoxuetuandui.domain.JiaoXueTuanDui;
import com.mycode.jiaoxueyanjiu.jiaoxuetuandui.domain.PingShen;
import com.mycode.jiaoxueyanjiu.jiaoxuetuandui.domain.PingShenTemplate;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * 教学研究-教学团队
 * @auther kexiangwei
 * @date 2019/11/13
 */
@Mapper
public interface JiaoXueTuanDuiMapper {

    List<JiaoXueTuanDui> getPageList(JiaoXueTuanDui jiaoXueTuanDui);

    @ResultType(Integer.class)
    @Select("SELECT COUNT(0) FROM SYS_USER_ROLE WHERE ROLE_ID = 654321 AND USER_ID = #{shenHeUserId}")
    Integer isPsAccount(@Param("shenHeUserId") Integer shenHeUserId);

    int getNotShenHeNum(@Param("shenHeUserId") Integer shenHeUserId);

    boolean insert(JiaoXueTuanDui jiaoXueTuanDui);

    boolean update(JiaoXueTuanDui jiaoXueTuanDui);

    @Delete("delete from JXYJ_JXTD where code = #{code}")
    boolean delete(@Param("code") String code);

    boolean batchSubimt(@Param("jiaoXueTuanDuiList") List<JiaoXueTuanDui> jiaoXueTuanDuiList);

    ShenHeNode getShenheNode(@Param("relationCode") String relationCode, @Param("userId") Integer userId);

    int isShenhePass(@Param("relationCode") String relationCode, @Param("batchNum") Integer batchNum);

    int isPingshenPass(@Param("relationCode") String relationCode, @Param("batchNum") Integer batchNum);

    @ResultType(PingShenTemplate.class)
    @Select("SELECT * FROM JXYJ_JXTD_PS_TEMPLATE")
    List<PingShenTemplate> getPingShenTemplate();

    List<PingShen> getPingShenInfo(@Param("relationCode") String relationCode, @Param("batchNum") Integer batchNum, @Param("pingshenType") String pingshenType
            , @Param("userId") String userId);

    boolean insertPingShenInfo(PingShen pingShen);

    boolean deletePingShenInfo(@Param("relationCode") String relationCode, @Param("batchNum") Integer batchNum, @Param("pingshenType") String pingshenType
            , @Param("userId") String userId);

    @Select("SELECT * FROM JXYJ_JXTD_MEMBER WHERE RELATION_CODE = #{relationCode}")
    List<Map<String, Object>> getMemberList(@Param("relationCode") String relationCode);

    @Insert("insert into JXYJ_JXTD_MEMBER (RELATION_CODE,USER_ID,USER_NAME) values (#{relationCode},#{userId},#{userName})")
    boolean insertMember(@Param("relationCode") String relationCode, @Param("userId") String userId, @Param("userName") String userName);

    @Delete("delete from JXYJ_JXTD_MEMBER WHERE RELATION_CODE = #{relationCode} and USER_ID = #{userId} ")
    boolean deleteMember(@Param("relationCode") String relationCode, @Param("userId") String userId);
}
