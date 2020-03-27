package com.mycode.jiaoxuejiangcheng.kchddjl.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.common.file.domain.FileInfo;
import com.mycode.common.file.mapper.FileMapper;
import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.common.shenhe.domain.ShenHeNode;
import com.mycode.common.shenhe.mapper.ShenHeMapper;
import com.mycode.jiaoxuejiangcheng.kchddjl.domian.Kchddjl;
import com.mycode.jiaoxuejiangcheng.kchddjl.mapper.KchddjlMapper;
import com.mycode.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教学奖惩-课程获得的奖励
 */
@Service
public class KchddjlServiceImpl implements KchddjlService {

    @Autowired
    private KchddjlMapper kchddjlMapper;
    @Autowired
    private ShenHeMapper shenHeMapper;
    @Autowired
    private FileMapper fileMapper;

    @Override
    public Map<String, Object> getPageList(Kchddjl obj) {
        Map<String, Object> resultMap = new HashMap<>();
        if(StringUtils.isNotEmpty(obj.getShenHeUserId())){
            int unShenHeNum = kchddjlMapper.getNotShenHeNum(obj.getShenHeUserId());//获取未审核数
            resultMap.put("unShenHeNum", unShenHeNum);
        }
        Page<Object> pageInfo = PageHelper.startPage(obj.getPageIndex(), obj.getPageSize());
        List<Kchddjl> pageList = kchddjlMapper.getPageList(obj);
        resultMap.put("totalNum",pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public boolean insert(Kchddjl obj) {
        return kchddjlMapper.insert(obj);
    }

    @Override
    public boolean update(Kchddjl obj) {
        return kchddjlMapper.update(obj);
    }

    @Override
    public boolean delete(String objCode) {
        boolean bool = kchddjlMapper.delete(objCode);
        if(bool){
            List<FileInfo> fileListByRelationCode = fileMapper.getFileListByRelationCode(objCode);
            if(!fileListByRelationCode.isEmpty()){
                bool = fileMapper.deleteFileInfo(null, objCode);
            }
        }
        return bool;
    }

    @Override
    public boolean toSubimt(String activeShenheCode, List<Kchddjl> objList) {
        for (Kchddjl obj : objList) {
            obj.setShenheCode(activeShenheCode);
            obj.setBatchNum(org.springframework.util.StringUtils.isEmpty(obj.getBatchNum())?1:obj.getBatchNum()+1);//提交批次，每提交一次加1
        }
        return shenHeMapper.batchSubimt(objList);
    }

    @Override
    public boolean toShenhe(ShenHeItem item, List<Kchddjl> objList) {
        boolean bool = false;
        for (Kchddjl obj : objList) {
            item.setRelationCode(obj.getCode());
            item.setBatchNum(obj.getBatchNum());
            ShenHeNode node = shenHeMapper.getShenheNode("V_JXJC_KCHDDJL_SHENHE",item.getRelationCode(), item.getUserId()); //获取符合当前用户的审核节点信息
            item.setNodeCode(node.getNodeCode());
            item.setNodeName(node.getNodeName());
            bool = shenHeMapper.toShenhe(item); //提交审核
            if(bool){
                if(item.getStatus().equals("退回")){
                    return shenHeMapper.changeStatus(item.getRelationCode(),item.getBatchNum(),"退回");
                } else { // 通过 | 未通过
                    int isPass = shenHeMapper.isShenhePass("V_JXJC_KCHDDJL_SHENHE",item.getRelationCode(), item.getBatchNum());
                    if(isPass == 1){
                        return shenHeMapper.changeStatus(item.getRelationCode(),item.getBatchNum(),item.getStatus());
                    }
                }
            }
        }
        return bool;
    }

}