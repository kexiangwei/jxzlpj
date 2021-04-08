package com.mycode.jxzlpj.zhtj.controller;

import com.mycode.jxzlpj.zhtj.service.XyService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/zhtj")
public class XyController {

    @Autowired
    private XyService xyService;

    @GetMapping("/getList.do")
    public JsonResult<Object> getList(){
        List<Map<String,Object>> mapList = xyService.getList();
        return JsonResult.success(mapList);
    }
}
