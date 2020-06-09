package com.mycode.jiaoxuepingjia.thpj.mapper;

import com.mycode.jiaoxuepingjia.pjset.domain.PjSetTarget;
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

    List<Map<String, Object>> getThpjTargetList();

    List<PjSetTarget> getPjSetTargetList();

    boolean insert(Thpj thpj);

    boolean update(Thpj thpj);

    boolean delete(@Param("code") String code);
}
