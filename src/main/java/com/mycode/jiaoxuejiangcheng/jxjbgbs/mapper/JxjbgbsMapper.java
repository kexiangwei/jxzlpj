package com.mycode.jiaoxuejiangcheng.jxjbgbs.mapper;

import com.mycode.jiaoxuejiangcheng.jxjbgbs.domian.Jxjbgbs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 教学奖惩-教学基本功比赛
 */
@Mapper
public interface JxjbgbsMapper {

    List<Jxjbgbs> getPageList(Jxjbgbs obj);

    int getNotShenHeNum(@Param("shenHeUserId") String shenHeUserId);

    boolean insert(Jxjbgbs obj);

    boolean update(Jxjbgbs obj);

    boolean delete(@Param("objCode") String objCode);

}
