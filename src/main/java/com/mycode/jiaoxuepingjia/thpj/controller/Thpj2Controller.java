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
    @RequestMapping("/isFull.do")
    public JsonResult<Object> isFull(@RequestParam("userId") String userId){
        Integer isFull = thpjService.isFull(userId);
        return JsonResult.success(isFull);
    }
}
