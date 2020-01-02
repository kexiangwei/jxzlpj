package com.mycode.jiaoxuexiaoguo.sjfx.mapper;

import com.mycode.common.shenhe.domain.ShenHeNode;
import com.mycode.jiaoxuesheji.skjh.domian.SkjhItem;
import com.mycode.jiaoxuexiaoguo.sjfx.domian.Sjfx;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 教学效果-试卷分析
 * @auther kexiangwei
 * @date 2019/11/13
 */
@Mapper
public interface SjfxMapper {

    List<Sjfx> getPageList(Sjfx sjfx);

    int getNotShenHeNum(@Param("shenHeUserId") Integer shenHeUserId);

    boolean insert(Sjfx sjfx);

    boolean batchImport(@Param("mapList") List<Map<String, Object>> mapList, @Param("userId") String userId,@Param("userName")  String userName);

    boolean update(Sjfx sjfx);

    boolean delete(@Param("code") String code);

    boolean batchSubimt(@Param("sjfxList") List<Sjfx> sjfxList);

    ShenHeNode getShenheNode(@Param("relationCode") String relationCode, @Param("userId") Integer userId);

    int isShenhePass(@Param("relationCode") String relationCode, @Param("batchNum") Integer batchNum);
}
