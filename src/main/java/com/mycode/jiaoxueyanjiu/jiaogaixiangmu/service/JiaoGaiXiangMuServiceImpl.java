package com.mycode.jiaoxueyanjiu.jiaogaixiangmu.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.common.file.domain.FileInfo;
import com.mycode.common.file.mapper.FileMapper;
import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.common.shenhe.domain.ShenHeNode;
import com.mycode.common.shenhe.mapper.ShenHeMapper;
import com.mycode.jiaoxueyanjiu.jiaogaixiangmu.domain.FundBudget;
import com.mycode.jiaoxueyanjiu.jiaogaixiangmu.domain.JiaoGaiXiangMu;
import com.mycode.jiaoxueyanjiu.jiaogaixiangmu.domain.Member;
import com.mycode.jiaoxueyanjiu.jiaogaixiangmu.domain.ZjshItem;
import com.mycode.jiaoxueyanjiu.jiaogaixiangmu.mapper.JiaoGaiXiangMuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教学研究-教改项目
 * @auther kexiangwei
 * @date 2019/11/13
 */
@Service
public class JiaoGaiXiangMuServiceImpl implements JiaoGaiXiangMuService {

    @Autowired
    private JiaoGaiXiangMuMapper jiaoGaiXiangMuMapper;
    @Autowired
    private ShenHeMapper shenHeMapper;
    @Autowired
    private FileMapper fileMapper;

    @Override
    public Map<String, Object> getPageList(JiaoGaiXiangMu jiaoGaiXiangMu) {
        Map<String, Object> resultMap = new HashMap<>();
        if(!StringUtils.isEmpty(jiaoGaiXiangMu.getShenHeUserId())){
            //是否教务处管理员
            resultMap.put("isJwcGly", jiaoGaiXiangMuMapper.isJwcGly(jiaoGaiXiangMu.getShenHeUserId()));
            //判断是否为校外专家审核账号
            Integer isZjshAccount = jiaoGaiXiangMuMapper.isZjshAccount(jiaoGaiXiangMu.getShenHeUserId());
            jiaoGaiXiangMu.setIsZjshAccount(isZjshAccount);
            resultMap.put("isZjshAccount", isZjshAccount);
            //获取未审核数
            resultMap.put("unShenHeNum", jiaoGaiXiangMuMapper.getNotShenHeNum(jiaoGaiXiangMu.getShenHeUserId(),isZjshAccount));
        }
        //
        Page<Object> pageInfo = PageHelper.startPage(jiaoGaiXiangMu.getPageIndex(), jiaoGaiXiangMu.getPageSize());
        List<JiaoGaiXiangMu> pageList = jiaoGaiXiangMuMapper.getPageList(jiaoGaiXiangMu);
        for (JiaoGaiXiangMu xiangMu : pageList) {
            xiangMu.setMemberList(jiaoGaiXiangMuMapper.getMemberList(xiangMu.getCode())); //项目成员列表
            xiangMu.setFundBudgetList(jiaoGaiXiangMuMapper.getFundBudgetList(xiangMu.getCode())); //经费预算列表
            xiangMu.setZjshItemList(jiaoGaiXiangMuMapper.getZjshProcess(xiangMu.getCode(),xiangMu.getBatchNum()));
        }
        resultMap.put("totalNum",pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public boolean insert(JiaoGaiXiangMu jiaoGaiXiangMu) {
        return jiaoGaiXiangMuMapper.insert(jiaoGaiXiangMu);
    }

    @Override
    public boolean update(JiaoGaiXiangMu jiaoGaiXiangMu) {
        return jiaoGaiXiangMuMapper.update(jiaoGaiXiangMu);
    }

    @Override
    public boolean delete(String code) {
        boolean bool = jiaoGaiXiangMuMapper.delete(code);
        if(bool){
            List<FileInfo> fileListByRelationCode = fileMapper.getFileListByRelationCode(code);
            if(!fileListByRelationCode.isEmpty()){
                bool = fileMapper.deleteFileInfo(null, code);
            }
        }
        return bool;
    }

    @Override
    public boolean toSubimt(String activeShenheCode, List<JiaoGaiXiangMu> jiaoGaiXiangMuList) {
        for (JiaoGaiXiangMu jiaoGaiXiangMu : jiaoGaiXiangMuList) {
            jiaoGaiXiangMu.setShenheCode(activeShenheCode);
            jiaoGaiXiangMu.setBatchNum(StringUtils.isEmpty(jiaoGaiXiangMu.getBatchNum())?1:jiaoGaiXiangMu.getBatchNum()+1);//提交批次，每提交一次加1
            jiaoGaiXiangMu.setStatus("审核中");
        }
        return jiaoGaiXiangMuMapper.batchSubimt(jiaoGaiXiangMuList);
    }

    @Override
    public boolean toShenhe(ShenHeItem item, List<JiaoGaiXiangMu> jiaoGaiXiangMuList) {
        boolean bool = false;
        for (JiaoGaiXiangMu jiaoGaiXiangMu : jiaoGaiXiangMuList) {
            item.setRelationCode(jiaoGaiXiangMu.getCode());
            item.setBatchNum(jiaoGaiXiangMu.getBatchNum());
            if(item.getIsZjshAccount()==1){
                bool = jiaoGaiXiangMuMapper.toZjShenhe(item); //专家审核
            }else{
                ShenHeNode node = jiaoGaiXiangMuMapper.getShenheNode(item.getRelationCode(), item.getUserId()); //获取符合当前用户的审核节点信息
                item.setNodeCode(node.getNodeCode());
                item.setNodeName(node.getNodeName());
                bool = shenHeMapper.toShenhe(item); //提交审核
                if(bool){
                    if(item.getShenheType().equals("终审")&&item.getStatus().equals("通过")){
                        return shenHeMapper.changeStatus(item.getRelationCode(),item.getBatchNum(),"通过");
                    }else if(item.getStatus().equals("退回")){
                        return shenHeMapper.changeStatus(item.getRelationCode(),item.getBatchNum(),"退回");
                    }
                }
            }
        }
        return bool;
    }

    @Override
    public List<ZjshItem> getZjshProcess(String xmCode, Integer batchNum) {
        return jiaoGaiXiangMuMapper.getZjshProcess(xmCode,batchNum);
    }

    @Override
    public List<Member> getMemberList(String xmCode) {
        return jiaoGaiXiangMuMapper.getMemberList(xmCode);
    }

    @Override
    public boolean insertMember(Member member) {
        return jiaoGaiXiangMuMapper.insertMember(member);
    }

    @Override
    public boolean deleteMember(Member member) {
        return jiaoGaiXiangMuMapper.deleteMember(member);
    }

    @Override
    public List<FundBudget> getFundBudgetList(String xmCode) {
        return jiaoGaiXiangMuMapper.getFundBudgetList(xmCode);
    }

    @Override
    public boolean insertFundBudget(FundBudget fundBudget) {
        return jiaoGaiXiangMuMapper.insertFundBudget(fundBudget);
    }

    @Override
    public boolean deleteFundBudget(FundBudget fundBudget) {
        return jiaoGaiXiangMuMapper.deleteFundBudget(fundBudget);
    }

}
