package com.mycode.jiaoxueyanjiu.jiaoxuetuandui.service;

import com.mycode.jiaoxueyanjiu.jiaoxuetuandui.domain.CountryTeam;

import java.util.Map;

/**
 * 教学研究-教学团队-国家级团队
 */
public interface CountryTeamService {

    Map<String, Object> getPageList(CountryTeam countryTeam);

    boolean insert(CountryTeam countryTeam);

    boolean update(CountryTeam countryTeam);

    boolean delete(String code);

}
