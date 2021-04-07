package com.mycode.jxzlpj.jiaoxuepingjia.pjset.mapper;

import com.mycode.jxzlpj.jiaoxuepingjia.pjset.domain.PjSetTarget;
import com.mycode.jxzlpj.jiaoxuepingjia.pjset.domain.PjSetTemplate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 教学评价-评教设置
 */
@Mapper
public interface PjSetTargetMapper {

    List<PjSetTarget> getPjSetTargetList(PjSetTarget target);

    Boolean insertTarget(PjSetTarget target);

    Boolean updateTarget(PjSetTarget target);

    Boolean deleteTarget(@Param("targetCode") String targetCode);
}
