package com.mycode.jiaoxuepingjia.xspj.mapper;

import com.mycode.jiaoxuepingjia.xspj.domain.Target;
import com.mycode.jiaoxuepingjia.xspj.domain.Course;
import com.mycode.jiaoxuepingjia.xspj.domain.XspjSet;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @auther kexiangwei
 * @date 2019/10/8
 */
@Mapper
public interface XspjMapper {

    List<XspjSet> getXspjSetList(XspjSet xspjSet);

    List<Map<String, Object>> getXspjTemplateList();

    List<Target> getXspjTargetList();

    List<Target> getXspjTargetListByTemplateCode();

    List<Course> getXspjCourseList(Course course);
}
