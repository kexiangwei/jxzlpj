package com.mycode.jxzlpj.jiaoxuesheji.skjh.mapper;

import com.mycode.jxzlpj.jiaoxuesheji.skjh.domian.Skjh;
import com.mycode.jxzlpj.jiaoxuesheji.skjh.domian.SkjhItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 教学设计-授课计划
 */
@Mapper
public interface SkjhMapper {

    int getNotShenHeNum(@Param("shenHeUserId") String shenHeUserId);

    List<Skjh> getShenHePageList(Skjh skjh);

    List<Skjh> getPageList(Skjh skjh);

    Skjh getSkJhInfoByCode(@Param("code") String code);

    boolean insert(Skjh skjh);

    boolean update(Skjh skjh);

    boolean delete(@Param("code") String code);

    List<SkjhItem> getSkjhItemList(@Param("relationCode") String relationCode);

    boolean insertSkjhItem(SkjhItem skjhItem);

    boolean deleteSkjhItem(@Param("relationCode") String relationCode, @Param("itemCode") String itemCode);
}
