package com.mycode.jiaoxuepingjia.thpj.controller;

import com.mycode.jiaoxuepingjia.pjset.domain.PjSetTemplate;
import com.mycode.jiaoxuepingjia.thpj.service.ThpjService;
import com.mycode.util.JsonResult;
import com.mycode.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 教学评价-同行评教
 */
@CrossOrigin
@Controller
@RequestMapping("/jxpj_thpj")
public class ThpjSubController {

    @Autowired
    private ThpjService thpjService;

    /**
     *
     * @param pjCode 查询详情时使用的参数
     * @return
     */
    @ResponseBody
    @RequestMapping("/getThpjTargetList.do")
    public JsonResult<Object> getThpjTargetList(@RequestParam(value = "pjCode",required = false) String pjCode){
        String templateCode = null;
        if(StringUtils.isEmpty(pjCode)){
            templateCode = thpjService.isPjDate(); //是否评教时间（即查看当前是否有执行中的模板信息）
        } else {
            templateCode = thpjService.getThpjTemplateByPjCode(pjCode).getTemplateCode();
        }
        if(StringUtils.isEmpty(templateCode)){
            return JsonResult.error("暂无可用模板");
        }
        List<Map<String, Object>> thpjTargetList = thpjService.getThpjTargetList(templateCode);
        return JsonResult.success(thpjTargetList);
    }

    @ResponseBody
    @RequestMapping("/getTeacherBar.do")
    public JsonResult<Object> getTeacherBar(@RequestParam("menuName") String menuName, @RequestParam("userId") String userId){
        List<Map<String, Object>> teacherBar = thpjService.getTeacherBar(menuName, userId);
        return JsonResult.success(teacherBar);
    }

    @ResponseBody
    @RequestMapping("/getTeacherPie.do")
    public JsonResult<Object> getTeacherPie(@RequestParam("menuName") String menuName, @RequestParam("userId") String userId){
        List<Map<String, Object>> teacherPie = thpjService.getTeacherPie(menuName, userId);
        return JsonResult.success(teacherPie);
    }

    @ResponseBody
    @RequestMapping("/getTeacherTab.do")
    public JsonResult<Object> getTeacherTab(@RequestParam("menuName") String menuName){
        List<Map<String, Object>> teacherTab = thpjService.getTeacherTab(menuName);
        return JsonResult.success(teacherTab);
    }

    /**
     *
     * @param menuName
     * @param userId
     * @param status 审核状态【待审核、审核中、通过、未通过、退回】
     * @return
     */
    @ResponseBody
    @RequestMapping("/getTeacherTabData.do")
    public JsonResult<Object> getTeacherTabData(@RequestParam("menuName") String menuName, @RequestParam("userId") String userId
            , @RequestParam(value = "status",required = false) String status){
        List<Map<String, Object>> teacherTab = thpjService.getTeacherTabData(menuName, userId, status);
        return JsonResult.success(teacherTab);
    }

}
