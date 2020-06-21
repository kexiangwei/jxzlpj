package com.mycode.shaungchuangjiaoyu.xkzybs.mapper;

import com.mycode.shaungchuangjiaoyu.xkzybs.domian.Xkzybs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 双创教育-学科专业比赛
 */
@Mapper
public interface XkzybsMapper {

    int getNotShenHeNum(@Param("shenHeUserId") String shenHeUserId);

    List<Xkzybs> getPageList(Xkzybs obj);

    boolean insert(Xkzybs obj);

    boolean update(Xkzybs obj);

    boolean delete(@Param("objCode") String objCode);

}
