package com.mycode.jxzlpj.jiaoxueyanjiu.jiaogaixiangmu.mapper;

import com.mycode.jxzlpj.jiaoxueyanjiu.jiaogaixiangmu.domain.JiaoGaiXiangMuCountry;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JiaoGaiXiangMuCountryMapper {

    List<JiaoGaiXiangMuCountry> getPageList(JiaoGaiXiangMuCountry jiaoGaiXiangMuCountry);

    boolean insert(JiaoGaiXiangMuCountry jiaoGaiXiangMuCountry);

    boolean update(JiaoGaiXiangMuCountry jiaoGaiXiangMuCountry);

    boolean delete(@Param("code") String code);

}
