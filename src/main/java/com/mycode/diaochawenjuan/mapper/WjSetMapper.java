package com.mycode.diaochawenjuan.mapper;

import com.mycode.diaochawenjuan.domian.Question;
import com.mycode.diaochawenjuan.domian.WjSet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @auther kexiangwei
 * @date 2019/10/8
 */
@Mapper
public interface WjSetMapper {

    @Select("select * from QUESTIONNAIRE_SET")
    List<WjSet> getWjSetPageList(WjSet wjSet);

    @Select("select * from QUESTIONNAIRE_QUESTION")
    List<Question> getQuestionPageList(Question question);

    @Select("select * from QUESTIONNAIRE_QUESTION_OPTION where q_code = #{qCode}")
    List<Map<String, Object>> getOption(@Param("qCode") String qCode);
}
