package com.mycode.jiaoxuepingjia.thpj.controller;

import com.mycode.jiaoxuepingjia.thpj.service.ThpjService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 教学评价-同行评教-比较评价
 */
@CrossOrigin
@Controller
@RequestMapping("/jxpj_thpj")
public class Thpj2Controller {

    @Autowired
    private ThpjService thpjService;

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
