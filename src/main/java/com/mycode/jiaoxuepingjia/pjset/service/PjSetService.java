package com.mycode.jiaoxuepingjia.pjset.service;

import com.mycode.jiaoxuepingjia.pjset.domain.PjSetTarget;
import com.mycode.jiaoxuepingjia.pjset.domain.PjSetTemplate;

import java.util.List;
import java.util.Map;

/**
 * 教学评价-评教设置
 */
public interface PjSetService {

    Map<String, Object> getExecTemplate(String templateType);

    Map<String, Object> getPjSetTemplateList(PjSetTemplate pjSetTemplate);

    Boolean insertOrUpdateTemplate(PjSetTemplate template,String[] targetCodes);

    Boolean deleteTemplate(String templateCode);

    List<PjSetTarget> getPjSetTargetList(PjSetTarget pjSetTarget);

    Boolean insertOrUpdateTarget(PjSetTarget target);

    Boolean deleteTarget(String targetCode);
}
