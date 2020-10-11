package com.mycode.jiaoxueyanjiu.jiaoxuetuandui.mapper;

import com.mycode.jiaoxueyanjiu.jiaoxuetuandui.domain.CountryTeam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 教学研究-教学团队-国家级团队
 */
@Mapper
public interface CountryTeamMapper {

    List<CountryTeam> getPageList(CountryTeam countryTeam);

    boolean insert(CountryTeam countryTeam);

    boolean update(CountryTeam countryTeam);

    boolean delete(@Param("code") String code);

}
