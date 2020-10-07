package com.mycode.jiaoxueyanjiu.jixujiaoyu.mapper;

import com.mycode.jiaoxueyanjiu.jixujiaoyu.domian.JiXuJiaoYu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 教学研究-继续教育
 */
@Mapper
public interface JiXuJiaoYuMapper {

    List<JiXuJiaoYu> getPageList(JiXuJiaoYu jiXuJiaoYu);

    boolean insert(JiXuJiaoYu jiXuJiaoYu);

    boolean update(JiXuJiaoYu jiXuJiaoYu);

    boolean delete(@Param("code") String code);

}
