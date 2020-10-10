package com.mycode.jiaoxuejiangcheng.qtbkjxjl.controller;

import com.mycode.jiaoxuejiangcheng.qtbkjxjl.domian.Qtbkjxjl;
import com.mycode.jiaoxuejiangcheng.qtbkjxjl.service.QtbkjxjlService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 教学奖惩-其他本科教学奖励
 */
@CrossOrigin
@Controller
@RequestMapping("/jxjc_qtbkjxjl")
public class QtbkjxjlController {

    @Autowired
    private QtbkjxjlService qtbkjxjlService;

    @ResponseBody
    @RequestMapping("/getPageList.do")
    public JsonResult<Object> getPageList(Qtbkjxjl qtbkjxjl){
        Map<String, Object> resultMap = qtbkjxjlService.getPageList(qtbkjxjl);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/insert.do")
    public JsonResult<Object> insert(Qtbkjxjl qtbkjxjl){
        boolean bool = qtbkjxjlService.insert(qtbkjxjl);
        if(!bool){
            return JsonResult.error("新增失败");
        }
        return JsonResult.success("新增成功",null);
    }

    @ResponseBody
    @RequestMapping("/update.do")
    public JsonResult<Object> update(Qtbkjxjl qtbkjxjl){
        boolean bool = qtbkjxjlService.update(qtbkjxjl);
        if(!bool){
            return JsonResult.error("修改失败");
        }
        return JsonResult.success("修改成功",null);
    }

    @ResponseBody
    @RequestMapping("/delete.do")
    public JsonResult<Object> delete(@RequestParam("code") String code){
        boolean bool = qtbkjxjlService.delete(code);
        if(!bool){
            return JsonResult.error("删除失败");
        }
        return JsonResult.success("删除成功",null);
    }
}
