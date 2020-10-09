package com.mycode.jiaoxueyanjiu.jiaogailunwen.mapper;

import com.mycode.jiaoxueyanjiu.jiaogailunwen.domian.JiaoGaiLunWen;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 教学研究-教改论文
 */
@Mapper
public interface JiaoGaiLunWenMapper {

    List<JiaoGaiLunWen> getPageList(JiaoGaiLunWen jiaoGaiLunWen);

    boolean insert(JiaoGaiLunWen jiaoGaiLunWen);

    boolean update(JiaoGaiLunWen jiaoGaiLunWen);

    boolean delete(@Param("code") String code);
}
