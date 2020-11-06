package com.mycode.jiaoxueyanjiu.jiaoxuetuandui.mapper;

import com.mycode.jiaoxueyanjiu.jiaoxuetuandui.domain.JiaoXueTuanDuiCountry;
import com.mycode.jiaoxueyanjiu.jiaoxuetuandui.domain.JiaoXueTuanDuiProvince;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JiaoXueTuanDuiProvinceMapper {

    List<JiaoXueTuanDuiProvince> getPageList(JiaoXueTuanDuiProvince jiaoXueTuanDuiProvince);

    boolean insert(JiaoXueTuanDuiProvince jiaoXueTuanDuiProvince);

    boolean update(JiaoXueTuanDuiProvince jiaoXueTuanDuiProvince);

    boolean delete(@Param("code") String code);

}
