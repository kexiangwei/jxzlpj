package com.mycode.jxzlpj.jiaoxueyanjiu.jiaoxuetuandui.mapper;

import com.mycode.common.shenheSet.domain.ShenHeItem;
import com.mycode.common.shenheSet.domain.ZjshItem;
import com.mycode.jxzlpj.jiaoxueyanjiu.jiaoxuetuandui.domain.JiaoXueTuanDui;
import com.mycode.jxzlpj.jiaoxueyanjiu.jiaoxuetuandui.domain.PingShen;
import com.mycode.jxzlpj.jiaoxueyanjiu.jiaoxuetuandui.domain.PingShenTemplate;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 教学研究-教学团队
 */
@Mapper
public interface JiaoXueTuanDuiMapper {

    @ResultType(Integer.class)
    @Select("SELECT COUNT(0) FROM SYS_USER_ROLE WHERE ROLE_ID = 4 AND USER_ID = #{shenHeUserId}")
    Integer isJwcGly(@Param("shenHeUserId") String shenHeUserId);

    @ResultType(Integer.class)
    @Select("SELECT COUNT(0) FROM SYS_USER_ROLE WHERE ROLE_ID = 3 AND USER_ID = #{shenHeUserId}")
    Integer isZjAccount(@Param("shenHeUserId") String shenHeUserId);

    int getNotShenHeNum(JiaoXueTuanDui jiaoXueTuanDui);

    List<JiaoXueTuanDui> getPageList(JiaoXueTuanDui jiaoXueTuanDui);

    boolean insert(JiaoXueTuanDui jiaoXueTuanDui);

    boolean update(JiaoXueTuanDui jiaoXueTuanDui);

    boolean delete(@Param("code") String code);

    //专家评审
    List<ZjshItem> getZjshProcess(@Param("xmCode") String xmCode, @Param("batchNum") Integer batchNum);

    Integer isZjshAll(@Param("relationCode") String relationCode, @Param("batchNum") Integer batchNum);

    boolean toZjShenhe(ShenHeItem item);

    @ResultType(PingShenTemplate.class)
    @Select("SELECT * FROM JXYJ_JXTD_ZJPS_TEMPLATE")
    List<PingShenTemplate> getPingShenTemplate();

    //
    List<PingShen> getPingShenInfo(@Param("relationCode") String relationCode, @Param("batchNum") Integer batchNum, @Param("userId") String userId);

    boolean insertPingShenInfo(PingShen pingShen);

    boolean deletePingShenInfo(@Param("relationCode") String relationCode, @Param("batchNum") Integer batchNum, @Param("userId") String userId);

}
