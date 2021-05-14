package com.mycode.common.shenheSet.service;

import com.mycode.common.shenheSet.domain.ShenHeNode;
import com.mycode.common.shenheSet.mapper.ShenHeNodeMapper;
import com.mycode.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ShenHeNodeServiceImpl implements ShenHeNodeService {

    @Autowired
    private ShenHeNodeMapper ShenHeNodeMapper;

    @Override
    public List<ShenHeNode> getShenHeNodeList(String shenheCode) {
        return ShenHeNodeMapper.getShenheNodeList(shenheCode,null);
    }

    @Override
    public String addShenHeNode(ShenHeNode node) {
        String code = UUID.randomUUID().toString().replace("-","");
        node.setNodeCode(code);
        List<ShenHeNode> shenheNodeList = ShenHeNodeMapper.getShenheNodeList(node.getShenheCode(),null);
        if(shenheNodeList != null && shenheNodeList.size() > 0){
            node.setExecLevel(shenheNodeList.size()+1);
        }else{
            node.setExecLevel(1);
        }
        boolean bool = ShenHeNodeMapper.addShenHeNode(node);
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
            ShenHeNode node = ShenHeNodeMapper.getShenheNodeList(shenHeNode.getShenheCode(),execLevel).get(0);
            node.setExecLevel(shenHeNode.getExecLevel());
            boolean bool = ShenHeNodeMapper.updateShenHeNodeByCode(node);
            if(bool){
                shenHeNode.setExecLevel(execLevel);
            }
        }
        return ShenHeNodeMapper.updateShenHeNodeByCode(shenHeNode);
    }

    @Override
    public boolean deleteShenHeNodeByCode(String nodeCode) {
        ShenHeNode shenHeNode = ShenHeNodeMapper.getShenHeNodeByCode(nodeCode);
        boolean bool = ShenHeNodeMapper.batchUpdateShenHeNodeExecLevel(shenHeNode); //同一个审核流程中，执行级别大于当前（待删除）节点的数据，执行级别依次递减
        if(bool){
            bool = ShenHeNodeMapper.deleteShenHeNodeByCode(nodeCode);
        }
        return bool;
    }

}
