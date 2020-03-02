package com.mycode.shaungchuangjiaoyu.zdxscy.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.common.file.domain.FileInfo;
import com.mycode.common.file.mapper.FileMapper;
import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.common.shenhe.domain.ShenHeNode;
import com.mycode.common.shenhe.mapper.ShenHeMapper;
import com.mycode.shaungchuangjiaoyu.zdxscy.domian.Zdxscy;
import com.mycode.shaungchuangjiaoyu.zdxscy.mapper.ZdxscyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 双创教育-指导学生创业
 */
@Service
public class ZdxscyServiceImpl implements ZdxscyService {

    @Autowired
    private ZdxscyMapper zdxscyMapper;
    @Autowired
    private ShenHeMapper shenHeMapper;

    @Override
    public Map<String, Object> getPageList(Zdxscy obj) {
        Map<String, Object> resultMap = new HashMap<>();
        if(!StringUtils.isEmpty(obj.getShenHeUserId())){
            int unShenHeNum = zdxscyMapper.getNotShenHeNum(obj.getShenHeUserId());//获取未审核数
            resultMap.put("unShenHeNum", unShenHeNum);
        }
        Page<Object> pageInfo = PageHelper.startPage(obj.getPageIndex(), obj.getPageSize());
        List<Zdxscy> pageList = zdxscyMapper.getPageList(obj);
        resultMap.put("totalNum",pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public boolean insert(Zdxscy obj) {
        return zdxscyMapper.insert(obj);
    }

    @Override
    public boolean update(Zdxscy obj) {
        return zdxscyMapper.update(obj);
    }

    @Override
    public boolean delete(String objCode) {
        return zdxscyMapper.delete(objCode);
    }

    @Override
    public boolean toSubimt(String activeShenheCode, List<Zdxscy> objList) {
        for (Zdxscy obj : objList) {
            obj.setShenheCode(activeShenheCode);
            obj.setBatchNum(StringUtils.isEmpty(obj.getBatchNum())?1:obj.getBatchNum()+1);//提交批次，每提交一次加1
        }
        return shenHeMapper.batchSubimt(objList);
    }

    @Override
    public boolean toShenhe(ShenHeItem item, List<Zdxscy> objList) {
        boolean bool = false;
        for (Zdxscy obj : objList) {
            item.setRelationCode(obj.getCode());
            item.setBatchNum(obj.getBatchNum());
            ShenHeNode node = shenHeMapper.getShenheNode("V_SCJY_ZDXSCY_SHENHE",item.getRelationCode(), item.getUserId()); //获取符合当前用户的审核节点信息
            item.setNodeCode(node.getNodeCode());
            item.setNodeName(node.getNodeName());
            bool = shenHeMapper.toShenhe(item); //提交审核
            if(bool){
                if(item.getStatus().equals("退回")){
                    return shenHeMapper.changeStatus(item.getRelationCode(),item.getBatchNum(),"退回");
                } else { // 通过 | 未通过
                    int isPass = shenHeMapper.isShenhePass("V_SCJY_ZDXSCY_SHENHE",item.getRelationCode(), item.getBatchNum());
                    if(isPass == 1){
                        return shenHeMapper.changeStatus(item.getRelationCode(),item.getBatchNum(),item.getStatus());
                    }
                }
            }
        }
        return bool;
    }

}
