package com.mycode.jiaoxuepingjia.thpj.mapper;

import com.mycode.jiaoxuepingjia.pjset.domain.PjSetTarget;
import com.mycode.jiaoxuepingjia.pjset.domain.PjSetTemplate;
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

    List<ThpjQuery> getPageList(ThpjQuery thpjQuery);

    Thpj getThpjInfo(@Param("code") String code);

    List<Map<String,Object>> getThpjItemListByRelationCode(@Param("relationCode") String relationCode);

    boolean insert(Thpj thpj);

    boolean insertTarget(@Param("relationCode") String relationCode, @Param("pjSetTargetList") List<PjSetTarget> pjSetTargetList, @Param("paramMap") Map<String, Object> paramMap);


    String getThpjTemplateCode(String code);

    List<Map<String, Object>> getThpjTargetList(@Param("templateCode") String templateCode);


    List<Map<String, Object>> getTeacherBar(@Param("menuName") String menuName, @Param("userId") String userId);

    List<Map<String, Object>> getTeacherPie(@Param("menuName") String menuName, @Param("userId") String userId);

    List<Map<String, Object>> getTeacherTab(@Param("menuName") String menuName);

    List<Map<String, Object>> getTeacherTabData(@Param("menuName") String menuName, @Param("userId") String userId, @Param("status") String status);
}
