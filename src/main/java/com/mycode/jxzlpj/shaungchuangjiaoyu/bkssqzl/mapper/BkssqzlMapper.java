package com.mycode.jxzlpj.shaungchuangjiaoyu.bkssqzl.mapper;

import com.mycode.jxzlpj.shaungchuangjiaoyu.bkssqzl.domian.Bkssqzl;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 双创教育-本科生申请专利
 */
@Mapper
public interface BkssqzlMapper {

    List<Bkssqzl> getPageList(Bkssqzl bkssqzl);

    boolean insert(Bkssqzl bkssqzl);

    boolean update(Bkssqzl bkssqzl);

    boolean delete(@Param("code") String code);

}
