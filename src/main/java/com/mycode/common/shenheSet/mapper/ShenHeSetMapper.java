package com.mycode.common.shenheSet.mapper;

import com.mycode.common.shenheSet.domain.ShenHeSet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ShenHeSetMapper {

    List<ShenHeSet> getShenHeSetPageList(ShenHeSet shenHeSet);

    boolean addShenheSet(ShenHeSet shenHeSet);

    @Update("update COMMON_SHENHE_SET set status = 0 where menu_id = #{menuId}")
    boolean updateShenheSetStatusByMenuId(@Param("menuId") Long menuId);

    boolean updateShenheSetByCode(ShenHeSet shenHeSet);

    int batchDeleteShenHeSet(@Param("shenheCodes") String[] shenheCodes);

}
