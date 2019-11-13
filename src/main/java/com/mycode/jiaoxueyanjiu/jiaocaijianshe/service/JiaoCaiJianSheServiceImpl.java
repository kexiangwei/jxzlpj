package com.mycode.jiaoxueyanjiu.jiaocaijianshe.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.common.file.domain.FileInfo;
import com.mycode.common.file.mapper.FileMapper;
import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.common.shenhe.domain.ShenHeNode;
import com.mycode.common.shenhe.mapper.ShenHeMapper;
import com.mycode.jiaoxueyanjiu.jiaocaijianshe.domian.JiaoCaiJianShe;
import com.mycode.jiaoxueyanjiu.jiaocaijianshe.mapper.JiaoCaiJianSheMapper;
import com.mycode.jiaoxueyanjiu.jiaogailunwen.domian.JiaoGaiLunWen;
import com.mycode.jiaoxueyanjiu.jiaogailunwen.mapper.JiaoGaiLunWenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教学研究-教材建设
 * @auther kexiangwei
 * @date 2019/11/13
 */
@Service
public class JiaoCaiJianSheServiceImpl implements JiaoCaiJianSheService {

    @Autowired
    private JiaoCaiJianSheMapper jiaoCaiJianSheMapper;
    @Autowired
    private ShenHeMapper shenHeMapper;
    @Autowired
    private FileMapper fileMapper;

    @Override
    public Map<String, Object> getPageList(JiaoCaiJianShe jiaoCaiJianShe) {
        Map<String, Object> resultMap = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(jiaoCaiJianShe.getPageIndex(), jiaoCaiJianShe.getPageSize());
        List<JiaoCaiJianShe> list = jiaoCaiJianSheMapper.getPageList(jiaoCaiJianShe);
        if(!StringUtils.isEmpty(jiaoCaiJianShe.getShenHeUserId())){
            int unShenHeNum = jiaoCaiJianSheMapper.getNotShenHeNum(jiaoCaiJianShe.getShenHeUserId());//获取未审核数
            resultMap.put("unShenHeNum", unShenHeNum);
        }
        resultMap.put("totalNum",pageInfo.getTotal());
        resultMap.put("pageList", list);
        return resultMap;
    }

    @Override
    public boolean insert(JiaoCaiJianShe jiaoCaiJianShe) {
        return jiaoCaiJianSheMapper.insert(jiaoCaiJianShe);
    }

    @Override
    public boolean update(JiaoCaiJianShe jiaoCaiJianShe) {
        return jiaoCaiJianSheMapper.update(jiaoCaiJianShe);
    }

    @Override
    public boolean delete(String code) {
        boolean bool = jiaoCaiJianSheMapper.delete(code);
        if(bool){
            List<FileInfo> fileListByRelationCode = fileMapper.getFileListByRelationCode(code);
            if(!fileListByRelationCode.isEmpty()){
                bool = fileMapper.deleteFileInfo(null, code);
            }
        }
        return bool;
    }

    @Override
    public boolean toSubimt(String activeShenheCode, List<JiaoCaiJianShe> jiaoCaiJianSheList) {
        for (JiaoCaiJianShe jiaoCaiJianShe : jiaoCaiJianSheList) {
            jiaoCaiJianShe.setShenheCode(activeShenheCode);
            jiaoCaiJianShe.setBatchNum(StringUtils.isEmpty(jiaoCaiJianShe.getBatchNum())?1:jiaoCaiJianShe.getBatchNum()+1);//提交批次，每提交一次加1
            jiaoCaiJianShe.setStatus("审核中");
        }
        return jiaoCaiJianSheMapper.batchSubimt(jiaoCaiJianSheList);
    }

    @Override
    public boolean toShenhe(ShenHeItem item, List<JiaoCaiJianShe> jiaoCaiJianSheList) {
        boolean bool = false;
        for (JiaoCaiJianShe jiaoCaiJianShe : jiaoCaiJianSheList) {
            item.setRelationCode(jiaoCaiJianShe.getCode());
            item.setBatchNum(jiaoCaiJianShe.getBatchNum());
            ShenHeNode node = jiaoCaiJianSheMapper.getShenheNode(item.getRelationCode(), item.getUserId()); //获取符合当前用户的审核节点信息
            item.setNodeCode(node.getNodeCode());
            item.setNodeName(node.getNodeName());
            bool = shenHeMapper.toShenhe(item); //提交审核
            if(bool){
                if(item.getStatus().equals("通过")){
                    int isPass = jiaoCaiJianSheMapper.isShenhePass(item.getRelationCode(), item.getBatchNum());
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
