package com.mycode.jxzlpj.jiaoxuepingjia.pjset.service;

import com.mycode.jxzlpj.jiaoxuepingjia.pjset.domain.PjSetTarget;

import java.util.List;

/**
 * 教学评价-评教设置
 */
public interface PjSetTargetService {

    List<PjSetTarget> getPjSetTargetList(PjSetTarget target);

    Boolean insertOrUpdateTarget(PjSetTarget target);

    Boolean deleteTarget(String targetCode);
}
