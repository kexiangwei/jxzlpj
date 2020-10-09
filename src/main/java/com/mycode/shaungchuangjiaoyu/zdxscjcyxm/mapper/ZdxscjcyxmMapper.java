package com.mycode.shaungchuangjiaoyu.zdxscjcyxm.mapper;

import com.mycode.shaungchuangjiaoyu.zdxscjcyxm.domian.Zdxscjcyxm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 双创教育-指导学生参加创业项目
 */
@Mapper
public interface ZdxscjcyxmMapper {

    List<Zdxscjcyxm> getPageList(Zdxscjcyxm zdxscjcyxm);

    boolean insert(Zdxscjcyxm zdxscjcyxm);

    boolean update(Zdxscjcyxm zdxscjcyxm);

    boolean delete(@Param("code") String code);

}
