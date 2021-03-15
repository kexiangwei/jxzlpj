package com.mycode.jxzlpj.jiaoxuejiangcheng.jyjxcgj.mapper;

import com.mycode.jxzlpj.jiaoxuejiangcheng.jyjxcgj.domian.Jyjxcgj;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 教学奖惩-教育教学成果奖
 */
@Mapper
public interface JyjxcgjMapper {

    List<Jyjxcgj> getPageList(Jyjxcgj jyjxcgj);

    boolean insert(Jyjxcgj jyjxcgj);

    boolean update(Jyjxcgj jyjxcgj);

    boolean delete(@Param("code") String code);

}
