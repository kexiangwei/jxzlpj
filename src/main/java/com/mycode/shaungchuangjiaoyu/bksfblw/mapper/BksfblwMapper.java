package com.mycode.shaungchuangjiaoyu.bksfblw.mapper;

import com.mycode.shaungchuangjiaoyu.bksfblw.domian.Bksfblw;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 双创教育-本科生发表论文
 */
@Mapper
public interface BksfblwMapper {

    int getNotShenHeNum(@Param("shenHeUserId") Integer shenHeUserId);

    List<Bksfblw> getPageList(Bksfblw obj);

    boolean insert(Bksfblw obj);

    boolean update(Bksfblw obj);

    boolean delete(@Param("objCode") String objCode);

}
