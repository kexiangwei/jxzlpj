package com.mycode.jxzlpj.shaungchuangjiaoyu.wtbs.mapper;

import com.mycode.jxzlpj.shaungchuangjiaoyu.wtbs.domian.Wtbs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 双创教育-文、体类比赛
 */
@Mapper
public interface WtbsMapper {

    List<Wtbs> getPageList(Wtbs wtbs);

    boolean insert(Wtbs wtbs);

    boolean update(Wtbs wtbs);

    boolean delete(@Param("code") String code);

}
