package com.mycode.shenheSet.mapper;

import com.mycode.shenheSet.domain.ShenHeNode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ShenHeNodeMapper {

    List<ShenHeNode> getShenheNodeList(@Param("shenheCode") String shenheCode, @Param("execLevel") Integer execLevel);

    @Select("SELECT * from COMMON_SHENHE_NODE where node_code = #{nodeCode}")
    ShenHeNode getShenHeNodeByCode(@Param("nodeCode") String nodeCode);

    boolean addShenHeNode(ShenHeNode shenHeNode);

    boolean updateShenHeNodeByCode(ShenHeNode shenHeNode);

    boolean batchUpdateShenHeNodeExecLevel(ShenHeNode shenHeNode);

    boolean deleteShenHeNodeByCode(@Param("nodeCode") String nodeCode);

}
