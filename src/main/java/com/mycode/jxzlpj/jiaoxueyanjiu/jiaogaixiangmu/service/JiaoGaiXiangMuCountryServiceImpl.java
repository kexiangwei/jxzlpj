package com.mycode.jxzlpj.jiaoxueyanjiu.jiaogaixiangmu.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.common.shenheSet.mapper.ShenHeMapper;
import com.mycode.jxzlpj.jiaoxueyanjiu.jiaogaixiangmu.domain.JiaoGaiXiangMuCountry;
import com.mycode.jxzlpj.jiaoxueyanjiu.jiaogaixiangmu.mapper.JiaoGaiXiangMuCountryMapper;
import com.mycode.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JiaoGaiXiangMuCountryServiceImpl implements JiaoGaiXiangMuCountryService {

    @Autowired
    private JiaoGaiXiangMuCountryMapper jiaoGaiXiangMuCountryMapper;
    @Autowired
    private ShenHeMapper shenHeMapper;

    @Override
    public Map<String, Object> getPageList(JiaoGaiXiangMuCountry jiaoGaiXiangMuCountry) {
        Map<String, Object> resultMap = new HashMap<>();
        Integer jwcGly = 0;
        if(StringUtils.isNotEmpty(jiaoGaiXiangMuCountry.getShenHeUserId())){
            //判断是否为校外专家审核账号
            Integer isZjshAccount = shenHeMapper.isZjAccount(jiaoGaiXiangMuCountry.getShenHeUserId());
            jiaoGaiXiangMuCountry.setIsZjshAccount(isZjshAccount);
            resultMap.put("isZjshAccount", isZjshAccount);
            //判断是否为教务处管理员
            if(isZjshAccount == 0){
                jwcGly = shenHeMapper.isJwcGly(jiaoGaiXiangMuCountry.getShenHeUserId());
                resultMap.put("isJwcGly", jwcGly);
            }
            //获取未审核数
            resultMap.put("unShenHeNum", shenHeMapper.getNotShenHeNum("V_JXYJ_JGXM_COUNTRY_SHENHE"
                    , jiaoGaiXiangMuCountry.getShenHeUserId()
                    ,isZjshAccount
                    ,jwcGly));
        }
        //
        Page<Object> pageInfo = PageHelper.startPage(jiaoGaiXiangMuCountry.getPageIndex(), jiaoGaiXiangMuCountry.getPageSize());
        List<JiaoGaiXiangMuCountry> pageList = jiaoGaiXiangMuCountryMapper.getPageList(jiaoGaiXiangMuCountry);
        if(jwcGly == 1){
            if(pageList !=null && pageList.size() > 0){
                for (JiaoGaiXiangMuCountry jiaoGaiXiangMu : pageList) {
                    //若数据未提交，则不执行此查询
                    if(StringUtils.isNotEmpty(jiaoGaiXiangMu.getShenheCode())){
                        jiaoGaiXiangMu.setIsZjshAll(shenHeMapper.isZjshAll(jiaoGaiXiangMu.getCode(),jiaoGaiXiangMu.getBatchNum()));
                        jiaoGaiXiangMu.setZjshItemList(shenHeMapper.getZjshProcess(jiaoGaiXiangMu.getCode(),jiaoGaiXiangMu.getBatchNum())); //专家审核意见
                    }
                }
            }
        }
        resultMap.put("totalNum", pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public boolean insert(JiaoGaiXiangMuCountry jiaoGaiXiangMuCountry) {
        return jiaoGaiXiangMuCountryMapper.insert(jiaoGaiXiangMuCountry);
    }

    @Override
    public boolean update(JiaoGaiXiangMuCountry jiaoGaiXiangMuCountry) {
        return jiaoGaiXiangMuCountryMapper.update(jiaoGaiXiangMuCountry);
    }

    @Override
    public boolean delete(String code) {
        return jiaoGaiXiangMuCountryMapper.delete(code);
    }

}
