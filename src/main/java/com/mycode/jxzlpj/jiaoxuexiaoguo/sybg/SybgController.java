package com.mycode.jxzlpj.jiaoxuexiaoguo.sybg;

import com.mycode.jxzlpj.jiaoxuexiaoguo.sybg.domian.Sybg;
import com.mycode.jxzlpj.jiaoxuexiaoguo.sybg.service.SybgService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/jxxg_sybg")
public class SybgController {

    @Autowired
    private SybgService sybgService;

    @RequestMapping("/getPageList.do")
    public JsonResult<Object> getPageList(Sybg sybg){
        Map<String, Object> resultMap = sybgService.getPageList(sybg);
        return JsonResult.success(resultMap);
    }

    @RequestMapping("/insert.do")
    public JsonResult<Object> insert(Sybg sybg){
        boolean bool = sybgService.insert(sybg);
        if(!bool){
            return JsonResult.error("新增失败");
        }
        return JsonResult.success("新增成功",null);
    }

    @RequestMapping("/update.do")
    public JsonResult<Object> update(Sybg sybg){
        boolean bool = sybgService.update(sybg);
        if(!bool){
            return JsonResult.error("修改失败");
        }
        return JsonResult.success("修改成功",null);
    }

    @RequestMapping("/delete.do")
    public JsonResult<Object> delete(@RequestParam("code") String code){
        boolean bool = sybgService.delete(code);
        if(!bool){
            return JsonResult.error("删除失败");
        }
        return JsonResult.success("删除成功",null);
    }

}
