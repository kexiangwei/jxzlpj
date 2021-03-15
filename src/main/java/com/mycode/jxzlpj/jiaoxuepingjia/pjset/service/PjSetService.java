package com.mycode.jxzlpj.jiaoxuepingjia.pjset.service;

import com.mycode.jxzlpj.jiaoxuepingjia.pjset.domain.PjSetTarget;
import com.mycode.jxzlpj.jiaoxuepingjia.pjset.domain.PjSetTemplate;

import java.util.List;
import java.util.Map;

/**
 * 教学评价-评教设置
 */
public interface PjSetService {

    String getActiveTemplateCode(String templateType);

    List<PjSetTarget> getActiveTemplate(String templateCode);


    Map<String, Object> getPjSetTemplateList(PjSetTemplate pjSetTemplate);

    Boolean insertOrUpdateTemplate(PjSetTemplate template,String[] targetCodes);

    Boolean deleteTemplate(String templateCode);


    List<PjSetTarget> getPjSetTargetList(PjSetTarget target);

    Boolean insertOrUpdateTarget(PjSetTarget target);

    Boolean deleteTarget(String targetCode);
}
