package com.mycode.jiaoxuesheji.skjh.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.common.shenhe.domain.ShenHeNode;
import com.mycode.common.shenhe.mapper.ShenHeMapper;
import com.mycode.jiaoxuesheji.skjh.domian.Skjh;
import com.mycode.jiaoxuesheji.skjh.domian.SkjhItem;
import com.mycode.jiaoxuesheji.skjh.mapper.SkjhMapper;
import com.mycode.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教学设计-授课计划
 */
@Service
public class SkjhServiceImpl implements SkjhService {

    @Autowired
    private SkjhMapper skjhMapper;
    @Autowired
    private ShenHeMapper shenHeMapper;

    @Override
    public Map<String, Object> getPageList(Skjh skjh) {
        Map<String, Object> resultMap = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(skjh.getPageIndex(), skjh.getPageSize());
        List<Skjh> pageList = null;
        if(StringUtils.isNotEmpty(skjh.getShenHeUserId())){
            resultMap.put("unShenHeNum", skjhMapper.getNotShenHeNum(skjh.getShenHeUserId())); //获取未审核数
            pageList = skjhMapper.getShenHePageList(skjh);
        }else{
            pageList = skjhMapper.getPageList(skjh);
        }
        resultMap.put("totalNum", pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public boolean insert(SkjhItem skjhItem) {
        Skjh skJhInfoByCode = skjhMapper.getSkJhInfoByCode(skjhItem.getCode());
        if(skJhInfoByCode == null){
            boolean bool = skjhMapper.insert(skjhItem); //使用了多态特性
            if(!bool){
                return false;
            }
        }
        return skjhMapper.insertSkjhItem(skjhItem);
    }

    @Override
    public boolean update(Skjh skjh) {
        return skjhMapper.update(skjh);
    }

    @Override
    public boolean delete(String code) {
        boolean bool = skjhMapper.delete(code);
        if(bool){
            List<SkjhItem> skjhItemList = skjhMapper.getSkjhItemList(code);
            if(!skjhItemList.isEmpty()){
                bool = skjhMapper.deleteSkjhItem(code,null);
            }
        }
        return bool;
    }

    @Override
    public boolean toSubimt(String activeShenheCode, List<Skjh> skjhList) {
        for (Skjh skjh : skjhList) {
            skjh.setShenheCode(activeShenheCode);
            skjh.setBatchNum(StringUtils.isEmpty(skjh.getBatchNum())?1:skjh.getBatchNum()+1);//提交批次，每提交一次加1
        }
        return shenHeMapper.batchSubimt(skjhList);
    }

    @Override
    public boolean toShenhe(ShenHeItem item, List<Skjh> skjhList) {
        boolean bool = false;
        /*for (Skjh skjh : skjhList) {
            item.setRelationCode(skjh.getCode());
            item.setBatchNum(skjh.getBatchNum());
            ShenHeNode node = skjhMapper.getShenheNode(item.getRelationCode(), item.getUserId()); //获取符合当前用户的审核节点信息
            item.setNodeCode(node.getNodeCode());
            item.setNodeName(node.getNodeName());
            bool = shenHeMapper.toShenhe(item); //提交审核
            if(bool){
                if(item.getStatus().equals("通过")){
                    int isPass = skjhMapper.isShenhePass(item.getRelationCode(), item.getBatchNum());
                    if(isPass == 1){
                        return shenHeMapper.changeStatus(item.getRelationCode(),item.getBatchNum(),"通过");
                    }
                }else if(item.getStatus().equals("退回")){
                    return shenHeMapper.changeStatus(item.getRelationCode(),item.getBatchNum(),"退回");
                }
            }
        }*/
        return bool;
    }

}
