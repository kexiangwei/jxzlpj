package com.mycode.jiaoxuexiaoguo.sybg.mapper;

import com.mycode.jiaoxuexiaoguo.sybg.domian.Sybg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SybgMapper {

    List<Sybg> getPageList(Sybg sybg);

    boolean insert(Sybg sybg);

    boolean update(Sybg sybg);

    boolean delete(@Param("code") String code);

}
