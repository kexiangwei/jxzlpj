package com.mycode.jxzlpj.jiaoxuepingjia.pjset.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mycode.jxzlpj.jiaoxuepingjia.pjset.domain.PjSetTarget;
import com.mycode.jxzlpj.jiaoxuepingjia.pjset.domain.PjSetTemplate;
import com.mycode.jxzlpj.jiaoxuepingjia.pjset.mapper.PjSetTemplateMapper;
import com.mycode.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教学评价-评教设置
 */
@Service
public class PjSetTemplateServiceImpl implements PjSetTemplateService {

    @Autowired
    private PjSetTemplateMapper pjSetTemplateMapper;

    @Override
    public Map<String, Object> getPjSetTemplateList(PjSetTemplate template) {
        Map<String, Object> resultMap = new HashMap<>();
        Page<Object> pageInfo = PageHelper.startPage(template.getPageIndex(), template.getPageSize());
        List<PjSetTemplate> pageList = pjSetTemplateMapper.getPjSetTemplateList(template);
        resultMap.put("totalNum",pageInfo.getTotal());
        resultMap.put("pageList", pageList);
        return resultMap;
    }

    @Override
    public String getActiveTemplateCodeByType(String templateType) {
        return pjSetTemplateMapper.getActiveTemplateCodeByType(templateType);
    }

    @Override
    public List<PjSetTarget> getActiveTemplate(String templateCode) {
        return pjSetTemplateMapper.getPjSetTargetListByTemplateCode(templateCode); //根据模板编号获取模板信息
    }

    @Override
    public Boolean insertOrUpdateTemplate(PjSetTemplate template, String[] targetCodes) {
        Boolean bool = false;
        if(template == null){
            return bool;
        }
        if(StringUtils.isEmpty(template.getTemplateCode())){
            template.setTemplateCode(StringUtils.uuid());
            bool = pjSetTemplateMapper.insertTemplate(template);
        }else{
            bool = pjSetTemplateMapper.updateTemplate(template);
            if(bool && targetCodes !=null){ //
                List<PjSetTarget> targetList = pjSetTemplateMapper.getPjSetTargetListByTemplateCode(template.getTemplateCode());
                if(targetList !=null && targetList.size() >0){
                    bool = pjSetTemplateMapper.deletePjSetTargetByTemplateCode(template.getTemplateCode());
                }
            }
        }
        if(bool && targetCodes !=null){
            bool = pjSetTemplateMapper.insertPjSetTemplateTargets(template.getTemplateCode(),targetCodes);
        }
        return bool;
    }

    @Override
    public Boolean deleteTemplate(String templateCode) {
        Integer execNum = pjSetTemplateMapper.deleteTemplate(templateCode);
        return execNum == -1; //mybatis一次对多条数据进行操作成功后返回值为 -1
    }

}
