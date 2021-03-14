package com.mycode.jxzlpj.jiaoxueyanjiu.jiaogaixiangmu.mapper;

import com.mycode.jxzlpj.jiaoxueyanjiu.jiaogaixiangmu.domain.JiaoGaiXiangMuProvince;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JiaoGaiXiangMuProvinceMapper {

    List<JiaoGaiXiangMuProvince> getPageList(JiaoGaiXiangMuProvince jiaoGaiXiangMuProvince);

    boolean insert(JiaoGaiXiangMuProvince jiaoGaiXiangMuProvince);

    boolean update(JiaoGaiXiangMuProvince jiaoGaiXiangMuProvince);

    boolean delete(@Param("code") String code);

}
