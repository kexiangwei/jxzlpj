package com.mycode.jiaoxuejiangcheng.jyjxcgj.mapper;

import com.mycode.jiaoxuejiangcheng.jyjxcgj.domian.Jyjxcgj;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 教学奖惩-教育教学成果奖
 */
@Mapper
public interface JyjxcgjMapper {

    List<Jyjxcgj> getPageList(Jyjxcgj obj);

    int getNotShenHeNum(@Param("shenHeUserId") Integer shenHeUserId);

    boolean insert(Jyjxcgj obj);

    boolean update(Jyjxcgj obj);

    boolean delete(@Param("objCode") String objCode);

}
