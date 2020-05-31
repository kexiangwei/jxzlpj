package com.mycode.jiaoxuesheji.kcssjh.mapper;

import com.mycode.jiaoxuesheji.kcssjh.domian.Kcssjh;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 教学设计-课程实施计划
 */
@Mapper
public interface KcssjhMapper {

    int getNotShenHeNum(@Param("shenHeUserId") Integer shenHeUserId);

    List<Kcssjh> getPageList(Kcssjh kcssjh);

    boolean insert(Kcssjh kcssjh);

    boolean update(Kcssjh kcssjh);

    boolean delete(@Param("code") String code);

}
