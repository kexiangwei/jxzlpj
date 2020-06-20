package com.mycode.edu;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @auther kexiangwei
 * @date 2020/6/20
 */
@Mapper
public interface EduMapper {

    boolean resetEduDataInfo(@Param("tab") String tab, @Param("mapList") List<LinkedHashMap<String, Object>> mapList);

    boolean deleteEduDataInfo(@Param("tab") String tab);

    boolean resetSysUserTeacherInfo();

    boolean resetSysUserStudentInfo();

    boolean resetSysUserRoleInfo();
}
