package com.mycode.common.shenhe.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.system.menu.domain.Menu;
import com.mycode.system.role.domain.Role;
import com.mycode.common.shenhe.domain.ShenHe;
import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.common.shenhe.domain.ShenHeSet;
import com.mycode.common.shenhe.domain.ShenHeNode;
import com.mycode.common.shenhe.mapper.ShenHeMapper;
import com.mycode.util.CodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther kexiangwei
 * @date 2019/7/13
 */
@Service
public class ShenHeServiceImpl implements ShenHeService {

    @Autowired
    private ShenHeMapper shenHeMapper;

    @Override
    public List<ShenHe> getShenheProcess(String relationCode) {
        List<ShenHe> shenHeList = shenHeMapper.getShenheByRelationCode(relationCode);
        for (ShenHe shenHe : shenHeList) {
            List<ShenHeItem> shenHeItemList = shenHeMapper.getShenheItem(shenHe.getRelationCode(),shenHe.getBatchNum());
            shenHe.setShenHeItemList(shenHeItemList);
        }
        return shenHeList;
    }

    @Override
    public String getActiveShenheCode(Integer menuId) {
        return shenHeMapper.getActiveShenheCode(menuId);
    }

    @Override
    public Map<String, Object> getShenHeList(ShenHeSet shenHe) {
        Map<String, Object> resultMap = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(shenHe.getPageIndex(), shenHe.getPageSize());
        List<ShenHeSet> pageList = shenHeMapper.getShenHeList(shenHe);
        resultMap.put("totalNum",pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public List<Menu> getMenuParentList(Long menuId) {
        return shenHeMapper.getMenuParentList(menuId);
    }

    @Override
    public List<Menu> getMenuListForShenHe() {
        return shenHeMapper.getMenuListForShenHe();
    }

    @Override
    public boolean addShenhe(ShenHeSet shenHe) {
        List<ShenHeSet> shenHeList = shenHeMapper.getShenHeList(shenHe);
        if(shenHeList!=null&&shenHeList.size()>0){
            shenHeMapper.updateShenheByMenuId(shenHe.getMenuId());
        }
        return shenHeMapper.addShenhe(shenHe);
    }

    @Override
    public boolean updateShenheByCode(ShenHeSet shenHe) {
        return shenHeMapper.updateShenheByCode(shenHe);
    }

    @Override
    public boolean batchDelete(String[] codeArr) {
        return shenHeMapper.batchDelete(codeArr);
    }

    @Override
    public List<ShenHeNode> getShenHeNodeList(String shenheCode) {
        return shenHeMapper.getShenheNodeList(shenheCode,null);
    }

    @Override
    public String addShenHeNode(ShenHeNode node) {
        String code = CodeUtil.randomChar(16, false);
        node.setNodeCode(code);
        List<ShenHeNode> shenheNodeList = shenHeMapper.getShenheNodeList(node.getShenheCode(),null);
        if(shenheNodeList!=null){
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
    public boolean updateShenHeNodeByCode(ShenHeNode node) {
        Integer execLevel = null;
        if(!StringUtils.isEmpty(node.getSortType())){
            if(node.getSortType().trim().equalsIgnoreCase("up")){
                execLevel = node.getExecLevel()-1;
            }
            if(node.getSortType().trim().equalsIgnoreCase("down")){
                execLevel = node.getExecLevel()+1;
            }
            ShenHeNode dbNode = shenHeMapper.getShenheNodeList(node.getShenheCode(),execLevel).get(0);
            dbNode.setExecLevel(node.getExecLevel());
            boolean bool = shenHeMapper.updateShenHeNodeByCode(dbNode);
            if(bool){
                node.setExecLevel(execLevel);
            }
        }
        return shenHeMapper.updateShenHeNodeByCode(node);
    }

    @Override
    public boolean deleteShenHeNodeByCode(String nodeCode) {
        ShenHeNode dbNode = shenHeMapper.getShenHeNodeByCode(nodeCode);
        boolean bool = shenHeMapper.batchUpdateShenHeNode(dbNode);
        if(bool){
            bool = shenHeMapper.deleteShenHeNodeByCode(nodeCode);
        }
        return bool;
    }
}
