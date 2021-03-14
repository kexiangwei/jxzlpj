package com.mycode.jxzlpj.jiaoxueyanjiu.jiaogailunwen.controller;

import com.mycode.jxzlpj.jiaoxueyanjiu.jiaogailunwen.domian.JiaoGaiLunWen;
import com.mycode.jxzlpj.jiaoxueyanjiu.jiaogailunwen.service.JiaoGaiLunWenService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 教学研究-教改论文
 */
@CrossOrigin
@Controller
@RequestMapping("/jxyj_jglw")
public class JiaoGaiLunWenController {

    @Autowired
    private JiaoGaiLunWenService jiaoGaiLunWenService;

    @ResponseBody
    @RequestMapping("/getPageList.do")
    public JsonResult<Object> getPageList(JiaoGaiLunWen jiaoGaiLunWen){
        Map<String, Object> resultMap = jiaoGaiLunWenService.getPageList(jiaoGaiLunWen);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/insert.do")
    public JsonResult<Object> insert(JiaoGaiLunWen jiaoGaiLunWen){
        boolean bool = jiaoGaiLunWenService.insert(jiaoGaiLunWen);
        if(!bool){
            return JsonResult.error("新增失败");
        }
        return JsonResult.success("新增成功",null);
    }

    @ResponseBody
    @RequestMapping("/update.do")
    public JsonResult<Object> update(JiaoGaiLunWen jiaoGaiLunWen){
        boolean bool = jiaoGaiLunWenService.update(jiaoGaiLunWen);
        if(!bool){
            return JsonResult.error("修改失败");
        }
        return JsonResult.success("修改成功",null);
    }

    @ResponseBody
    @RequestMapping("/delete.do")
    public JsonResult<Object> delete(@RequestParam("code") String code){
        boolean bool = jiaoGaiLunWenService.delete(code);
        if(!bool){
            return JsonResult.error("删除失败");
        }
        return JsonResult.success("删除成功",null);
    }
}
