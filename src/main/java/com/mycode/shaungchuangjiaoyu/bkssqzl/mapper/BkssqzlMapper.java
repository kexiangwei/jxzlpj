package com.mycode.shaungchuangjiaoyu.bkssqzl.mapper;

import com.mycode.shaungchuangjiaoyu.bkssqzl.domian.Bkssqzl;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 双创教育-本科生申请专利
 */
@Mapper
public interface BkssqzlMapper {

    int getNotShenHeNum(@Param("shenHeUserId") String shenHeUserId);

    List<Bkssqzl> getPageList(Bkssqzl obj);

    boolean insert(Bkssqzl obj);

    boolean update(Bkssqzl obj);

    boolean delete(@Param("objCode") String objCode);

}
