package com.mycode.jiaoxuepingjia.xspj.mapper;

import com.mycode.jiaoxuepingjia.xspj.domain.Xspj;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 教学评价-学生评教
 */
@Mapper
public interface XspjMapper {

    List<Xspj> getPageList(Xspj xspj);

    boolean insert(@Param("xspj") Xspj xspj, @Param("templateCode") String templateCode, @Param("paramMap") Map<String,Object> paramMap);

    List<Map<String, Object>> getPjInfo(@Param("courseCode") String courseCode);

    List<String> getPjSuggestList(@Param("courseCode") String courseCode, @Param("templateCode") String templateCode);
}
