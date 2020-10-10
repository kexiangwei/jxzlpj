package com.mycode.jiaoxuejiangcheng.kcjscgj.controller;

import com.mycode.jiaoxuejiangcheng.kcjscgj.domian.Kcjscgj;
import com.mycode.jiaoxuejiangcheng.kcjscgj.service.KcjscgjService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 教学奖惩-课程建设成果奖
 */
@CrossOrigin
@Controller
@RequestMapping("/jxjc_kcjscgj")
public class KcjscgjController {

    @Autowired
    private KcjscgjService kcjscgjService;

    @ResponseBody
    @RequestMapping("/getPageList.do")
    public JsonResult<Object> getPageList(Kcjscgj kcjscgj){
        Map<String, Object> resultMap = kcjscgjService.getPageList(kcjscgj);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/insert.do")
    public JsonResult<Object> insert(Kcjscgj kcjscgj){
        boolean bool = kcjscgjService.insert(kcjscgj);
        if(!bool){
            return JsonResult.error("新增失败");
        }
        return JsonResult.success("新增成功",null);
    }

    @ResponseBody
    @RequestMapping("/update.do")
    public JsonResult<Object> update(Kcjscgj kcjscgj){
        boolean bool = kcjscgjService.update(kcjscgj);
        if(!bool){
            return JsonResult.error("修改失败");
        }
        return JsonResult.success("修改成功",null);
    }

    @ResponseBody
    @RequestMapping("/delete.do")
    public JsonResult<Object> delete(@RequestParam("code") String code){
        boolean bool = kcjscgjService.delete(code);
        if(!bool){
            return JsonResult.error("删除失败");
        }
        return JsonResult.success("删除成功",null);
    }
}
