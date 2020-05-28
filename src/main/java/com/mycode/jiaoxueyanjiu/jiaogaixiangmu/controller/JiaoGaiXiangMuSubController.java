package com.mycode.jiaoxueyanjiu.jiaogaixiangmu.controller;

import com.mycode.common.shenhe.service.ShenHeService;
import com.mycode.jiaoxueyanjiu.jiaogaixiangmu.domain.FundBudget;
import com.mycode.jiaoxueyanjiu.jiaogaixiangmu.domain.Member;
import com.mycode.jiaoxueyanjiu.jiaogaixiangmu.service.JiaoGaiXiangMuService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/jiaoGaiXiangMu")
public class JiaoGaiXiangMuSubController {

    @Autowired
    private JiaoGaiXiangMuService jiaoGaiXiangMuService;
    @Autowired
    private ShenHeService shenHeService;

    @ResponseBody
    @RequestMapping("/getMemberList.do")
    public JsonResult<Object> getMemberList(@RequestParam("xmCode") String xmCode){
        List<Member> memberList = jiaoGaiXiangMuService.getMemberList(xmCode);
        return JsonResult.success(memberList);
    }
    @ResponseBody
    @RequestMapping("/insertMember.do")
    public JsonResult<Object> insertMember(Member member){
        boolean bool = jiaoGaiXiangMuService.insertMember(member);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }
    @ResponseBody
    @RequestMapping("/deleteMember.do")
    public JsonResult<Object> deleteMember(Member member){
        boolean bool = jiaoGaiXiangMuService.deleteMember(member);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }

    @ResponseBody
    @RequestMapping("/getFundBudgetList.do")
    public JsonResult<Object> getFundBudgetList(@RequestParam("xmCode") String xmCode){
        List<FundBudget> fundBudgetList = jiaoGaiXiangMuService.getFundBudgetList(xmCode);
        return JsonResult.success(fundBudgetList);
    }
    @ResponseBody
    @RequestMapping("/insertFundBudget.do")
    public JsonResult<Object> insertFundBudget(FundBudget fundBudget){
        boolean bool = jiaoGaiXiangMuService.insertFundBudget(fundBudget);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }
    @ResponseBody
    @RequestMapping("/deleteFundBudget.do")
    public JsonResult<Object> deleteFundBudget(FundBudget fundBudget){
        boolean bool = jiaoGaiXiangMuService.deleteFundBudget(fundBudget);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }
}
