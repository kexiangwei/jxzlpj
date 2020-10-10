package com.mycode.jiaoxuejiangcheng.jyjxcgj.controller;

import com.mycode.jiaoxuejiangcheng.jyjxcgj.domian.Jyjxcgj;
import com.mycode.jiaoxuejiangcheng.jyjxcgj.service.JyjxcgjService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 教学奖惩-教育教学成果奖
 */
@CrossOrigin
@Controller
@RequestMapping("/jxjc_jyjxcgj")
public class JyjxcgjController {

    @Autowired
    private JyjxcgjService jyjxcgjService;

    @ResponseBody
    @RequestMapping("/getPageList.do")
    public JsonResult<Object> getPageList(Jyjxcgj jyjxcgj){
        Map<String, Object> resultMap = jyjxcgjService.getPageList(jyjxcgj);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/insert.do")
    public JsonResult<Object> insert(Jyjxcgj jyjxcgj){
        boolean bool = jyjxcgjService.insert(jyjxcgj);
        if(!bool){
            return JsonResult.error("新增失败");
        }
        return JsonResult.success("新增成功",null);
    }

    @ResponseBody
    @RequestMapping("/update.do")
    public JsonResult<Object> update(Jyjxcgj jyjxcgj){
        boolean bool = jyjxcgjService.update(jyjxcgj);
        if(!bool){
            return JsonResult.error("修改失败");
        }
        return JsonResult.success("修改成功",null);
    }

    @ResponseBody
    @RequestMapping("/delete.do")
    public JsonResult<Object> delete(@RequestParam("code") String code){
        boolean bool = jyjxcgjService.delete(code);
        if(!bool){
            return JsonResult.error("删除失败");
        }
        return JsonResult.success("删除成功",null);
    }
}
