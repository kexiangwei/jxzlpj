package com.mycode.jiaoxuepingjia.pjset.mapper;

import com.mycode.jiaoxuepingjia.pjset.domain.PjSetTarget;
import com.mycode.jiaoxuepingjia.pjset.domain.PjSet;
import com.mycode.jiaoxuepingjia.pjset.domain.PjSetTemplate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 教学评价-评教设置
 */
@Mapper
public interface PjSetMapper {

    List<PjSet> getPjSetList(PjSet pjSet);

    List<PjSetTemplate> getPjSetTemplateList(PjSetTemplate pjSetTemplate);

    List<PjSetTarget> getPjSetTargetList(PjSetTarget pjSetTarget);

    String isPj(@Param("templateType") String templateType);

    List<PjSetTarget> getPjSetTargetListByTemplateCode(@Param("templateCode") String templateCode);
}
