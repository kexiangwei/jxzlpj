package com.mycode.jxzlpj.shaungchuangjiaoyu.zdxscy.mapper;

import com.mycode.jxzlpj.shaungchuangjiaoyu.zdxscy.domian.Zdxscy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 双创教育-指导学生创业
 */
@Mapper
public interface ZdxscyMapper {

    List<Zdxscy> getPageList(Zdxscy zdxscy);

    boolean insert(Zdxscy zdxscy);

    boolean update(Zdxscy zdxscy);

    boolean delete(@Param("code") String code);

}
