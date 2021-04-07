package com.mycode.shenheSet.mapper;

import com.mycode.shenheSet.domain.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ShenHeMapper {

    List<ShenHe> getShenheByRelationCode(@Param("relationCode") String relationCode);

    List<ShenHeItem> getShenheItem(@Param("relationCode") String relationCode, @Param("batchNum") Integer batchNum);

    String getActiveShenheCode(@Param("menuId") Integer code);

    boolean batchSubimt(@Param("objList") List<? extends ShenHeObj> objList);

    ShenHeNode getShenheNode(@Param("viewName") String viewName, @Param("relationCode") String relationCode, @Param("userId") String userId);

    boolean toShenhe(ShenHeItem shenHeItem);

    boolean toZjShenhe(ShenHeItem item);

    int isShenhePass(@Param("viewName") String viewName, @Param("relationCode") String relationCode, @Param("batchNum") Integer batchNum);

    boolean changeStatus(@Param("relationCode") String relationCode, @Param("batchNum") Integer batchNum, @Param("status") String status);

    //有专家审核时调用
    int getNotShenHeNum(@Param("viewName") String viewName, @Param("shenHeUserId") String shenHeUserId
            , @Param("isZjshAccount") Integer isZjshAccount, @Param("jwcGly") Integer jwcGly);
    //有审核流程时调用
    default int getNotShenHeNum(@Param("viewName") String viewName, @Param("shenHeUserId") String shenHeUserId){
        return this.getNotShenHeNum(viewName,shenHeUserId,null,null);
    }

    @ResultType(Integer.class)
    @Select("SELECT COUNT(0) FROM SYS_USER_ROLE WHERE ROLE_ID = 4 AND USER_ID = #{shenHeUserId}")
    Integer isJwcGly(@Param("shenHeUserId") String shenHeUserId);

    @ResultType(Integer.class)
    @Select("SELECT COUNT(0) FROM SYS_USER_ROLE WHERE ROLE_ID = 3 AND USER_ID = #{shenHeUserId}")
    Integer isZjAccount(@Param("shenHeUserId") String shenHeUserId);

    List<ZjshItem> getZjshProcess(@Param("xmCode") String xmCode, @Param("batchNum") Integer batchNum);

    Integer isZjshAll(@Param("relationCode") String relationCode, @Param("batchNum") Integer batchNum);

}
