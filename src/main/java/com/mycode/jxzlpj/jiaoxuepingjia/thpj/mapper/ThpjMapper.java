package com.mycode.jxzlpj.jiaoxuepingjia.thpj.mapper;

import com.mycode.jxzlpj.jiaoxuepingjia.pjset.domain.PjSetTarget;
import com.mycode.jxzlpj.jiaoxuepingjia.thpj.domian.Thpj;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 教学评价-同行评教
 */
@Mapper
public interface ThpjMapper {


    List<Thpj> getPageList(Thpj thpj);


    Thpj getThpjInfo(@Param("pjCode") String pjCode);

    List<Map<String,Object>> getThpjItemListByRelationCode(@Param("relationCode") String relationCode);

    boolean insert(Thpj thpj);

    boolean insertTarget(@Param("relationCode") String relationCode, @Param("pjSetTargetList") List<PjSetTarget> pjSetTargetList, @Param("paramMap") Map<String, Object> paramMap);

    boolean deleteTargetByRelationCode(@Param("relationCode") String relationCode);

    //    boolean delete(String pjCode);


    String getThpjTemplateCode(String pjCode);

    List<Map<String, Object>> getPjzb(@Param("templateCode") String templateCode);

    /*
   同行评教-比较评价
    */
    Integer isTopFull(@Param("userId") String userId);

    boolean submit(@Param("code") String code);

    boolean resetSubmit(@Param("code") String code);
}
