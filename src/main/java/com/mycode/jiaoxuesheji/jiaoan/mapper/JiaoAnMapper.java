package com.mycode.jiaoxuesheji.jiaoan.mapper;

import com.mycode.common.shenhe.domain.ShenHeNode;
import com.mycode.jiaoxuesheji.jiaoan.domian.JiaoAn;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 教学设计-教案
 * @auther kexiangwei
 * @date 2019/11/13
 */
@Mapper
public interface JiaoAnMapper {

    List<JiaoAn> getPageList(JiaoAn jiaoAn);

    int getNotShenHeNum(@Param("shenHeUserId") Integer shenHeUserId);

    boolean insert(JiaoAn jiaoAn);

    boolean update(JiaoAn jiaoAn);

    @Delete("delete from JXSJ_JA where code = #{code}")
    boolean delete(@Param("code") String code);

    boolean batchSubimt(@Param("jiaoAnList") List<JiaoAn> jiaoAnList);

    ShenHeNode getShenheNode(@Param("relationCode") String relationCode, @Param("userId") Integer userId);

    int isShenhePass(@Param("relationCode") String relationCode, @Param("batchNum") Integer batchNum);
}
