package com.mycode.shaungchuangjiaoyu.wtbs.mapper;

import com.mycode.shaungchuangjiaoyu.Stu;
import com.mycode.shaungchuangjiaoyu.wtbs.domian.Wtbs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 双创教育-文、体类比赛
 */
@Mapper
public interface WtbsMapper {

    int getNotShenHeNum(@Param("shenHeUserId") Integer shenHeUserId);

    List<Wtbs> getPageList(Wtbs obj);

    boolean insert(Wtbs obj);

    boolean update(Wtbs obj);

    boolean delete(@Param("objCode") String objCode);

    List<Stu> getStuInfo(@Param("relationCode") String relationCode);

    boolean addStuInfo(Stu stu);

    boolean delStuInfo(@Param("relationCode") String relationCode, @Param("stuCode") String stuCode);
}
