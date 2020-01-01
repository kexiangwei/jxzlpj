package com.mycode.jiaoxuesheji.jiaoan.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.common.file.domain.FileInfo;
import com.mycode.common.file.mapper.FileMapper;
import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.common.shenhe.domain.ShenHeNode;
import com.mycode.common.shenhe.mapper.ShenHeMapper;
import com.mycode.jiaoxuesheji.jiaoan.domian.JiaoAn;
import com.mycode.jiaoxuesheji.jiaoan.mapper.JiaoAnMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教学设计-教案
 * @auther kexiangwei
 * @date 2019/11/13
 */
@Service
public class JiaoAnServiceImpl implements JiaoAnService {

    @Autowired
    private JiaoAnMapper jiaoAnMapper;
    @Autowired
    private ShenHeMapper shenHeMapper;

    @Override
    public Map<String, Object> getPageList(JiaoAn jiaoAn) {
        Map<String, Object> resultMap = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(jiaoAn.getPageIndex(), jiaoAn.getPageSize());
        List<JiaoAn> pageList = jiaoAnMapper.getPageList(jiaoAn);
        resultMap.put("totalNum",pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        //
        if(!StringUtils.isEmpty(jiaoAn.getShenHeUserId())){
            int unShenHeNum = jiaoAnMapper.getNotShenHeNum(jiaoAn.getShenHeUserId());//获取未审核数
            resultMap.put("unShenHeNum", unShenHeNum);
        }
        //
        return resultMap;
    }

    @Override
    public boolean insert(JiaoAn jiaoAn) {
        return jiaoAnMapper.insert(jiaoAn);
    }

    @Override
    public boolean update(JiaoAn jiaoAn) {
        return jiaoAnMapper.update(jiaoAn);
    }

    @Override
    public boolean delete(String code) {
        return jiaoAnMapper.delete(code);
    }

    @Override
    public boolean toSubimt(String activeShenheCode, List<JiaoAn> jiaoAnList) {
        for (JiaoAn jiaoAn : jiaoAnList) {
            jiaoAn.setShenheCode(activeShenheCode);
            jiaoAn.setBatchNum(StringUtils.isEmpty(jiaoAn.getBatchNum())?1:jiaoAn.getBatchNum()+1);//提交批次，每提交一次加1
            jiaoAn.setStatus("审核中");
        }
        return jiaoAnMapper.batchSubimt(jiaoAnList);
    }

    @Override
    public boolean toShenhe(ShenHeItem item, List<JiaoAn> jiaoAnList) {
        boolean bool = false;
        for (JiaoAn jiaoAn : jiaoAnList) {
            item.setRelationCode(jiaoAn.getCode());
            item.setBatchNum(jiaoAn.getBatchNum());
            ShenHeNode node = jiaoAnMapper.getShenheNode(item.getRelationCode(), item.getUserId()); //获取符合当前用户的审核节点信息
            item.setNodeCode(node.getNodeCode());
            item.setNodeName(node.getNodeName());
            bool = shenHeMapper.toShenhe(item); //提交审核
            if(bool){
                if(item.getStatus().equals("通过")){
                    int isPass = jiaoAnMapper.isShenhePass(item.getRelationCode(), item.getBatchNum());
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
