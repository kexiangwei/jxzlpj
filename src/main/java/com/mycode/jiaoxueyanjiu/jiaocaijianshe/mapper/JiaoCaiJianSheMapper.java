package com.mycode.jiaoxueyanjiu.jiaocaijianshe.mapper;

import com.mycode.jiaoxueyanjiu.jiaocaijianshe.domian.JiaoCaiJianShe;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 教学研究-教材建设
 */
@Mapper
public interface JiaoCaiJianSheMapper {

    List<JiaoCaiJianShe> getPageList(JiaoCaiJianShe jiaoCaiJianShe);

    boolean insert(JiaoCaiJianShe jiaoCaiJianShe);

    boolean update(JiaoCaiJianShe jiaoCaiJianShe);

    boolean delete(@Param("code") String code);
}
