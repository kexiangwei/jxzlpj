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

    List<Map<String, Object>> getThpjTargetAvgList(@Param("userId") String userId, @Param("courseCode") String courseCode);

}
