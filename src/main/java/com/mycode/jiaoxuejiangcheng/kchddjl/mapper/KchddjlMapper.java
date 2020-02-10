package com.mycode.jiaoxuejiangcheng.kchddjl.mapper;

import com.mycode.jiaoxuejiangcheng.kchddjl.domian.Kchddjl;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 教学奖惩-课程获得的奖励
 */
@Mapper
public interface KchddjlMapper {

    List<Kchddjl> getPageList(Kchddjl obj);

    int getNotShenHeNum(@Param("shenHeUserId") Integer shenHeUserId);

    boolean insert(Kchddjl obj);

    boolean update(Kchddjl obj);

    boolean delete(@Param("objCode") String objCode);

}
