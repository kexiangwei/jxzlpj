package com.mycode.jxzlpj.jiaoxuepingjia.pjset.service;

import com.mycode.jxzlpj.jiaoxuepingjia.pjset.domain.PjSetTarget;
import com.mycode.jxzlpj.jiaoxuepingjia.pjset.domain.PjSetTemplate;

import java.util.List;
import java.util.Map;

/**
 * 教学评价-评教设置
 */
public interface PjSetTemplateService {

    Map<String, Object> getPjSetTemplateList(PjSetTemplate pjSetTemplate);

    String getActiveTemplateCodeByType(String templateType);

    List<PjSetTarget> getActiveTemplate(String templateCode);

    Boolean insertOrUpdateTemplate(PjSetTemplate template, String[] targetCodes);

    Boolean deleteTemplate(String templateCode);

}
