package com.mycode.jxzlpj.shaungchuangjiaoyu.xkzybs.mapper;

import com.mycode.jxzlpj.shaungchuangjiaoyu.xkzybs.domian.Xkzybs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 双创教育-学科专业比赛
 */
@Mapper
public interface XkzybsMapper {

    List<Xkzybs> getPageList(Xkzybs xkzybs);

    boolean insert(Xkzybs xkzybs);

    boolean update(Xkzybs xkzybs);

    boolean delete(@Param("code") String code);

}
