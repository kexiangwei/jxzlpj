package com.mycode.jiaoxuesheji.kcssjh.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.common.file.domain.FileInfo;
import com.mycode.common.file.mapper.FileMapper;
import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.common.shenhe.domain.ShenHeNode;
import com.mycode.common.shenhe.mapper.ShenHeMapper;
import com.mycode.jiaoxuesheji.kcssjh.domian.Kcssjh;
import com.mycode.jiaoxuesheji.kcssjh.mapper.KcssjhMapper;
import com.mycode.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教学设计-课程实施计划
 */
@Service
public class KcssjhServiceImpl implements KcssjhService {

    @Autowired
    private KcssjhMapper kcssjhMapper;
    @Autowired
    private ShenHeMapper shenHeMapper;
    @Autowired
    private FileMapper fileMapper;

    @Override
    public Map<String, Object> getPageList(Kcssjh kcssjh) {
        Map<String, Object> resultMap = new HashMap<>();
        //获取未审核数
        if(StringUtils.isNotEmpty(kcssjh.getShenHeUserId())){
            resultMap.put("unShenHeNum", kcssjhMapper.getNotShenHeNum(kcssjh.getShenHeUserId()));
        }
        //获取分页列表
        Page<Object> pageInfo = PageHelper.startPage(kcssjh.getPageIndex(), kcssjh.getPageSize());
        List<Kcssjh> pageList = kcssjhMapper.getPageList(kcssjh);
        resultMap.put("totalNum", pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public boolean insert(Kcssjh kcssjh) {
        return kcssjhMapper.insert(kcssjh);
    }

    @Override
    public boolean update(Kcssjh kcssjh) {
        return kcssjhMapper.update(kcssjh);
    }

    @Override
    public boolean delete(String code) {
        boolean bool = kcssjhMapper.delete(code);
        if(bool){
            List<FileInfo> fileList = fileMapper.getFileListByRelationCode(code);
            if(!fileList.isEmpty()){
                bool = fileMapper.deleteFileInfo(null, code);
            }
        }
        return bool;
    }

    @Override
    public boolean toSubimt(String activeShenheCode, List<Kcssjh> objList) {
        for (Kcssjh obj : objList) {
            obj.setShenheCode(activeShenheCode);
            obj.setBatchNum(StringUtils.isEmpty(obj.getBatchNum())?1:obj.getBatchNum()+1);//提交批次，每提交一次加1
        }
        return shenHeMapper.batchSubimt(objList);
    }

    @Override
    public boolean toShenhe(ShenHeItem item, List<Kcssjh> objList) {
        boolean bool = false;
        for (Kcssjh obj : objList) {
            item.setRelationCode(obj.getCode());
            item.setBatchNum(obj.getBatchNum());
            ShenHeNode node = shenHeMapper.getShenheNode("V_JXSJ_KCSSJH_SHENHE",item.getRelationCode(), item.getUserId()); //获取符合当前用户的审核节点信息
            item.setNodeCode(node.getNodeCode());
            item.setNodeName(node.getNodeName());
            bool = shenHeMapper.toShenhe(item); //提交审核
            if(bool){
                if(item.getStatus().equals("退回")){
                    return shenHeMapper.changeStatus(item.getRelationCode(),item.getBatchNum(),"退回");
                } else { // 通过 | 未通过
                    int isPass = shenHeMapper.isShenhePass("V_JXSJ_KCSSJH_SHENHE",item.getRelationCode(), item.getBatchNum());
                    if(isPass == 1){
                        return shenHeMapper.changeStatus(item.getRelationCode(),item.getBatchNum(),item.getStatus());
                    }
                }
            }
        }
        return bool;
    }

}
