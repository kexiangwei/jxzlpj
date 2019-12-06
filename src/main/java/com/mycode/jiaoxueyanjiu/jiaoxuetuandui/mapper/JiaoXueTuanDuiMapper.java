package com.mycode.jiaoxueyanjiu.jiaoxuetuandui.mapper;

import com.mycode.common.shenhe.domain.ShenHeNode;
import com.mycode.jiaoxueyanjiu.jiaoxuetuandui.domain.JiaoXueTuanDui;
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

    int getNotShenHeNum(@Param("shenHeUserId") Integer shenHeUserId);

    boolean insert(JiaoXueTuanDui jiaoXueTuanDui);

    boolean update(JiaoXueTuanDui jiaoXueTuanDui);

    @Delete("delete from JXYJ_JXTD where code = #{code}")
    boolean delete(@Param("code") String code);

    boolean batchSubimt(@Param("jiaoXueTuanDuiList") List<JiaoXueTuanDui> jiaoXueTuanDuiList);

    ShenHeNode getShenheNode(@Param("relationCode") String relationCode, @Param("userId") Integer userId);

    int isShenhePass(@Param("relationCode") String relationCode, @Param("batchNum") Integer batchNum);

    @Select("SELECT * FROM JXYJ_JXTD_MEMBER WHERE RELATION_CODE = #{relationCode}")
    List<Map<String, Object>> getMemberList(@Param("relationCode") String relationCode);

    @Insert("insert into JXYJ_JXTD_MEMBER (RELATION_CODE,USER_ID,USER_NAME) values (#{relationCode},#{userId},#{userName})")
    boolean insertMember(@Param("relationCode") String relationCode, @Param("userId") String userId, @Param("userName") String userName);

    @Delete("delete from JXYJ_JXTD_MEMBER WHERE RELATION_CODE = #{relationCode} and USER_ID = #{userId} ")
    boolean deleteMember(@Param("relationCode") String relationCode, @Param("userId") String userId);
}
