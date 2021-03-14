package com.mycode.jxzlpj.jiaoxueyanjiu.jiaogaixiangmu.controller;

import com.mycode.jxzlpj.jiaoxueyanjiu.jiaogaixiangmu.domain.JiaoGaiXiangMuProvince;
import com.mycode.jxzlpj.jiaoxueyanjiu.jiaogaixiangmu.service.JiaoGaiXiangMuProvinceService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/jxyj_jgxm_province")
public class JiaoGaiXiangMuProvinceController {

    @Autowired
    private JiaoGaiXiangMuProvinceService jiaoGaiXiangMuProvinceService;

    @RequestMapping("/getPageList.do")
    public JsonResult<Object> getPageList(JiaoGaiXiangMuProvince jiaoGaiXiangMuProvince){
        Map<String, Object> resultMap = jiaoGaiXiangMuProvinceService.getPageList(jiaoGaiXiangMuProvince);
        return JsonResult.success(resultMap);
    }

    @RequestMapping("/insert.do")
    public JsonResult<Object> insert(JiaoGaiXiangMuProvince jiaoGaiXiangMuProvince){
        boolean bool = jiaoGaiXiangMuProvinceService.insert(jiaoGaiXiangMuProvince);
        if(!bool){
            return JsonResult.error("新增失败");
        }
        return JsonResult.success("新增成功", null);
    }

    @RequestMapping("/update.do")
    public JsonResult<Object> update(JiaoGaiXiangMuProvince jiaoGaiXiangMuProvince){
        boolean bool = jiaoGaiXiangMuProvinceService.update(jiaoGaiXiangMuProvince);
        if(!bool){
            return JsonResult.error("修改失败");
        }
        return JsonResult.success("修改成功", null);
    }

    @RequestMapping("/delete.do")
    public JsonResult<Object> delete(@RequestParam("code") String code){
        boolean bool = jiaoGaiXiangMuProvinceService.delete(code);
        if(!bool){
            return JsonResult.error("删除失败");
        }
        return JsonResult.success("删除成功", null);
    }
}
