package com.mycode.shaungchuangjiaoyu.xkzybs.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.common.file.domain.FileInfo;
import com.mycode.common.file.mapper.FileMapper;
import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.common.shenhe.domain.ShenHeNode;
import com.mycode.common.shenhe.mapper.ShenHeMapper;
import com.mycode.shaungchuangjiaoyu.Stu;
import com.mycode.shaungchuangjiaoyu.xkzybs.domian.Xkzybs;
import com.mycode.shaungchuangjiaoyu.xkzybs.mapper.XkzybsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 双创教育-学科专业比赛
 */
@Service
public class XkzybsServiceImpl implements XkzybsService {

    @Autowired
    private XkzybsMapper xkzybsMapper;
    @Autowired
    private ShenHeMapper shenHeMapper;
    @Autowired
    private FileMapper fileMapper;

    @Override
    public Map<String, Object> getPageList(Xkzybs obj) {
        Map<String, Object> resultMap = new HashMap<>();
        if(!StringUtils.isEmpty(obj.getShenHeUserId())){
            int unShenHeNum = xkzybsMapper.getNotShenHeNum(obj.getShenHeUserId());//获取未审核数
            resultMap.put("unShenHeNum", unShenHeNum);
        }
        Page<Object> pageInfo = PageHelper.startPage(obj.getPageIndex(), obj.getPageSize());
        List<Xkzybs> pageList = xkzybsMapper.getPageList(obj);
        resultMap.put("totalNum",pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public boolean insert(Xkzybs obj) {
        return xkzybsMapper.insert(obj);
    }

    @Override
    public boolean update(Xkzybs obj) {
        return xkzybsMapper.update(obj);
    }

    @Override
    public boolean delete(String objCode) {
        boolean bool = xkzybsMapper.delete(objCode);
        if(bool){
            List<FileInfo> fileListByRelationCode = fileMapper.getFileListByRelationCode(objCode);
            if(!fileListByRelationCode.isEmpty()){
                bool = fileMapper.deleteFileInfo(null, objCode);
            }
        }
        return bool;
    }

    @Override
    public boolean toSubimt(String activeShenheCode, List<Xkzybs> objList) {
        for (Xkzybs obj : objList) {
            obj.setShenheCode(activeShenheCode);
            obj.setBatchNum(StringUtils.isEmpty(obj.getBatchNum())?1:obj.getBatchNum()+1);//提交批次，每提交一次加1
        }
        return shenHeMapper.batchSubimt(objList);
    }

    @Override
    public boolean toShenhe(ShenHeItem item, List<Xkzybs> objList) {
        boolean bool = false;
        for (Xkzybs obj : objList) {
            item.setRelationCode(obj.getCode());
            item.setBatchNum(obj.getBatchNum());
            ShenHeNode node = shenHeMapper.getShenheNode("V_SCJY_XKZYBS_SHENHE",item.getRelationCode(), item.getUserId()); //获取符合当前用户的审核节点信息
            item.setNodeCode(node.getNodeCode());
            item.setNodeName(node.getNodeName());
            bool = shenHeMapper.toShenhe(item); //提交审核
            if(bool){
                if(item.getStatus().equals("退回")){
                    return shenHeMapper.changeStatus(item.getRelationCode(),item.getBatchNum(),"退回");
                } else { // 通过 | 未通过
                    int isPass = shenHeMapper.isShenhePass("V_SCJY_XKZYBS_SHENHE",item.getRelationCode(), item.getBatchNum());
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
        return xkzybsMapper.getStuInfo(relationCode);
    }

    @Override
    public boolean addStuInfo(Stu stu) {
        return xkzybsMapper.addStuInfo(stu);
    }

    @Override
    public boolean delStuInfo(String relationCode, String stuCode) {
        return xkzybsMapper.delStuInfo(relationCode, stuCode);
    }

}
