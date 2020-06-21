package com.mycode.jiaoxuexiaoguo.sjfx.mapper;

import com.mycode.jiaoxuexiaoguo.sjfx.domian.Sjfx;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SjfxMapper {

    int getNotShenHeNum(@Param("shenHeUserId") String shenHeUserId);

    List<Sjfx> getPageList(Sjfx sjfx);

    boolean insert(Sjfx sjfx);

    boolean update(Sjfx sjfx);

    boolean delete(@Param("code") String code);

}
