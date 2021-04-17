package com.mycode.jxzlpj.jiaoxuexiaoguo.kczlfxbg.mapper;

import com.mycode.common.common.domain.Course;
import com.mycode.jxzlpj.jiaoxuexiaoguo.kczlfxbg.doman.Kczlfxbg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface KczlfxbgMapper {

    List<Kczlfxbg> getPageList(Kczlfxbg kczlfxbg);

    Kczlfxbg getKczlfxbg(@Param("code") String code);

    boolean insert(Kczlfxbg kczlfxbg);

    boolean update(Kczlfxbg kczlfxbg);

    boolean delete(@Param("code") String code);
}
