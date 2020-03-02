package com.mycode.shaungchuangjiaoyu.zdxscy.mapper;

import com.mycode.shaungchuangjiaoyu.zdxscy.domian.Zdxscy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 双创教育-指导学生创业
 */
@Mapper
public interface ZdxscyMapper {

    int getNotShenHeNum(@Param("shenHeUserId") Integer shenHeUserId);

    List<Zdxscy> getPageList(Zdxscy obj);

    boolean insert(Zdxscy obj);

    boolean update(Zdxscy obj);

    boolean delete(@Param("objCode") String objCode);

}
