package com.mycode.jiaoxuexiaoguo.sykcj.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.common.file.domain.FileInfo;
import com.mycode.common.file.mapper.FileMapper;
import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.common.shenhe.domain.ShenHeNode;
import com.mycode.common.shenhe.mapper.ShenHeMapper;
import com.mycode.jiaoxuexiaoguo.sykcj.domian.Sykcj;
import com.mycode.jiaoxuexiaoguo.sykcj.mapper.SykcjMapper;
import com.mycode.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SykcjServiceImpl implements SykcjService {

    @Autowired
    private SykcjMapper sykcjMapper;
    @Autowired
    private ShenHeMapper shenHeMapper;
    @Autowired
    private FileMapper fileMapper;

    @Override
    public Map<String, Object> getPageList(Sykcj sykcj) {
        Map<String, Object> resultMap = new HashMap<>();
        //获取未审核数
        if(StringUtils.isNotEmpty(sykcj.getShenHeUserId())){
            resultMap.put("unShenHeNum", sykcjMapper.getNotShenHeNum(sykcj.getShenHeUserId()));
        }
        //获取分页列表
        Page<Object> pageInfo = PageHelper.startPage(sykcj.getPageIndex(), sykcj.getPageSize());
        List<Sykcj> pageList = sykcjMapper.getPageList(sykcj);
        resultMap.put("totalNum",pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public boolean insert(Sykcj sykcj) {
        return sykcjMapper.insert(sykcj);
    }

    @Override
    public boolean update(Sykcj sykcj) {
        return sykcjMapper.update(sykcj);
    }

    @Override
    public boolean delete(String code) {
        boolean bool = sykcjMapper.delete(code);
        if(bool){
            List<FileInfo> fileList = fileMapper.getFileListByRelationCode(code);
            if(!fileList.isEmpty()){
                bool = fileMapper.deleteFileInfo(null, code);
            }
        }
        return bool;
    }

    @Override
    public boolean toSubimt(String activeShenheCode, List<Sykcj> sykcjList) {
        for (Sykcj sykcj : sykcjList) {
            sykcj.setShenheCode(activeShenheCode);
            sykcj.setBatchNum(StringUtils.isEmpty(sykcj.getBatchNum())?1:sykcj.getBatchNum()+1);//提交批次，每提交一次加1
        }
        return shenHeMapper.batchSubimt(sykcjList);
    }

    @Override
    public boolean toShenhe(ShenHeItem item, List<Sykcj> sykcjList) {
        boolean bool = false;
        for (Sykcj sykcj : sykcjList) {
            item.setRelationCode(sykcj.getCode());
            item.setBatchNum(sykcj.getBatchNum());
            ShenHeNode node = shenHeMapper.getShenheNode("V_JXXG_SYKCJ_SHENHE",item.getRelationCode(), item.getUserId()); //获取符合当前用户的审核节点信息
            item.setNodeCode(node.getNodeCode());
            item.setNodeName(node.getNodeName());
            bool = shenHeMapper.toShenhe(item); //提交审核
            if(bool){
                if(item.getStatus().equals("退回")){
                    return shenHeMapper.changeStatus(item.getRelationCode(),item.getBatchNum(),"退回");
                } else { // 通过 | 未通过
                    int isPass = shenHeMapper.isShenhePass("V_JXXG_SYKCJ_SHENHE",item.getRelationCode(), item.getBatchNum());
                    if(isPass == 1){
                        return shenHeMapper.changeStatus(item.getRelationCode(),item.getBatchNum(),item.getStatus());
                    }
                }
            }
        }
        return bool;
    }

}
