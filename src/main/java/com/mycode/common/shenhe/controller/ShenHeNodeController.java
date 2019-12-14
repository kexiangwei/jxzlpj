package com.mycode.common.shenhe.controller;

import com.mycode.common.shenhe.domain.ShenHe;
import com.mycode.common.shenhe.domain.ShenHeNode;
import com.mycode.common.shenhe.domain.ShenHeSet;
import com.mycode.common.shenhe.service.ShenHeService;
import com.mycode.system.menu.domain.Menu;
import com.mycode.system.role.domain.Role;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @auther kexiangwei
 * @date 2019/6/24
 */
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
    public JsonResult<Object> addShenHeNode(ShenHeNode node){
        String code = shenHeService.addShenHeNode(node);
        if(StringUtils.isEmpty(code)){
            return JsonResult.error();
        }
        return JsonResult.success(code);
    }
    @ResponseBody
    @RequestMapping("/updateShenHeNodeByCode.do")
    public JsonResult<Object> updateShenHeNodeByCode(ShenHeNode node){
        boolean bool = shenHeService.updateShenHeNodeByCode(node);
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
