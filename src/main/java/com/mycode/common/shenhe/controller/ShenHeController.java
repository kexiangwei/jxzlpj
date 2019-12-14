package com.mycode.common.shenhe.controller;

import com.mycode.common.shenhe.domain.ShenHe;
import com.mycode.system.menu.domain.Menu;
import com.mycode.system.role.domain.Role;
import com.mycode.common.shenhe.domain.ShenHeSet;
import com.mycode.common.shenhe.domain.ShenHeNode;
import com.mycode.common.shenhe.service.ShenHeService;
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
public class ShenHeController {

    @Autowired
    private ShenHeService shenHeService;

    /**
     * 根据relationCode 获取审核流程
     * @param relationCode
     * @return
     */
    @ResponseBody
    @RequestMapping("/getShenheProcess.do")
    public JsonResult<Object> getShenheProcess(@RequestParam("relationCode") String relationCode){
        List<ShenHe> statusList = shenHeService.getShenheProcess(relationCode);
        return JsonResult.success(statusList);
    }

    @ResponseBody
    @RequestMapping("/getShenHeList.do")
    public JsonResult<Object> getShenHeList(ShenHeSet shenHe){
        Map<String, Object> resultMap = shenHeService.getShenHeList(shenHe);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/getMenuParentList.do")
    public JsonResult<Object> getMenuParentList(@RequestParam(value = "menuId",required = false) Long menuId){
        List<Menu> menuList = shenHeService.getMenuParentList(menuId);
        return JsonResult.success(menuList);
    }

    @ResponseBody
    @RequestMapping("/getMenuListForShenHe.do")
    public JsonResult<Object> getMenuListForShenHe(){
        List<Menu> menuList = shenHeService.getMenuListForShenHe();
        return JsonResult.success(menuList);
    }
    @ResponseBody
    @RequestMapping("/addShenhe.do")
    public JsonResult<Object> addShenhe(ShenHeSet shenHe){
        boolean bool = shenHeService.addShenhe(shenHe);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }

    @ResponseBody
    @RequestMapping("/updateShenheByCode.do")
    public JsonResult<Object> updateShenheByCode(ShenHeSet shenHe){
        boolean bool = shenHeService.updateShenheByCode(shenHe);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }

    @ResponseBody
    @RequestMapping("/batchDeleteForShenHe.do")
    public JsonResult<Object> batchDelete(String[] codeArr){
        boolean bool = shenHeService.batchDelete(codeArr);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }
}
