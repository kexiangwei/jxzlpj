package com.mycode.diaochawenjuan.controller;

import com.mycode.util.JsonResult;
import com.mycode.diaochawenjuan.domian.Question;
import com.mycode.diaochawenjuan.domian.WjSet;
import com.mycode.diaochawenjuan.service.WjSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 学生评教
 * @auther kexiangwei
 * @date 2019/10/8
 */
@CrossOrigin
@Controller
public class WjSetController {

    @Autowired
    private WjSetService wjSetService;

    @ResponseBody
    @RequestMapping("/getWjSetPageList.do")
    public JsonResult<Object> getWjSetPageList(WjSet wjSet){
        Map<String, Object> resultMap = wjSetService.getWjSetPageList(wjSet);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/getQuestionPageList.do")
    public JsonResult<Object> getList(Question question){
        Map<String, Object> resultMap = wjSetService.getQuestionPageList(question);
        return JsonResult.success(resultMap);
    }
    @ResponseBody
    @RequestMapping("/getOption.do")
    public JsonResult<Object> getOption(@RequestParam("qCode") String qCode){
        List<Map<String, Object>> resultList = wjSetService.getOption(qCode);
        return JsonResult.success(resultList);
    }
}
