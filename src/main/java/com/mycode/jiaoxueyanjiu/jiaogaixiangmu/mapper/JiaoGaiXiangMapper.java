package com.mycode.jiaoxueyanjiu.jiaogaixiangmu.mapper;

import com.mycode.jiaoxueyanjiu.jiaogaixiangmu.domain.JiaoGaiXiangMu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @auther kexiangwei
 * @date 2019/8/19
 */
@Mapper
public interface JiaoGaiXiangMapper {

    List<JiaoGaiXiangMu> getList(JiaoGaiXiangMu jiaoGaiXiangMu);

    boolean insert(JiaoGaiXiangMu jiaoGaiXiangMu);
}
