package com.mycode.common.shenhe.mapper;

import com.mycode.jiaoxuejiangcheng.jyjxcgj.domian.Jyjxcgj;
import com.mycode.system.menu.domain.Menu;
import com.mycode.system.role.domain.Role;
import com.mycode.common.shenhe.domain.ShenHe;
import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.common.shenhe.domain.ShenHeSet;
import com.mycode.common.shenhe.domain.ShenHeNode;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ShenHeMapper {

    //ShenHeSet
    List<ShenHeSet> getShenHeSetPageList(ShenHeSet shenHeSet);

    boolean addShenheSet(ShenHeSet shenHeSet);

    @Update("update COMMON_SHENHE_SET set status = 0 where menu_id = #{menuId}")
    boolean updateShenheSetStatusByMenuId(@Param("menuId") Long menuId);

    boolean updateShenheSetByCode(ShenHeSet shenHeSet);

    int batchDeleteShenHeSet(@Param("codeArr") String[] codeArr);

    //ShenHeNode
    List<ShenHeNode> getShenheNodeList(@Param("shenheCode") String shenheCode, @Param("execLevel") Integer execLevel);

    @Select("SELECT * from COMMON_SHENHE_NODE where node_code = #{nodeCode}")
    ShenHeNode getShenHeNodeByCode(@Param("nodeCode") String nodeCode);

    boolean addShenHeNode(ShenHeNode shenHeNode);

    boolean updateShenHeNodeByCode(ShenHeNode shenHeNode);

    boolean batchUpdateShenHeNodeExecLevel(ShenHeNode shenHeNode);

    boolean deleteShenHeNodeByCode(@Param("nodeCode") String nodeCode);

    //获取审核流程
    @ResultType(ShenHe.class)
    @Select("SELECT * from COMMON_SHENHE where relation_code = #{relationCode}")
    List<ShenHe> getShenheByRelationCode(@Param("relationCode") String relationCode);
    @ResultType(ShenHeItem.class)
    @Select("SELECT * FROM COMMON_SHENHE_ITEM where relation_code = #{relationCode} and batch_num = #{batchNum} ORDER BY CREATE_DATE")
    List<ShenHeItem> getShenheItem(@Param("relationCode") String relationCode, @Param("batchNum") Integer batchNum);

    //获取当前处于激活状态的审核流程编号
    @ResultType(String.class)
    @Select("select shenhe_code from COMMON_SHENHE_SET where menu_id = #{menuId} and status = 1")
    String getActiveShenheCode(@Param("menuId") Integer code);

    //
    boolean batchSubimt(@Param("objList") List<?> objList);

    ShenHeNode getShenheNode(@Param("v_tab_shenhe") String v_tab_shenhe, @Param("relationCode") String relationCode, @Param("userId") Integer userId);

    boolean toShenhe(ShenHeItem status);

    int isShenhePass(@Param("v_tab_shenhe") String v_tab_shenhe, @Param("relationCode") String relationCode, @Param("batchNum") Integer batchNum);

    @Update("update COMMON_SHENHE set status = #{status} where relation_code = #{relationCode} and batch_num = #{batchNum}")
    boolean changeStatus(@Param("relationCode") String relationCode, @Param("batchNum") Integer batchNum, @Param("status") String status);

}
