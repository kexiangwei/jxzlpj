package com.mycode.shenhe.controller;

import com.mycode.shenhe.domain.ShenHeNode;
import com.mycode.shenhe.service.ShenHeService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@CrossOrigin
@Controller
public class ShenHeNodeController {

    @Autowired
    private ShenHeService shenHeService;

    @ResponseBody
    @RequestMapping("/getShenHeNodeList.do")
    public JsonResult<Object> getShenHeNodeList(@RequestParam("shenheCode") String shenheCode){
        List<ShenHeNode> nodeList = shenHeService.getShenHeNodeList(shenheCode);
        return JsonResult.success(nodeList);
    }

    @ResponseBody
    @RequestMapping("/addShenHeNode.do")
    public JsonResult<Object> addShenHeNode(ShenHeNode shenHeNode){
        String code = shenHeService.addShenHeNode(shenHeNode);
        if(StringUtils.isEmpty(code)){
            return JsonResult.error();
        }
        return JsonResult.success(code);
    }

    @ResponseBody
    @RequestMapping("/updateShenHeNodeByCode.do")
    public JsonResult<Object> updateShenHeNodeByCode(ShenHeNode shenHeNode){
        boolean bool = shenHeService.updateShenHeNodeByCode(shenHeNode);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }

    @ResponseBody
    @RequestMapping("/deleteShenHeNodeByCode.do")
    public JsonResult<Object> deleteShenHeNodeByCode(@RequestParam("nodeCode") String nodeCode){
        boolean bool = shenHeService.deleteShenHeNodeByCode(nodeCode);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }

}
