package com.mycode.jiaoxuexiaoguo.kcxj.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.file.domain.FileInfo;
import com.mycode.file.mapper.FileMapper;
import com.mycode.commonset.shenheSet.domain.ShenHeItem;
import com.mycode.commonset.shenheSet.domain.ShenHeNode;
import com.mycode.commonset.shenheSet.mapper.ShenHeMapper;
import com.mycode.jiaoxuexiaoguo.kcxj.domian.Kcxj;
import com.mycode.jiaoxuexiaoguo.kcxj.mapper.KcxjMapper;
import com.mycode.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KcxjServiceImpl implements KcxjService {

    @Autowired
    private KcxjMapper kcxjMapper;
    @Autowired
    private ShenHeMapper shenHeMapper;
    @Autowired
    private FileMapper fileMapper;

    @Override
    public Map<String, Object> getPageList(Kcxj kcxj) {
        Map<String, Object> resultMap = new HashMap<>();
        //获取未审核数
        if(StringUtils.isNotEmpty(kcxj.getShenHeUserId())){
            resultMap.put("unShenHeNum", kcxjMapper.getNotShenHeNum(kcxj.getShenHeUserId()));
        }
        //获取分页列表
        Page<Object> pageInfo = PageHelper.startPage(kcxj.getPageIndex(), kcxj.getPageSize());
        List<Kcxj> pageList = kcxjMapper.getPageList(kcxj);
        resultMap.put("totalNum",pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public boolean insert(Kcxj kcxj) {
        return kcxjMapper.insert(kcxj);
    }

    @Override
    public boolean update(Kcxj kcxj) {
        return kcxjMapper.update(kcxj);
    }

    @Override
    public boolean delete(String code) {
        boolean bool = kcxjMapper.delete(code);
        if(bool){
            List<FileInfo> fileList = fileMapper.getFileListByRelationCode(code);
            if(!fileList.isEmpty()){
                bool = fileMapper.deleteFileInfo(null, code);
            }
        }
        return bool;
    }

    @Override
    public boolean toSubimt(String activeShenheCode, List<Kcxj> kcxjList) {
        for (Kcxj kcxj : kcxjList) {
            kcxj.setShenheCode(activeShenheCode);
            kcxj.setBatchNum(StringUtils.isEmpty(kcxj.getBatchNum())?1:kcxj.getBatchNum()+1);//提交批次，每提交一次加1
        }
        return shenHeMapper.batchSubimt(kcxjList);
    }

    @Override
    public boolean toShenhe(ShenHeItem item, List<Kcxj> kcxjList) {
        boolean bool = false;
        for (Kcxj kcxj : kcxjList) {
            item.setRelationCode(kcxj.getCode());
            item.setBatchNum(kcxj.getBatchNum());
            ShenHeNode node = shenHeMapper.getShenheNode("V_JXXG_KCXJ_SHENHE",item.getRelationCode(), item.getUserId()); //获取符合当前用户的审核节点信息
            item.setNodeCode(node.getNodeCode());
            item.setNodeName(node.getNodeName());
            bool = shenHeMapper.toShenhe(item); //提交审核
            if(bool){
                if(item.getStatus().equals("退回")){
                    return shenHeMapper.changeStatus(item.getRelationCode(),item.getBatchNum(),"退回");
                } else { // 通过 | 未通过
                    int isPass = shenHeMapper.isShenhePass("V_JXXG_KCXJ_SHENHE",item.getRelationCode(), item.getBatchNum());
                    if(isPass == 1){
                        return shenHeMapper.changeStatus(item.getRelationCode(),item.getBatchNum(),item.getStatus());
                    }
                }
            }
        }
        return bool;
    }

}
