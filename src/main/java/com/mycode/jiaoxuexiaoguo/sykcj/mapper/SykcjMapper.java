package com.mycode.jiaoxuexiaoguo.sykcj.mapper;

import com.mycode.jiaoxuexiaoguo.sykcj.domian.Sykcj;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SykcjMapper {

    int getNotShenHeNum(@Param("shenHeUserId") String shenHeUserId);

    List<Sykcj> getPageList(Sykcj sykcj);

    boolean insert(Sykcj sykcj);

    boolean update(Sykcj sykcj);

    boolean delete(@Param("code") String code);

}
