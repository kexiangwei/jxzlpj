package com.mycode.jxzlpj.jiaoxuepingjia.pjset.controller;

import com.mycode.jxzlpj.jiaoxuepingjia.pjset.domain.PjSetTarget;
import com.mycode.jxzlpj.jiaoxuepingjia.pjset.domain.PjSetTemplate;
import com.mycode.jxzlpj.jiaoxuepingjia.pjset.service.PjSetTemplateService;
import com.mycode.util.JsonResult;
import com.mycode.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 教学评价-评教设置
 */
@CrossOrigin
@Controller
public class PjSetTemplateController {

    @Autowired
    private PjSetTemplateService pjSetTemplateService;

    @ResponseBody
    @RequestMapping("/getPjSetTemplateList.do")
    public JsonResult<Object> getPjSetTemplateList(PjSetTemplate template){
        Map<String, Object> resultMap = pjSetTemplateService.getPjSetTemplateList(template);
        return JsonResult.success(resultMap);
    }

    /**
     * 获取当前可用模板信息
     * @param templateType 模板类型，可选值【学生评教，同行评教】
     * @return
     */
    @ResponseBody
    @RequestMapping("/getActiveTemplate.do")
    public JsonResult<Object> getExecTemplate(@RequestParam("templateType") String templateType
            , @RequestParam(value = "templateCode",required = false) String templateCode){ //学生评教-比较评教-点击未评按钮事件参数
        if(templateCode == null){
            templateCode = pjSetTemplateService.getActiveTemplateCodeByType(templateType); //查看当前是否有执行中的模板信息
            if(StringUtils.isEmpty(templateCode)){
                return JsonResult.error("暂无可用模板！");
            }
        }
        List<PjSetTarget> pjSetTargetList = pjSetTemplateService.getActiveTemplate(templateCode);
        return JsonResult.success(pjSetTargetList);
    }

    @ResponseBody
    @RequestMapping("/insertOrUpdateTemplate.do")
    public JsonResult<Object> insertOrUpdateTemplate(PjSetTemplate template
            , @RequestParam(value = "targetCodes[]",required = false) String[] targetCodes){
        Boolean bool = pjSetTemplateService.insertOrUpdateTemplate(template,targetCodes);
        if(!bool){
            JsonResult.error("保存失败");
        }
        return JsonResult.success("保存成功",null);
    }

    @ResponseBody
    @RequestMapping("/deleteTemplate.do")
    public JsonResult<Object> deleteTemplate(@RequestParam("templateCode") String templateCode){
        Boolean bool = pjSetTemplateService.deleteTemplate(templateCode);
        if(!bool){
            return JsonResult.error("删除失败");
        }
        return JsonResult.success("删除成功",null);
    }

}
