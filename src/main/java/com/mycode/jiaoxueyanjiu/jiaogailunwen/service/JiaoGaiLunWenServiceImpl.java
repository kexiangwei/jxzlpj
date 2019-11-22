package com.mycode.jiaoxueyanjiu.jiaogailunwen.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.common.file.domain.FileInfo;
import com.mycode.common.file.mapper.FileMapper;
import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.common.shenhe.domain.ShenHeNode;
import com.mycode.common.shenhe.mapper.ShenHeMapper;
import com.mycode.jiaoxueyanjiu.jiaogailunwen.domian.JiaoGaiLunWen;
import com.mycode.jiaoxueyanjiu.jiaogailunwen.mapper.JiaoGaiLunWenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教学研究-教改论文
 * @auther kexiangwei
 * @date 2019/7/13
 */
@Service
public class JiaoGaiLunWenServiceImpl implements JiaoGaiLunWenService {

    @Autowired
    private JiaoGaiLunWenMapper jiaoGaiLunWenMapper;
    @Autowired
    private ShenHeMapper shenHeMapper;
    @Autowired
    private FileMapper fileMapper;

    @Override
    public Map<String, Object> getPageList(JiaoGaiLunWen jiaoGaiLunWen) {
        Map<String, Object> resultMap = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(jiaoGaiLunWen.getPageIndex(), jiaoGaiLunWen.getPageSize());
        List<JiaoGaiLunWen> list = jiaoGaiLunWenMapper.getPageList(jiaoGaiLunWen);
        if(!StringUtils.isEmpty(jiaoGaiLunWen.getShenHeUserId())){
            int unShenHeNum = jiaoGaiLunWenMapper.getNotShenHeNum(jiaoGaiLunWen.getShenHeUserId());//获取未审核数
            resultMap.put("unShenHeNum", unShenHeNum);
        }
        resultMap.put("totalNum",pageInfo.getTotal());
        resultMap.put("pageList", list);
        return resultMap;
    }

    @Override
    public JiaoGaiLunWen get(JiaoGaiLunWen jiaoGaiLunWen) {
        return jiaoGaiLunWenMapper.get(jiaoGaiLunWen);
    }

    @Override
    public boolean insert(JiaoGaiLunWen jiaoGaiLunWen) {
        return jiaoGaiLunWenMapper.insert(jiaoGaiLunWen);
    }

    @Override
    public boolean update(JiaoGaiLunWen jiaoGaiLunWen) {
        return jiaoGaiLunWenMapper.update(jiaoGaiLunWen);
    }

    @Override
    public boolean delete(String code) {
        boolean bool = jiaoGaiLunWenMapper.delete(code);
        if(bool){
            List<FileInfo> fileListByRelationCode = fileMapper.getFileListByRelationCode(code);
            if(!fileListByRelationCode.isEmpty()){
                bool = fileMapper.deleteFileInfo(null, code);
            }
        }
        return bool;
    }

    @Override
    public boolean toSubimt(String activeShenheCode, List<JiaoGaiLunWen> jiaoGaiLunWenList) {
        for (JiaoGaiLunWen JiaoGaiLunWen : jiaoGaiLunWenList) {
            JiaoGaiLunWen.setShenheCode(activeShenheCode);
            JiaoGaiLunWen.setBatchNum(StringUtils.isEmpty(JiaoGaiLunWen.getBatchNum())?1:JiaoGaiLunWen.getBatchNum()+1);//提交批次，每提交一次加1
            JiaoGaiLunWen.setStatus("审核中");
        }
        return jiaoGaiLunWenMapper.batchSubimt(jiaoGaiLunWenList);
    }

    @Override
    public boolean toShenhe(ShenHeItem item, List<JiaoGaiLunWen> jiaoGaiLunWenList) {
        boolean bool = false;
        for (JiaoGaiLunWen JiaoGaiLunWen : jiaoGaiLunWenList) {
            item.setRelationCode(JiaoGaiLunWen.getCode());
            item.setBatchNum(JiaoGaiLunWen.getBatchNum());
            ShenHeNode node = jiaoGaiLunWenMapper.getShenheNode(item.getRelationCode(), item.getUserId()); //获取符合当前用户的审核节点信息
            item.setNodeCode(node.getNodeCode());
            item.setNodeName(node.getNodeName());
            bool = shenHeMapper.toShenhe(item); //提交审核
            if(bool){
                if(item.getStatus().equals("通过")){
                    int isPass = jiaoGaiLunWenMapper.isShenhePass(item.getRelationCode(), item.getBatchNum());
                    if(isPass == 1){
                        return shenHeMapper.changeStatus(item.getRelationCode(),item.getBatchNum(),"通过");
                    }
                }else if(item.getStatus().equals("退回")){
                    return shenHeMapper.changeStatus(item.getRelationCode(),item.getBatchNum(),"退回");
                }
            }
        }
        return bool;
    }

}
