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
import com.mycode.jiaoxueyanjiu.jiaogaixiangmu.domain.JiaoGaiXiangMuForCity;
import com.mycode.jiaoxueyanjiu.jiaogaixiangmu.domain.Member;
import com.mycode.jiaoxueyanjiu.jiaogaixiangmu.mapper.JiaoGaiXiangMuCityMapper;
import com.mycode.jiaoxueyanjiu.jiaogaixiangmu.mapper.JiaoGaiXiangMuMapper;
import com.mycode.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JiaoGaiXiangMuCityServiceImpl implements JiaoGaiXiangMuForCityService {

    @Autowired
    private JiaoGaiXiangMuMapper jiaoGaiXiangMuMapper;
    @Autowired
    private JiaoGaiXiangMuCityMapper jiaoGaiXiangMuCityMapper;
    @Autowired
    private ShenHeMapper shenHeMapper;

    @Override
    public Map<String, Object> getPageList(JiaoGaiXiangMuForCity jiaoGaiXiangMuForCity) {
        Map<String, Object> resultMap = new HashMap<>();
        Integer isJwcGly = 0;
        if(StringUtils.isNotEmpty(jiaoGaiXiangMuForCity.getShenHeUserId())){
            //是否教务处管理员
            isJwcGly = jiaoGaiXiangMuMapper.isJwcGly(jiaoGaiXiangMuForCity.getShenHeUserId());
            resultMap.put("isJwcGly", isJwcGly);
            //是否专家评审账号
            Integer isZjAccount = jiaoGaiXiangMuMapper.isZjAccount(jiaoGaiXiangMuForCity.getShenHeUserId());
            jiaoGaiXiangMuForCity.setIsZjshAccount(isZjAccount);
            resultMap.put("isZjshAccount", isZjAccount);
            //获取未审核数
            int notShenHeNum = jiaoGaiXiangMuCityMapper.getNotShenHeNum(jiaoGaiXiangMuForCity.getShenHeUserId(), isZjAccount);
            resultMap.put("unShenHeNum", notShenHeNum);
        }
        //获取分页列表
        Page<Object> pageInfo = PageHelper.startPage(jiaoGaiXiangMuForCity.getPageIndex(), jiaoGaiXiangMuForCity.getPageSize());
        List<JiaoGaiXiangMuForCity> pageList = jiaoGaiXiangMuCityMapper.getPageList(jiaoGaiXiangMuForCity);
        if(pageList !=null && pageList.size() >0){
            for (JiaoGaiXiangMuForCity jiaoGaiXiangMuForCity1 : pageList) {
                //
                if(StringUtils.isNotEmpty(jiaoGaiXiangMuForCity1.getShenheCode())){ //若数据未提交，则不执行此查询
                    jiaoGaiXiangMuForCity1.setZjshItemList(jiaoGaiXiangMuMapper.getZjshProcess(jiaoGaiXiangMuForCity1.getCode(),jiaoGaiXiangMuForCity1.getBatchNum()));//专家审核意见
                    if(isJwcGly == 1){
                        jiaoGaiXiangMuForCity1.setIsZjshAll(jiaoGaiXiangMuMapper.isZjshAll(jiaoGaiXiangMuForCity1.getCode(),jiaoGaiXiangMuForCity1.getBatchNum()));
                    }
                }
            }
        }
        resultMap.put("totalNum", pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public boolean insert(JiaoGaiXiangMuForCity jiaoGaiXiangMuForCity) {
        return jiaoGaiXiangMuCityMapper.insert(jiaoGaiXiangMuForCity);
    }

    @Override
    public boolean update(JiaoGaiXiangMuForCity jiaoGaiXiangMuForCity) {
        return jiaoGaiXiangMuCityMapper.update(jiaoGaiXiangMuForCity);
    }

    @Override
    public boolean delete(String code) {
        return jiaoGaiXiangMuCityMapper.delete(code);
    }

    @Override
    public boolean toSubimt(String activeShenheCode, List<JiaoGaiXiangMuForCity> objList) {
        for (JiaoGaiXiangMuForCity obj : objList) {
            obj.setShenheCode(activeShenheCode);
            obj.setBatchNum(StringUtils.isEmpty(obj.getBatchNum())?1:obj.getBatchNum()+1); //提交批次，每提交一次加1
        }
        return shenHeMapper.batchSubimt(objList);
    }

    @Override
    public boolean toShenhe(ShenHeItem item, List<JiaoGaiXiangMuForCity> objList) {
        boolean bool = false;
        for (JiaoGaiXiangMuForCity obj : objList) {
            item.setRelationCode(obj.getCode());
            item.setBatchNum(obj.getBatchNum());
            if(item.getIsZjshAccount() == 1){
                bool = jiaoGaiXiangMuMapper.toZjShenhe(item); //专家审核
            }else{
                ShenHeNode node = shenHeMapper.getShenheNode("v_JXYJ_JGXM_CITY_SHENHE",item.getRelationCode(), item.getUserId()); //获取符合当前用户的审核节点信息
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

}
