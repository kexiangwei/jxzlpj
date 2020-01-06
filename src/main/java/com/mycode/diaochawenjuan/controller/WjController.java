package com.mycode.diaochawenjuan.controller;

import com.mycode.diaochawenjuan.domian.WjQuestion;
import com.mycode.diaochawenjuan.domian.WjSet;
import com.mycode.diaochawenjuan.service.WjService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 调查问卷
 * @auther kexiangwei
 * @date 2019/10/8
 */
@CrossOrigin
@Controller
public class WjController {

    @Autowired
    private WjService wjService;

    @ResponseBody
    @RequestMapping("/getWjSetPageList.do")
    public JsonResult<Object> getWjSetPageList(WjSet wjSet){
        Map<String, Object> resultMap = wjService.getWjSetPageList(wjSet);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/getWjQuestionPageList.do")
    public JsonResult<Object> getWjQuestionPageList(WjQuestion wjQuestion){
        Map<String, Object> resultMap = wjService.getWjQuestionPageList(wjQuestion);
        return JsonResult.success(resultMap);
    }

}
