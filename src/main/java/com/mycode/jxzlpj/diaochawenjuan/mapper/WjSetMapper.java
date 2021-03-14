package com.mycode.jxzlpj.diaochawenjuan.mapper;

import com.mycode.jxzlpj.diaochawenjuan.domain.WjSet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface WjSetMapper {

    List<WjSet> getWjSetPageList(WjSet wjSet);

    List<WjSet> getWjSetQ(@Param("wjCode") String wjCode);

    List<Map<String, Object>> getWjSetOpt();

    List<Map<String, Object>> getWjInfo(@Param("wjCode") String wjCode, @Param("userId") String userId);

    boolean addWjInfo(@Param("wjSet") WjSet wjSet, @Param("paramMap") Map<String, Object> paramMap);
}
