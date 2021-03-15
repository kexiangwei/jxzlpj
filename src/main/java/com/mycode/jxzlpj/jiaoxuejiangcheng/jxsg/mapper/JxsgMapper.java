package com.mycode.jxzlpj.jiaoxuejiangcheng.jxsg.mapper;

import com.mycode.jxzlpj.jiaoxuejiangcheng.jxsg.domian.Jxsg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 教学奖惩-教学事故
 */
@Mapper
public interface JxsgMapper {

    int isAdmin(@Param("userId") String userId);

    List<Jxsg> getPageList(Jxsg obj);

    boolean insert(Jxsg obj);

    boolean update(Jxsg obj);

    boolean delete(@Param("objCode") String objCode);
}
