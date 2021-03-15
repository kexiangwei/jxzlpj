package com.mycode.jxzlpj.jiaoxuejiangcheng.zyjscgj.controller;

import com.mycode.jxzlpj.jiaoxuejiangcheng.zyjscgj.domian.Zyjscgj;
import com.mycode.jxzlpj.jiaoxuejiangcheng.zyjscgj.service.ZyjscgjService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 教学奖惩-专业建设成果奖
 */
@CrossOrigin
@Controller
@RequestMapping("/jxjc_zyjscgj")
public class ZyjscgjController {

    @Autowired
    private ZyjscgjService zyjscgjService;

    @ResponseBody
    @RequestMapping("/getPageList.do")
    public JsonResult<Object> getPageList(Zyjscgj zyjscgj){
        Map<String, Object> resultMap = zyjscgjService.getPageList(zyjscgj);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/insert.do")
    public JsonResult<Object> insert(Zyjscgj zyjscgj){
        boolean bool = zyjscgjService.insert(zyjscgj);
        if(!bool){
            return JsonResult.error("新增失败");
        }
        return JsonResult.success("新增成功",null);
    }

    @ResponseBody
    @RequestMapping("/update.do")
    public JsonResult<Object> update(Zyjscgj zyjscgj){
        boolean bool = zyjscgjService.update(zyjscgj);
        if(!bool){
            return JsonResult.error("修改失败");
        }
        return JsonResult.success("修改成功",null);
    }

    @ResponseBody
    @RequestMapping("/delete.do")
    public JsonResult<Object> delete(@RequestParam("code") String code){
        boolean bool = zyjscgjService.delete(code);
        if(!bool){
            return JsonResult.error("删除失败");
        }
        return JsonResult.success("删除成功",null);
    }
}
