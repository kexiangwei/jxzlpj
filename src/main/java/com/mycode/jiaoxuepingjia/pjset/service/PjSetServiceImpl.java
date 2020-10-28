package com.mycode.jiaoxuepingjia.pjset.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.jiaoxuepingjia.pjset.domain.PjSetTarget;
import com.mycode.jiaoxuepingjia.pjset.domain.PjSetTemplate;
import com.mycode.jiaoxuepingjia.pjset.mapper.PjSetMapper;
import com.mycode.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 教学评价-评教设置
 */
@Service
public class PjSetServiceImpl implements PjSetService {

    @Autowired
    private PjSetMapper pjSetMapper;

    @Override
    public String isPjDate(String templateType) {
        return pjSetMapper.isPjDate(templateType);
    }

    @Override
    public List<PjSetTarget> getExecTemplate(String templateCode) {
        return pjSetMapper.getPjSetTargetListByTemplateCode(templateCode); //根据模板编号获取模板信息
    }

    @Override
    public Map<String, Object> getPjSetTemplateList(PjSetTemplate pjSetTemplate) {
        Map<String, Object> resultMap = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(pjSetTemplate.getPageIndex(), pjSetTemplate.getPageSize());
        List<PjSetTemplate> pageList = pjSetMapper.getPjSetTemplateList(pjSetTemplate);
        resultMap.put("totalNum",pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public Boolean insertOrUpdateTemplate(PjSetTemplate template, String[] targetCodes) {
        Boolean bool = false;
        if(template != null && StringUtils.isEmpty(template.getTemplateCode())){
            template.setTemplateCode(org.springframework.util.StringUtils.replace(UUID.randomUUID().toString(), "-", ""));
            bool = pjSetMapper.insertTemplate(template);
        }else{
            bool = pjSetMapper.updateTemplate(template);
        }
        if(bool && targetCodes !=null){ //
            List<PjSetTarget> targetList = pjSetMapper.getPjSetTargetListByTemplateCode(template.getTemplateCode());
            if(targetList !=null && targetList.size() >0){
                bool = pjSetMapper.deleteTemplateTargetByTemplateCode(template.getTemplateCode());
            }
            bool = pjSetMapper.insertTemplateTarget(template.getTemplateCode(),targetCodes);
        }
        return bool;
    }

    @Override
    public Boolean deleteTemplate(String templateCode) {
        Integer execNum = pjSetMapper.deleteTemplate(templateCode); //mybatis一次对多条数据进行操作成功后返回值为 -1
        return execNum < 0;
    }

    @Override
    public List<PjSetTarget> getPjSetTargetList(PjSetTarget pjSetTarget) {
        return pjSetMapper.getPjSetTargetList(pjSetTarget);
    }

    @Override
    public Boolean insertOrUpdateTarget(PjSetTarget target) {
        Boolean bool = false;
        if(target != null && StringUtils.isEmpty(target.getTargetCode())){
            target.setTargetCode(org.springframework.util.StringUtils.replace(UUID.randomUUID().toString(), "-", ""));
            bool = pjSetMapper.insertTarget(target);
        }else{
            bool = pjSetMapper.updateTarget(target);
        }
        return bool;
    }

    @Override
    public Boolean deleteTarget(String targetCode) {
        return pjSetMapper.deleteTarget(targetCode);
    }

}
