package com.mycode.jxzlpj.jiaoxuejiangcheng.jsgrcgj.mapper;

import com.mycode.jxzlpj.jiaoxuejiangcheng.jsgrcgj.domian.Jsgrcgj;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 教学奖惩-教师个人成果奖
 */
@Mapper
public interface JsgrcgjMapper {

    List<Jsgrcgj> getPageList(Jsgrcgj jsgrcgj);

    boolean insert(Jsgrcgj jsgrcgj);

    boolean update(Jsgrcgj jsgrcgj);

    boolean delete(@Param("code") String code);

}
