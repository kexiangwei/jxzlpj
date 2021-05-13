package com.mycode.jxzlpj.jiaoxuepingjia.thpj.controller;

import com.mycode.jxzlpj.jiaoxuepingjia.pjset.service.PjSetTemplateService;
import com.mycode.jxzlpj.jiaoxuepingjia.thpj.domian.Thpj;
import com.mycode.jxzlpj.jiaoxuepingjia.thpj.service.ThpjService;
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
@RequestMapping("/thpj")
public class ThpjController {

    @Autowired
    private ThpjService thpjService;
    @Autowired
    private PjSetTemplateService pjSetTemplateService;

    @ResponseBody
    @RequestMapping("/getPageList.do")
    public JsonResult<Object> getPageList(Thpj thpj){
        Map<String, Object> resultMap = thpjService.getPageList(thpj);
        return JsonResult.success(resultMap);
    }

    /**
     * 获取模板信息
     * @param pjCode 查询详情时使用的参数
     * @return
     */
    @ResponseBody
    @RequestMapping("/getThpjTargetList.do")
    public JsonResult<Object> getThpjTargetList(@RequestParam(value = "code",required = false) String pjCode){
        String templateCode = null;
        if(StringUtils.isEmpty(pjCode)){
            templateCode = pjSetTemplateService.getActiveTemplateCodeByType("同行评教");
        } else {
            templateCode = thpjService.getThpjTemplateCode(pjCode); //城头变幻大王旗
        }
        if(StringUtils.isEmpty(templateCode)){
            return JsonResult.error("暂无可用模板！");
        }
        List<Map<String, Object>> thpjTargetList = thpjService.getThpjTargetList(templateCode);
        return JsonResult.success(thpjTargetList);
    }

    /**
     * 查看优秀名额是否已满
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("/isTopFull.do")
    public JsonResult<Object> isTopFull(@RequestParam("userId") String userId){
        Integer isFull = thpjService.isTopFull(userId);
        return JsonResult.success(isFull);
    }

    @ResponseBody
    @RequestMapping("/submit.do")
    public JsonResult<Object> submit(@RequestParam("code") String code){
        boolean bool = thpjService.submit(code);
        if(!bool){
            return JsonResult.error("提交失败");
        }
        return JsonResult.success("提交成功",null);
    }
}
