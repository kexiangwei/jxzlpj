package com.mycode.jxzlpj.jiaoxuexiaoguo.kczlfxbg.mapper;

import com.mycode.jxzlpj.jiaoxuexiaoguo.kczlfxbg.doman.Kczlfxbg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;

@Mapper
public interface KczlfxbgMapper {

    List<Kczlfxbg> getPageList(Kczlfxbg kczlfxbg);

    Kczlfxbg getKczlfxbg(@Param("code") String code);

    List<Map<String, Object>> getKczlfxbgA1(@Param("relationCode") String relationCode);

    List<Map<String, Object>> getKczlfxbgA2(@Param("relationCode") String relationCode);

    boolean insert(Kczlfxbg kczlfxbg);

    boolean insertA1(@Param("relationCode") String relationCode, @Param("maplist") List<SortedMap<String, Object>> maplist);

    boolean insertA2(@Param("relationCode") String relationCode, @Param("maplist") List<SortedMap<String, Object>> maplist);

    boolean update(Kczlfxbg kczlfxbg);

    boolean submit(@Param("code") String code);

    boolean deleteA1(@Param("relationCode") String relationCode);

    boolean deleteA2(@Param("relationCode") String relationCode);
}
