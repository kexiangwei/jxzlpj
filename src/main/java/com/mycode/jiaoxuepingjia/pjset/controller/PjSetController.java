package com.mycode.jiaoxuepingjia.pjset.controller;

import com.mycode.jiaoxuepingjia.pjset.domain.PjSetTarget;
import com.mycode.jiaoxuepingjia.pjset.domain.PjSet;
import com.mycode.jiaoxuepingjia.pjset.domain.PjSetTemplate;
import com.mycode.jiaoxuepingjia.pjset.service.PjSetService;
import com.mycode.util.JsonResult;
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
public class PjSetController {

    @Autowired
    private PjSetService pjSetService;

    @ResponseBody
    @RequestMapping("/getPjSetList.do")
    public JsonResult<Object> getPjSetList(PjSet pjSet){
        Map<String, Object> resultMap = pjSetService.getPjSetList(pjSet);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/getPjSetTemplateList.do")
    public JsonResult<Object> getPjSetTemplateList(PjSetTemplate pjSetTemplate){
        Map<String, Object> resultMap = pjSetService.getPjSetTemplateList(pjSetTemplate);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/getPjSetTargetList.do")
    public JsonResult<Object> getPjSetTargetList(PjSetTarget pjSetTarget){
        List<PjSetTarget> mapList = pjSetService.getPjSetTargetList(pjSetTarget);
        return JsonResult.success(mapList);
    }

    /**
     * 获取当前可执行模板信息
     * @param templateType 模板类型，可选值【学生评教，同行评教】
     * @return
     */
    @ResponseBody
    @RequestMapping("/getCurrentTemplate.do")
    public JsonResult<Object> getCurrentTemplate(@RequestParam("templateType") String templateType){
        Map<String, Object> resultMap = pjSetService.getCurrentTemplate(templateType);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/insertOrUpdateTemplate.do")
    public JsonResult<Object> insertOrUpdateTemplate(PjSetTemplate template
            , @RequestParam("targetCodes[]") String[] targetCodes){
        Boolean bool = pjSetService.insertOrUpdateTemplate(template,targetCodes);
        if(!bool){
            JsonResult.error("保存失败");
        }
        return JsonResult.success("保存成功",null);
    }

    @ResponseBody
    @RequestMapping("/deleteTemplate.do")
    public JsonResult<Object> deleteTemplate(@RequestParam("templateCode") String templateCode){
        Boolean bool = pjSetService.deleteTemplate(templateCode);
        if(!bool){
            return JsonResult.error("删除失败");
        }
        return JsonResult.success("删除成功",null);
    }

    @ResponseBody
    @RequestMapping("/insertOrUpdateTarget.do")
    public JsonResult<Object> insertOrUpdateTarget(PjSetTarget target){
        Boolean bool = pjSetService.insertOrUpdateTarget(target);
        if(!bool){
            JsonResult.error("保存失败");
        }
        return JsonResult.success("保存成功",null);
    }

    @ResponseBody
    @RequestMapping("/deleteTarget.do")
    public JsonResult<Object> deleteTarget(@RequestParam("targetCode") String targetCode){
        Boolean bool = pjSetService.deleteTarget(targetCode);
        if(!bool){
            return JsonResult.error("删除失败");
        }
        return JsonResult.success("删除成功",null);
    }
}
