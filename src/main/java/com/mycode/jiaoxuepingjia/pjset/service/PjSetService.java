package com.mycode.jiaoxuepingjia.pjset.service;

import com.mycode.jiaoxuepingjia.pjset.domain.PjSetTarget;
import com.mycode.jiaoxuepingjia.pjset.domain.PjSet;
import com.mycode.jiaoxuepingjia.pjset.domain.PjSetTemplate;

import java.util.List;
import java.util.Map;

/**
 * 教学评价-评教设置
 */
public interface PjSetService {

    Map<String, Object> getPjSetList(PjSet pjSet);

    Map<String, Object> getPjSetTemplateList(PjSetTemplate pjSetTemplate);

    List<PjSetTarget> getPjSetTargetList(PjSetTarget pjSetTarget);

    Map<String, Object> getCurrentTemplate(String templateType);
}
