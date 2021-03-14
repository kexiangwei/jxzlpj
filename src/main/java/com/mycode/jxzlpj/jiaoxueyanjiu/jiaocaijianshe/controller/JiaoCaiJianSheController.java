package com.mycode.jxzlpj.jiaoxueyanjiu.jiaocaijianshe.controller;

import com.mycode.jxzlpj.jiaoxueyanjiu.jiaocaijianshe.domian.JiaoCaiJianShe;
import com.mycode.jxzlpj.jiaoxueyanjiu.jiaocaijianshe.service.JiaoCaiJianSheService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 教学研究-教材建设
 */
@CrossOrigin
@Controller
@RequestMapping("/jxyj_jcjs")
public class JiaoCaiJianSheController {

    @Autowired
    private JiaoCaiJianSheService jiaoCaiJianSheService;

    @ResponseBody
    @RequestMapping("/getPageList.do")
    public JsonResult<Object> getPageList(JiaoCaiJianShe jiaoCaiJianShe){
        Map<String, Object> resultMap = jiaoCaiJianSheService.getPageList(jiaoCaiJianShe);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/insert.do")
    public JsonResult<Object> insert(JiaoCaiJianShe jiaoCaiJianShe){
        boolean bool = jiaoCaiJianSheService.insert(jiaoCaiJianShe);
        if(!bool){
            return JsonResult.error("新增失败");
        }
        return JsonResult.success("新增成功",null);
    }

    @ResponseBody
    @RequestMapping("/update.do")
    public JsonResult<Object> update(JiaoCaiJianShe jiaoCaiJianShe){
        boolean bool = jiaoCaiJianSheService.update(jiaoCaiJianShe);
        if(!bool){
            return JsonResult.error("修改失败");
        }
        return JsonResult.success("修改成功",null);
    }

    @ResponseBody
    @RequestMapping("/delete.do")
    public JsonResult<Object> delete(@RequestParam("code") String code){
        boolean bool = jiaoCaiJianSheService.delete(code);
        if(!bool){
            return JsonResult.error("删除失败");
        }
        return JsonResult.success("删除成功",null);
    }
}
