package com.mycode.shenheSet.controller;

import com.alibaba.fastjson.JSON;
import com.mycode.shenheSet.domain.ShenHeObj;
import com.mycode.shenheSet.domain.ShenHe;
import com.mycode.shenheSet.domain.ShenHeItem;
import com.mycode.shenheSet.service.ShenHeService;
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

    /**
     * 提交,支持批量提交
     * @param menuId 业务模块编号
     * @param jsonString 提交的表单数据
     * @return
     */
    @ResponseBody
    @RequestMapping("/toSubimt.do")
    public JsonResult<Object> toSubimt(@RequestParam("menuId") Integer menuId,@RequestParam("jsonString") String jsonString){
        String activeShenheCode = shenHeService.getActiveShenheCode(menuId); //根据业务模块编号menuId 获取当前处于激活状态的审核流程编号
        if(StringUtils.isEmpty(activeShenheCode)){
            return JsonResult.error("未设置审核流程！");
        }
        boolean bool = shenHeService.toSubimt(activeShenheCode, JSON.parseArray(jsonString, ShenHeObj.class));
        if(!bool){
            return JsonResult.error("提交失败！");
        }
        return JsonResult.success("提交成功！",null);
    }

    /**
     *  审核，支持批量审核
     * @param item
     * @return
     */
    @ResponseBody
    @RequestMapping("/toShenhe.do")
    public JsonResult<Object> toShenhe(ShenHeItem item, @RequestParam("jsonString") String jsonString){
        boolean bool = shenHeService.toShenhe(item,JSON.parseArray(jsonString, ShenHeObj.class));
        if(!bool){
            return JsonResult.error("审核失败！");
        }
        return JsonResult.success("审核成功！",null);
    }
}
