package com.mycode.jiaoxuejiangcheng.kcjscgj.mapper;

import com.mycode.jiaoxuejiangcheng.kcjscgj.domian.Kcjscgj;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 教学奖惩-课程建设成果奖
 */
@Mapper
public interface KcjscgjMapper {

    List<Kcjscgj> getPageList(Kcjscgj kcjscgj);

    boolean insert(Kcjscgj kcjscgj);

    boolean update(Kcjscgj kcjscgj);

    boolean delete(@Param("code") String code);

}
