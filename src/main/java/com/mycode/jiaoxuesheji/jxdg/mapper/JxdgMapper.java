package com.mycode.jiaoxuesheji.jxdg.mapper;

import com.mycode.common.shenhe.domain.ShenHeNode;
import com.mycode.jiaoxuesheji.jxdg.domain.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 教学设计-教学大纲
 * @auther kexiangwei
 * @date 2019/10/8
 */
@Mapper
public interface JxdgMapper {

    List<Course> getCourseList(Course course);

    Integer getNotShenHeNum(@Param("shenHeUserId") String shenHeUserId);

    boolean batchSubimt(@Param("courseList") List<Course> courseList);

    ShenHeNode getShenheNode(@Param("relationCode") String relationCode, @Param("userId") Integer userId);

    int isShenhePass(@Param("relationCode") String relationCode, @Param("batchNum") Integer batchNum);
}
