package com.mycode.jiaoxueyanjiu.jiaoxuetuandui.mapper;

import com.mycode.common.shenhe.domain.ShenHeNode;
import com.mycode.jiaoxueyanjiu.jiaoxuetuandui.domain.JiaoXueTuanDui;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    @Delete("delete from JXYJ_JCJS where code = #{code}")
    boolean delete(@Param("code") String code);

    boolean batchSubimt(@Param("jiaoXueTuanDuiList") List<JiaoXueTuanDui> jiaoXueTuanDuiList);

    ShenHeNode getShenheNode(@Param("relationCode") String relationCode, @Param("userId") Integer userId);

    int isShenhePass(@Param("relationCode") String relationCode, @Param("batchNum") Integer batchNum);
}
