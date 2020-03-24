package com.mycode.jiaoxueyanjiu.jixujiaoyu.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.common.file.domain.FileInfo;
import com.mycode.common.file.mapper.FileMapper;
import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.common.shenhe.domain.ShenHeNode;
import com.mycode.common.shenhe.mapper.ShenHeMapper;
import com.mycode.jiaoxueyanjiu.jixujiaoyu.domian.JiXuJiaoYu;
import com.mycode.jiaoxueyanjiu.jixujiaoyu.mapper.JiXuJiaoYuMapper;
import com.mycode.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教学研究-继续教育
 */
@Service
public class JiXuJiaoYuServiceImpl implements JiXuJiaoYuService {

    @Autowired
    private JiXuJiaoYuMapper jiXuJiaoYuMapper;
    @Autowired
    private ShenHeMapper shenHeMapper;
    @Autowired
    private FileMapper fileMapper;

    @Override
    public Map<String, Object> getPageList(JiXuJiaoYu jiXuJiaoYu) {
        Map<String, Object> map = new HashMap<>();
        if(StringUtils.isNotEmpty(jiXuJiaoYu.getShenHeUserId())){
            int unShenHeNum = jiXuJiaoYuMapper.getNotShenHeNum(jiXuJiaoYu.getShenHeUserId()); //获取未审核数
            map.put("unShenHeNum", unShenHeNum);
        }
        Page<Object> pageInfo = PageHelper.startPage(jiXuJiaoYu.getPageIndex(), jiXuJiaoYu.getPageSize());
        List<JiXuJiaoYu> list = jiXuJiaoYuMapper.getPageList(jiXuJiaoYu);
        map.put("totalNum",pageInfo.getTotal());
        map.put("pageList", list);
        return map;
    }

    @Override
    public boolean insert(JiXuJiaoYu jiXuJiaoYu) {
        return jiXuJiaoYuMapper.insert(jiXuJiaoYu);
    }

    @Override
    public boolean update(JiXuJiaoYu jiXuJiaoYu) {
        return jiXuJiaoYuMapper.update(jiXuJiaoYu);
    }

    @Override
    public boolean delete(String code) {
        boolean bool = jiXuJiaoYuMapper.delete(code);
        if(bool){
            List<FileInfo> fileListByRelationCode = fileMapper.getFileListByRelationCode(code);
            if(!fileListByRelationCode.isEmpty()){
                bool = fileMapper.deleteFileInfo(null, code);
            }
        }
        return bool;
    }

    @Override
    public boolean toSubimt(String activeShenheCode, List<JiXuJiaoYu> jiXuJiaoYuList) {
        for (JiXuJiaoYu jiXuJiaoYu : jiXuJiaoYuList) {
            jiXuJiaoYu.setShenheCode(activeShenheCode);
            jiXuJiaoYu.setBatchNum(StringUtils.isEmpty(jiXuJiaoYu.getBatchNum())?1:jiXuJiaoYu.getBatchNum()+1);//提交批次，每提交一次加1
            jiXuJiaoYu.setStatus("审核中");
        }
        return jiXuJiaoYuMapper.batchSubimt(jiXuJiaoYuList);
    }

    @Override
    public boolean toShenhe(ShenHeItem item, List<JiXuJiaoYu> jiXuJiaoYuList) {
        boolean bool = false;
        for (JiXuJiaoYu jiXuJiaoYu : jiXuJiaoYuList) {
            item.setRelationCode(jiXuJiaoYu.getCode());
            item.setBatchNum(jiXuJiaoYu.getBatchNum());
            ShenHeNode node = jiXuJiaoYuMapper.getShenheNode(item.getRelationCode(), item.getUserId()); //获取符合当前用户的审核节点信息
            item.setNodeCode(node.getNodeCode());
            item.setNodeName(node.getNodeName());
            bool = shenHeMapper.toShenhe(item); //提交审核
            if(bool){
                if(item.getStatus().equals("退回")){
                    return shenHeMapper.changeStatus(item.getRelationCode(),item.getBatchNum(),"退回");
                } else { // 通过 | 未通过
                    int isPass = shenHeMapper.isShenhePass("V_JXYJ_JXJY_SHENHE",item.getRelationCode(), item.getBatchNum());
                    if(isPass == 1){
                        return shenHeMapper.changeStatus(item.getRelationCode(),item.getBatchNum(),item.getStatus());
                    }
                }
            }
        }
        return bool;
    }

}
