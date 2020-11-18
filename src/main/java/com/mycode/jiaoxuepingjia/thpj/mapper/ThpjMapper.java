package com.mycode.jiaoxuepingjia.thpj.mapper;

import com.mycode.jiaoxuepingjia.pjset.domain.PjSetTarget;
import com.mycode.jiaoxuepingjia.pjset.domain.PjSetTemplate;
import com.mycode.jiaoxuepingjia.thpj.domian.Ckpj;
import com.mycode.jiaoxuepingjia.thpj.domian.Thpj;
import com.mycode.jiaoxuepingjia.thpj.domian.ThpjQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 教学评价-同行评教
 */
@Mapper
public interface ThpjMapper {


    List<Map<String, Object>> getThpjTargetAvgList(@Param("userId") String userId, @Param("courseCode") String courseCode);

    List<Ckpj> getCkpjPageList(Ckpj ckpj);

    List<ThpjQuery> getPageList(ThpjQuery thpjQuery);

    Thpj getThpjInfo(@Param("code") String code);

    List<Map<String,Object>> getThpjItemListByRelationCode(@Param("relationCode") String relationCode);

    boolean insert(Thpj thpj);

    boolean insertTarget(@Param("relationCode") String relationCode, @Param("pjSetTargetList") List<PjSetTarget> pjSetTargetList, @Param("paramMap") Map<String, Object> paramMap);


    String getThpjTemplateCode(String code);

    List<Map<String, Object>> getPjzb(@Param("templateCode") String templateCode);


    /*List<Map<String, Object>> getTeacherBar(@Param("menuName") String menuName, @Param("userId") String userId);

    List<Map<String, Object>> getTeacherPie(@Param("menuName") String menuName, @Param("userId") String userId);*/

    List<Map<String, Object>> getTableCols(@Param("tableName") String tableName);

    List<Map<String, Object>> getTableDatas(@Param("viewName") String viewName, @Param("userId") String userId);

}
