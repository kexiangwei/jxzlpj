package com.mycode.jiaoxueyanjiu.jixujiaoyu.mapper;

import com.mycode.common.shenhe.domain.ShenHeNode;
import com.mycode.jiaoxueyanjiu.jixujiaoyu.domian.JiXuJiaoYu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 教学研究-继续教育
 * @auther kexiangwei
 * @date 2019/7/13
 */
@Mapper
public interface JiXuJiaoYuMapper {

    int getNotShenHeNum(@Param("shenHeUserId") Integer shenHeUserId);

    List<JiXuJiaoYu> getPageList(JiXuJiaoYu jiXuJiaoYu);

    boolean insert(JiXuJiaoYu jiXuJiaoYu);

    boolean update(JiXuJiaoYu jiXuJiaoYu);

    boolean delete(@Param("code") String code);

    boolean batchSubimt(@Param("jiXuJiaoYuList") List<JiXuJiaoYu> jiXuJiaoYuList);

    ShenHeNode getShenheNode(@Param("relationCode") String relationCode, @Param("userId") Integer userId);

    int isShenhePass(@Param("relationCode") String relationCode, @Param("batchNum") Integer batchNum);
}
