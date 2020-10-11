package com.mycode.commonset.shenheSet.mapper;

import com.mycode.commonset.shenheSet.domain.*;
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

    //ShenHe
    List<ShenHe> getShenheByRelationCode(@Param("relationCode") String relationCode);

    List<ShenHeItem> getShenheItem(@Param("relationCode") String relationCode, @Param("batchNum") Integer batchNum);

    String getActiveShenheCode(@Param("menuId") Integer code);

    boolean batchSubimt(@Param("objList") List<? extends ShenHeObj> objList);

    ShenHeNode getShenheNode(@Param("viewName") String viewName, @Param("relationCode") String relationCode, @Param("userId") String userId);

    boolean toShenhe(ShenHeItem shenHeItem);

    boolean toZjShenhe(ShenHeItem item);

    int isShenhePass(@Param("viewName") String viewName, @Param("relationCode") String relationCode, @Param("batchNum") Integer batchNum);

    boolean changeStatus(@Param("relationCode") String relationCode, @Param("batchNum") Integer batchNum, @Param("status") String status);

    //有专家审核时调用（教学团队）
    int getNotShenHeNum(@Param("viewName") String viewName, @Param("shenHeUserId") String shenHeUserId
            , @Param("isZjshAccount") Integer isZjshAccount, @Param("jwcGly") Integer jwcGly, @Param("menuId") String menuId);
    //有专家审核时调用
    default int getNotShenHeNum(@Param("viewName") String viewName, @Param("shenHeUserId") String shenHeUserId
            , @Param("isZjshAccount") Integer isZjshAccount, @Param("jwcGly") Integer jwcGly){
        return this.getNotShenHeNum(viewName,shenHeUserId,isZjshAccount,jwcGly,null);
    }
    //有审核流程时调用
    default int getNotShenHeNum(@Param("viewName") String viewName, @Param("shenHeUserId") String shenHeUserId){
        return this.getNotShenHeNum(viewName,shenHeUserId,null,null,null);
    }

    @ResultType(Integer.class)
    @Select("SELECT COUNT(0) FROM SYS_USER_ROLE WHERE ROLE_ID = 1 AND USER_ID = #{shenHeUserId}")
    Integer isJwcGly(@Param("shenHeUserId") String shenHeUserId);

    @ResultType(Integer.class)
    @Select("SELECT COUNT(0) FROM SYS_USER_ROLE WHERE ROLE_ID = 7 AND USER_ID = #{shenHeUserId}")
    Integer isZjAccount(@Param("shenHeUserId") String shenHeUserId);

    @ResultType(Integer.class)
    @Select("SELECT COUNT(0) FROM SYS_USER_ROLE WHERE ROLE_ID = 8 AND USER_ID = #{shenHeUserId}")
    Integer isJXTD_ZjAccount(@Param("shenHeUserId") String shenHeUserId);

    @ResultType(ZjshItem.class)
    @Select("SELECT * FROM COMMON_SHENHE_ZJSH WHERE XM_CODE = #{xmCode} AND BATCH_NUM = #{batchNum}")
    List<ZjshItem> getZjshProcess(@Param("xmCode") String xmCode, @Param("batchNum") Integer batchNum);

    Integer isZjshAll(@Param("relationCode") String relationCode, @Param("batchNum") Integer batchNum, @Param("flag") String flag);

    default Integer isZjshAll(@Param("relationCode") String relationCode, @Param("batchNum") Integer batchNum){
        return this.isZjshAll(relationCode,batchNum,null);
    }
}
