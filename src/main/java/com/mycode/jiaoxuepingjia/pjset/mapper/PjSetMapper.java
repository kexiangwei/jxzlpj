package com.mycode.jiaoxuepingjia.pjset.mapper;

import com.mycode.jiaoxuepingjia.pjset.domain.PjSetTarget;
import com.mycode.jiaoxuepingjia.pjset.domain.PjSet;
import com.mycode.jiaoxuepingjia.pjset.domain.PjSetTemplate;
import com.mycode.jiaoxuepingjia.xspj.domain.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 教学评价-评教设置
 * @auther kexiangwei
 * @date 2019/10/8
 */
@Mapper
public interface PjSetMapper {

    List<PjSet> getPjSetList(PjSet pjSet);

    List<PjSetTemplate> getPjSetTemplateList(PjSetTemplate pjSetTemplate);

    List<PjSetTarget> getPjSetTargetList(PjSetTarget pjSetTarget);

    String isPj();
    List<PjSetTarget> getPjSetTargetListByTemplateCode(@Param("templateCode") String templateCode);
}
