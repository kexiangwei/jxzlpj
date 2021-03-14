package com.mycode.jxzlpj.jiaoxueyanjiu.jixujiaoyu.mapper;

import com.mycode.jxzlpj.jiaoxueyanjiu.jixujiaoyu.domian.JiXuJiaoYu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 教学研究-继续教育
 */
@Mapper
public interface JiXuJiaoYuMapper {

    Integer getNotShenHeNumByAuth(@Param("shenHeUserId") String shenHeUserId, @Param("maxAuthLevel") String maxAuthLevel, @Param("collegeCode") String collegeCode);

    List<JiXuJiaoYu> getPageList(JiXuJiaoYu jiXuJiaoYu);

    boolean insert(JiXuJiaoYu jiXuJiaoYu);

    boolean update(JiXuJiaoYu jiXuJiaoYu);

    boolean delete(@Param("code") String code);
}
