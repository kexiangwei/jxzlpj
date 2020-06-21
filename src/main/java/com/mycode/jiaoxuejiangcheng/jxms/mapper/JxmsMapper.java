package com.mycode.jiaoxuejiangcheng.jxms.mapper;

import com.mycode.jiaoxuejiangcheng.jxms.domian.Jxms;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 教学奖惩-教学名师
 */
@Mapper
public interface JxmsMapper {

    List<Jxms> getPageList(Jxms obj);

    int getNotShenHeNum(@Param("shenHeUserId") String shenHeUserId);

    boolean insert(Jxms obj);

    boolean update(Jxms obj);

    boolean delete(@Param("objCode") String objCode);

}
