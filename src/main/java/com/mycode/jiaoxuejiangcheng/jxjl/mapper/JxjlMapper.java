package com.mycode.jiaoxuejiangcheng.jxjl.mapper;

import com.mycode.jiaoxuejiangcheng.jxjl.domian.Jxjl;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 教学奖惩-教学奖励
 */
@Mapper
public interface JxjlMapper {

    List<Jxjl> getPageList(Jxjl obj);

    int getNotShenHeNum(@Param("shenHeUserId") Integer shenHeUserId);

    boolean insert(Jxjl obj);

    boolean update(Jxjl obj);

    boolean delete(@Param("objCode") String objCode);

}
