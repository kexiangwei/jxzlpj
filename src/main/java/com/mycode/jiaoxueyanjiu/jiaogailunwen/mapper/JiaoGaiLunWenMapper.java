package com.mycode.jiaoxueyanjiu.jiaogailunwen.mapper;

import com.mycode.common.shenhe.domain.ShenHeNode;
import com.mycode.jiaoxueyanjiu.jiaogailunwen.domian.JiaoGaiLunWen;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 教学研究-教改论文
 * @auther kexiangwei
 * @date 2019/7/13
 */
@Mapper
public interface JiaoGaiLunWenMapper {

    List<JiaoGaiLunWen> getPageList(JiaoGaiLunWen jiaoGaiLunWen);

    int getNotShenHeNum(@Param("shenHeUserId") Integer shenHeUserId);

    JiaoGaiLunWen get(JiaoGaiLunWen jiaoGaiLunWen);

    boolean insert(JiaoGaiLunWen jiaoGaiLunWen);

    boolean update(JiaoGaiLunWen jiaoGaiLunWen);

    @Delete("delete from JXYJ_JGLW where code = #{code}")
    boolean delete(@Param("code") String code);

    boolean batchSubimt(@Param("jiaoGaiLunWenList") List<JiaoGaiLunWen> jiaoGaiLunWenList);

    ShenHeNode getShenheNode(@Param("relationCode") String relationCode, @Param("userId") Integer userId);

    int isShenhePass(@Param("relationCode") String relationCode, @Param("batchNum") Integer batchNum);
}
