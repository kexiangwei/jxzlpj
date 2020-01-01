package com.mycode.jiaoxuesheji.skjh.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.common.file.domain.FileInfo;
import com.mycode.common.file.mapper.FileMapper;
import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.common.shenhe.domain.ShenHeNode;
import com.mycode.common.shenhe.mapper.ShenHeMapper;
import com.mycode.jiaoxuesheji.skjh.domian.Skjh;
import com.mycode.jiaoxuesheji.skjh.domian.SkjhItem;
import com.mycode.jiaoxuesheji.skjh.mapper.SkjhMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教学设计-授课计划
 * @auther kexiangwei
 * @date 2019/11/13
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
        List<Skjh> pageList = skjhMapper.getPageList(skjh);
        resultMap.put("totalNum",pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        //
        if(!StringUtils.isEmpty(skjh.getShenHeUserId())){
            int unShenHeNum = skjhMapper.getNotShenHeNum(skjh.getShenHeUserId());//获取未审核数
            resultMap.put("unShenHeNum", unShenHeNum);
        }
        //
        return resultMap;
    }

    @Override
    public boolean insert(Skjh skjh) {
        return skjhMapper.insert(skjh);
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
            skjh.setStatus("审核中");
        }
        return skjhMapper.batchSubimt(skjhList);
    }

    @Override
    public boolean toShenhe(ShenHeItem item, List<Skjh> skjhList) {
        boolean bool = false;
        for (Skjh skjh : skjhList) {
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
        }
        return bool;
    }

}
