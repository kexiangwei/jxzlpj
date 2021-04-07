package com.mycode.jxzlpj.jiaoxuexiaoguo.kczlfxbg.mapper;

import com.mycode.common.Course;
import com.mycode.jxzlpj.jiaoxuexiaoguo.kczlfxbg.doman.Kczlfxbg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface KczlfxbgMapper {

    List<Course> getPageList(Kczlfxbg kczlfxbg);

    boolean insert(Kczlfxbg kczlfxbg);

    boolean update(Kczlfxbg kczlfxbg);

    boolean delete(@Param("code") String code);
}
