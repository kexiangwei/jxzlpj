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
public interface PjSetTemplateMapper {

    List<PjSetTemplate> getPjSetTemplateList(PjSetTemplate pjSetTemplate);

    String getActiveTemplateCodeByType(@Param("templateType") String templateType);

    Boolean insertTemplate(PjSetTemplate template);

    Boolean updateTemplate(PjSetTemplate template);

    Integer deleteTemplate(@Param("templateCode") String templateCode);

    List<PjSetTarget> getPjSetTargetListByTemplateCode(@Param("templateCode") String templateCode);

    Boolean insertPjSetTemplateTargets(@Param("templateCode") String templateCode, @Param("targetCodes") String[] targetCodes);

    Boolean deletePjSetTargetByTemplateCode(@Param("templateCode") String templateCode);
}
