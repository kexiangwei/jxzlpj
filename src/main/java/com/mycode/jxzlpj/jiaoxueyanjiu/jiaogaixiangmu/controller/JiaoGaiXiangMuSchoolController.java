package com.mycode.jxzlpj.jiaoxueyanjiu.jiaogaixiangmu.controller;

import com.mycode.jxzlpj.jiaoxueyanjiu.jiaogaixiangmu.domain.FundBudget;
import com.mycode.jxzlpj.jiaoxueyanjiu.jiaogaixiangmu.domain.JiaoGaiXiangMuSchool;
import com.mycode.jxzlpj.jiaoxueyanjiu.jiaogaixiangmu.domain.Member;
import com.mycode.jxzlpj.jiaoxueyanjiu.jiaogaixiangmu.service.JiaoGaiXiangMuSchoolService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
@RequestMapping("/jxyj_jgxm_school")
public class JiaoGaiXiangMuSchoolController {

    @Autowired
    private JiaoGaiXiangMuSchoolService jiaoGaiXiangMuSchoolService;

    @ResponseBody
    @RequestMapping("/getPageList.do")
    public JsonResult<Object> getPageList(JiaoGaiXiangMuSchool jiaoGaiXiangMuSchool){
        Map<String, Object> resultMap = jiaoGaiXiangMuSchoolService.getPageList(jiaoGaiXiangMuSchool);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/insert.do")
    public JsonResult<Object> insert(JiaoGaiXiangMuSchool jiaoGaiXiangMuSchool){
        boolean bool = jiaoGaiXiangMuSchoolService.insert(jiaoGaiXiangMuSchool);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }

    @ResponseBody
    @RequestMapping("/update.do")
    public JsonResult<Object> update(JiaoGaiXiangMuSchool jiaoGaiXiangMuSchool){
        boolean bool = jiaoGaiXiangMuSchoolService.update(jiaoGaiXiangMuSchool);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }

    @ResponseBody
    @RequestMapping("/delete.do")
    public JsonResult<Object> delete(@RequestParam("code") String code){
        boolean bool = jiaoGaiXiangMuSchoolService.delete(code);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }

    @ResponseBody
    @RequestMapping("/getMemberList.do")
    public JsonResult<Object> getMemberList(@RequestParam("xmCode") String xmCode){
        List<Member> memberList = jiaoGaiXiangMuSchoolService.getMemberList(xmCode);
        return JsonResult.success(memberList);
    }
    @ResponseBody
    @RequestMapping("/insertMember.do")
    public JsonResult<Object> insertMember(Member member){
        boolean bool = jiaoGaiXiangMuSchoolService.insertMember(member);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }
    @ResponseBody
    @RequestMapping("/deleteMember.do")
    public JsonResult<Object> deleteMember(Member member){
        boolean bool = jiaoGaiXiangMuSchoolService.deleteMember(member);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }

    @ResponseBody
    @RequestMapping("/getFundBudgetList.do")
    public JsonResult<Object> getFundBudgetList(@RequestParam("xmCode") String xmCode){
        List<FundBudget> fundBudgetList = jiaoGaiXiangMuSchoolService.getFundBudgetList(xmCode);
        return JsonResult.success(fundBudgetList);
    }
    @ResponseBody
    @RequestMapping("/insertFundBudget.do")
    public JsonResult<Object> insertFundBudget(FundBudget fundBudget){
        boolean bool = jiaoGaiXiangMuSchoolService.insertFundBudget(fundBudget);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }
    @ResponseBody
    @RequestMapping("/deleteFundBudget.do")
    public JsonResult<Object> deleteFundBudget(FundBudget fundBudget){
        boolean bool = jiaoGaiXiangMuSchoolService.deleteFundBudget(fundBudget);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }
}
