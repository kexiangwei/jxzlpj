package com.mycode.jxzlpj.jiaoxuejiangcheng.jsgrcgj.controller;

import com.mycode.jxzlpj.jiaoxuejiangcheng.jsgrcgj.domian.Jsgrcgj;
import com.mycode.jxzlpj.jiaoxuejiangcheng.jsgrcgj.service.JsgrcgjService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 教学奖惩-教师个人成果奖
 */
@CrossOrigin
@Controller
@RequestMapping("/jxjc_jsgrcgj")
public class JsgrcgjController {

    @Autowired
    private JsgrcgjService jsgrcgjService;

    @ResponseBody
    @RequestMapping("/getPageList.do")
    public JsonResult<Object> getPageList(Jsgrcgj jsgrcgj){
        Map<String, Object> resultMap = jsgrcgjService.getPageList(jsgrcgj);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/insert.do")
    public JsonResult<Object> insert(Jsgrcgj jsgrcgj){
        boolean bool = jsgrcgjService.insert(jsgrcgj);
        if(!bool){
            return JsonResult.error("新增失败");
        }
        return JsonResult.success("新增成功",null);
    }

    @ResponseBody
    @RequestMapping("/update.do")
    public JsonResult<Object> update(Jsgrcgj jsgrcgj){
        boolean bool = jsgrcgjService.update(jsgrcgj);
        if(!bool){
            return JsonResult.error("修改失败");
        }
        return JsonResult.success("修改成功",null);
    }

    @ResponseBody
    @RequestMapping("/delete.do")
    public JsonResult<Object> delete(@RequestParam("code") String code){
        boolean bool = jsgrcgjService.delete(code);
        if(!bool){
            return JsonResult.error("删除失败");
        }
        return JsonResult.success("删除成功",null);
    }
}
