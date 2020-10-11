package com.mycode.jiaoxuejiangcheng.zyjscgj.mapper;

import com.mycode.jiaoxuejiangcheng.zyjscgj.domian.Zyjscgj;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 教学奖惩-专业建设成果奖
 */
@Mapper
public interface ZyjscgjMapper {

    List<Zyjscgj> getPageList(Zyjscgj zyjscgj);

    boolean insert(Zyjscgj zyjscgj);

    boolean update(Zyjscgj zyjscgj);

    boolean delete(@Param("code") String code);

}
