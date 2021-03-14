package com.mycode.jxzlpj.jiaoxueyanjiu.jiaoxuetuandui.mapper;

import com.mycode.jxzlpj.jiaoxueyanjiu.jiaoxuetuandui.domain.JiaoXueTuanDuiCountry;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 教学研究-教学团队-国家级团队
 */
@Mapper
public interface JiaoXueTuanDuiCountryMapper {

    List<JiaoXueTuanDuiCountry> getPageList(JiaoXueTuanDuiCountry jiaoXueTuanDuiCountry);

    boolean insert(JiaoXueTuanDuiCountry jiaoXueTuanDuiCountry);

    boolean update(JiaoXueTuanDuiCountry jiaoXueTuanDuiCountry);

    boolean delete(@Param("code") String code);

}
