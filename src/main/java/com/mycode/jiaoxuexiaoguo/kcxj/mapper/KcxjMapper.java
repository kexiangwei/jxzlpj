package com.mycode.jiaoxuexiaoguo.kcxj.mapper;

import com.mycode.jiaoxuexiaoguo.kcxj.domian.Kcxj;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface KcxjMapper {

    int getNotShenHeNum(@Param("shenHeUserId") String shenHeUserId);

    List<Kcxj> getPageList(Kcxj kcxj);

    boolean insert(Kcxj kcxj);

    boolean update(Kcxj kcxj);

    boolean delete(@Param("code") String code);

}
