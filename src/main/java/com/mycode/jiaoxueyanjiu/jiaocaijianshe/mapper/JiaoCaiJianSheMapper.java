package com.mycode.jiaoxueyanjiu.jiaocaijianshe.mapper;

import com.mycode.common.shenhe.domain.ShenHeNode;
import com.mycode.jiaoxueyanjiu.jiaocaijianshe.domian.JiaoCaiJianShe;
import com.mycode.jiaoxueyanjiu.jiaogailunwen.domian.JiaoGaiLunWen;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 教学研究-教材建设
 */
@Mapper
public interface JiaoCaiJianSheMapper {

    int getNotShenHeNum(@Param("shenHeUserId") String shenHeUserId);

    List<JiaoCaiJianShe> getPageList(JiaoCaiJianShe jiaoCaiJianShe);

    boolean insert(JiaoCaiJianShe jiaoCaiJianShe);

    boolean update(JiaoCaiJianShe jiaoCaiJianShe);

    boolean delete(@Param("code") String code);

    boolean batchSubimt(@Param("jiaoCaiJianSheList") List<JiaoCaiJianShe> jiaoCaiJianSheList);

    ShenHeNode getShenheNode(@Param("relationCode") String relationCode, @Param("userId") String userId);

    int isShenhePass(@Param("relationCode") String relationCode, @Param("batchNum") Integer batchNum);
}
