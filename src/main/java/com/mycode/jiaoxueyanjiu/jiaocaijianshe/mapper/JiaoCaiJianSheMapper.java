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
 * @auther kexiangwei
 * @date 2019/11/13
 */
@Mapper
public interface JiaoCaiJianSheMapper {

    List<JiaoCaiJianShe> getPageList(JiaoCaiJianShe jiaoCaiJianShe);

    int getNotShenHeNum(@Param("shenHeUserId") Integer shenHeUserId);

    boolean insert(JiaoCaiJianShe jiaoCaiJianShe);

    boolean update(JiaoCaiJianShe jiaoCaiJianShe);

    @Delete("delete from JXYJ_JCJS where code = #{code}")
    boolean delete(@Param("code") String code);

    boolean batchSubimt(@Param("jiaoCaiJianSheList") List<JiaoCaiJianShe> jiaoCaiJianSheList);

    ShenHeNode getShenheNode(@Param("relationCode") String relationCode, @Param("userId") Integer userId);

    int isShenhePass(@Param("relationCode") String relationCode, @Param("batchNum") Integer batchNum);
}
