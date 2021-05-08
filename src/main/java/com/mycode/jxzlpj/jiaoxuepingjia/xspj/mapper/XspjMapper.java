package com.mycode.jxzlpj.jiaoxuepingjia.xspj.mapper;

import com.mycode.jxzlpj.jiaoxuepingjia.xspj.domain.BjpjParams;
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

    List<Map<String,Object>> getPjInfoTransferData(@Param("userId") String userId);

    boolean insertBjpj(BjpjParams params);

    boolean insertBjpjTarget(@Param("mapList") List<Map<String, Object>> mapList);

    boolean insertBjpjSuggest(@Param("relationCode") String relationCode
            , @Param("courseCode") String courseCode
            , @Param("suggest") String suggest);

    String selectBjpjSuggest(@Param("relationCode") String relationCode
            , @Param("courseCode") String courseCode);

    boolean deleteBjpjSuggest(@Param("relationCode") String relationCode
            , @Param("courseCode") String courseCode);

    List<Xspj> getBjpjPageList(Xspj xspj);

    List<Map<String, Object>> getBjpjPjInfo(@Param("courseCode") String courseCode);

    List<String> getBjpjPjInfoSuggestList(@Param("courseCode") String courseCode, @Param("templateCode") String templateCode);

}
