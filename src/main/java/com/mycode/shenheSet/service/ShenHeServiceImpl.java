package com.mycode.shenheSet.service;

import com.mycode.shenheSet.domain.ShenHeObj;
import com.mycode.shenheSet.domain.ShenHe;
import com.mycode.shenheSet.domain.ShenHeItem;
import com.mycode.shenheSet.domain.ShenHeNode;
import com.mycode.shenheSet.mapper.ShenHeMapper;
import com.mycode.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShenHeServiceImpl implements ShenHeService {

    @Autowired
    private ShenHeMapper shenHeMapper;

    @Override
    public List<ShenHe> getShenheProcess(String relationCode) {
        List<ShenHe> shenHeList = shenHeMapper.getShenheByRelationCode(relationCode);
        if(shenHeList != null && shenHeList.size() >0){
            for (ShenHe shenHe : shenHeList) {
                List<ShenHeItem> shenHeItemList = shenHeMapper.getShenheItem(shenHe.getRelationCode(),shenHe.getBatchNum());
                shenHe.setShenHeItemList(shenHeItemList);
            }
        }
        return shenHeList;
    }

    @Override
    public String getActiveShenheCode(Integer menuId) {
        return shenHeMapper.getActiveShenheCode(menuId);
    }

    @Override
    public boolean toSubimt(String activeShenheCode, List<ShenHeObj> objList) {
        for (ShenHeObj shenHeObj : objList) {
            shenHeObj.setShenheCode(activeShenheCode);
            shenHeObj.setBatchNum(StringUtils.isEmpty(shenHeObj.getBatchNum())?1: shenHeObj.getBatchNum()+1);//提交批次，每提交一次加1
        }
        return shenHeMapper.batchSubimt(objList);
    }

    @Override
    public boolean toShenhe(ShenHeItem item, List<ShenHeObj> objList) {
        boolean bool = false;
        for (ShenHeObj shenHeObj : objList) {
            item.setRelationCode(shenHeObj.getCode());
            item.setBatchNum(shenHeObj.getBatchNum());
            if(item.getIsZjshAccount() != null && item.getIsZjshAccount()==1){
                bool = shenHeMapper.toZjShenhe(item); //专家审核
            }else{
                ShenHeNode node = shenHeMapper.getShenheNode(item.getViewName(),item.getRelationCode(), item.getUserId()); //获取符合当前用户的审核节点信息
                item.setNodeCode(node.getNodeCode());
                item.setNodeName(node.getNodeName());
                bool = shenHeMapper.toShenhe(item); //提交审核
                if(bool){
                    if("通过".equals(item.getStatus())){
                        int isPass = shenHeMapper.isShenhePass(item.getViewName(),item.getRelationCode(), item.getBatchNum());
                        if((isPass == 1 && "初审".equals(item.getShenheType())) || isPass != 1){
                            continue;
                        }
                    }
                    bool =  shenHeMapper.changeStatus(item.getRelationCode(),item.getBatchNum(),item.getStatus());
                }
            }
        }
        return bool;
    }
}
