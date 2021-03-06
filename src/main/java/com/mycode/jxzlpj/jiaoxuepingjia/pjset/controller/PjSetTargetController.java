package com.mycode.jxzlpj.jiaoxuepingjia.pjset.controller;

import com.mycode.jxzlpj.jiaoxuepingjia.pjset.domain.PjSetTarget;
import com.mycode.jxzlpj.jiaoxuepingjia.pjset.service.PjSetTargetService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * 教学评价-评教设置
 */
@CrossOrigin
@Controller
public class PjSetTargetController {

    @Autowired
    private PjSetTargetService pjSetTargetService;

    @ResponseBody
    @RequestMapping("/getPjSetTargetList.do")
    public JsonResult<Object> getPjSetTargetList(PjSetTarget target){
        List<PjSetTarget> mapList = pjSetTargetService.getPjSetTargetList(target);
        return JsonResult.success(mapList);
    }

    @ResponseBody
    @GetMapping("/getPjSetTargetCodes.do")
    public JsonResult<Object> getPjSetTargetCodes(@RequestParam("templateCode") String templateCode){
        Set<String> stringSet = pjSetTargetService.getPjSetTargetCodes(templateCode);
        return JsonResult.success(stringSet);
    }

    @ResponseBody
    @RequestMapping("/insertOrUpdateTarget.do")
    public JsonResult<Object> insertOrUpdateTarget(PjSetTarget target){
        Boolean bool = pjSetTargetService.insertOrUpdateTarget(target);
        if(!bool){
            JsonResult.error("保存失败！");
        }
        return JsonResult.success("保存成功！",null);
    }

    @ResponseBody
    @RequestMapping("/deleteTarget.do")
    public JsonResult<Object> deleteTarget(@RequestParam("targetCode") String targetCode){
        Boolean bool = pjSetTargetService.deleteTarget(targetCode);
        if(!bool){
            return JsonResult.error("删除失败！");
        }
        return JsonResult.success("删除成功！",null);
    }
}
