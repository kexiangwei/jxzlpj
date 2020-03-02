package com.mycode.shaungchuangjiaoyu.zdxscjcyxm.mapper;

import com.mycode.shaungchuangjiaoyu.zdxscjcyxm.domian.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 双创教育-指导学生参加创业项目
 */
@Mapper
public interface ProjectMapper {

    int getNotShenHeNum(@Param("shenHeUserId") Integer shenHeUserId);

    List<Project> getPageList(Project obj);

    boolean insert(Project obj);

    boolean update(Project obj);

    boolean delete(@Param("objCode") String objCode);

}
