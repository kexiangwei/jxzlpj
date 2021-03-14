package com.mycode.jxzlpj.jiaoxuesheji.kcjxdg.mapper;

import com.mycode.jxzlpj.jiaoxuesheji.kcjxdg.domian.Kcjxdg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 教学设计-课程教学大纲
 */
@Mapper
public interface KcjxdgMapper {

    List<Kcjxdg> getPageList(Kcjxdg kcjxdg);

    boolean insert(Kcjxdg kcjxdg);

    boolean update(Kcjxdg kcjxdg);

    boolean delete(@Param("code") String code);

}
