package com.mycode.jxzlpj.shaungchuangjiaoyu.bksfblw.mapper;

import com.mycode.jxzlpj.shaungchuangjiaoyu.bksfblw.domian.Bksfblw;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 双创教育-本科生发表论文
 */
@Mapper
public interface BksfblwMapper {

    List<Bksfblw> getPageList(Bksfblw bksfblw);

    boolean insert(Bksfblw bksfblw);

    boolean update(Bksfblw bksfblw);

    boolean delete(@Param("code") String code);

}
