package com.mycode.jiaoxueyanjiu.jiaoxuetuandui.controller;

import com.alibaba.fastjson.JSON;
import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.common.shenhe.service.ShenHeService;
import com.mycode.jiaoxueyanjiu.jiaoxuetuandui.domain.JiaoXueTuanDui;
import com.mycode.jiaoxueyanjiu.jiaoxuetuandui.domain.PingShen;
import com.mycode.jiaoxueyanjiu.jiaoxuetuandui.domain.PingShenTemplate;
import com.mycode.jiaoxueyanjiu.jiaoxuetuandui.service.JiaoXueTuanDuiService;
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
 * 教学研究-教学团队
 * @auther kexiangwei
 * @date 2019/11/13
 */
@CrossOrigin
@Controller
@RequestMapping("/jiaoXueTuanDui")
public class JiaoXueTuanDuiController {

    @Autowired
    private JiaoXueTuanDuiService jiaoXueTuanDuiService;
    @Autowired
    private ShenHeService shenHeService;

    @ResponseBody
    @RequestMapping("/getPageList.do")
    public JsonResult<Object> getPageList(JiaoXueTuanDui jiaoXueTuanDui){
        Map<String, Object> resultMap = jiaoXueTuanDuiService.getPageList(jiaoXueTuanDui);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/insert.do")
    public JsonResult<Object> insert(JiaoXueTuanDui jiaoXueTuanDui){
        boolean bool = jiaoXueTuanDuiService.insert(jiaoXueTuanDui);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }

    @ResponseBody
    @RequestMapping("/update.do")
    public JsonResult<Object> update(JiaoXueTuanDui jiaoXueTuanDui){
        boolean bool = jiaoXueTuanDuiService.update(jiaoXueTuanDui);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }

    @ResponseBody
    @RequestMapping("/delete.do")
    public JsonResult<Object> delete(@RequestParam("code") String code){
        boolean bool = jiaoXueTuanDuiService.delete(code);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }

    /**
     * 提交，支持批量提交
     * @param menuId 用于获取当前处于激活状态的审核流程编号
     * @param jsonStr
     * @return
     */
    @ResponseBody
    @RequestMapping("/toSubimt.do")
    public JsonResult<Object> toSubimt(@RequestParam("menuId") Integer menuId,@RequestParam("jsonStr") String jsonStr){
        String activeShenheCode = shenHeService.getActiveShenheCode(menuId);
        if(StringUtils.isEmpty(activeShenheCode)){
            return JsonResult.error("未设置审核流程");
        }
        List<JiaoXueTuanDui> jiaoXueTuanDuiList = JSON.parseArray(jsonStr, JiaoXueTuanDui.class);
        boolean bool = jiaoXueTuanDuiService.toSubimt(activeShenheCode,jiaoXueTuanDuiList);
        if(!bool){
            return JsonResult.error("提交失败");
        }
        return JsonResult.success();
    }

    /**
     *  审核，支持批量审核
     * @param item
     * @return
     */
    @ResponseBody
    @RequestMapping("/toShenhe.do")
    public JsonResult<Object> toShenhe(ShenHeItem item,@RequestParam("jsonStr") String jsonStr){
        List<JiaoXueTuanDui> jiaoXueTuanDuiList = JSON.parseArray(jsonStr, JiaoXueTuanDui.class);
        boolean bool = jiaoXueTuanDuiService.toShenhe(item,jiaoXueTuanDuiList);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }

    @ResponseBody
    @RequestMapping("/getPingShenTemplate.do")
    public JsonResult<Object> getPingShenTemplate(){
        List<PingShenTemplate> pingShenSets = jiaoXueTuanDuiService.getPingShenTemplate();
        return JsonResult.success(pingShenSets);
    }

    @ResponseBody
    @RequestMapping("/getPingShenInfo.do")
    public JsonResult<Object> getPingShen(@RequestParam("relationCode") String relationCode,@RequestParam("batchNum") Integer batchNum
            ,@RequestParam("pingshenType") String pingshenType,@RequestParam(value = "userId",required = false) String userId){
        List<PingShen> pingShenInfo = jiaoXueTuanDuiService.getPingShenInfo(relationCode, batchNum, pingshenType, userId);
        return JsonResult.success(pingShenInfo);
    }

    @ResponseBody
    @RequestMapping("/insertPingShenInfo.do")
    public JsonResult<Object> insertPingShenInfo(PingShen pingShen){
        boolean isSuccessful = jiaoXueTuanDuiService.insertPingShenInfo(pingShen);
        if(!isSuccessful){
            JsonResult.error();
        }
        return JsonResult.success();
    }

    @ResponseBody
    @RequestMapping("/getMemberList.do")
    public JsonResult<Object> getMemberList(@RequestParam("relationCode") String relationCode){
        List<Map<String,Object>> memberList = jiaoXueTuanDuiService.getMemberList(relationCode);
        return JsonResult.success(memberList);
    }
    @ResponseBody
    @RequestMapping("/insertMember.do")
    public JsonResult<Object> insertMember(@RequestParam("relationCode") String relationCode
            ,@RequestParam("userId") String userId
            ,@RequestParam("userName") String userName){
        boolean bool = jiaoXueTuanDuiService.insertMember(relationCode,userId,userName);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }
    @ResponseBody
    @RequestMapping("/deleteMember.do")
    public JsonResult<Object> deleteMember(@RequestParam("relationCode") String relationCode
            ,@RequestParam("userId") String userId){
        boolean bool = jiaoXueTuanDuiService.deleteMember(relationCode,userId);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }
}
