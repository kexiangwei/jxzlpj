package com.mycode.jxzlpj.jiaoxuexiaoguo.kczlfxbg.controller;

import com.mycode.jxzlpj.jiaoxuexiaoguo.kczlfxbg.doman.Kczlfxbg;
import com.mycode.jxzlpj.jiaoxuexiaoguo.kczlfxbg.service.KczlfxbgService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/jxxg_kczlfxbg")
public class KczlfxbgController {

    @Autowired
    private KczlfxbgService kczlfxbgService;

    @RequestMapping("/getPageList.do")
    public JsonResult<Object> getPageList(Kczlfxbg kczlfxbg){
        Map<String, Object> resultMap = kczlfxbgService.getPageList(kczlfxbg);
        return JsonResult.success(resultMap);
    }

    @GetMapping("/getKczlfxbg.do")
    public JsonResult<Object> getKczlfxbg(@RequestParam("code") String code){
        Map<String,Object> resultMap = kczlfxbgService.getKczlfxbg(code);
        return JsonResult.success(resultMap);
    }

    /*@RequestMapping("/insert.do")
    public JsonResult<Object> insert(Kczlfxbg kczlfxbg){
        boolean bool = kczlfxbgService.insert(kczlfxbg);
        if(!bool){
            return JsonResult.error("新增失败");
        }
        return JsonResult.success("新增成功",null);
    }*/

    /**
     * 新增
     * @param params 获取所有的请求参数
     * @return
     */
    @RequestMapping("/insert.do")
    public JsonResult<Object> insert(@RequestParam Map<String,Object> params){
        boolean bool = kczlfxbgService.insert2(params);
        if(!bool){
            return JsonResult.error("新增失败");
        }
        return JsonResult.success("新增成功",null);
    }

    @RequestMapping("/update.do")
    public JsonResult<Object> update(Kczlfxbg kczlfxbg){
        boolean bool = kczlfxbgService.update(kczlfxbg);
        if(!bool){
            return JsonResult.error("修改失败");
        }
        return JsonResult.success("修改成功",null);
    }

    @RequestMapping("/submit.do")
    public JsonResult<Object> submit(@RequestParam("code") String code){
        boolean bool = kczlfxbgService.submit(code);
        if(!bool){
            return JsonResult.error("提交失败");
        }
        return JsonResult.success("提交成功",null);
    }

}
