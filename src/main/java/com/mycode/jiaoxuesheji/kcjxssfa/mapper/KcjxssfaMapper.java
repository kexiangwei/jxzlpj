package com.mycode.jiaoxuesheji.kcjxssfa.mapper;

import com.mycode.jiaoxuesheji.kcjxssfa.domian.Course;
import com.mycode.jiaoxuesheji.kcjxssfa.domian.Kcjxssfa;
import com.mycode.jiaoxuesheji.kcjxssfa.domian.KcjxssfaItem;
import com.mycode.shaungchuangjiaoyu.wtbs.domian.Wtbs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 教学设计-课程教学实施方案
 */
@Mapper
public interface KcjxssfaMapper {

    List<Kcjxssfa> getPageList(Kcjxssfa kcjxssfa);

    List<KcjxssfaItem> getItemListByRelationCode(@Param("relationCode") String relationCode);

    List<Course> getCourseListByUserId(@Param("userId") String userId);

    boolean insert(KcjxssfaItem item);

    boolean insertItem(KcjxssfaItem item);

    /*boolean update(Kcjxssfa kcjxssfa);

    boolean delete(@Param("code") String code);*/
}
