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

    /*
    查看评教
     */
    List<Ckpj> getCkpjPageList(Ckpj ckpj);

    List<Map<String, Object>> getThpjTargetAvgList(@Param("userId") String userId, @Param("courseCode") String courseCode);

    /*
    同行评教
     */
    List<ThpjQuery> getPageList(ThpjQuery thpjQuery);

    Thpj getThpjInfo(@Param("code") String code);

    List<Map<String,Object>> getThpjItemListByRelationCode(@Param("relationCode") String relationCode);

    boolean insert(Thpj thpj);

    boolean insertTarget(@Param("relationCode") String relationCode, @Param("pjSetTargetList") List<PjSetTarget> pjSetTargetList, @Param("paramMap") Map<String, Object> paramMap);

    boolean deleteTargetByRelationCode(@Param("relationCode") String relationCode);
    /*

     */
    String getThpjTemplateCode(String code);

    List<Map<String, Object>> getPjzb(@Param("templateCode") String templateCode);

    /*
   同行评教-比较评价
    */
    Integer isTopFull(@Param("userId") String userId);

    boolean submit(@Param("code") String code);

    boolean resetSubmit(@Param("code") String code);
}
