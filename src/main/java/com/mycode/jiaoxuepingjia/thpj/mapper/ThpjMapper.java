package com.mycode.jiaoxuepingjia.thpj.mapper;

import com.mycode.jiaoxuepingjia.thpj.domian.Thpj;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 教学评价-同行评教
 */
@Mapper
public interface ThpjMapper {

    List<Thpj> getPageList(Thpj thpj);

    boolean insert(Thpj thpj);

    boolean update(Thpj thpj);

    boolean delete(@Param("code") String code);
}
