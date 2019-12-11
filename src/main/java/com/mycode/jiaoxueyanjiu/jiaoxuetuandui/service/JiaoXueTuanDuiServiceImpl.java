package com.mycode.jiaoxueyanjiu.jiaoxuetuandui.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.common.file.domain.FileInfo;
import com.mycode.common.file.mapper.FileMapper;
import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.common.shenhe.domain.ShenHeNode;
import com.mycode.common.shenhe.mapper.ShenHeMapper;
import com.mycode.jiaoxueyanjiu.jiaoxuetuandui.domain.JiaoXueTuanDui;
import com.mycode.jiaoxueyanjiu.jiaoxuetuandui.domain.PingShenTemplate;
import com.mycode.jiaoxueyanjiu.jiaoxuetuandui.mapper.JiaoXueTuanDuiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教学研究-教学团队
 * @auther kexiangwei
 * @date 2019/11/13
 */
@Service
public class JiaoXueTuanDuiServiceImpl implements JiaoXueTuanDuiService {

    @Autowired
    private JiaoXueTuanDuiMapper jiaoXueTuanDuiMapper;
    @Autowired
    private ShenHeMapper shenHeMapper;
    @Autowired
    private FileMapper fileMapper;

    @Override
    public Map<String, Object> getPageList(JiaoXueTuanDui jiaoXueTuanDui) {
        Map<String, Object> resultMap = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(jiaoXueTuanDui.getPageIndex(), jiaoXueTuanDui.getPageSize());
        List<JiaoXueTuanDui> pageList = jiaoXueTuanDuiMapper.getPageList(jiaoXueTuanDui);
        if(pageList !=null && pageList.size() >0){
            pageList.forEach(jxtd -> {
                jxtd.setMemberList(jiaoXueTuanDuiMapper.getMemberList(jxtd.getCode()));
            });
        }
        if(!StringUtils.isEmpty(jiaoXueTuanDui.getShenHeUserId())){
            //判断是否为评审账号
            Integer isPsAccount = jiaoXueTuanDuiMapper.isPsAccount(jiaoXueTuanDui.getShenHeUserId());
            jiaoXueTuanDui.setIsPsAccount(isPsAccount);
            resultMap.put("isPsAccount", isPsAccount);
            resultMap.put("unShenHeNum", jiaoXueTuanDuiMapper.getNotShenHeNum(jiaoXueTuanDui.getShenHeUserId())); //获取未审核数
        }
        resultMap.put("totalNum",pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public boolean insert(JiaoXueTuanDui jiaoXueTuanDui) {
        return jiaoXueTuanDuiMapper.insert(jiaoXueTuanDui);
    }

    @Override
    public boolean update(JiaoXueTuanDui jiaoXueTuanDui) {
        return jiaoXueTuanDuiMapper.update(jiaoXueTuanDui);
    }

    @Override
    public boolean delete(String code) {
        boolean bool = jiaoXueTuanDuiMapper.delete(code);
        if(bool){
            List<FileInfo> fileListByRelationCode = fileMapper.getFileListByRelationCode(code);
            if(!fileListByRelationCode.isEmpty()){
                bool = fileMapper.deleteFileInfo(null, code);
            }
        }
        return bool;
    }

    @Override
    public boolean toSubimt(String activeShenheCode, List<JiaoXueTuanDui> jiaoXueTuanDuiList) {
        for (JiaoXueTuanDui jiaoXueTuanDui : jiaoXueTuanDuiList) {
            jiaoXueTuanDui.setShenheCode(activeShenheCode);
            jiaoXueTuanDui.setBatchNum(StringUtils.isEmpty(jiaoXueTuanDui.getBatchNum())?1:jiaoXueTuanDui.getBatchNum()+1);//提交批次，每提交一次加1
            jiaoXueTuanDui.setStatus("审核中");
        }
        return jiaoXueTuanDuiMapper.batchSubimt(jiaoXueTuanDuiList);
    }

    @Override
    public boolean toShenhe(ShenHeItem item, List<JiaoXueTuanDui> jiaoXueTuanDuiList) {
        boolean bool = false;
        for (JiaoXueTuanDui jiaoXueTuanDui : jiaoXueTuanDuiList) {
            item.setRelationCode(jiaoXueTuanDui.getCode());
            item.setBatchNum(jiaoXueTuanDui.getBatchNum());
            ShenHeNode node = jiaoXueTuanDuiMapper.getShenheNode(item.getRelationCode(), item.getUserId()); //获取符合当前用户的审核节点信息
            item.setNodeCode(node.getNodeCode());
            item.setNodeName(node.getNodeName());
            bool = shenHeMapper.toShenhe(item); //提交审核
            if(bool){
                if(item.getStatus().equals("通过")){
                    int isPass = jiaoXueTuanDuiMapper.isShenhePass(item.getRelationCode(), item.getBatchNum());
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

    @Override
    public List<PingShenTemplate> getPingShenTemplate() {
        return jiaoXueTuanDuiMapper.getPingShenTemplate();
    }

    @Override
    public List<Map<String, Object>> getMemberList(String relationCode) {
        return jiaoXueTuanDuiMapper.getMemberList(relationCode);
    }

    @Override
    public boolean insertMember(String relationCode, String userId, String userName) {
        return jiaoXueTuanDuiMapper.insertMember(relationCode,userId,userName);
    }

    @Override
    public boolean deleteMember(String relationCode, String userId) {
        return jiaoXueTuanDuiMapper.deleteMember(relationCode,userId);
    }

}
