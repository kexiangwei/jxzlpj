package com.mycode.jiaoxuexiaoguo.kczlfxbg.mapper;

import com.mycode.common.common.domain.Course;
import com.mycode.jiaoxuexiaoguo.kczlfxbg.doman.Kczlfxbg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

@Mapper
public interface KczlfxbgMapper {

    Set<String> getUserGroup(String userId);

    List<Course> getPageList(Kczlfxbg kczlfxbg);

    boolean insert(Kczlfxbg kczlfxbg);

    boolean update(Kczlfxbg kczlfxbg);

    boolean delete(@Param("code") String code);
}
