package com.mycode.jiaoxueyanjiu.jiaoxuetuandui.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.commonset.shenheSet.domain.ShenHeItem;
import com.mycode.commonset.shenheSet.domain.ShenHeNode;
import com.mycode.commonset.shenheSet.mapper.ShenHeMapper;
import com.mycode.jiaoxueyanjiu.jiaoxuetuandui.domain.JiaoXueTuanDui;
import com.mycode.jiaoxueyanjiu.jiaoxuetuandui.domain.PingShen;
import com.mycode.jiaoxueyanjiu.jiaoxuetuandui.domain.PingShenTemplate;
import com.mycode.jiaoxueyanjiu.jiaoxuetuandui.mapper.JiaoXueTuanDuiMapper;
import com.mycode.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教学研究-教学团队
 */
@Service
public class JiaoXueTuanDuiServiceImpl implements JiaoXueTuanDuiService {

    @Autowired
    private JiaoXueTuanDuiMapper jiaoXueTuanDuiMapper;
    @Autowired
    private ShenHeMapper shenHeMapper;

    @Override
    public Map<String, Object> getPageList(JiaoXueTuanDui jiaoXueTuanDui) {
        Map<String, Object> resultMap = new HashMap<>();
        //
        Integer isJwcGly = 0;
        if(StringUtils.isNotEmpty(jiaoXueTuanDui.getShenHeUserId())){
            //是否教务处管理员
            isJwcGly = jiaoXueTuanDuiMapper.isJwcGly(jiaoXueTuanDui.getShenHeUserId());
            resultMap.put("isJwcGly", isJwcGly);
            //是否专家评审账号
            Integer isZjshAccount = jiaoXueTuanDuiMapper.isZjAccount(jiaoXueTuanDui.getShenHeUserId());
            jiaoXueTuanDui.setIsZjshAccount(isZjshAccount);
            resultMap.put("isZjshAccount", isZjshAccount);
            //获取未审核数
            int notShenHeNum = jiaoXueTuanDuiMapper.getNotShenHeNum(jiaoXueTuanDui);
            resultMap.put("unShenHeNum", notShenHeNum);
        }
        //
        Page<Object> pageInfo = PageHelper.startPage(jiaoXueTuanDui.getPageIndex(), jiaoXueTuanDui.getPageSize());
        List<JiaoXueTuanDui> pageList = jiaoXueTuanDuiMapper.getPageList(jiaoXueTuanDui);
        if(pageList !=null && pageList.size() >0){
            for (JiaoXueTuanDui jiaoXueTuanDui1 : pageList) {
                //
                if(StringUtils.isNotEmpty(jiaoXueTuanDui1.getShenheCode())){ //若数据未提交，则不执行此查询
                    jiaoXueTuanDui1.setZjshItemList(jiaoXueTuanDuiMapper.getZjshProcess(jiaoXueTuanDui1.getCode(),jiaoXueTuanDui1.getBatchNum()));//专家审核意见
                    if(isJwcGly == 1){
                        jiaoXueTuanDui1.setIsZjshAll(jiaoXueTuanDuiMapper.isZjshAll(jiaoXueTuanDui1.getCode(),jiaoXueTuanDui1.getBatchNum()));
                    }
                }
            }
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
        return jiaoXueTuanDuiMapper.delete(code);
    }

    @Override
    public boolean toSubimt(String activeShenheCode, List<JiaoXueTuanDui> jiaoXueTuanDuiList) {
        for (JiaoXueTuanDui jiaoXueTuanDui : jiaoXueTuanDuiList) {
            jiaoXueTuanDui.setShenheCode(activeShenheCode);
            jiaoXueTuanDui.setBatchNum(StringUtils.isEmpty(jiaoXueTuanDui.getBatchNum())?1:jiaoXueTuanDui.getBatchNum()+1);//提交批次，每提交一次加1
        }
        return shenHeMapper.batchSubimt(jiaoXueTuanDuiList);
    }

    @Override
    public boolean toShenhe(ShenHeItem item, List<JiaoXueTuanDui> jiaoGaiXiangMuList) {
        boolean bool = false;
        for (JiaoXueTuanDui jiaoGaiXiangMu : jiaoGaiXiangMuList) {
            item.setRelationCode(jiaoGaiXiangMu.getCode());
            item.setBatchNum(jiaoGaiXiangMu.getBatchNum());
            if(item.getIsZjshAccount()==1){
                bool = jiaoXueTuanDuiMapper.toZjShenhe(item); //专家审核
            }else{
                ShenHeNode node = shenHeMapper.getShenheNode("V_JXYJ_JXTD_SHENHE",item.getRelationCode(), item.getUserId()); //获取符合当前用户的审核节点信息
                item.setNodeCode(node.getNodeCode());
                item.setNodeName(node.getNodeName());
                bool = shenHeMapper.toShenhe(item); //提交审核
                if(bool){
                    if(item.getStatus().equals("退回")){
                        return shenHeMapper.changeStatus(item.getRelationCode(),item.getBatchNum(),"退回");
                    } else { // 通过，未通过
                        if(item.getShenheType().equals("终审")){
                            return shenHeMapper.changeStatus(item.getRelationCode(),item.getBatchNum(),item.getStatus());
                        }
                    }
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
    public List<PingShen> getPingShenInfo(String relationCode, Integer batchNum, String userId) {
        return jiaoXueTuanDuiMapper.getPingShenInfo(relationCode,batchNum,userId);
    }

    @Override
    public boolean insertPingShenInfo(PingShen pingShen) {
        List<PingShen> pingShenInfo = jiaoXueTuanDuiMapper.getPingShenInfo(pingShen.getRelationCode(), pingShen.getBatchNum(), pingShen.getUserId().toString());
        if(pingShenInfo != null && pingShenInfo.size() >0){
            boolean bool = jiaoXueTuanDuiMapper.deletePingShenInfo(pingShen.getRelationCode(), pingShen.getBatchNum(), pingShen.getUserId().toString());
            if(!bool){
                return false;
            }
        }
        return jiaoXueTuanDuiMapper.insertPingShenInfo(pingShen);
    }

}
