package com.mycode.jiaoxuejiangcheng.qtbkjxjl.mapper;

import com.mycode.jiaoxuejiangcheng.qtbkjxjl.domian.Qtbkjxjl;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 教学奖惩-其他本科教学奖励
 */
@Mapper
public interface QtbkjxjlMapper {

    List<Qtbkjxjl> getPageList(Qtbkjxjl qtbkjxjl);

    boolean insert(Qtbkjxjl qtbkjxjl);

    boolean update(Qtbkjxjl qtbkjxjl);

    boolean delete(@Param("code") String code);

}
