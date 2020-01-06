package com.mycode.diaochawenjuan.mapper;

import com.mycode.diaochawenjuan.domian.WjQuestion;
import com.mycode.diaochawenjuan.domian.WjSet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 调查问卷
 * @auther kexiangwei
 * @date 2019/10/8
 */
@Mapper
public interface WjMapper {

    @Select("SELECT * FROM DCWJ_WJSET")
    List<WjSet> getWjSetPageList(WjSet wjSet);

    List<WjQuestion> getWjQuestionPageList(WjQuestion wjQuestion);

}
