package com.mycode.jxzlpj.jiaoxuepingjia.thpj.mapper;

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

    /*
   同行评教-比较评价
    */
    Integer isTopFull(@Param("userId") String userId);

    boolean submit(@Param("code") String code);

    boolean resetSubmit(@Param("code") String code);
}
