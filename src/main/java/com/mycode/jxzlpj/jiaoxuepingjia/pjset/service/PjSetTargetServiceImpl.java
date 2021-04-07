package com.mycode.jxzlpj.jiaoxuepingjia.pjset.service;

import com.mycode.jxzlpj.jiaoxuepingjia.pjset.domain.PjSetTarget;
import com.mycode.jxzlpj.jiaoxuepingjia.pjset.mapper.PjSetTargetMapper;
import com.mycode.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 教学评价-评教设置
 */
@Service
public class PjSetTargetServiceImpl implements PjSetTargetService {

    @Autowired
    private PjSetTargetMapper pjSetTargetMapper;

    @Override
    public List<PjSetTarget> getPjSetTargetList(PjSetTarget target) {
        return pjSetTargetMapper.getPjSetTargetList(target);
    }

    @Override
    public Boolean insertOrUpdateTarget(PjSetTarget target) {
        Boolean bool = false;
        if(target == null){
            return bool;
        }
        if(StringUtils.isEmpty(target.getTargetCode())){
            target.setTargetCode(StringUtils.uuid());
            bool = pjSetTargetMapper.insertTarget(target);
        }else{
            bool = pjSetTargetMapper.updateTarget(target);
        }
        return bool;
    }

    @Override
    public Boolean deleteTarget(String targetCode) {
        return pjSetTargetMapper.deleteTarget(targetCode);
    }

}
