package com.mycode.jxzlpj.jiaoxuepingjia.thpj.mapper;

import com.mycode.jxzlpj.jiaoxuepingjia.thpj.domian.Ckpj;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 教学评价-同行评教
 */
@Mapper
public interface CkpjMapper {

    /*
    查看评教
     */
    List<Ckpj> getCkpjPageList(Ckpj ckpj);

    Map<String, Object> getPjInfo(@Param("courseCode") String courseCode, @Param("skjsCode") String skjsCode);

    List<Map<String, Object>> getPjInfoSuggestList(@Param("courseCode") String courseCode, @Param("skjsCode") String skjsCode);
}
