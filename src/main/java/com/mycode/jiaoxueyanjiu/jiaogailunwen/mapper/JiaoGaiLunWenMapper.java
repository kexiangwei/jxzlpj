package com.mycode.jiaoxueyanjiu.jiaogailunwen.mapper;

import com.mycode.common.shenhe.domain.ShenHeNode;
import com.mycode.jiaoxueyanjiu.jiaogailunwen.domian.JiaoGaiLunWen;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 教学研究-教改论文
 */
@Mapper
public interface JiaoGaiLunWenMapper {

    int getNotShenHeNum(@Param("shenHeUserId") String shenHeUserId);

    List<JiaoGaiLunWen> getPageList(JiaoGaiLunWen jiaoGaiLunWen);

    JiaoGaiLunWen get(JiaoGaiLunWen jiaoGaiLunWen);

    boolean insert(JiaoGaiLunWen jiaoGaiLunWen);

    boolean update(JiaoGaiLunWen jiaoGaiLunWen);

    boolean delete(@Param("code") String code);

    boolean batchSubimt(@Param("jiaoGaiLunWenList") List<JiaoGaiLunWen> jiaoGaiLunWenList);

    ShenHeNode getShenheNode(@Param("relationCode") String relationCode, @Param("userId") String userId);

    int isShenhePass(@Param("relationCode") String relationCode, @Param("batchNum") Integer batchNum);
}
