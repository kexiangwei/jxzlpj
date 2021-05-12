package com.mycode.jxzlpj.jiaoxuepingjia.xspj.mapper;

import com.mycode.jxzlpj.jiaoxuepingjia.xspj.domain.Xspj;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 教学评价-学生评教
 */
@Mapper
public interface XspjMapper {

    List<Xspj> getPageList(Xspj xspj);

    boolean insert(Xspj xspj);

    boolean insertItem(@Param("xspj") Xspj xspj, @Param("paramMap") Map<String,Object> paramMap);

    Map<String,Object> getPjInfo(@Param("xn") String xn, @Param("xq") String xq, @Param("courseCode") String courseCode, @Param("teacherCode") String teacherCode);

    List<String> getPjInfoSuggestList(@Param("xn") String xn, @Param("xq") String xq, @Param("courseCode") String courseCode, @Param("teacherCode") String teacherCode);

    List<Map<String,Object>> getBjpjTransferData(@Param("userId") String userId);

    boolean insertBjpj(@Param("xspjList") List<Map<String,Object>> xspjList);

    boolean insertBjpjItem(@Param("itemList") List<Map<String, Object>> itemList);

    List<Map<String, Object>> getBjpjPjSuggestList(@Param("userId") String userId, @Param("templateCode") String templateCode);

    List<Xspj> getBjpjPageList(Xspj xspj);

    Map<String,Object> getBjpjPjInfo(@Param("xn") String xn, @Param("xq") String xq, @Param("courseCode") String courseCode, @Param("teacherCode") String teacherCode);

    List<String> getBjpjPjInfoSuggestList(@Param("xn") String xn, @Param("xq") String xq, @Param("courseCode") String courseCode, @Param("teacherCode") String teacherCode);
}
