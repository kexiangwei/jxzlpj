package com.mycode.jiaoxuepingjia.thpj.controller;

import com.mycode.jiaoxuepingjia.thpj.domian.Thpj;
import com.mycode.jiaoxuepingjia.thpj.domian.ThpjQuery;
import com.mycode.jiaoxuepingjia.thpj.service.ThpjService;
import com.mycode.util.JsonResult;
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

    @ResponseBody
    @RequestMapping("/getPageList.do")
    public JsonResult<Object> getPageList(ThpjQuery thpjQuery){
        Map<String, Object> resultMap = thpjService.getPageList(thpjQuery);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/getThpjTargetList.do")
    public JsonResult<Object> getThpjTargetList(){
        List<Map<String, Object>> thpjTargetList = thpjService.getThpjTargetList();
        return JsonResult.success(thpjTargetList);
    }

    @ResponseBody
    @RequestMapping("/getTeacherInfo.do")
    public JsonResult<Object> getTeacherInfo(@RequestParam("menuName") String menuName, @RequestParam("userId") String userId){
        List<Map<String, Object>> teacherInfo = thpjService.getTeacherInfo(menuName,userId);
        return JsonResult.success(teacherInfo);
    }

    @ResponseBody
    @RequestMapping("/insert.do")
    public JsonResult<Object> insert(Thpj thpj){
        boolean bool = thpjService.insert(thpj);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }

    @ResponseBody
    @RequestMapping("/update.do")
    public JsonResult<Object> update(Thpj thpj){
        boolean bool = thpjService.update(thpj);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }

    @ResponseBody
    @RequestMapping("/delete.do")
    public JsonResult<Object> delete(@RequestParam("code") String code){
        boolean bool = thpjService.delete(code);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }
}
