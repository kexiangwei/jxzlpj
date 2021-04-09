package com.mycode.jxzlpj.jiaoxuepingjia.thpj.mapper;

import com.mycode.jxzlpj.jiaoxuepingjia.pjset.domain.PjSetTarget;
import com.mycode.jxzlpj.jiaoxuepingjia.thpj.domian.Thpj;
import com.mycode.jxzlpj.jiaoxuepingjia.thpj.domian.ThpjQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 教学评价-同行评教
 */
@Mapper
public interface DfpjMapper {

    List<ThpjQuery> getPageList(ThpjQuery thpjQuery);

    Thpj getThpjInfo(@Param("pjCode") String pjCode);

    List<Map<String,Object>> getThpjItemListByRelationCode(@Param("relationCode") String relationCode);

    boolean insert(Thpj thpj);

    boolean insertTarget(@Param("relationCode") String relationCode, @Param("pjSetTargetList") List<PjSetTarget> pjSetTargetList, @Param("paramMap") Map<String, Object> paramMap);

    boolean deleteTargetByRelationCode(@Param("relationCode") String relationCode);

//    boolean delete(String pjCode);

}
