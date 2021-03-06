package com.mycode.jiaoxueyanjiu.jiaogaixiangmu.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.common.shenheSet.mapper.ShenHeMapper;
import com.mycode.jiaoxueyanjiu.jiaogaixiangmu.domain.FundBudget;
import com.mycode.jiaoxueyanjiu.jiaogaixiangmu.domain.JiaoGaiXiangMuSchool;
import com.mycode.jiaoxueyanjiu.jiaogaixiangmu.domain.Member;
import com.mycode.jiaoxueyanjiu.jiaogaixiangmu.mapper.JiaoGaiXiangMuSchoolMapper;
import com.mycode.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JiaoGaiXiangMuSchoolServiceImpl implements JiaoGaiXiangMuSchoolService {

    @Autowired
    private JiaoGaiXiangMuSchoolMapper jiaoGaiXiangMuSchoolMapper;
    @Autowired
    private ShenHeMapper shenHeMapper;

    @Override
    public Map<String, Object> getPageList(JiaoGaiXiangMuSchool jiaoGaiXiangMuSchool) {
        Map<String, Object> resultMap = new HashMap<>();
        Integer jwcGly = 0;
        if(StringUtils.isNotEmpty(jiaoGaiXiangMuSchool.getShenHeUserId())){
            //判断是否为校外专家审核账号
            Integer isZjshAccount = shenHeMapper.isZjAccount(jiaoGaiXiangMuSchool.getShenHeUserId());
            jiaoGaiXiangMuSchool.setIsZjshAccount(isZjshAccount);
            resultMap.put("isZjshAccount", isZjshAccount);
            //判断是否为教务处管理员
            if(isZjshAccount == 0){
                jwcGly = shenHeMapper.isJwcGly(jiaoGaiXiangMuSchool.getShenHeUserId());
                resultMap.put("isJwcGly", jwcGly);
            }
            //获取未审核数
            resultMap.put("unShenHeNum", shenHeMapper.getNotShenHeNum("V_JXYJ_JGXM_SCHOOL_SHENHE"
                    , jiaoGaiXiangMuSchool.getShenHeUserId(),isZjshAccount,jwcGly));
        }
        //
        Page<Object> pageInfo = PageHelper.startPage(jiaoGaiXiangMuSchool.getPageIndex(), jiaoGaiXiangMuSchool.getPageSize());
        List<JiaoGaiXiangMuSchool> pageList = jiaoGaiXiangMuSchoolMapper.getPageList(jiaoGaiXiangMuSchool);
        if(pageList !=null && pageList.size() > 0){
            for (JiaoGaiXiangMuSchool jiaoGaiXiangMu : pageList) {
                jiaoGaiXiangMu.setMemberList(jiaoGaiXiangMuSchoolMapper.getMemberList(jiaoGaiXiangMu.getCode())); //项目成员列表
                jiaoGaiXiangMu.setFundBudgetList(jiaoGaiXiangMuSchoolMapper.getFundBudgetList(jiaoGaiXiangMu.getCode())); //经费预算列表
                //
                if(StringUtils.isNotEmpty(jiaoGaiXiangMu.getShenheCode())){ //若数据未提交，则不执行此查询
                    jiaoGaiXiangMu.setZjshItemList(shenHeMapper.getZjshProcess(jiaoGaiXiangMu.getCode(),jiaoGaiXiangMu.getBatchNum())); //专家审核意见
                    if(jwcGly == 1){
                        jiaoGaiXiangMu.setIsZjshAll(shenHeMapper.isZjshAll(jiaoGaiXiangMu.getCode(),jiaoGaiXiangMu.getBatchNum()));
                    }
                }
            }
        }
        resultMap.put("totalNum", pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public boolean insert(JiaoGaiXiangMuSchool jiaoGaiXiangMuSchool) {
        return jiaoGaiXiangMuSchoolMapper.insert(jiaoGaiXiangMuSchool);
    }

    @Override
    public boolean update(JiaoGaiXiangMuSchool jiaoGaiXiangMuSchool) {
        return jiaoGaiXiangMuSchoolMapper.update(jiaoGaiXiangMuSchool);
    }

    @Override
    public boolean delete(String code) {
        return jiaoGaiXiangMuSchoolMapper.delete(code);
    }

    @Override
    public List<Member> getMemberList(String xmCode) {
        return jiaoGaiXiangMuSchoolMapper.getMemberList(xmCode);
    }

    @Override
    public boolean insertMember(Member member) {
        return jiaoGaiXiangMuSchoolMapper.insertMember(member);
    }

    @Override
    public boolean deleteMember(Member member) {
        return jiaoGaiXiangMuSchoolMapper.deleteMember(member);
    }

    @Override
    public List<FundBudget> getFundBudgetList(String xmCode) {
        return jiaoGaiXiangMuSchoolMapper.getFundBudgetList(xmCode);
    }

    @Override
    public boolean insertFundBudget(FundBudget fundBudget) {
        return jiaoGaiXiangMuSchoolMapper.insertFundBudget(fundBudget);
    }

    @Override
    public boolean deleteFundBudget(FundBudget fundBudget) {
        return jiaoGaiXiangMuSchoolMapper.deleteFundBudget(fundBudget);
    }

}
