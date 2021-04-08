package com.mycode.jxzlpj.jiaoxuepingjia.thpj.controller;

import com.alibaba.fastjson.JSON;
import com.mycode.jxzlpj.jiaoxuepingjia.pjset.service.PjSetTemplateService;
import com.mycode.jxzlpj.jiaoxuepingjia.thpj.domian.Thpj;
import com.mycode.jxzlpj.jiaoxuepingjia.thpj.domian.ThpjQuery;
import com.mycode.jxzlpj.jiaoxuepingjia.thpj.service.ThpjService;
import com.mycode.util.JsonResult;
import com.mycode.util.StringUtils;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 教学评价-同行评教
 */
@CrossOrigin
@Controller
@RequestMapping("/jxpj_thpj")
public class ThpjController {

    @Autowired
    private ThpjService thpjService;
    @Autowired
    private PjSetTemplateService pjSetTemplateService;

    @ResponseBody
    @RequestMapping("/getPageList.do")
    public JsonResult<Object> getPageList(ThpjQuery thpjQuery){
        Map<String, Object> resultMap = thpjService.getPageList(thpjQuery);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/detail.do")
    public JsonResult<Object> detail(@RequestParam("code") String code){
        Thpj thpj = thpjService.detail(code);
        return JsonResult.success(thpj);
    }

    @ResponseBody
    @RequestMapping("/insert.do")
    public JsonResult<Object> insert(Thpj thpj, @RequestParam("jsonString") String jsonString){
        Map<String,Object> paramMap = JSON.parseObject(jsonString, Map.class);
        boolean bool = thpjService.insert(thpj, paramMap);
        if(!bool){
            return JsonResult.error("保存失败！");
        }
        return JsonResult.success("保存成功！",null);
    }

    @ResponseBody
    @RequestMapping("/update.do")
    public JsonResult<Object> update(Thpj thpj, @RequestParam("jsonString") String jsonString, HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Headers","Content-Type,Access-Control-Allow-Headers,Authorization,X-Requested-With");
        response.setHeader("Access-Control-Allow-Origin", "*");

        Map<String,Object> paramMap = JSON.parseObject(jsonString, Map.class);
        boolean bool = thpjService.update(thpj, paramMap);
        if(!bool){
            return JsonResult.error("修改失败！");
        }
        return JsonResult.success("修改成功！",null);
    }

    /*@ResponseBody
    @RequestMapping("/delete.do")
    public JsonResult<Object> delete(@RequestParam("code") String code){
        boolean bool = thpjService.delete(code);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }*/

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
            templateCode = pjSetTemplateService.getActiveTemplateCodeByType("同行评教");
        } else {
            templateCode = thpjService.getThpjTemplateCode(code); //城头变幻大王旗
        }
        if(StringUtils.isEmpty(templateCode)){
            return JsonResult.error("暂无可用模板！");
        }
        List<Map<String, Object>> thpjTargetList = thpjService.getThpjTargetList(templateCode);
        return JsonResult.success(thpjTargetList);
    }
}
