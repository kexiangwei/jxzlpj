package com.mycode.jiaoxuexiaoguo.kczlfxbg.controller;

import com.mycode.jiaoxuexiaoguo.kczlfxbg.doman.Kczlfxbg;
import com.mycode.jiaoxuexiaoguo.kczlfxbg.service.KczlfxbgService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/insert.do")
    public JsonResult<Object> insert(Kczlfxbg kczlfxbg){
        boolean bool = kczlfxbgService.insert(kczlfxbg);
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

    @RequestMapping("/delete.do")
    public JsonResult<Object> delete(@RequestParam("code") String code){
        boolean bool = kczlfxbgService.delete(code);
        if(!bool){
            return JsonResult.error("删除失败");
        }
        return JsonResult.success("删除成功",null);
    }

}
