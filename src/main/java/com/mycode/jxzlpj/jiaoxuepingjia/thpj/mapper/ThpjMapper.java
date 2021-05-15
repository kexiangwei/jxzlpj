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

    String getThpjTemplateCode(String pjCode);

    List<Map<String, Object>> getPjzb(@Param("templateCode") String templateCode);

    Thpj getThpjInfo(@Param("pjCode") String pjCode);

    List<Map<String,Object>> getThpjItemList(@Param("relationCode") String relationCode);

    boolean insert(Thpj thpj);

    boolean insertTarget(@Param("relationCode") String relationCode, @Param("templateCode") String templateCode
            , @Param("pjSetTargetList") List<PjSetTarget> pjSetTargetList, @Param("paramMap") Map<String, Object> paramMap);

    boolean deleteItems(@Param("relationCode") String relationCode);

    /*
   同行评教-比较评价
    */
    Integer isTopFull(@Param("userId") String userId);

    boolean submit(@Param("pjCode") String pjCode);

    boolean resetSubmit(@Param("pjCode") String pjCode);

    List<Map<String,Object>> ckpj(Thpj thpj);
}
