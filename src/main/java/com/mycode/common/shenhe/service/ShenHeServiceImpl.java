package com.mycode.common.shenhe.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.system.menu.domain.Menu;
import com.mycode.common.shenhe.domain.ShenHe;
import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.common.shenhe.domain.ShenHeSet;
import com.mycode.common.shenhe.domain.ShenHeNode;
import com.mycode.common.shenhe.mapper.ShenHeMapper;
import com.mycode.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShenHeServiceImpl implements ShenHeService {

    @Autowired
    private ShenHeMapper shenHeMapper;

    //ShenHeSet
    @Override
    public Map<String, Object> getShenHeSetPageList(ShenHeSet shenHeSet) {
        Map<String, Object> resultMap = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(shenHeSet.getPageIndex(), shenHeSet.getPageSize());
        List<ShenHeSet> pageList = shenHeMapper.getShenHeSetPageList(shenHeSet);
        resultMap.put("totalNum", pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public boolean addShenheSet(ShenHeSet shenHeSet) {
        List<ShenHeSet> shenHeSetPageList = shenHeMapper.getShenHeSetPageList(shenHeSet);
        if(shenHeSetPageList != null && shenHeSetPageList.size() > 0){ //如果该业务模块已经设置了审核流程
            shenHeMapper.updateShenheSetStatusByMenuId(shenHeSet.getMenuId()); //则把之前的审核流程状态设置为“已禁用”
        }
        return shenHeMapper.addShenheSet(shenHeSet);
    }

    @Override
    public boolean updateShenheSetByCode(ShenHeSet shenHeSet) {
        return shenHeMapper.updateShenheSetByCode(shenHeSet);
    }

    @Override
    public boolean batchDeleteShenHeSet(String[] codeArr) {
        int execNum = shenHeMapper.batchDeleteShenHeSet(codeArr); //mybatis一次对多条数据进行操作成功后返回值为 -1
        return execNum < 0;
    }

    //ShenHeNode
    @Override
    public List<ShenHeNode> getShenHeNodeList(String shenheCode) {
        return shenHeMapper.getShenheNodeList(shenheCode,null);
    }

    @Override
    public String addShenHeNode(ShenHeNode node) {
        String code = StringUtils.guid(16, true);
        node.setNodeCode(code);
        List<ShenHeNode> shenheNodeList = shenHeMapper.getShenheNodeList(node.getShenheCode(),null);
        if(shenheNodeList != null && shenheNodeList.size() > 0){
            node.setExecLevel(shenheNodeList.size()+1);
        }else{
            node.setExecLevel(1);
        }
        boolean bool = shenHeMapper.addShenHeNode(node);
        if(!bool){
            return null;
        }
        return code;
    }

    @Override
    public boolean updateShenHeNodeByCode(ShenHeNode shenHeNode) {
        Integer execLevel = null;
        if(StringUtils.isNotEmpty(shenHeNode.getSortType())){
            if(shenHeNode.getSortType().trim().equalsIgnoreCase("up")){
                execLevel = shenHeNode.getExecLevel()-1;
            }
            if(shenHeNode.getSortType().trim().equalsIgnoreCase("down")){
                execLevel = shenHeNode.getExecLevel()+1;
            }
            ShenHeNode node = shenHeMapper.getShenheNodeList(shenHeNode.getShenheCode(),execLevel).get(0);
            node.setExecLevel(shenHeNode.getExecLevel());
            boolean bool = shenHeMapper.updateShenHeNodeByCode(node);
            if(bool){
                shenHeNode.setExecLevel(execLevel);
            }
        }
        return shenHeMapper.updateShenHeNodeByCode(shenHeNode);
    }

    @Override
    public boolean deleteShenHeNodeByCode(String nodeCode) {
        ShenHeNode shenHeNode = shenHeMapper.getShenHeNodeByCode(nodeCode);
        boolean bool = shenHeMapper.batchUpdateShenHeNodeExecLevel(shenHeNode); //同一个审核流程中，执行级别大于当前（待删除）节点的数据，执行级别依次递减
        if(bool){
            bool = shenHeMapper.deleteShenHeNodeByCode(nodeCode);
        }
        return bool;
    }

    //
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

    //
    @Override
    public String getActiveShenheCode(Integer menuId) {
        return shenHeMapper.getActiveShenheCode(menuId);
    }

}
