package com.mycode.jiaoxuesheji.skjh.mapper;

import com.mycode.common.shenhe.domain.ShenHeNode;
import com.mycode.jiaoxuesheji.skjh.domian.Skjh;
import com.mycode.jiaoxuesheji.skjh.domian.SkjhItem;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 教学设计-授课计划
 * @auther kexiangwei
 * @date 2019/11/13
 */
@Mapper
public interface SkjhMapper {

    List<Skjh> getPageList(Skjh skjh);

    int getNotShenHeNum(@Param("shenHeUserId") Integer shenHeUserId);

    boolean insert(Skjh skjh);

    boolean update(Skjh skjh);

    @Delete("delete from JXSJ_SKJH where code = #{code}")
    boolean delete(@Param("code") String code);

    boolean batchSubimt(@Param("skjhList") List<Skjh> skjhList);

    ShenHeNode getShenheNode(@Param("relationCode") String relationCode, @Param("userId") Integer userId);

    int isShenhePass(@Param("relationCode") String relationCode, @Param("batchNum") Integer batchNum);

    List<SkjhItem> getSkjhItemList(@Param("relationCode") String relationCode);

    boolean deleteSkjhItem(@Param("relationCode") String relationCode, @Param("code") String code);
}
