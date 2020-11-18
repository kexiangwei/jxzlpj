package com.mycode.jiaoxuepingjia.thpj.controller;

import com.mycode.jiaoxuepingjia.pjset.domain.PjSetTemplate;
import com.mycode.jiaoxuepingjia.pjset.service.PjSetService;
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
    @Autowired
    private PjSetService pjSetService;

    /**
     *
     * @param code 查询详情时使用的参数
     * @return
     */
    @ResponseBody
    @RequestMapping("/getThpjTargetList.do")
    public JsonResult<Object> getThpjTargetList(@RequestParam(value = "code",required = false) String code){
        String templateCode = null;
        if(StringUtils.isEmpty(code)){
            templateCode = pjSetService.getActiveTemplateCode("同行评教");
        } else {
            templateCode = thpjService.getThpjTemplateCode(code); //城头变幻大王旗
        }
        if(StringUtils.isEmpty(templateCode)){
            return JsonResult.error("暂无可用模板");
        }
        List<Map<String, Object>> thpjTargetList = thpjService.getThpjTargetList(templateCode);
        return JsonResult.success(thpjTargetList);
    }

    /*@ResponseBody
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
    }*/

    @ResponseBody
    @RequestMapping("/getTableCols.do")
    public JsonResult<Object> getTableCols(@RequestParam("tableName") String tableName){
        List<Map<String, Object>> tableCols = thpjService.getTableCols(tableName);
        return JsonResult.success(tableCols);
    }

    @ResponseBody
    @RequestMapping("/getTableDatas.do")
    public JsonResult<Object> getTeacherTabData(@RequestParam("viewName") String viewName, @RequestParam("userId") String userId){
        List<Map<String, Object>> tableDatas = thpjService.getTableDatas(viewName, userId);
        return JsonResult.success(tableDatas);
    }


}
