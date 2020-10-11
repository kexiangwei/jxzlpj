package com.mycode.jiaoxueyanjiu.jiaoxuetuandui.controller;

import com.mycode.jiaoxueyanjiu.jiaoxuetuandui.domain.CountryTeam;
import com.mycode.jiaoxueyanjiu.jiaoxuetuandui.service.CountryTeamService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 教学研究-教学团队-国家级团队
 */
@CrossOrigin
@Controller
@RequestMapping("/jxyj_jxtd_country")
public class CountryTeamController {

    @Autowired
    private CountryTeamService countryTeamService;

    @ResponseBody
    @RequestMapping("/getPageList.do")
    public JsonResult<Object> getPageList(CountryTeam countryTeam){
        Map<String, Object> resultMap = countryTeamService.getPageList(countryTeam);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/insert.do")
    public JsonResult<Object> insert(CountryTeam countryTeam){
        boolean bool = countryTeamService.insert(countryTeam);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }

    @ResponseBody
    @RequestMapping("/update.do")
    public JsonResult<Object> update(CountryTeam countryTeam){
        boolean bool = countryTeamService.update(countryTeam);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }

    @ResponseBody
    @RequestMapping("/delete.do")
    public JsonResult<Object> delete(@RequestParam("code") String code){
        boolean bool = countryTeamService.delete(code);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }
}
