package com.mycode.jiaoxueyanjiu.jiaogaixiangmu.controller;

import com.alibaba.fastjson.JSON;
import com.mycode.common.shenhe.domain.ShenHe;
import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.common.shenhe.service.ShenHeService;
import com.mycode.jiaoxueyanjiu.jiaogaixiangmu.domain.FundBudget;
import com.mycode.jiaoxueyanjiu.jiaogaixiangmu.domain.JiaoGaiXiangMu;
import com.mycode.jiaoxueyanjiu.jiaogaixiangmu.domain.Member;
import com.mycode.jiaoxueyanjiu.jiaogaixiangmu.domain.ZjshItem;
import com.mycode.jiaoxueyanjiu.jiaogaixiangmu.service.JiaoGaiXiangMuService;
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
 * 教学研究-教改项目
 * @auther kexiangwei
 * @date 2019/11/13
 */
@CrossOrigin
@Controller
@RequestMapping("/jiaoGaiXiangMu")
public class JiaoGaiXiangMuController {

    @Autowired
    private JiaoGaiXiangMuService jiaoGaiXiangMuService;
    @Autowired
    private ShenHeService shenHeService;

    @ResponseBody
    @RequestMapping("/getPageList.do")
    public JsonResult<Object> getPageList(JiaoGaiXiangMu jiaoGaiXiangMu){
        Map<String, Object> resultMap = jiaoGaiXiangMuService.getPageList(jiaoGaiXiangMu);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/insert.do")
    public JsonResult<Object> insert(JiaoGaiXiangMu jiaoGaiXiangMu){
        boolean bool = jiaoGaiXiangMuService.insert(jiaoGaiXiangMu);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }

    @ResponseBody
    @RequestMapping("/update.do")
    public JsonResult<Object> update(JiaoGaiXiangMu jiaoGaiXiangMu){
        boolean bool = jiaoGaiXiangMuService.update(jiaoGaiXiangMu);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }

    @ResponseBody
    @RequestMapping("/delete.do")
    public JsonResult<Object> delete(@RequestParam("code") String code){
        boolean bool = jiaoGaiXiangMuService.delete(code);
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
        List<JiaoGaiXiangMu> jiaoGaiXiangMuList = JSON.parseArray(jsonStr, JiaoGaiXiangMu.class);
        boolean bool = jiaoGaiXiangMuService.toSubimt(activeShenheCode,jiaoGaiXiangMuList);
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
        List<JiaoGaiXiangMu> jiaoGaiXiangMuList = JSON.parseArray(jsonStr, JiaoGaiXiangMu.class);
        boolean bool = jiaoGaiXiangMuService.toShenhe(item,jiaoGaiXiangMuList);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }

    /**
     * 根据xmCode 获取校外专家审核意见
     * @param xmCode
     * @return
     */
    @ResponseBody
    @RequestMapping("/getZjshProcess.do")
    public JsonResult<Object> getZjshProcess(@RequestParam("xmCode") String xmCode,@RequestParam("batchNum") Integer batchNum){
        List<ZjshItem> zjshItemList = jiaoGaiXiangMuService.getZjshProcess(xmCode,batchNum);
        return JsonResult.success(zjshItemList);
    }

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
