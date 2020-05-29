package com.mycode.jiaoxueyanjiu.jiaogaixiangmu.mapper;

import com.mycode.jiaoxueyanjiu.jiaogaixiangmu.domain.JiaoGaiXiangMuForCity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JiaoGaiXiangMuCityMapper {

    int getNotShenHeNum(@Param("shenHeUserId") Integer shenHeUserId, @Param("isZjshAccount") Integer isZjshAccount);

    List<JiaoGaiXiangMuForCity> getPageList(JiaoGaiXiangMuForCity jiaoGaiXiangMuForCity);

    boolean insert(JiaoGaiXiangMuForCity jiaoGaiXiangMuForCity);

    boolean update(JiaoGaiXiangMuForCity jiaoGaiXiangMuForCity);

    boolean delete(@Param("code") String code);

}
