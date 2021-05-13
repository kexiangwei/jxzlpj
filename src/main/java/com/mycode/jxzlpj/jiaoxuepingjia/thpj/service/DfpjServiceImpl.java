package com.mycode.jxzlpj.jiaoxuepingjia.thpj.service;

import com.alibaba.fastjson.JSON;
import com.mycode.jxzlpj.jiaoxuepingjia.pjset.domain.PjSetTarget;
import com.mycode.jxzlpj.jiaoxuepingjia.pjset.mapper.PjSetTemplateMapper;
import com.mycode.jxzlpj.jiaoxuepingjia.thpj.domian.Thpj;
import com.mycode.jxzlpj.jiaoxuepingjia.thpj.mapper.DfpjMapper;
import com.mycode.jxzlpj.jiaoxuepingjia.thpj.mapper.ThpjMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class DfpjServiceImpl implements DfpjService {

    @Autowired
    private DfpjMapper dfpjMapper;
    @Autowired
    private ThpjMapper thpjMapper;
    @Autowired
    private PjSetTemplateMapper pjSetTemplateMapper;

    @Override
    public Thpj detail(String pjCode) {
        Thpj thpj = dfpjMapper.getThpjInfo(pjCode);
        if(thpj != null){
            List<Map<String,Object>> thpjItemList = dfpjMapper.getThpjItemListByRelationCode(pjCode);
            thpj.setThpjItemList(thpjItemList);
        }
        return thpj;
    }

    @Override
    @Transactional
    public boolean insert(Thpj thpj, String jsonString) {
        boolean bool = dfpjMapper.insert(thpj);
        if(bool){
            List<PjSetTarget> pjSetTargetList = pjSetTemplateMapper.getPjSetTargetListByTemplateCode(thpj.getTemplateCode());
            Map<String,Object> paramMap = JSON.parseObject(jsonString, Map.class);
            bool = dfpjMapper.insertTarget(thpj.getCode(), pjSetTargetList, paramMap);
        }
        return bool;
    }

   @Override
   @Transactional
    public boolean update(Thpj thpj, String jsonString) {
       boolean bool = dfpjMapper.deleteTargetByRelationCode(thpj.getCode()); //删除以前的记录
       List<PjSetTarget> pjSetTargetList = pjSetTemplateMapper.getPjSetTargetListByTemplateCode(thpj.getTemplateCode());
       Map<String,Object> paramMap = JSON.parseObject(jsonString, Map.class);
       bool = dfpjMapper.insertTarget(thpj.getCode(), pjSetTargetList, paramMap); //然后再重新录入
       if(bool){
           bool = thpjMapper.resetSubmit(thpj.getCode());
       }
       return bool;
    }

    /*@Override
    public boolean delete(String pjCode) {
        return thpjMapper.delete(pjCode);
    }*/

}
