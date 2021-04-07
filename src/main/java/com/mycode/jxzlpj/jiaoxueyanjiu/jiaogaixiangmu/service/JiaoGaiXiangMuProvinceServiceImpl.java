package com.mycode.jxzlpj.jiaoxueyanjiu.jiaogaixiangmu.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.jxzlpj.jiaoxueyanjiu.jiaogaixiangmu.domain.JiaoGaiXiangMuProvince;
import com.mycode.jxzlpj.jiaoxueyanjiu.jiaogaixiangmu.mapper.JiaoGaiXiangMuProvinceMapper;
import com.mycode.shenheSet.mapper.ShenHeMapper;
import com.mycode.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JiaoGaiXiangMuProvinceServiceImpl implements JiaoGaiXiangMuProvinceService {

    @Autowired
    private JiaoGaiXiangMuProvinceMapper jiaoGaiXiangMuProvinceMapper;
    @Autowired
    private ShenHeMapper shenHeMapper;

    @Override
    public Map<String, Object> getPageList(JiaoGaiXiangMuProvince jiaoGaiXiangMuProvince) {
        Map<String, Object> resultMap = new HashMap<>();
        Integer jwcGly = 0;
        if(StringUtils.isNotEmpty(jiaoGaiXiangMuProvince.getShenHeUserId())){
            //判断是否为校外专家审核账号
            Integer isZjshAccount = shenHeMapper.isZjAccount(jiaoGaiXiangMuProvince.getShenHeUserId());
            jiaoGaiXiangMuProvince.setIsZjshAccount(isZjshAccount);
            resultMap.put("isZjshAccount", isZjshAccount);
            //判断是否为教务处管理员
            if(isZjshAccount == 0){
                jwcGly = shenHeMapper.isJwcGly(jiaoGaiXiangMuProvince.getShenHeUserId());
                resultMap.put("isJwcGly", jwcGly);
            }
            //获取未审核数
            resultMap.put("unShenHeNum", shenHeMapper.getNotShenHeNum("V_JXYJ_JGXM_PROVINCE_SHENHE"
                    , jiaoGaiXiangMuProvince.getShenHeUserId()
                    ,isZjshAccount
                    ,jwcGly));
        }
        //
        Page<Object> pageInfo = PageHelper.startPage(jiaoGaiXiangMuProvince.getPageIndex(), jiaoGaiXiangMuProvince.getPageSize());
        List<JiaoGaiXiangMuProvince> pageList = jiaoGaiXiangMuProvinceMapper.getPageList(jiaoGaiXiangMuProvince);
        if(pageList !=null && pageList.size() > 0){
            for (JiaoGaiXiangMuProvince jiaoGaiXiangMu : pageList) {
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
    public boolean insert(JiaoGaiXiangMuProvince jiaoGaiXiangMuProvince) {
        return jiaoGaiXiangMuProvinceMapper.insert(jiaoGaiXiangMuProvince);
    }

    @Override
    public boolean update(JiaoGaiXiangMuProvince jiaoGaiXiangMuProvince) {
        return jiaoGaiXiangMuProvinceMapper.update(jiaoGaiXiangMuProvince);
    }

    @Override
    public boolean delete(String code) {
        return jiaoGaiXiangMuProvinceMapper.delete(code);
    }

}
