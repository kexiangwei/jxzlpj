package com.mycode.common.shenhe.mapper;

import com.mycode.system.menu.domain.Menu;
import com.mycode.system.role.domain.Role;
import com.mycode.common.shenhe.domain.ShenHe;
import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.common.shenhe.domain.ShenHeSet;
import com.mycode.common.shenhe.domain.ShenHeNode;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @auther kexiangwei
 * @date 2019/6/24
 */
@Mapper
public interface ShenHeMapper {

    List<ShenHeSet> getShenHeList(ShenHeSet shenHe);

    List<Menu> getMenuParentList(@Param("menuId") Long menuId);

    List<Menu> getMenuListForShenHe();

    boolean addShenhe(ShenHeSet shenHe);

    boolean updateShenheByCode(ShenHeSet shenHe);

    @Update("update COMMON_SHENHE_SET set status = 0 where menu_id = #{menuId}")
    boolean updateShenheByMenuId(@Param("menuId") Long menuId);

    @ResultType(ShenHe.class)
    @Select("SELECT * from COMMON_SHENHE where relation_code = #{relationCode}")
    List<ShenHe> getShenheByRelationCode(@Param("relationCode") String relationCode);

    @ResultType(ShenHeItem.class)
    @Select("SELECT * FROM COMMON_SHENHE_ITEM where relation_code = #{relationCode} and batch_num = #{batchNum} ORDER BY CREATE_DATE")
    List<ShenHeItem> getShenheItem(@Param("relationCode") String relationCode, @Param("batchNum") Integer batchNum);
    /*
    获取当前处于激活状态的审核流程编号
     */
    @ResultType(String.class)
    @Select("select shenhe_code from COMMON_SHENHE_SET where menu_id = #{menuId} and status = 1")
    String getActiveShenheCode(@Param("menuId") Integer code);

    boolean toShenhe(ShenHeItem status);

    @Update("update COMMON_SHENHE set status = #{status} where relation_code = #{relationCode} and batch_num = #{batchNum}")
    boolean changeStatus(@Param("relationCode") String relationCode, @Param("batchNum") Integer batchNum, @Param("status") String status);

    boolean batchDelete(@Param("codeArr") String[] codeArr);
    //
    List<ShenHeNode> getShenheNodeList(@Param("shenheCode") String shenheCode, @Param("execLevel") Integer execLevel);

    @Select("SELECT * from COMMON_SHENHE_NODE where node_code = #{nodeCode}")
    ShenHeNode getShenHeNodeByCode(@Param("nodeCode") String nodeCode);

    boolean addShenHeNode(ShenHeNode node);

    boolean updateShenHeNodeByCode(ShenHeNode node);

    @Update("update COMMON_SHENHE_NODE set exec_level = exec_level-1 where shenhe_code = #{shenheCode} and exec_level > #{execLevel}")
    boolean batchUpdateShenHeNode(ShenHeNode dbNode);

    @Delete("delete from COMMON_SHENHE_NODE where node_code = #{nodeCode}")
    boolean deleteShenHeNodeByCode(@Param("nodeCode") String nodeCode);
}
