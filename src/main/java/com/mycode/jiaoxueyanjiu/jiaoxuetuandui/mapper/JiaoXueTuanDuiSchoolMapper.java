package com.mycode.jiaoxueyanjiu.jiaoxuetuandui.mapper;

import com.mycode.jiaoxueyanjiu.jiaoxuetuandui.domain.JiaoXueTuanDuiSchool;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JiaoXueTuanDuiSchoolMapper {

    List<JiaoXueTuanDuiSchool> getPageList(JiaoXueTuanDuiSchool jiaoXueTuanDuiSchool);

    boolean insert(JiaoXueTuanDuiSchool jiaoXueTuanDuiSchool);

    boolean update(JiaoXueTuanDuiSchool jiaoXueTuanDuiSchool);

    boolean delete(@Param("code") String code);

}
