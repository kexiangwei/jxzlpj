package com.mycode.shaungchuangjiaoyu.wtbs.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.common.file.domain.FileInfo;
import com.mycode.common.file.mapper.FileMapper;
import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.common.shenhe.domain.ShenHeNode;
import com.mycode.common.shenhe.mapper.ShenHeMapper;
import com.mycode.shaungchuangjiaoyu.Stu;
import com.mycode.shaungchuangjiaoyu.wtbs.domian.Wtbs;
import com.mycode.shaungchuangjiaoyu.wtbs.mapper.WtbsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 双创教育-文、体类比赛
 */
@Service
public class WtbsServiceImpl implements WtbsService {

    @Autowired
    private WtbsMapper wtbsMapper;
    @Autowired
    private ShenHeMapper shenHeMapper;
    @Autowired
    private FileMapper fileMapper;

    @Override
    public Map<String, Object> getPageList(Wtbs obj) {
        Map<String, Object> resultMap = new HashMap<>();
        if(!StringUtils.isEmpty(obj.getShenHeUserId())){
            int unShenHeNum = wtbsMapper.getNotShenHeNum(obj.getShenHeUserId());//获取未审核数
            resultMap.put("unShenHeNum", unShenHeNum);
        }
        Page<Object> pageInfo = PageHelper.startPage(obj.getPageIndex(), obj.getPageSize());
        List<Wtbs> pageList = wtbsMapper.getPageList(obj);
        resultMap.put("totalNum",pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public boolean insert(Wtbs obj) {
        return wtbsMapper.insert(obj);
    }

    @Override
    public boolean update(Wtbs obj) {
        return wtbsMapper.update(obj);
    }

    @Override
    public boolean delete(String objCode) {
        boolean bool = wtbsMapper.delete(objCode);
        if(bool){
            List<FileInfo> fileListByRelationCode = fileMapper.getFileListByRelationCode(objCode);
            if(!fileListByRelationCode.isEmpty()){
                bool = fileMapper.deleteFileInfo(null, objCode);
            }
        }
        return bool;
    }

    @Override
    public boolean toSubimt(String activeShenheCode, List<Wtbs> objList) {
        for (Wtbs obj : objList) {
            obj.setShenheCode(activeShenheCode);
            obj.setBatchNum(StringUtils.isEmpty(obj.getBatchNum())?1:obj.getBatchNum()+1);//提交批次，每提交一次加1
        }
        return shenHeMapper.batchSubimt(objList);
    }

    @Override
    public boolean toShenhe(ShenHeItem item, List<Wtbs> objList) {
        boolean bool = false;
        for (Wtbs obj : objList) {
            item.setRelationCode(obj.getCode());
            item.setBatchNum(obj.getBatchNum());
            ShenHeNode node = shenHeMapper.getShenheNode("V_SCJY_WTBS_SHENHE",item.getRelationCode(), item.getUserId()); //获取符合当前用户的审核节点信息
            item.setNodeCode(node.getNodeCode());
            item.setNodeName(node.getNodeName());
            bool = shenHeMapper.toShenhe(item); //提交审核
            if(bool){
                if(item.getStatus().equals("退回")){
                    return shenHeMapper.changeStatus(item.getRelationCode(),item.getBatchNum(),"退回");
                } else { // 通过 | 未通过
                    int isPass = shenHeMapper.isShenhePass("V_SCJY_WTBS_SHENHE",item.getRelationCode(), item.getBatchNum());
                    if(isPass == 1){
                        return shenHeMapper.changeStatus(item.getRelationCode(),item.getBatchNum(),item.getStatus());
                    }
                }
            }
        }
        return bool;
    }

    @Override
    public List<Stu> getStuInfo(String relationCode) {
        return wtbsMapper.getStuInfo(relationCode);
    }

    @Override
    public boolean addStuInfo(Stu stu) {
        return wtbsMapper.addStuInfo(stu);
    }

    @Override
    public boolean delStuInfo(String relationCode, String stuCode) {
        return wtbsMapper.delStuInfo(relationCode, stuCode);
    }

}
